package uk.fergcb.sakila.hateoas;

import java.util.Map;

public abstract class HateoasCollection<T extends HateoasResource> extends HateoasResource {

    private final String name;
    private final Iterable<T> resources;

    protected HateoasCollection (String name, Iterable<T> resources) {
        this.name = name;
        this.resources = resources;
    }

    public Map<String, Iterable<T>> get_embedded() {
        return Map.of(name, resources);
    }

}
