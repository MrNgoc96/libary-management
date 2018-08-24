package struts2.book.dto;

import java.util.Date;

/**
 * Book DTO
 *
 * @author longoc
 */
public class BookDTO {

    private int id;

    private String name;

    private String category;

    private String author;

    private String publisher;

    private Date releaseDate;

    private String avatar;

    public BookDTO() {
    }

    public BookDTO(int id, String name, String category, String author, String publisher, Date releaseDate, String avatar) {
        this.id = id;
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
}
