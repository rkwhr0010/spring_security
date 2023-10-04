package com.springsecurity.services;

import javax.annotation.security.RolesAllowed;

import org.springframework.stereotype.Service;

@Service
public class NameService {

    @RolesAllowed("ROLE_ADMIN")
//    @Secured("ROLE_ADMIN")
    public String getName() {
        return "Fantastico";
    }
}
