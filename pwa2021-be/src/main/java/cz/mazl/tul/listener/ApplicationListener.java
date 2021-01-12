package cz.mazl.tul.listener;

import cz.mazl.tul.service.room.ChatroomService;
import cz.mazl.tul.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class ApplicationListener {

    public static final String PARAM_EMAIL = "email";

    private ChatroomService chatroomService;
    private UserService userService;

    @Autowired
    public ApplicationListener(ChatroomService chatroomService, UserService userService) {
        this.chatroomService = chatroomService;
        this.userService = userService;
    }

    @EventListener(SessionConnectEvent.class)
    public void handleWebsocketConnectListener(SessionConnectEvent event) {
        if (event.getUser() instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) event.getUser();
            String userId = (String) oAuth2AuthenticationToken.getPrincipal().getAttributes().get(PARAM_EMAIL);
            String sessionId = oAuth2AuthenticationToken.getName();
            userService.auditUserLogin(userId, sessionId);
        }

        userService.sendOnlineUserBrowcast();
    }

    @EventListener(SessionDisconnectEvent.class)
    public void handlewebsocketdisconnectlistener(SessionDisconnectEvent event) {
        if (event.getUser() instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) event.getUser();
            String userId = (String) oAuth2AuthenticationToken.getPrincipal().getAttributes().get(PARAM_EMAIL);
            String sessionId = oAuth2AuthenticationToken.getName();
            userService.logoutUserLogin(userId, sessionId);
        }
        userService.sendOnlineUserBrowcast();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        chatroomService.createGlobalChatRoom();
        userService.deleteUserAudits();
    }
}
