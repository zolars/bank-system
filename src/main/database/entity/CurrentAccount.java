package database.entity;

/**
 * CurrentAccount
 */
public class CurrentAccount extends Account {
    public CurrentAccount(Customer customer) {
        super(customer);
    }

    public CurrentAccount(Account account) {
        super(account);
    }
}