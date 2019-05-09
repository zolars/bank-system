package database.dao.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import database.BaseDao;
import database.dao.*;
import database.entity.*;

/**
 * AccountDao
 */
public class AccountDaoImpl implements AccountDao {

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // search for the Credit Status
    private boolean confirmCreditStatus(String name) {
        // some process by name
        return true;
    }

    public Account findAccount(int id) throws IOException {
        if (BaseDao.search(id + ".txt", "0", 0).size() == 0)
            return null;

        String[] resultStr = BaseDao.search(id + ".txt", "0", 0).get(0);

        Account account = new Account();
        account.setId(id);
        account.setPin(Integer.parseInt(resultStr[1]));
        account.setBalance(Double.valueOf(resultStr[2]));
        account.setOverdraftLimit(Double.valueOf(resultStr[3]));
        account.setSuspended(Boolean.valueOf(resultStr[4]));
        account.setActive(Boolean.valueOf(resultStr[5]));
        account.setNoticeNeeded(Boolean.valueOf(resultStr[6]));

        return account;
    }

    // 1 for success; 0 for existed; -1 for unavailable credit;
    public int addAccount(Account account) throws IOException {
        if (!confirmCreditStatus(account.getCustomer().getName()))
            return -1;
        else {
            account.setId(BaseDao.fileCount());
            if (!BaseDao.addFile(account.toFileName(), account.toString()))
                return 0;
            else
                return 1;
        }
    }

    public boolean deleteAccount(Account account) throws IOException {
        account.setActive(false);
        return BaseDao.replace(account.toFileName(), account.toString());
    }

    // true for success; false for failure;
    public boolean addDeposit(Account account, double num, String depositType) throws IOException {
        if (depositType.equals("cash") || depositType.split(":")[0].equals("chequeclear")) {
            account.setBalance(account.getBalance() + num);
            BaseDao.replace(account.toFileName(), account.toString());
        }

        return BaseDao.addLine(account.toFileName(),
                BaseDao.dataCount(account.toFileName(), "", 0) + "\t|\t" + "deposit" + "\t|\t"
                        + sf.format(Calendar.getInstance().getTime()) + "\t|\t" + num + "\t|\t" + account.getBalance()
                        + "\t|\t" + depositType);
    }

    // public boolean addWithdral(Account account, double num) throws IOException {
    // account.getBalance();
    // return true;
    // }

    public boolean clearFundsByAccount(Account account) throws IOException {
        List<String[]> resultStr = BaseDao.search(account.toFileName(), "", 4);

        AccountDao dao = new AccountDaoImpl();
        for (int i = resultStr.size() - 1; i >= 0; i--) {
            if (resultStr.get(i)[5].split(":")[0].equals("chequeclear"))
                break;
            else if (resultStr.get(i)[5].equals("cheque"))
                if (!dao.addDeposit(account, Double.valueOf(resultStr.get(i)[3]), "chequeclear:" + resultStr.get(i)[0]))
                    return false;
        }
        return true;
    }

    public boolean clearFundsAll() throws IOException {
        AccountDao dao = new AccountDaoImpl();
        List<String[]> resultStr = BaseDao.search("accounts.txt", "", 0);
        for (String[] strs : resultStr) {
            if (!dao.clearFundsByAccount(findAccount(Integer.parseInt(strs[0]))))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

    }
}