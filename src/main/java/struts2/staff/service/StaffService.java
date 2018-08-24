package struts2.staff.service;


import struts2.common.basic.BaseService;
import struts2.common.basic.SearchResult;
import struts2.staff.dto.StaffDTO;

import java.util.List;


public interface StaffService extends BaseService {


    StaffDTO getStaffByUsername(String username);

    SearchResult<StaffDTO> searchStaff(String key, String columnSearch, int page);

    List<StaffDTO> getListStaff(int page);

    StaffDTO getStaffById(int id);

    boolean saveOrUpdateStaff(StaffDTO Staff);

    boolean deleteStaff(int id);

    int getTotalPage();

    int getTotalPageSearch(String key, String columnName);

    int totalResultSearch(String key,String columnName);
}
