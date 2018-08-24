package struts2.common.entity;



import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String category;
    private String author;
    private String publisher;
    @Column(name = "release_date")
    private Date releaseDate;
    private String avatar;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<LoanCard> loanCardList;

    public Book() {
    }

    public Book(String name, String category, String author, String publisher, Date releaseDate, String avatar) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", releaseDate=" + releaseDate +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
