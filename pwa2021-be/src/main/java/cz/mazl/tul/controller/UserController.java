package cz.mazl.tul.controller;

import cz.mazl.tul.bussines.dto.UserDTO;
import cz.mazl.tul.dto.UserDataDto;
import cz.mazl.tul.dto.UserDto;
import cz.mazl.tul.service.message.MessageService;
import cz.mazl.tul.service.user.UserService;
import org.dom4j.util.UserDataDocumentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    private SimpMessagingTemplate messageSender;
    private UserService userService;
    private MessageService messageService;

    @Autowired
    public UserController(SimpMessagingTemplate messageSender, UserService userService, MessageService messageService) {
        this.messageSender = messageSender;
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/user/context")
    public UserDto getUserContext(
            @AuthenticationPrincipal OidcUser principal) {
        UserDto userDto = new UserDto();
        userDto.setFullName(principal.getFullName());
        userDto.setUserId(principal.getEmail());

        userService.createUser(prepareDTO(principal));
        String lastRoomId = messageService.getLastRoomId(principal.getEmail());
        userDto.setLastRoomId(lastRoomId);

        Set<UserDataDto> avaibleUsers = userService.avaibleUsers();
        userDto.setUserDataDtoSet(avaibleUsers);

        return userDto;
    }

    private UserDTO prepareDTO(OidcUser principal) {
        UserDTO user = new UserDTO();
        user.setUserId(principal.getEmail());
        user.setAvatar(principal.getUserInfo().getPicture());
        user.setUserName(principal.getUserInfo().getFullName());
        return user;
    }

}
