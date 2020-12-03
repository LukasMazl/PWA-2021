import ApiCaller from "../api/ApiCaller";

class UserAction {

   static getLastDataForRoom(roomId, onGetMessage) {
        ApiCaller.getCall(
            ApiCaller.GET_LAST_MESSAGES_FROM_ROOM,
            {
                "roomID": roomId
            },
            (res) => {
                onGetMessage(res);
            },
            (res) => {
                console.error(res);
            });
    }

    static sendMessage(roomId, value, onRecive) {
        ApiCaller.call(ApiCaller.SEND_MESSAGE_TO_ROOM,
            "POST",
            {
                "roomId": roomId,
                "message": value
            },
            (resp) => {
                onRecive();
            }
        );
    }

    static getUserContext(onRecive) {
        ApiCaller.getCall(ApiCaller.GET_USER_CONTEXT,
            null,
            (resp) => {
                onRecive(resp);
            }
        );
    }
}

export default UserAction;
