package database.dao.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import database.BaseDao;
import database.dao.*;
import database.entity.*;

/**
 * SaverAccountDaoImpl
 */
public class SaverAccountDaoImpl extends AccountDaoImpl implements SaverAccountDao {

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public SaverAccount findAccount(int id) throws IOException {
        List<String[]> result = BaseDao.search(id + ".txt", "0", 0);
        if (result == null)
            return null;

        String[] resultStr = BaseDao.search(id + ".txt", "0", 0).get(0);

        SaverAccount account = new SaverAccount();
        account.setId(id);
        account.setPin(Integer.parseInt(resultStr[1]));
        account.setBalance(Double.valueOf(resultStr[2]));
        account.setOverdraftLimit(Double.valueOf(resultStr[3]));
        account.setSuspended(Boolean.valueOf(resultStr[4]));
        account.setActive(Boolean.valueOf(resultStr[5]));
        account.setAvailableAmount(Double.valueOf(resultStr[6]));

        return account;
    }

    // 1 for success; 0 for existed account; -1 for unavailable credit;
    public int addAccount(Customer customer) throws IOException {
        int result = super.addAccount(customer);
        if (result != 1)
            return result;

        BaseDao.addLine("accounts.txt", (BaseDao.fileCount() - 1) + "\t|\tsaver");
        return 1;
    }

    // 1 for success; 0 for no account; -2 for wrong pin;
    // -3 for suspended; -4 for no order   ;
    @Override
    public int addWithdral(int id, int pin) throws IOException {
        SaverAccount account = findAccount(id);
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

        double num = account.getAvailableAmount();
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
    // -3 for suspended; -4 for already order   ;
    public int addOrder(int id, int pin, double num) throws IOException {
        SaverAccount account = findAccount(id);
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
            System.out.println(dao.addWithdral(5, 390149));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}