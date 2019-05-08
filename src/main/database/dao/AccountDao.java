package database.dao;

import java.io.IOException;

import database.entity.*;

/**
 * AccountDao
 */
public interface AccountDao {
    public boolean addAccount(Account account) throws IOException;

    public boolean deleteAccount(Account account) throws IOException;

}