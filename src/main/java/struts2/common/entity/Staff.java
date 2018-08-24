package struts2.common.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String phone;
    @Column(name = "work_day")
    private Date workDay;
    private String avatar;
    private String username;
    @OneToMany(mappedBy = "staff")
    private List<LoanCard> loanCardList;

    public Staff() {
    }

    public Staff(String name, Date dateOfBirth, String gender, String address, String phone, Date workDay, String avatar, String username) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.workDay = workDay;
        this.avatar = avatar;
        this.username = username;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getWorkDay() {
        return workDay;
    }

    public void setWorkDay(Date workDay) {
        this.workDay = workDay;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<LoanCard> getLoanCardList() {
        return loanCardList;
    }
}
