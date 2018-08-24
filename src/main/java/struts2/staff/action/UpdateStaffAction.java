package struts2.staff.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.basic.BaseUtils;
import struts2.common.users.UserRole;
import struts2.staff.dto.StaffDTO;
import struts2.staff.service.StaffService;

import java.io.File;

/**
 * Update Staff Action
 *
 * @author longoc
 */
@InterceptorRefs({@InterceptorRef(value = "defaultStack"),
        @InterceptorRef(value = "fileUpload", params = {"allowedTypes", "image/jpeg,image/gif,image/png"}),
        @InterceptorRef(value = "store", params = {"operationMode", "AUTOMATIC"})})
public class UpdateStaffAction extends BaseAction {

    @Autowired
    StaffService staffService;

    private StaffDTO staff;

    private int bookId;

    private File image;

    private String imageContentType;

    private String imageFileName;


    @Override
    @Action(value = "update-staff", results = {@Result(name = "success", params = {"actionName", "list-staff"}, type = "redirectAction"),
            @Result(name = "input", location = "/views/staff/edit-staff.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String execute() throws Exception {
        logger.info("===================Update Staff Action====" + staff.toString());

        logger.info(bookId);

        staff.setId(bookId);

        StaffDTO s = staffService.getStaffById(bookId);

        String[] info = {getText("global.staff"), staff.getName()};

        if (image != null) {

            String imageLink = BaseUtils.updateImage("Staff", image, s.getAvatar(), imageFileName);

            staff.setAvatar(imageLink);

        } else {

            staff.setAvatar(s.getAvatar());
        }

        staff.setWorkDay(s.getWorkDay());

        if (staffService.saveOrUpdateStaff(staff)) {

            addActionMessage(getText("global.updateSuccess", info));

            return SUCCESS;
        } else {

            addActionMessage(getText("global.updateFail", info));

            return INPUT;
        }
    }

    @SkipValidation
    @Action(value = "update-staff-form", results = {@Result(name = "success", location = "/views/staff/edit-staff.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String display() {
        staff = staffService.getStaffById(bookId);
        return SUCCESS;
    }

    @Override
    public void validate() {

        logger.info("======================Begin validate Staff===============");

        String[] info = {getText("global.staff")};

        if (staff.getUsername() == null || staff.getUsername().trim().equals("")) {
            logger.info("Staff username invalid !");

            addFieldError("staff.username", getText("error.username",info));

        } else if (staff.getName() == null || staff.getName().trim().equals("")) {

            logger.info("Staff name invalid !");

            addFieldError("staff.name", getText("error.name", info));

        } else if (staff.getDateOfBirth() == null) {

            logger.info("Staff date of birth invalid !");

            addFieldError("staff.dateOfBirth", getText("error.dateOfBirth", info));

        } else if (staff.getGender() == null) {

            logger.info("Staff release date invalid !");

            addFieldError("staff.gender", getText("error.gender", info));

        } else if (staff.getAddress() == null || staff.getAddress().trim().equals("")) {

            logger.info("Staff address date invalid !");
            addFieldError("staff.address", getText("error.address", info));

        } else if (staff.getPhone() == null || staff.getPhone().trim().equals("")) {

            logger.info("Staff phone invalid !");
            addFieldError("staff.phone", getText("error.phone", info));

        }

        logger.info("======================End validate Staff ===============");

    }


    public StaffDTO getStaff() {
        return staff;
    }

    public void setStaff(StaffDTO book) {
        this.staff = book;
    }

    public int getStaffId() {
        return bookId;
    }

    public void setStaffId(int bookId) {
        this.bookId = bookId;
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
