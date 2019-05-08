package database.entity;

/**
 * JuniorAccount
 */
public class JuniorAccount extends Account {
    public JuniorAccount() {
        setOverdraftLimit(0);
    }
}