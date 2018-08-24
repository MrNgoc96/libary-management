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
 * Create Book Action
 *
 * @author longoc
 */

@InterceptorRefs({
        @InterceptorRef(value = "fileUpload", params = {"allowedTypes", "image/jpeg,image/gif,image/png"}),
        @InterceptorRef(value = "store", params = {"operationMode", "STORE"})
})
public class CreateBookAction extends BaseAction {

    @Autowired
    BookService bookService;

    private BookDTO book;

    private File image;

    private String imageContentType;

    private String imageFileName;

    private String[] categories = {"Sách lập trình", "Truyện tranh", "Tiểu thuyết", "Sách Khoa Học"};

    private String[] publishers = {"NXB Đại Học Bách Khoa", "NXB Đại Học FPT", "NXB Đại Học Công Nghệ", "NXB Văn Học", "NXB Kim Đồng"};


    @Override
    @Action(value = "create-book", results = {@Result(name = "success", params = {"actionName", "list-book"}, type = "redirectAction"),
            @Result(name = "input", location = "/views/book/create-book.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String execute() throws Exception {

        logger.info("===================Create Book Action====" + book.toString());


        String imageLink = BaseUtils.saveImage("Book", image, imageFileName);

        book.setAvatar(imageLink);

        String[] info = {getText("global.book"), book.getName()};

        if (bookService.saveOrUpdateBook(book)) {

            addActionMessage(getText("global.insertSuccess",info));

            return SUCCESS;
        } else {

            addActionMessage(getText("global.insertFail",info));

            return INPUT;
        }

    }

    @SkipValidation
    @Action(value = "create-book-form", results = {@Result(name = "success", location = "/views/book/create-book.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String display() {
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

        } else if (image == null) {

            addFieldError("image", getText("error.image",info));
        }

        logger.info("============End validate Book===============");
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


    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
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

