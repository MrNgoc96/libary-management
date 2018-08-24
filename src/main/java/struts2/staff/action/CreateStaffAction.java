package struts2.staff.action;


import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.users.UserRole;
import struts2.staff.dto.StaffDTO;
import struts2.staff.service.StaffService;

import javax.servlet.ServletContext;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@InterceptorRefs({@InterceptorRef(value = "defaultStack"),
        @InterceptorRef(value = "fileUpload", params = {"allowedTypes", "image/jpeg,image/gif,image/png"}),
        @InterceptorRef(value = "store", params = {"operationMode", "STORE"}),
})
public class CreateStaffAction extends BaseAction {

    @Autowired
    StaffService staffService;

    private StaffDTO staff;

    private File image;
    private String imageContentType;
    private String imageFileName;


    @Override
    @Action(value = "create-staff", results = {@Result(name = "success", params = {"actionName", "list-staff"}, type = "redirectAction"),
            @Result(name = "input", location = "/views/staff/create-staff.jsp")})
    @UserPermission({UserRole.ADMIN})
    public String execute() throws Exception {

        logger.info("===================Create Staff Action====" + staff.toString());

        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHss");

        String idImage = format.format(new Date());


        ServletContext context = ServletActionContext.getServletContext();

        String realPath = context.getRealPath("resources/images/") + "Staff_" + idImage + "_" + imageFileName;

        logger.info(realPath);


        FileUtils.copyFile(image, new File(realPath));

        String imageLink = "resources/images/" + "Staff_" + idImage + "_" + imageFileName;

        staff.setAvatar(imageLink);
        staff.setWorkDay(Date.from(Instant.now()));

        String[] info = {getText("global.staff"), staff.getName()};

        if (staffService.saveOrUpdateStaff(staff)) {

            addActionMessage(getText("global.insertSuccess", info));

            return SUCCESS;
        } else {
            addActionMessage(getText("global.insertFail", info));
            return INPUT;
        }

    }

    @SkipValidation
    @Action(value = "create-staff-form", results = {@Result(name = "success", location = "/views/staff/create-staff.jsp")})
    @UserPermission({UserRole.ADMIN})
    public String display() {
        return SUCCESS;
    }

    @Override
    public void validate() {

        logger.info("======================Begin validate Staff===============");

        String[] info = {getText("global.staff")};

        if (staff.getUsername() == null || staff.getUsername().trim().equals("")) {
            logger.info("Staff username invalid !");

            addFieldError("staff.username", getText("error.username", info));

        } else if (staffService.getStaffByUsername(staff.getUsername()) != null) {
            logger.info("Staff username was exits !");

            info = new String[]{staff.getUsername()};

            addFieldError("staff.username", getText("error.usernameExist",info));

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

        } else if (image == null) {

            addFieldError("image", getText("error.image", info));
        }

        logger.info("======================End validate Staff ===============");

    }

    public StaffDTO getStaff() {
        return staff;
    }

    public void setStaff(StaffDTO Staff) {
        this.staff = Staff;
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

