package advisor.dto;

public class Pagination {

    String current;
    String next;
    String prev;
    Integer currPage;
    Integer pages;

    public Pagination(String current, String next, String prev, Integer currPage, Integer pages) {
        this.current = current;
        this.next = next;
        this.prev = prev;
        this.currPage = currPage;
        this.pages = pages;
    }

    public String getNext() {
        return next;
    }

    public String getPrev() {
        return prev;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public Integer getPages() {
        return pages;
    }

    public boolean hasNext() {
        return next != null;
    }

    public boolean hasPrev() {
        return prev != null;
    }

    public String getCurrent() {
        return current;
    }
}
