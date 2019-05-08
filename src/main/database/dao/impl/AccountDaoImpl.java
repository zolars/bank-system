package database.dao.impl;

import java.io.IOException;

import database.BaseDao;
import database.dao.*;
import database.entity.*;

/**
 * AccountDao
 */
public class AccountDaoImpl implements AccountDao {

    public boolean addAccount(Account account) throws IOException {
        return BaseDao.addFile(account.getId() + ".txt", account.toString());
    }

    public boolean deleteAccount(Account account) throws IOException {
        account.setActive(false);
        return BaseDao.replace(account.getId() + ".txt", account.toString());
    }

    public static void main(String[] args) {

    }
}