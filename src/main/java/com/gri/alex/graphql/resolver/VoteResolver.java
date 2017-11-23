package com.gri.alex.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.gri.alex.model.Link;
import com.gri.alex.model.User;
import com.gri.alex.model.Vote;
import com.gri.alex.repository.LinkRepository;
import com.gri.alex.repository.UserRepository;

/**
 * User: Alex
 * Date: 11/23/17
 */
public class VoteResolver implements GraphQLResolver<Vote> {

    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    public VoteResolver(LinkRepository linkRepository, UserRepository userRepository) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
    }

    public User user(Vote vote) {
        return userRepository.findById(vote.getUserId());
    }

    public Link link(Vote vote) {
        return linkRepository.findById(vote.getLinkId());
    }
}
