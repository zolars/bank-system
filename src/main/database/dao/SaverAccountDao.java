package database.dao;

import java.io.IOException;

import database.entity.*;

/**
 * SaverAccountDao
 */
public interface SaverAccountDao extends AccountDao {

    public SaverAccount findAccount(int id) throws IOException;

    public int addAccount(Customer customer) throws IOException;

    public int addWithdral(int id, int pin, double num) throws IOException;

    public int addOrder(int id, int pin, double num) throws IOException;

}