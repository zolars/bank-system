package database.entity;

import java.util.Date;

/**
 * SaverAccount
 */
public class SaverAccount extends Account {
    private Date noticeDate;
    private double noticeAmount;

    public SaverAccount(Customer customer) {
        super(customer);
    }

    public SaverAccount(Account account) {
        super(account);
    }

    /**
     * @param noticeDate the noticeDate to set
     */
    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    /**
     * @return the noticeDate
     */
    public Date getNoticeDate() {
        return noticeDate;
    }

    /**
     * @param noticeAmount the noticeAmount to set
     */
    public void setNoticeAmount(double noticeAmount) {
        this.noticeAmount = noticeAmount;
    }

    /**
     * @return the noticeAmount
     */
    public double getNoticeAmount() {
        return noticeAmount;
    }

}