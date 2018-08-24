package struts2.reader.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import struts2.book.dto.BookDTO;
import struts2.common.basic.BaseUtils;
import struts2.common.basic.SearchResult;
import struts2.common.basic.TransformUtils;
import struts2.common.entity.Reader;
import struts2.reader.dto.ReaderDTO;
import struts2.reader.repository.ReaderRepository;
import struts2.reader.service.ReaderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    TransformUtils<Reader, ReaderDTO> transformUtils;


    @Transactional(readOnly = true)
    @Override
    public SearchResult<ReaderDTO> searchReader(String key, String columnSearch, int page) {
        SearchResult<ReaderDTO> searchResult = new SearchResult<>();
        key = "%" + key + "%";

        columnSearch = columnSearch.toLowerCase();

        SearchResult<Reader> search = readerRepository.searchReader(key, columnSearch, page);

        List<ReaderDTO> readerDTOList = transformUtils.transformList(search.getResults(), ReaderDTO.class);

        searchResult.setResults(readerDTOList);
        searchResult.setTotalPages(search.getTotalPages());
        searchResult.setTotalResults(search.getTotalResults());

        return searchResult;

    }

    @Transactional(readOnly = true)
    @Override
    public List<ReaderDTO> getListReader(int page) {

        return transformToListDTO(readerRepository.getListReader(page));
    }

    @Transactional(readOnly = true)
    @Override
    public ReaderDTO getReaderById(int id) {
        return (ReaderDTO) BaseUtils.transform(readerRepository.getReaderById(id), ReaderDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReaderDTO> getTop5NewReader() {
        return transformToListDTO(readerRepository.getTop5NewReader());
    }

    @Transactional(readOnly = true)
    @Override
    public HashMap<ReaderDTO, Integer> getTop5Reader() throws Exception {

        HashMap<Reader, Integer> map = readerRepository.getTop5Reader();

        return transformToMapDTO(map);
    }

    @Transactional
    @Override
    public boolean saveOrUpdateReader(ReaderDTO ReaderDTO) {

        Reader Reader = (Reader) BaseUtils.transform(ReaderDTO, Reader.class);

        return readerRepository.saveOrUpdateReader(Reader);
    }

    @Override
    public int totalResultSearchReader(String key, String columnName) {
        key = "%" + key + "%";
        columnName = columnName.toLowerCase();
        return readerRepository.totalResultSearchReader(key, columnName);
    }

    @Override
    public boolean deleteReader(int id) {
        return readerRepository.deleteReader(id);
    }

    @Override
    public int getTotalPage() {
        return readerRepository.getTotalPage();
    }

    @Override
    public int getTotalPageSearch(String key, String columnName) {
        key = "%" + key + "%";
        columnName = columnName.toLowerCase();
        return readerRepository.getTotalPageSearch(key, columnName);
    }


    private List<ReaderDTO> transformToListDTO(List<Reader> sourceList) {
        List<ReaderDTO> ReaderDTOList = new ArrayList<>();

        for (Reader b : sourceList) {
            ReaderDTOList.add((ReaderDTO) BaseUtils.transform(b, ReaderDTO.class));
        }

        return ReaderDTOList;
    }

    private HashMap<ReaderDTO, Integer> transformToMapDTO(HashMap<Reader, Integer> map) {
        HashMap<ReaderDTO, Integer> top5 = new HashMap<>();
        for (Reader b : map.keySet()) {
            ReaderDTO ReaderDTO = (ReaderDTO) BaseUtils.transform(b, ReaderDTO.class);
            top5.put(ReaderDTO, map.get(b));
        }
        return BaseUtils.sortDescByValues(top5);
    }

}
