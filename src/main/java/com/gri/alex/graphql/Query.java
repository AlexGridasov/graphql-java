package com.gri.alex.graphql;

import com.gri.alex.model.Link;
import com.gri.alex.model.LinkFilter;
import com.gri.alex.repository.LinkRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;

import java.util.List;

/**
 * User: Alex
 * Date: 11/16/17
 */
public class Query {

    private final LinkRepository linkRepository;

    public Query(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @GraphQLQuery
    public List<Link> allLinks(LinkFilter filter,
                               @GraphQLArgument(name = "skip", defaultValue = "0") Number skip,
                               @GraphQLArgument(name = "first", defaultValue = "0") Number first) {

        return linkRepository.getAllLinks(filter, skip.intValue(), first.intValue());
    }
}
