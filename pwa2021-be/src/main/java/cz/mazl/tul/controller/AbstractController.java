package cz.mazl.tul.controller;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public abstract class AbstractController {

    public static String getUserId(OidcUser oidcUser) {
        return oidcUser.getEmail();
    }
}
