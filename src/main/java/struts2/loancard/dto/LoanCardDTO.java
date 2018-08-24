package struts2.loancard.dto;

import java.util.Date;

/**
 * LoanCard DTO
 *
 * @author longoc
 */
public class LoanCardDTO {

    private int id;

    private int staffId;

    private int readerId;

    private int bookId;

    private Date createDate;

    private Date returnDate;

    public LoanCardDTO() {
    }

    public LoanCardDTO(int id, int staffId, int readerId, int bookId, Date createDate, Date returnDate) {
        this.id = id;
        this.staffId = staffId;
        this.readerId = readerId;
        this.bookId = bookId;
        this.createDate = createDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
