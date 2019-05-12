package database.dao.impl;

import java.io.IOException;
import java.util.Calendar;

import database.BaseDao;
import database.dao.*;
import database.entity.*;

/**
 * JuniorAccountDaoImpl
 */
public class JuniorAccountDaoImpl extends AccountDaoImpl implements JuniorAccountDao {

    public JuniorAccount findAccount(int id) throws IOException {
        Account account = super.findAccount(id);
        if (account != null)
            return new JuniorAccount(account);
        else
            return null;
    }

    // search for the Credit Status
    private boolean confirmCreditStatus(String name) {
        // some process by name
        return true;
    }

    // 1 for success; 0 for existed account; -1 for unavailable credit;
    // -3 for upper than 16-year-old;
    public int addAccount(Customer customer) throws IOException {
        try {
            if (getAge(customer.getDateOfBirth()) > 16)
                return -3;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        CurrentAccount account = new CurrentAccount(customer);
        if (!confirmCreditStatus(account.getCustomer().getName()))
            return -1;
        else {
            account.setId(BaseDao.fileCount());
            if (!BaseDao.addFile(account.toFileName(), account.toString()))
                return 0;
            else {
                BaseDao.addLine("accounts.txt", (BaseDao.fileCount() - 1) + "\t|\tjunior");
                return account.getId();
            }
        }
    }

    private int getAge(Calendar dateOfBirth) throws Exception {
        Calendar cal = Calendar.getInstance();

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        int yearBirth = dateOfBirth.get(Calendar.YEAR);
        int monthBirth = dateOfBirth.get(Calendar.MONTH);
        int dayOfMonthBirth = dateOfBirth.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;
            } else {
                age--;
            }
        }
        return age;
    }

    public static void main(String[] args) {
        JuniorAccountDao dao = new JuniorAccountDaoImpl();

        Customer customer = new Customer();
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 5, 5);
        customer.setDateOfBirth(cal);
        try {
            // dao.adjustSuspendedAccount(1, 455937);
            dao.addAccount(customer);
            // dao.clearFundsByAccount(1);
            // dao.addWithdral(1, 45593, 20);
            // System.out.println(dao.addWithdral(1, 45593, 20));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}