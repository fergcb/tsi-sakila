package uk.fergcb.sakila.data.hateoas;

import org.springframework.hateoas.Link;

import javax.persistence.Transient;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class HateoasResource {

    @Transient
    protected abstract Collection<Link> getLinks();

    public Map<String, LinkContent> get_links() {
        final Map<String, LinkContent> linkMap = new HashMap<>();
        getLinks().forEach(link ->
            linkMap.put(link.getRel().value(), new LinkContent(link.getHref())));
        return linkMap;
    }

    record LinkContent(String href) {}
}
