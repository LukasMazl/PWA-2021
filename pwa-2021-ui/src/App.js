import React, {Component} from 'react';
import './App.css';
import Grid from "@material-ui/core/Grid/Grid";
import Paper from "@material-ui/core/Paper/Paper";
import ChatRoom from "./page/chat/ChatRoom";
import UserContextHolder from "./holder/UserContextHolder";
import UserAction from "./action/UserAction";
import AppBar from "@material-ui/core/AppBar/AppBar";
import Toolbar from "@material-ui/core/Toolbar/Toolbar";
import IconButton from "@material-ui/core/IconButton/IconButton";
import Typography from "@material-ui/core/Typography/Typography";
import Button from "@material-ui/core/Button/Button";
import OnlineUsers from "./page/OnlineUsers";
import ApiCaller from "./api/ApiCaller";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isReady: false,
            roomId: null,
            roomTitle: undefined,
            messages: undefined
        };
        this.chatRoom = null;
    }

    shouldComponentUpdate(nextProps, nextState, nextContext) {
        return true;
    }

    render() {
        if (!this.state.isReady) {
            this.getUserContext();
            return (<div/>)
        } else {
            return (
                <div>
                    <AppBar position="static" color={"secondary"}>
                        <Toolbar>
                            <Typography variant="h6">
                                Chatting room
                            </Typography>
                        </Toolbar>
                    </AppBar>
                    <Grid
                        alignItems="center"
                        container
                        direction="row"
                        justify="space-between"
                        style={{"padding": "20px"}}
                    >
                        <Grid alignItems="center" xs={3} style={{"height": "auto"}}>
                            <Paper>
                                <OnlineUsers onSendButtomClicked={this.prepareRoomForUser.bind(this)} users={UserContextHolder.userHolder.userDataDtoSet}/>
                            </Paper>
                        </Grid>

                        <Grid alignItems="center" xs={8}>
                            <ChatRoom roomId={this.state.roomId} title={this.state.roomTitle} ref={(e) => {this.chatRoom = e}} messages={this.state.messages}/>
                        </Grid>

                    </Grid>
                </div>
            );
        }
    }

    prepareRoomForUser(userId) {
        ApiCaller.call(ApiCaller.PREPARE_ROOM, "POST", {"userId": userId},
            (res) => {
                this.setState(
                    {
                        roomId: res.roomId,
                        roomTitle: res.roomTitle,
                        messages: res.historicalMessagesDTO.messages
                    }, () => {
                    this.chatRoom.changeRoom(this.state.roomId, this.state.roomTitle, this.state.messages)
                });
            });
    }

    getSetUserHolder(res) {
        UserContextHolder.userHolder = res;
        this.setState({isReady: true, roomId: res.lastRoomId, roomTitle: res.roomTitle});
    }

    getUserContext() {
        UserAction.getUserContext(this.getSetUserHolder.bind(this));
    }
}

export default App;
