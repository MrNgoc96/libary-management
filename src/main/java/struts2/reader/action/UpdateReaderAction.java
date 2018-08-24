package struts2.reader.action;

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
import struts2.reader.dto.ReaderDTO;
import struts2.reader.service.ReaderService;


import java.io.File;

/**
 * Update Reader Action
 *
 * @author longoc
 */
@InterceptorRefs({@InterceptorRef(value = "defaultStack"),
        @InterceptorRef(value = "fileUpload", params = {"allowedTypes", "image/jpeg,image/gif,image/png"}),
        @InterceptorRef(value = "store", params = {"operationMode", "AUTOMATIC"})})
public class UpdateReaderAction extends BaseAction {

    @Autowired
    ReaderService readerService;

    private ReaderDTO reader;

    private int readerId;

    private File image;

    private String imageContentType;

    private String imageFileName;

    private String[] categories = {"Sách lập trình", "Truyện tranh", "Tiểu thuyết", "Sách Khoa Học"};

    private String[] publishers = {"NXB Đại Học Bách Khoa", "NXB Đại Học FPT", "NXB Đại Học Công Nghệ", "NXB Văn Học", "NXB Kim Đồng"};

    @Override
    @Action(value = "update-reader", results = {@Result(name = "success", params = {"actionName", "list-reader"}, type = "redirectAction"),
            @Result(name = "input", location = "/views/reader/edit-reader.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String execute() throws Exception {
        logger.info("===================Update Reader Action====" + reader.toString());

        logger.info(readerId);

        reader.setId(readerId);

        ReaderDTO r = readerService.getReaderById(readerId);

        reader.setCreateDate(r.getCreateDate());
        String[] info = {getText("global.reader"), reader.getName()};

        if (image != null) {

            String imageLink = BaseUtils.updateImage("Reader", image, r.getAvatar(), imageFileName);

            reader.setAvatar(imageLink);

        } else {

            reader.setAvatar(r.getAvatar());
        }

        if (readerService.saveOrUpdateReader(reader)) {

            addActionMessage(getText("global.updateSuccess", info));

            return SUCCESS;
        } else {

            addActionMessage(getText("global.updateFail", info));

            return INPUT;
        }
    }

    @SkipValidation
    @Action(value = "update-reader-form", results = {@Result(name = "success", location = "/views/reader/edit-reader.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String display() {
        reader = readerService.getReaderById(readerId);
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

        }

        logger.info("======================End validate reader ===============");

    }


    public ReaderDTO getReader() {
        return reader;
    }

    public void setReader(ReaderDTO book) {
        this.reader = book;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int bookId) {
        this.readerId = bookId;
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
