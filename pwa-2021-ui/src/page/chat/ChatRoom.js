import React from "react";
import SockJsClient from "../../sockjs/client"
import Button from "@material-ui/core/Button/Button";
import TextField from "@material-ui/core/TextField/TextField";
import Grid from "@material-ui/core/Grid/Grid";
import Card from "@material-ui/core/Card/Card";
import CardHeader from "@material-ui/core/CardHeader/CardHeader";
import CardContent from "@material-ui/core/CardContent/CardContent";
import Typography from "@material-ui/core/Typography/Typography";
import CardActions from "@material-ui/core/CardActions/CardActions";
import MessageListViewer from "../../component/MessageListViewer";
import GlobalConstant from "../../config/GlobalConstant";
import UserAction from "../../action/UserAction"

class ChatRoom extends React.Component {

    constructor(props) {
        super(props);
        console.log(props);
        this.state = {
            roomId: props.roomId,
            roomTittle: props.title,
            isLoaded: props.messages !== undefined,
            messages: props.messages
        };
        this.clientRef = null;
        this.messageList = null;
        this.messageBox = null;
    }

    renderChat(roomId) {
        this.getLastDataForRoom(roomId);
        return (<div/>);
    }

    changeRoom(roomId, title, messages) {
        this.getLastDataForRoom(roomId, title, messages);
    }

    getLastDataForRoom(roomId, title, messages) {
        if (messages === null || messages === undefined) {
            UserAction.getLastDataForRoom(roomId, this.onGetMessage.bind(this));
        } else {
            this.setState({
                messages: messages,
                isLoaded: true,
                roomId: roomId,
                roomTittle: title
            });
            this.forceUpdate();
        }
    }

    sendMessage(roomId, value, onRes) {
        UserAction.sendMessage(roomId, value, onRes);
    }

    renderTextForm(roomId) {
        console.log(roomId);
        return (
            <Grid container
                  direction="row"
                  justify="space-between"
                  alignItems="center">
                <Grid xs={8}>
                    <TextField
                        id="message_text"
                        label="Message"
                        inputRef={(e) => {
                            this.messageBox = e
                        }}
                        multiline
                        defaultValue=""
                        variant="outlined"
                        fullWidth
                        style={{margin: "5px"}}
                    />
                </Grid>
                <Grid xs={3}>
                    <Button variant="contained" style={{width: "100%", height: "100%", marginRight: "10px"}}
                            color={"primary"}
                            onClick={(e) => {
                                const value = this.messageBox.value;
                                this.sendMessage(roomId, value, () => {
                                    this.messageBox.value = "";
                                });
                            }}
                    >
                        Send
                    </Button>
                </Grid>
            </Grid>
        );
    }

    onMessage(message) {
        const messageList = this.state.messages;
        messageList.unshift(message);
        this.setState({messages: messageList});
    }

    render() {
        const roomId = this.state.roomId;
        let jsSock = this.prepareJsSock(roomId);
        console.log(roomId);
        if (!this.state.isLoaded) {
            return this.renderChat(roomId)
        } else {
            return (
                <Card style={{marginTop: "20px"}}>
                    <CardHeader title={
                        <Typography variant="body2" color="textSecondary" component="p">
                            {this.state.roomTittle}
                        </Typography>
                    }/>
                    <CardContent>
                        <MessageListViewer messages={
                            this.state.messages
                        }
                                           ref={(e) => this.messageList = e}/>
                    </CardContent>
                    <CardActions disableSpacing>
                        {jsSock}
                        {this.renderTextForm(roomId)}
                    </CardActions>

                </Card>
            );
        }
    }

    prepareJsSock(roomId) {
        return (<SockJsClient url={GlobalConstant.FULL_WEB_SOCKET_URL} topics={['/topics/room/' + roomId]}
                              onMessage={this.onMessage.bind(this)}
                              autoReconnect={true}
                              onConnect={(e) => {
                                  console.log(e);
                                }
                              }
                              onDisconnect={console.log("Disconnected!")}
                              ref={(client) => {
                                  this.clientRef = client
                              }}
                              debug={true}/>);
    }

    onGetMessage(res) {
        const messages = res.messages;
        this.setState({
            messages: messages,
            isLoaded: true,
            roomTittle: res.roomTitle,
            roomId: res.roomId
        });
    }
}

export default ChatRoom;
