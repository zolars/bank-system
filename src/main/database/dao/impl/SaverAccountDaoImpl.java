package database.dao.impl;

import java.io.IOException;

import database.BaseDao;
import database.dao.*;
import database.entity.*;

/**
 * SaverAccountDaoImpl
 */
public class SaverAccountDaoImpl extends AccountDaoImpl implements SaverAccountDao {

    public SaverAccount findAccount(int id) throws IOException {
        Account account = super.findAccount(id);
        if (account != null)
            return new SaverAccount(account);
        else
            return null;
    }

    // 1 for success; 0 for existed account; -1 for unavailable credit;
    public int addAccount(Customer customer) throws IOException {
        int result = super.addAccount(customer);
        if (result != 1)
            return result;

        BaseDao.addLine("accounts.txt", (BaseDao.fileCount() - 1) + "\t|\tsaver");
        return 1;
    }
}