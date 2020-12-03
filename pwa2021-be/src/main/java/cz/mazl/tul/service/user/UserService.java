package cz.mazl.tul.service.user;

import cz.mazl.tul.bussines.dto.UserDTO;

public interface UserService {

    void createUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    void auditUserLogin(String userId, String sessionId);

    void logoutUserLogin(String userId, String sessionId);

    void sendOnlineUserBrowcast();
}
