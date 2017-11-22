package com.gri.alex.graphql;

import com.gri.alex.model.User;
import graphql.servlet.GraphQLContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * User: Alex
 * Date: 11/22/17
 */
public class AuthContext extends GraphQLContext {

    private final User user;

    public AuthContext(User user, Optional<HttpServletRequest> request,
                       Optional<HttpServletResponse> response) {
        super(request, response);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
