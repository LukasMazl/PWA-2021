import React from "react";
import SockJsClient from 'react-stomp';
import Button from "@material-ui/core/Button/Button";
import TextField from "@material-ui/core/TextField/TextField";
import Grid from "@material-ui/core/Grid/Grid";
import Skeleton from '@material-ui/lab/Skeleton';
import Divider from "@material-ui/core/Divider/Divider";
import Card from "@material-ui/core/Card/Card";
import CardHeader from "@material-ui/core/CardHeader/CardHeader";
import CardContent from "@material-ui/core/CardContent/CardContent";
import Typography from "@material-ui/core/Typography/Typography";
import CardActions from "@material-ui/core/CardActions/CardActions";
import MessageListViewer from "../../component/MessageListViewer";

class ChatRoom extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            isLoaded: false,
            messages: []
        };
        this.clientRef = null;
        this.messageList = null;
    }

    renderChat(roomId) {
        return (<div/>);
    }

    renderTextForm(roomId) {
        return (
            <Grid container
                  direction="row"
                  justify="space-between"
                  alignItems="center">
                <Grid xs={8}>
                    <TextField
                        id="message_text"
                        label="Message"
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
                                this.clientRef.sendMessage("/app/send/" + roomId, "ahooj");
                            }}
                    >
                        Send
                    </Button>
                </Grid>
            </Grid>
        );
    }

    onMessage(message) {
        console.log(message);
        const messageList = this.state.messages;
        messageList.push(message);
        this.setState({messages: messageList});
    }

    render() {
        const roomId = this.props.roomId;
        if (this.state.isLoaded) {
            return this.renderChat(roomId)
        } else {
            return (
                <Card style={{marginTop: "20px"}}>
                    <CardHeader title={<Typography variant="body2" color="textSecondary" component="p">
                        {roomId}
                    </Typography>}/>
                    <CardContent>
                        <MessageListViewer messages={
                            this.state.messages
                        }
                                           ref={(e) => this.messageList = e}/>
                    </CardContent>
                    <CardActions disableSpacing>
                        <SockJsClient url='http://pwa-2021.herokuapp.com/ws' topics={['/topics/room/' + roomId]}
                                      onMessage={this.onMessage.bind(this)}
                                      ref={(client) => {
                                          this.clientRef = client
                                      }}/>
                        {this.renderTextForm(roomId)}
                    </CardActions>

                </Card>
            );
        }
    }
}

export default ChatRoom;
