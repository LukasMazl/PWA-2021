import ApiCaller from "../api/ApiCaller";

class UserContextHolder {
    static userHolder = null;
    static onlineUsersHolder = null;

    setUserContext(user) {
        UserContextHolder.userHolder = user;
    }

    getUserContext() {
        return UserContextHolder.userHolder;
    }

    setOnlineUsers(onlineUsers) {
        UserContextHolder.onlineUsersHolder = onlineUsers;
    }

    getOnlineUsers() {
        return UserContextHolder.userHolder;
    }
}

export default UserContextHolder;
