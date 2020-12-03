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

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isReady: false,
            roomId: null,
            roomTitle: null
        };
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
                    >
                        <Grid alignItems="center" xs={3}>
                            <Paper>
                                <OnlineUsers/>
                            </Paper>
                        </Grid>

                        <Grid alignItems="center" xs={6}>
                            <ChatRoom roomId={this.state.roomId} title={this.state.roomTitle}/>
                        </Grid>

                    </Grid>
                </div>
            );
        }
    }

    getSetUserHolder(res) {
        UserContextHolder.userHolder = res;
        this.setState({isReady: true, roomId: res.lastRoomId, roomTitle: res.title});
    }

    getUserContext() {
        UserAction.getUserContext(this.getSetUserHolder.bind(this));
    }
}

export default App;
