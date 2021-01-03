import {Component} from "react";
import SockJsClient from "react-stomp";
import GlobalConstant from "../config/GlobalConstant";
import React from "react";
import List from "@material-ui/core/List/List";
import ListItem from "@material-ui/core/ListItem/ListItem";
import ListItemAvatar from "@material-ui/core/ListItemAvatar/ListItemAvatar";
import Avatar from "@material-ui/core/Avatar/Avatar";
import ListItemText from "@material-ui/core/ListItemText/ListItemText";
import Typography from "@material-ui/core/Typography/Typography";
import Divider from "@material-ui/core/Divider/Divider";
import OnlineUserItem from "../component/user/OnlineUserItem";
import UserContextHolder from "../holder/UserContextHolder";

class OnlineUsers extends Component {

    constructor(props) {
        super(props);
        this.state = {users: null}
    }

    onMessage(data) {
        UserContextHolder.onlineUsersHolder = data;
        this.setState({users: data});
    }

    render() {
        let onlineUserItems = this.getOnlineUserItems();
        return (
            <div>
                <SockJsClient url={GlobalConstant.FULL_WEB_SOCKET_URL} topics={['/topics/user/online']}
                              onMessage={this.onMessage.bind(this)}
                              ref={(client) => {
                                  this.clientRef = client
                              }}/>
                <Typography variant="h4" component="h4">
                   Online users
                </Typography>
                <Divider/>
                <List>
                    {onlineUserItems}
                </List>
                <Divider/>
                <Divider/>
                <Typography variant="h4" component="h4">
                    My rooms
                </Typography>
                <Divider/>
                <List>
                </List>
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
            console.log(value.userId);
            if(value.userId !== UserContextHolder.onlineUsersHolder) {
                return (<OnlineUserItem user={value} onClick={this.onSendButtonClicked.bind(this)}/>);
            } else {
                return(<div/>);
            }
        });
        return result;
    }
}

export default OnlineUsers;
