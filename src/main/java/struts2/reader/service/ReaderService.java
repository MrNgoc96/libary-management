package struts2.reader.service;


import struts2.common.basic.BaseService;
import struts2.common.basic.SearchResult;
import struts2.reader.dto.ReaderDTO;

import java.util.HashMap;
import java.util.List;


public interface ReaderService extends BaseService {


    SearchResult<ReaderDTO> searchReader(String key, String columnSearch, int page);

    List<ReaderDTO> getListReader(int page);

    ReaderDTO getReaderById(int id);

    List<ReaderDTO> getTop5NewReader();

    HashMap<ReaderDTO, Integer> getTop5Reader() throws  Exception;

    boolean saveOrUpdateReader(ReaderDTO Reader);

    boolean deleteReader(int id);

    int getTotalPage();

    int getTotalPageSearch(String key, String columnName);

    int totalResultSearchReader(String key,String columnName);

}
