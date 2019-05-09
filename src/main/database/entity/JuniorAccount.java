package database.entity;

/**
 * JuniorAccount
 */
public class JuniorAccount extends Account {
    public JuniorAccount() {
        setOverdraftLimit(0);
    }

    public JuniorAccount(Customer customer) {
        super(customer);
        setOverdraftLimit(0);
    }

    public JuniorAccount(Account account) {
        super(account);
    }

}