package database.dao;

import database.entity.Customer;
import java.io.IOException;

/**
 * SaverAccountDao
 */
public interface SaverAccountDao extends AccountDao {

    public int addAccount(Customer customer) throws IOException;

    public int addWithdral(int id, int pin, double num) throws IOException;

    public int addOrder(int id, int pin, double num) throws IOException;

}
