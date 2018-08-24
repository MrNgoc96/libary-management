package struts2.common.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private String gender;
    private String address;
    @Column(name = "create_date")
    private Date createDate;
    private String avatar;
    @OneToMany(mappedBy = "reader")
    private List<LoanCard> loanCardList;

    public Reader() {
    }

    public Reader(String name, Date dateOfBirth, String gender, String address, Date createDate, String avatar) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.createDate = createDate;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<LoanCard> getLoanCardList() {
        return loanCardList;
    }

    public void setLoanCardList(List<LoanCard> loanCardList) {
        this.loanCardList = loanCardList;
    }
}
