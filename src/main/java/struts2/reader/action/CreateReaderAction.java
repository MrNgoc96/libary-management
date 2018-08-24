package struts2.reader.action;


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
import struts2.reader.dto.ReaderDTO;
import struts2.reader.service.ReaderService;

import javax.servlet.ServletContext;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@InterceptorRefs({@InterceptorRef(value = "defaultStack"),
        @InterceptorRef(value = "fileUpload", params = {"allowedTypes", "image/jpeg,image/gif,image/png"}),
        @InterceptorRef(value = "store", params = {"operationMode", "STORE"}),
})
public class CreateReaderAction extends BaseAction {

    @Autowired
    ReaderService readerService;

    private ReaderDTO reader;

    private File image;
    private String imageContentType;
    private String imageFileName;


    private String[] categories = {"Sách lập trình", "Truyện tranh", "Tiểu thuyết", "Sách Khoa Học"};

    private String[] publishers = {"NXB Đại Học Bách Khoa", "NXB Đại Học FPT", "NXB Đại Học Công Nghệ", "NXB Văn Học", "NXB Kim Đồng"};


    @Override
    @Action(value = "create-reader", results = {@Result(name = "success", params = {"actionName", "list-reader"}, type = "redirectAction"),
            @Result(name = "input", location = "/views/reader/create-reader.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String execute() throws Exception {
        logger.info("===================Create reader Action====" + reader.toString());

        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHss");

        String idImage = format.format(new Date());


        ServletContext context = ServletActionContext.getServletContext();

        String realPath = context.getRealPath("resources/images/") + "Reader_" + idImage + "_" + imageFileName;

        logger.info(realPath);


        FileUtils.copyFile(image, new File(realPath));

        String imageLink = "resources/images/" + "Reader_" + idImage + "_" + imageFileName;

        reader.setAvatar(imageLink);
        reader.setCreateDate(new Date());

        String[] info = {getText("global.reader"), reader.getName()};

        if (readerService.saveOrUpdateReader(reader)) {

            addActionMessage(getText("global.insertSuccess",info));

            return SUCCESS;
        } else {
            addActionMessage(getText("global.insertFail",info));
            return INPUT;
        }

    }

    @SkipValidation
    @Action(value = "create-reader-form", results = {@Result(name = "success", location = "/views/reader/create-reader.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String display() {
        return SUCCESS;
    }

    @Override
    public void validate() {

        logger.info("======================Begin validate reader===============");

        String[] info = {getText("global.reader")};

        if (reader.getName() == null || reader.getName().trim().equals("")) {


            addFieldError("reader.name", getText("error.name",info));

        } else if (reader.getDateOfBirth() == null) {

            addFieldError("reader.dateOfBirth",  getText("error.dateOfBirth",info));

        } else if (reader.getGender() == null) {

            logger.info("reader release date invalid !");

            addFieldError("reader.gender",  getText("error.gender",info));

        } else if (reader.getAddress() == null || reader.getAddress().trim().equals("")) {

            addFieldError("reader.address",  getText("error.address",info));

        } else if (image == null) {

            addFieldError("image",  getText("error.image",info));
        }

        logger.info("======================End validate reader ===============");

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


    public ReaderDTO getReader() {
        return reader;
    }

    public void setReader(ReaderDTO Reader) {
        this.reader = Reader;
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

