package database.dao.impl;

import java.io.IOException;

import database.BaseDao;
import database.dao.*;
import database.entity.*;

/**
 * CurrentAccountDaoImpl
 */
public class CurrentAccountDaoImpl extends AccountDaoImpl implements CurrentAccountDao {

    public CurrentAccount findAccount(int id) throws IOException {
        Account account = super.findAccount(id);
        if (account != null)
            return new CurrentAccount(account);
        else
            return null;
    }

    // 1 for success; 0 for existed account; -1 for unavailable credit;
    public int addAccount(Customer customer, double overdraftLimit) throws IOException {
        int result = super.addAccount(customer);
        if (result != 1)
            return result;

        Account account = findAccount(BaseDao.fileCount() - 1);
        account.setOverdraftLimit(overdraftLimit);
        BaseDao.replace(account.toFileName(), account.toString());

        BaseDao.addLine("accounts.txt", (BaseDao.fileCount() - 1) + "\t|\tcurrent");
        return 1;
    }

    public static void main(String[] args) {
        CurrentAccountDao dao = new CurrentAccountDaoImpl();

        Customer customer = new Customer();
        try {
            // dao.adjustSuspendedAccount(1, 455937);
            dao.addAccount(customer, 700);
            dao.clearFundsByAccount(1);
            // dao.addWithdral(1, 45593, 20);
            // System.out.println(dao.addWithdral(1, 45593, 20));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}