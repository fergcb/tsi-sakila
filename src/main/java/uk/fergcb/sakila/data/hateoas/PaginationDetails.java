package uk.fergcb.sakila.data.hateoas;

import org.springframework.data.domain.Page;

public class PaginationDetails {

    private final Page<?> page;

    public PaginationDetails(Page<?> page) {
        this.page = page;
    }

    public Integer getNumber() {
        return page.getNumber();
    }

    public Integer getSize() {
        return page.getSize();
    }

    public Integer getTotalPageCount() {
        return page.getTotalPages();
    }

    public Integer getItemCount() {
        return page.getNumberOfElements();
    }

    public Long getTotalItemCount() {
        return page.getTotalElements();
    }

    public Boolean getHasNext() {
        return page.hasNext();
    }

    public Boolean getHasPrevious() {
        return page.hasPrevious();
    }

    public Integer getFirstItem() {
        return getSize() * getNumber() + 1;
    }

    public Integer getLastItem() {
        return getFirstItem() + getItemCount() - 1;
    }

}
