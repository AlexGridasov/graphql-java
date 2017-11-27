package com.gri.alex.graphql;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.gri.alex.model.Link;
import com.gri.alex.model.LinkFilter;
import com.gri.alex.repository.LinkRepository;

import java.util.List;

/**
 * User: Alex
 * Date: 11/16/17
 */
public class Query implements GraphQLRootResolver {

    private final LinkRepository linkRepository;

    public Query(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public List<Link> allLinks(LinkFilter filter) {
        return linkRepository.getAllLinks(filter);
    }
}
