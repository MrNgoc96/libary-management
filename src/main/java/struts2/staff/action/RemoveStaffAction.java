package struts2.staff.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.basic.BaseUtils;
import struts2.common.users.UserRole;
import struts2.staff.dto.StaffDTO;
import struts2.staff.service.StaffService;

/**
 * Remove Staff Action
 *
 * @author longoc
 */

@InterceptorRefs({@InterceptorRef(value = "defaultStack"),
        @InterceptorRef(value = "store", params = {"operationMode", "AUTOMATIC"})})
public class RemoveStaffAction extends BaseAction {

    @Autowired
    StaffService bookService;

    private int bookId;

    @Override
    @Action(value = "remove-staff", results = {@Result(name = "success", params = {"actionName", "list-staff"}, type = "redirectAction")})
    @UserPermission({UserRole.ADMIN,UserRole.STAFF})
    public String execute() throws Exception {

        StaffDTO staff = bookService.getStaffById(bookId);
        String[] info = {getText("global.staff"), staff.getName()};

        if (staff != null) {

            String imageLink = staff.getAvatar();

            if (bookService.deleteStaff(bookId)) {

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


    public int getStaffId() {
        return bookId;
    }

    public void setStaffId(int bookId) {
        this.bookId = bookId;
    }
}
