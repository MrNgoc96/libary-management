package struts2.common.basic;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import javax.servlet.ServletContext;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Class with many base method
 *
 * @author longoc
 */
public class BaseUtils {


    public static Object transform(Object source, Class clazz) {

        if (source == null) return null;

        Object target = null;

        try {

            target = clazz.newInstance();
            BeanUtils.copyProperties(source, target);

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();
        }
        return target;
    }

    public static HashMap sortDescByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, (o1, o2) -> ((Comparable) ((Map.Entry) (o2)).getValue())
                .compareTo(((Map.Entry) (o1)).getValue()));

        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public static String updateImage(String tableName, File image, String oldImagePath, String newImageName) throws IOException {

        deleteImage(oldImagePath);

        return saveImage(tableName, image, newImageName);
    }

    public static String saveImage(String tableName, File image, String newImageName) throws IOException {

        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHss");

        String idImage = format.format(new Date());

        ServletContext context = ServletActionContext.getServletContext();

        String realPath = context.getRealPath("resources/images/") + tableName + "_" + idImage + "_" + newImageName;

        FileUtils.copyFile(image, new File(realPath));

        return "resources/images/" + tableName + "_" + idImage + "_" + newImageName;
    }

    public static void deleteImage(String imageLink) throws IOException {
        if (!imageLink.startsWith("resources/images/")) return;
        String imagePath = ServletActionContext.getServletContext().getRealPath(imageLink);
        Path path = Paths.get(imagePath);
        Files.deleteIfExists(path);
    }

    public static String encryptPassword(String password) {
        String sha1 = null;
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(password.getBytes("UTF-8"), 0, password.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sha1.toLowerCase();
    }


    public static HashMap sortByValues(HashMap map, boolean isSortAsc) {
        List list = new LinkedList(map.entrySet());
        if (isSortAsc) {
            Collections.sort(list, (o1, o2) -> ((Comparable) ((Map.Entry) (o1)).getValue())
                    .compareTo(((Map.Entry) (o2)).getValue()));
        } else {
            Collections.sort(list, (o1, o2) -> ((Comparable) ((Map.Entry) (o2)).getValue())
                    .compareTo(((Map.Entry) (o1)).getValue()));
        }
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public static boolean isNumber(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static int countTotalPage(int total, int maxResult) {
        int page = total / maxResult;
        if (total % maxResult > 0) {
            page++;
        }
        return page;
    }


}
