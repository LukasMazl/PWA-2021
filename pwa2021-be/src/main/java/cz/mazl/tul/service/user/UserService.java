package cz.mazl.tul.service.user;

import cz.mazl.tul.bussines.dto.UserDTO;
import cz.mazl.tul.dto.UserDataDto;
import java.util.Set;

public interface UserService {

    void createUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    void auditUserLogin(String userId, String sessionId);

    void logoutUserLogin(String userId, String sessionId);

    void sendOnlineUserBrowcast();

    void removeOldAudit();

    Set<UserDataDto> avaibleUsers();

    void deleteUserAudits();
}
