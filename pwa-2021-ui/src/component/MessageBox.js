import * as React from "react";
//import moment from 'moment';
import './Message.css';
import Avatar from '@material-ui/core/Avatar';
import UserContextHolder from "../holder/UserContextHolder";


class MessageBox extends React.Component {

    render() {
        const message = this.props.message.message;
        const messageAuthor = this.props.message.author.authorFullName;
        const isMine = this.props.message.author.userId === UserContextHolder.userHolder.userId;
        const avatar = this.props.message.author.userAvatar;
        const startsSequence = (this.props.startSequence) ? this.props.startSequence : "start";
        const endsSequence = (this.props.endSequence) ? this.props.endSequence : "end";
        const showTimestamp = false;
        const friendlyTimestamp = "" + new Date();

        let messageRowForTooltip = this.getMessageRowForTooltip(messageAuthor, avatar, message, isMine, friendlyTimestamp);
        return (
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
                    {messageRowForTooltip}
                </div>
        );
    }

    getMessageRowForTooltip(messageAuthor, avatar, message, mine, friendlyTimestamp) {
        if (!mine) {
            return (
                <div className="bubble-container">

                        <Avatar src={avatar} style={{"margin": "auto 0 auto 0"}}>
                            B
                        </Avatar>
                        <div className="bubble" title={friendlyTimestamp}>
                            {message}
                        </div>
                </div>
            );
        } else {
            return (<div className="bubble-container">

                    <div className="bubble">
                        {message}
                    </div>
                    <Avatar src={avatar} style={{"margin": "auto 0 auto 0"}}>
                        B
                    </Avatar>
            </div>);
        }
    }
}

export default MessageBox;
