package struts2.book.action;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.book.dto.BookDTO;
import struts2.book.service.BookService;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.basic.BaseUtils;
import struts2.common.users.UserRole;


import javax.servlet.ServletContext;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Update Book Action
 *
 * @author longoc
 */
@InterceptorRefs({@InterceptorRef(value = "defaultStack"),
        @InterceptorRef(value = "fileUpload", params = {"allowedTypes", "image/jpeg,image/gif,image/png"}),
        @InterceptorRef(value = "store", params = {"operationMode", "AUTOMATIC"})})
public class UpdateBookAction extends BaseAction {

    @Autowired
    BookService bookService;

    private BookDTO book;

    private int bookId;

    private File image;

    private String imageContentType;

    private String imageFileName;

    private String[] categories = {"Sách lập trình", "Truyện tranh", "Tiểu thuyết", "Sách Khoa Học"};

    private String[] publishers = {"NXB Đại Học Bách Khoa", "NXB Đại Học FPT", "NXB Đại Học Công Nghệ", "NXB Văn Học", "NXB Kim Đồng"};

    @Override
    @Action(value = "update-book", results = {@Result(name = "success", params = {"actionName", "list-book"}, type = "redirectAction"),
            @Result(name = "input", location = "/views/book/edit-book.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String execute() throws Exception {
        logger.info("===================Update Book Action====" + book.toString());

        logger.info(bookId);

        book.setId(bookId);

        BookDTO b = bookService.getBookById(bookId);

        String[] info = {getText("global.book"), book.getName()};

        if (image != null) {

            String imageLink = BaseUtils.updateImage("Book", image, b.getAvatar(), imageFileName);

            book.setAvatar(imageLink);

        } else {

            book.setAvatar(b.getAvatar());
        }

        if (bookService.saveOrUpdateBook(book)) {

            addActionMessage(getText("global.updateSuccess", info));

            return SUCCESS;
        } else {

            addActionMessage(getText("global.updateFail", info));

            return INPUT;
        }
    }

    @SkipValidation
    @Action(value = "update-book-form", results = {@Result(name = "success", location = "/views/book/edit-book.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String display() {
        book = bookService.getBookById(bookId);
        return SUCCESS;
    }

    @Override
    public void validate() {
        logger.info("=============Begin validate Book===============");
        String[] info = {getText("global.book")};
        if (book.getName() == null || book.getName().equals("")) {

            logger.info("Book name invalid !");

            addFieldError("book.name", getText("error.name",info));

        } else if (book.getAuthor() == null || book.getAuthor().equals("")) {

            logger.info("Book author invalid !");

            addFieldError("book.author", getText("error.author",info));

        } else if (book.getReleaseDate() == null) {

            logger.info("Book release date invalid !");

            addFieldError("book.releaseDate", getText("error.releaseDate",info));

        }

        logger.info("============End validate Book===============");
    }


    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String[] getPublishers() {
        return publishers;
    }

    public void setPublishers(String[] publishers) {
        this.publishers = publishers;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}
