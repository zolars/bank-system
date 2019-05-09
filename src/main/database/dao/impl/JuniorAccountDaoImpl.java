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

    // 1 for success; 0 for existed account; -1 for unavailable credit;
    // -3 for upper than 16-year-old;
    public int addAccount(Customer customer) throws IOException {
        try {
            if (getAge(customer.getDateOfBirth()) < 16) {
                int result = super.addAccount(customer);
                if (result != 1)
                    return result;
                BaseDao.addLine("accounts.txt", (BaseDao.fileCount() - 1) + "\t|\tjunior");
                return 1;
            } else
                return -3;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
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
}