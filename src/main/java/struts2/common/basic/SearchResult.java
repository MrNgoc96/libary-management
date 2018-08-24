package struts2.common.basic;

import java.util.List;

public class SearchResult<T> {

    private List<T> results;
    private int totalResults;
    private int totalPages;

    public SearchResult() {
    }

    public SearchResult(List<T> results, int totalResults, int totalPages) {
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
