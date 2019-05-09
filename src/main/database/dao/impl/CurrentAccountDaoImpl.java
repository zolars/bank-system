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
        return new CurrentAccount(account);
    }

    // 1 for success; 0 for existed account; -1 for unavailable credit;
    public int addAccount(Account account) throws IOException {
        int result = super.addAccount(account);
        if (result != 1)
            return result;

        BaseDao.addLine("accounts.txt", (BaseDao.fileCount() - 1) + "\t|\tcurrent");
        return 1;
    }
}