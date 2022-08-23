package uk.fergcb.sakila.data.hateoas;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class PagedCollection<T extends HateoasResource> extends HateoasCollection<T> {

    protected final PaginationDetails page;

    protected PagedCollection(String name, Page<T> page) {
        super(name, page.getContent());
        this.page = new PaginationDetails(page);
    }

    public PaginationDetails getPage() {
        return page;
    }

    protected abstract Link getNextLink();
    protected abstract Link getPreviousLink();
    protected abstract Collection<Link> getCollectionLinks();

    @Override
    protected Collection<Link> getLinks() {
        final List<Link> links = new ArrayList<>(getCollectionLinks());

        if (page.getHasNext()) links.add(getNextLink());
        if (page.getHasPrevious()) links.add(getPreviousLink());

        return links;
    }
}
