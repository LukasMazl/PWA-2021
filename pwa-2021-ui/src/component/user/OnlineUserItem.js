import {Component} from "react";
import ListItemAvatar from "@material-ui/core/ListItemAvatar/ListItemAvatar";
import Avatar from "@material-ui/core/Avatar/Avatar";
import ListItemText from "@material-ui/core/ListItemText/ListItemText";
import React from "react";
import Typography from "@material-ui/core/Typography/Typography";
import ListItem from "@material-ui/core/ListItem/ListItem";
import Button from "@material-ui/core/Button/Button";

class OnlineUserItem extends Component {

    render() {
        return (
            <ListItem alignItems="flex-start">
                <ListItemAvatar>
                    <Avatar alt={this.props.user.userName} src={this.props.user.avatar} />
                </ListItemAvatar>
                <ListItemText
                    primary={this.props.user.userName}
                    secondary={
                        <React.Fragment>
                            <Typography
                                component="span"
                                variant="body2"
                                color="textPrimary"
                            >
                                <Button variant="contained" color="primary" href="#contained-buttons">
                                    Link
                                </Button>
                            </Typography>
                        </React.Fragment>
                    }
                />
            </ListItem>);
    }
}

export default OnlineUserItem;
