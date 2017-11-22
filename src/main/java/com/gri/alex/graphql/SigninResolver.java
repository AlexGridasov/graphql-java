package com.gri.alex.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.gri.alex.model.SigninPayload;
import com.gri.alex.model.User;

/**
 * User: Alex
 * Date: 11/22/17
 */
public class SigninResolver implements GraphQLResolver<SigninPayload> {

    public User user(SigninPayload payload) {
        return payload.getUser();
    }
}
