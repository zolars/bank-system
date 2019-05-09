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
    // -2 for upper than 16-year-old;
    public int addAccount(Account account) throws IOException {
        if (account.getCustomer().getDateOfBirth().get(Calendar.YEAR) > Calendar.getInstance().get(Calendar.YEAR)
                - 16) {
            int result = super.addAccount(account);
            if (result != 1)
                return result;
            BaseDao.addLine("accounts.txt", (BaseDao.fileCount() - 1) + "\t|\tjunior");
            return 1;
        } else
            return -2;

    }
}