package database.dao;

import java.io.IOException;

import database.entity.*;

/**
 * SaverAccountDao
 */
public interface SaverAccountDao extends AccountDao {

    public SaverAccount findAccount(int id) throws IOException;

    public int addAccount(Account account) throws IOException;

}