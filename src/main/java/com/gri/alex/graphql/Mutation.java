package com.gri.alex.graphql;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.gri.alex.model.Link;
import com.gri.alex.repository.LinkRepository;

/**
 * User: Alex
 * Date: 11/19/17
 */
public class Mutation implements GraphQLRootResolver {

    private final LinkRepository linkRepository;

    public Mutation(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link createLink(String url, String description) {
        Link newLink = new Link(url, description);
        linkRepository.saveLink(newLink);
        return newLink;
    }
}
