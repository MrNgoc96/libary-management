package struts2.book.action;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.book.dto.BookDTO;
import struts2.book.service.BookService;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.basic.BaseUtils;
import struts2.common.users.UserRole;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Remove Book Action
 *
 * @author longoc
 */

@InterceptorRefs({@InterceptorRef(value = "defaultStack"),
        @InterceptorRef(value = "store", params = {"operationMode", "AUTOMATIC"})})
public class RemoveBookAction extends BaseAction {

    @Autowired
    BookService bookService;

    private int bookId;

    @Override
    @Action(value = "remove-book", results = {@Result(name = "success", params = {"actionName", "list-book"}, type = "redirectAction")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String execute() throws Exception {

        BookDTO book = bookService.getBookById(bookId);

        String[] info = {getText("global.book"), book.getName()};

        if (book != null) {

            String imageLink = book.getAvatar();

            if (bookService.deleteBook(bookId)) {

                BaseUtils.deleteImage(imageLink);

                addActionMessage(getText("global.removeSuccess", info));

            } else {
                addActionMessage(getText("global.removeFail", info));
            }
        } else {
            addActionMessage(getText("global.removeFail", info));
        }

        return SUCCESS;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
