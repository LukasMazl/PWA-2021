package cz.mazl.tul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private SimpMessagingTemplate messageSender;

    @Autowired
    public UserController(SimpMessagingTemplate messageSender) {
        this.messageSender = messageSender;
    }

    @GetMapping("/oidc-principal")
    public OidcUser getOidcUserPrincipal(
            @AuthenticationPrincipal OidcUser principal) {

        messageSender.convertAndSend("/topics/all", "ahoooj");
        System.out.println(principal.getEmail());
        return principal;
    }
}
