package database.entity;

/**
 * SaverAccount
 */
public class SaverAccount extends Account {

    public SaverAccount() {
        setOverdraftLimit(0);
    }

    public SaverAccount(Customer customer) {
        super(customer);
        setOverdraftLimit(0);
    }

    public SaverAccount(Account account) {
        super(account);
        setOverdraftLimit(0);
    }

}