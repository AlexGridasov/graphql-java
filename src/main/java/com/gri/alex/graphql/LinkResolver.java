package com.gri.alex.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.gri.alex.model.Link;
import com.gri.alex.model.User;
import com.gri.alex.repository.UserRepository;

/**
 * User: Alex
 * Date: 11/22/17
 */
public class LinkResolver implements GraphQLResolver<Link> {

    private final UserRepository userRepository;

    public LinkResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User postedBy(Link link) {
        if (link.getUserId() == null) {
            return null;
        }
        return userRepository.findById(link.getUserId());
    }
}
