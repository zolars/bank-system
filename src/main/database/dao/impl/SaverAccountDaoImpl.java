package database.dao.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import database.BaseDao;
import database.dao.*;
import database.entity.*;

/**
 * SaverAccountDaoImpl
 */
public class SaverAccountDaoImpl extends AccountDaoImpl implements SaverAccountDao {

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // search for the Credit Status
    private boolean confirmCreditStatus(String name) {
        // some process by name
        return true;
    }

    // 1 for success; 0 for existed account; -1 for unavailable credit;
    public int addAccount(Customer customer) throws IOException {
        SaverAccount account = new SaverAccount(customer);

        if (!confirmCreditStatus(account.getCustomer().getName()))
            return -1;
        else {
            account.setId(BaseDao.fileCount());
            if (!BaseDao.addFile(account.toFileName(), account.toString()))
                return 0;
            else {
                BaseDao.addLine("accounts.txt", (BaseDao.fileCount() - 1) + "\t|\tsaver");
                return account.getId();
            }
        }
    }

    // 1 for success; 0 for no account; -2 for wrong pin;
    // -3 for suspended; -4 for no order; -5 for ordered notice;
    // -6 for ordered not due
    @Override
    public int addWithdral(int id, int pin, double num) throws IOException {
        Account account = findAccount(id);

        if (pin < 0) {
            return addOrder(id, account.getPin(), num);
        }
        if (account == null)
            return 0;

        if (pin != account.getPin())
            return -2;

        if (account.isSuspended()) {
            BaseDao.addLine(account.toFileName(),
                    BaseDao.dataCount(account.toFileName(), "", 0) + "\t|\t" + "withdral" + "\t|\t"
                            + sf.format(Calendar.getInstance().getTime()) + "\t|\t"
                            + String.format("%-4.2f", account.getAvailableAmount()) + "\t|\t"
                            + String.format("%-4.2f", account.getBalance()) + "\t|\tfrozen");
            return -3;
        }

        if (account.getAvailableAmount() == 0) {
            return -4;
        }

        try {
            List<String[]> resultStr = BaseDao.search(account.toFileName(), "order   ", 1);
            String[] result = resultStr.get(resultStr.size() - 1);
            if (new Date().getTime() - sf.parse(result[2]).getTime() <= 24 * 60 * 60 * 1000)
                return -6;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (num >= 0) {
            return -5;
        }

        num = account.getAvailableAmount();
        account.setBalance(account.getBalance() - account.getAvailableAmount());
        account.setAvailableAmount(0);

        if (!BaseDao.replace(account.toFileName(), account.toString()))
            return 0;

        if (BaseDao.addLine(account.toFileName(),
                BaseDao.dataCount(account.toFileName(), "", 0) + "\t|\t" + "withdral" + "\t|\t"
                        + sf.format(Calendar.getInstance().getTime()) + "\t|\t" + String.format("%-4.2f", num) + "\t|\t"
                        + String.format("%-4.2f", account.getBalance()) + "\t|\tsuccess")) {
            return 1;
        } else
            return 0;
    }

    // 1 for success; 0 for no account; -1 for overrun; -2 for wrong pin;
    // -3 for suspended; -4 for already order ;
    public int addOrder(int id, int pin, double num) throws IOException {
        Account account = findAccount(id);
        if (account == null)
            return 0;

        if (pin != account.getPin())
            return -2;

        if (account.isSuspended()) {
            BaseDao.addLine(account.toFileName(),
                    BaseDao.dataCount(account.toFileName(), "", 0) + "\t|\t" + "order   " + "\t|\t"
                            + sf.format(Calendar.getInstance().getTime()) + "\t|\t" + String.format("%-4.2f", num)
                            + "\t|\t" + String.format("%-4.2f", account.getBalance()) + "\t|\tfrozen");
            return -3;
        }

        if (account.getAvailableAmount() != 0) {
            return -4;
        }

        // Judge overrun
        if (account.getBalance() - num + account.getOverdraftLimit() < 0) {
            BaseDao.addLine(account.toFileName(),
                    BaseDao.dataCount(account.toFileName(), "", 0) + "\t|\t" + "order   " + "\t|\t"
                            + sf.format(Calendar.getInstance().getTime()) + "\t|\t" + String.format("%-4.2f", num)
                            + "\t|\t" + String.format("%-4.2f", account.getBalance()) + "\t|\toverrun");
            return -1;
        }

        account.setAvailableAmount(num);

        if (!BaseDao.replace(account.toFileName(), account.toString()))
            return 0;

        if (BaseDao.addLine(account.toFileName(),
                BaseDao.dataCount(account.toFileName(), "", 0) + "\t|\t" + "order   " + "\t|\t"
                        + sf.format(Calendar.getInstance().getTime()) + "\t|\t"
                        + String.format("%-4.2f", account.getAvailableAmount()) + "\t|\t"
                        + String.format("%-4.2f", account.getBalance()) + "\t|\tsuccess"))
            return 1;
        else
            return 0;

    }

    public static void main(String[] args) {
        SaverAccountDao dao = new SaverAccountDaoImpl();
        try {
            System.out.println(dao.addWithdral(5, 390149, 123));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}