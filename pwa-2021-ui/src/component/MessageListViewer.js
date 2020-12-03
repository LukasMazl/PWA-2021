import * as React from "react";
import MessageBox from "./MessageBox";
import ScrollArea from 'react-scrollbar';

class MessageListViewer extends React.Component {

    constructor(props) {
        super(props);
        this.scrollArea = null;
    }

    onNewMessage() {
        if(this.scrollArea !== null) {
            this.scrollArea.scrollBottom();
        }
    }

    render() {
        const messages = this.props.messages;
        if (messages.length > 0) {
            let messageBoxes = messages.map((value, key) => {
                return (
                        <MessageBox message={value}/>
                )
            });

            return (
                <ScrollArea ref={(e) => {
                    this.scrollArea = e;
                }}
                            speed={0.8}
                            minScrollSize={400}
                            style={{height: "500px"}}
                >
                    <div>
                        {messageBoxes}
                    </div>
                </ScrollArea>
            );
        } else {
            return (<div/>);
        }

    }
}

export default MessageListViewer;
