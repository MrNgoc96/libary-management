package struts2.reader.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.basic.BaseUtils;
import struts2.common.users.UserRole;
import struts2.reader.dto.ReaderDTO;
import struts2.reader.service.ReaderService;


/**
 * Remove Reader Action
 *
 * @author longoc
 */

@InterceptorRefs({@InterceptorRef(value = "defaultStack"),
        @InterceptorRef(value = "store", params = {"operationMode", "AUTOMATIC"})})
public class RemoveReaderAction extends BaseAction {

    @Autowired
    ReaderService readerService;

    private int bookId;

    @Override
    @Action(value = "remove-reader", results = {@Result(name = "success", params = {"actionName", "list-reader"}, type = "redirectAction")})
    @UserPermission({UserRole.ADMIN,UserRole.STAFF})
    public String execute() throws Exception {

        ReaderDTO reader = readerService.getReaderById(bookId);

        String[] info = {getText("global.reader"), reader.getName()};

        if (reader != null) {

            String imageLink = reader.getAvatar();

            if (readerService.deleteReader(bookId)) {

                BaseUtils.deleteImage(imageLink);

                addActionMessage(getText("global.removeSuccess",info));

            } else {

                addActionMessage(getText("global.removeFail",info));

            }
        } else {

            addActionMessage(getText("global.removeFail",info));
        }

        return SUCCESS;
    }


    public int getReaderId() {
        return bookId;
    }

    public void setReaderId(int bookId) {
        this.bookId = bookId;
    }
}
