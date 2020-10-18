import * as React from "react";
//import moment from 'moment';
import './Message.css';
import Avatar from '@material-ui/core/Avatar';
import Grid from "@material-ui/core/Grid/Grid";
import Tooltip from "@material-ui/core/Tooltip/Tooltip";


class MessageBox extends React.Component {

    render() {
        const message = this.props.message.message;
        const author = this.props.author;
        const messageAuthor = this.props.message.author.name;
        const isMine = author !== messageAuthor;
        const avatar = this.props.message.author.avatar;
        const startsSequence = (this.props.startSequence) ? this.props.startSequence : "start";
        const endsSequence = (this.props.endSequence) ? this.props.endSequence : "start";
        const showTimestamp = false;
        // const friendlyTimestamp = moment(new Date()).format('LLLL');
        const friendlyTimestamp = "" + new Date();
        return (
            <div>

                <div className={[
                    'message',
                    `${isMine ? 'mine' : ''}`,
                    `${startsSequence ? 'start' : ''}`,
                    `${endsSequence ? 'end' : ''}`
                ].join(' ')}>
                    {
                        showTimestamp &&
                        <div className="timestamp">
                            {friendlyTimestamp}
                        </div>
                    }
                    <div className="bubble-container">
                        <Grid container
                              direction="row"
                              alignItems="center">
                            <Grid>
                                <Tooltip title={messageAuthor} aria-label="add">
                                    <Avatar alt="Remy Sharp" src={avatar}>
                                        B
                                    </Avatar>
                                </Tooltip>
                            </Grid>
                            <Grid>
                                <div className="bubble" title={friendlyTimestamp}>
                                    {message}
                                </div>
                            </Grid>
                        </Grid>
                    </div>
                </div>
            </div>
        );
    }
}

export default MessageBox;
