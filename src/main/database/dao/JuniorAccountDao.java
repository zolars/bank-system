package database.dao;

import java.io.IOException;

import database.entity.*;

/**
 * JuniorAccountDao
 */
public interface JuniorAccountDao extends AccountDao {

    public JuniorAccount findAccount(int id) throws IOException;

    public int addAccount(Account account) throws IOException;

}