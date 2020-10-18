import * as React from "react";
import MessageBox from "./MessageBox";
import GridList from "@material-ui/core/GridList/GridList";
import GridListTile from "@material-ui/core/GridListTile/GridListTile";

class MessageListViewer extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        const author = "lukas.mazl";
        const messages = this.props.messages;
        console.log(messages);
        if (messages.length > 0) {
            let messageBoxes = messages.map((value, key) => {
                return (
                        <MessageBox message={value} author={author}/>
                )
            });

            return (
                <GridList style={{height: "500px"}} cols={2} spacing={1}>
                    {messageBoxes}
                </GridList>
            );
        } else {
            return (<div/>);
        }

    }
}

export default MessageListViewer;
