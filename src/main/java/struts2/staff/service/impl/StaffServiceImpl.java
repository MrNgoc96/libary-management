package struts2.staff.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import struts2.book.dto.BookDTO;
import struts2.common.basic.BaseUtils;
import struts2.common.basic.SearchResult;
import struts2.common.basic.TransformUtils;
import struts2.common.entity.Book;
import struts2.common.entity.Staff;
import struts2.staff.repository.StaffRepository;
import struts2.staff.dto.StaffDTO;
import struts2.staff.service.StaffService;


import java.util.ArrayList;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffRepository staffRepository;
    @Autowired
    TransformUtils<Staff, StaffDTO> transformUtils;

    public StaffDTO getStaffByUsername(String username) {
        Staff staff = staffRepository.getStaffByUsername(username);
        return transformUtils.transform(staff, StaffDTO.class);
    }

    public SearchResult<StaffDTO> searchStaff(String key, String columnSearch, int page) {
        SearchResult<StaffDTO> searchResult = new SearchResult<>();
        key = "%" + key + "%";

        columnSearch = columnSearch.toLowerCase();

        SearchResult<Staff> search = staffRepository.searchStaff(key, columnSearch, page);

        List<StaffDTO> bookDTOList = transformUtils.transformList(search.getResults(),StaffDTO.class);

        searchResult.setResults(bookDTOList);
        searchResult.setTotalPages(search.getTotalPages());
        searchResult.setTotalResults(search.getTotalResults());

        return searchResult;
    }

    public List<StaffDTO> getListStaff(int page) {
        List<Staff> staffList = staffRepository.getListStaff(page);
        return transformUtils.transformList(staffList, StaffDTO.class);
    }


    public StaffDTO getStaffById(int id) {
        Staff staff = staffRepository.getStaffById(id);
        return transformUtils.transform(staff, StaffDTO.class);
    }


    public boolean saveOrUpdateStaff(StaffDTO staffDTO) {
        Staff staff = transformUtils.transformReverse(staffDTO, Staff.class);
        return staffRepository.saveOrUpdateStaff(staff);
    }

    @Override
    public int getTotalPageSearch(String key, String columnName) {
        key = "%" + key + "%";
        columnName = columnName.toLowerCase();
        return staffRepository.getTotalPageSearch(key, columnName);
    }

    @Override
    public int totalResultSearch(String key, String columnName) {
        key = "%" + key + "%";
        columnName = columnName.toLowerCase();
        return staffRepository.totalResultSearch(key, columnName);
    }

    public boolean deleteStaff(int id) {
        return staffRepository.deleteStaff(id);
    }

    public int getTotalPage() {
        return staffRepository.getTotalPage();
    }
}
