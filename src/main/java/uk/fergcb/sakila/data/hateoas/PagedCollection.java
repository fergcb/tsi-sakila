package uk.fergcb.sakila.data.hateoas;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class PagedCollection<T extends HateoasResource> extends HateoasCollection<T> {

    private final Page<T> page;

    protected PagedCollection(String name, Page<T> page) {
        super(name, page.getContent());
        this.page = page;
    }

    public Integer getPageNumber() {
        return page.getNumber();
    }

    public Integer getCount() {
        return page.getNumberOfElements();
    }

    public Integer getPageSize() {
        return page.getSize();
    }

    public Boolean getHasNext() {
        return page.hasNext();
    }

    public Boolean getHasPrevious() {
        return page.hasPrevious();
    }

    protected abstract Link getNextLink();
    protected abstract Link getPreviousLink();
    protected abstract Collection<Link> getCollectionLinks();

    @Override
    protected Collection<Link> getLinks() {
        final List<Link> links = new ArrayList<>(getCollectionLinks());

        if (getHasNext()) links.add(getNextLink());
        if (getHasPrevious()) links.add(getPreviousLink());

        return links;
    }
}
