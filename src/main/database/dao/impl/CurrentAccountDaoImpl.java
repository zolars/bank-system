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

    // search for the Credit Status
    private boolean confirmCreditStatus(String name) {
        // some process by name
        return true;
    }

    // 1 for success; 0 for existed account; -1 for unavailable credit;
    public int addAccount(Customer customer) throws IOException {
        CurrentAccount account = new CurrentAccount(customer);

        if (!confirmCreditStatus(account.getCustomer().getName()))
            return -1;
        else {
            account.setId(BaseDao.fileCount());
            if (!BaseDao.addFile(account.toFileName(), account.toString()))
                return 0;
            else {
                BaseDao.addLine("accounts.txt", (BaseDao.fileCount() - 1) + "\t|\tcurrent");
                return account.getId();
            }
        }
    }

    public static void main(String[] args) {
        CurrentAccountDao dao = new CurrentAccountDaoImpl();

        Customer customer = new Customer();
        try {
            // dao.adjustSuspendedAccount(1, 455937);
            dao.addAccount(customer);
            dao.clearFundsByAccount(1);
            // dao.addWithdral(1, 45593, 20);
            // System.out.println(dao.addWithdral(1, 45593, 20));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}