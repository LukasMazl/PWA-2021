import {Component} from "react";
import SockJsClient from "react-stomp";
import GlobalConstant from "../config/GlobalConstant";
import React from "react";
import List from "@material-ui/core/List/List";
import Typography from "@material-ui/core/Typography/Typography";
import Divider from "@material-ui/core/Divider/Divider";
import OnlineUserItem from "../component/user/OnlineUserItem";
import UserContextHolder from "../holder/UserContextHolder";

class OnlineUsers extends Component {

    constructor(props) {
        super(props);
        console.log(props);
        this.state = {users: props.users}
    }

    onMessage(data) {
        UserContextHolder.onlineUsersHolder = data;
        let onlineUser = this.prepareUsers(data);
        this.setState({users: onlineUser});
    }

    prepareUsers(onlineUser) {
        let usersClone = this.state.users;
        let users = [];
        for(let userIndex in usersClone) {
            users.push(
                {
                    userId: usersClone[userIndex].userId,
                    isOnline: false,
                    userName: usersClone[userIndex].userName,
                    avatar: usersClone[userIndex].avatar
                }
            );
        }
        if(onlineUser !== null && onlineUser !== undefined) {
            for(let userIndex in onlineUser) {
                let user = onlineUser[userIndex];
                let foundUserIndex = this.findUserIndex(user.userId, users);

                if(foundUserIndex == -1) {
                    user.isOnline = true;
                    users.push({
                        userId: user.userId,
                        isOnline: true,
                        userName: user.userName,
                        avatar: user.avatar
                    }
                        );
                } else {
                    let inStateUser = users[foundUserIndex];
                    inStateUser.isOnline = true;
                }
            }
            return users;
        }
        return users;
    }



    findUserIndex(userId, list) {
        if(list === null || list === undefined)
            return -1;
        for(let user in list) {
            if(list[user].userId === userId) {
                return user;
            }
        }
        return -1;
    }

    render() {
        let onlineUserItems = this.getOnlineUserItems();
        return (
            <div>
                <SockJsClient url={GlobalConstant.FULL_WEB_SOCKET_URL} topics={['/topics/user/online']}
                              onMessage={this.onMessage.bind(this)}
                              ref={(client) => {
                                  this.clientRef = client
                              }}
                              />
                <Typography variant="h4" component="h4" style={{"textAlign":"center"}}>
                   Users
                </Typography>
                <Divider/>
                <List>
                    {onlineUserItems}
                </List>
                <Divider/>

            </div>
        );
    }

    onSendButtonClicked(userId) {
        this.props.onSendButtomClicked(userId);
    }

    getOnlineUserItems() {
        if(this.state.users === null || this.state.users === undefined) {
            if(UserContextHolder.onlineUsersHolder !== null) {
                this.setState({users: UserContextHolder.onlineUsersHolder});
            }
            return (<div/>);
        }
        let users = this.state.users;

        let result = users.map((value, index) => {
            if(value.userId !== UserContextHolder.userHolder.userId) {
                return (<OnlineUserItem user={value} onClick={this.onSendButtonClicked.bind(this)} isOnline={value.isOnline}/>);
            } else {
                return(<div/>);
            }
        });
        return result;
    }
}

export default OnlineUsers;
