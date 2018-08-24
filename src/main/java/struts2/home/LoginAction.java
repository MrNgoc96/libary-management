package struts2.home;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.entity.Staff;
import struts2.common.entity.User;
import struts2.common.users.UserRole;
import struts2.common.users.UserService;
import struts2.staff.dto.StaffDTO;
import struts2.staff.service.StaffService;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;


@InterceptorRefs({@InterceptorRef(value = "store", params = {"operationMode", "STORE"})})
public class LoginAction extends BaseAction {

    private String username;
    private String password;

    @Autowired
    UserService userService;
    @Autowired
    StaffService staffService;


    Logger logger = LogManager.getLogger(LoginAction.class);


    @Override
    @Action(value = "login", results = {@Result(name = SUCCESS, location = "/home", type = "redirect"),
            @Result(name = LOGIN, params = {"actionName", ""}, type = "redirectAction")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF, UserRole.UNKNOWN})
    public String execute() {
        logger.info("=============Login Action======= Username : " + username + " -  Password : " + password);

        if (userService.login(username, password)) {

            User user = userService.getUser(username);

            if (user.getRole() == UserRole.STAFF) {
                StaffDTO staff = staffService.getStaffByUsername(username);
                staff.setWorkDay(Date.from(Instant.now()));
                staffService.saveOrUpdateStaff(staff);
                session.put("currentStaff", staff);
            }

            session.put("currentUser", user);
            return SUCCESS;

        } else {

            addActionMessage(getText("login.fail"));
            return LOGIN;
        }
    }


    @Action(value = "logout", results = {@Result(name = "success", location = "/", type = "redirect")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String logout() {
        logger.info("=============Logout Action==================");
        session.remove("currentUser");
        session.remove("currentStaff");
        return SUCCESS;
    }


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
