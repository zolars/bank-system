package database.entity;

import java.util.Random;

/**
 * Account
 */
public class Account {

    private int id;
    private int pin;
    private double balance;
    private double overdraftLimit;
    private boolean isSuspended;
    private boolean isActive;
    private boolean noticeNeeded;

    public Account() {
        generatePin();
        this.isSuspended = false;
        this.isActive = true;
        this.noticeNeeded = false;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param pin the pin to set
     */
    public void generatePin() {
        Random r = new Random();
        this.pin = (100000 + r.nextInt(900000));
    }

    /**
     * @return the pin
     */
    public int getPin() {
        return pin;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param overdraftLimit the overdraftLimit to set
     */
    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * @return the overdraftLimit
     */
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    /**
     * @param isSuspended the isSuspended to set
     */
    public void setSuspended(boolean isSuspended) {
        this.isSuspended = isSuspended;
    }

    /**
     * @return the isSuspended
     */
    public boolean isSuspended() {
        return isSuspended;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the isActive
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * @param noticeNeeded the noticeNeeded to set
     */
    public void setNoticeNeeded(boolean noticeNeeded) {
        this.noticeNeeded = noticeNeeded;
    }

    /**
     * @return the noticeNeeded
     */
    public boolean isNoticeNeeded() {
        return noticeNeeded;
    }

    @Override
    public String toString() {
        return "" + getPin() + "|" + getBalance() + "|" + getOverdraftLimit() + "|" + isSuspended() + "|" + isActive()
                + "|" + isNoticeNeeded();
    }

    public static void main(String[] args) {

    }

}