package cz.mazl.tul.service.user;

import cz.mazl.tul.bussines.dto.UserDTO;
import cz.mazl.tul.dto.UserDto;

import java.util.List;

public interface UserService {

    void createUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    void auditUserLogin(String userId, String sessionId);

    void logoutUserLogin(String userId, String sessionId);

    void sendOnlineUserBrowcast();

    void removeOldAudit();

    List<UserDto> avaibleUsers();
}
