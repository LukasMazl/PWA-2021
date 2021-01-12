import {Component} from "react";
import ListItemAvatar from "@material-ui/core/ListItemAvatar/ListItemAvatar";
import Avatar from "@material-ui/core/Avatar/Avatar";
import ListItemText from "@material-ui/core/ListItemText/ListItemText";
import React from "react";
import Badge from '@material-ui/core/Badge';
import Typography from "@material-ui/core/Typography/Typography";
import ListItem from "@material-ui/core/ListItem/ListItem";
import Button from "@material-ui/core/Button/Button";

import {withStyles } from '@material-ui/core/styles';

const StyledBadge = withStyles((theme) => ({
    badge: {
        backgroundColor: '#44b700',
        color: '#44b700',
        boxShadow: `0 0 0 2px ${theme.palette.background.paper}`,
        '&::after': {
            position: 'absolute',
            top: 0,
            left: 0,
            width: '100%',
            height: '100%',
            borderRadius: '50%',
            animation: '$ripple 1.2s infinite ease-in-out',
            border: '1px solid currentColor',
            content: '""',
        },
    },
    '@keyframes ripple': {
        '0%': {
            transform: 'scale(.8)',
            opacity: 1,
        },
        '100%': {
            transform: 'scale(2.4)',
            opacity: 0,
        },
    },
}))(Badge);

class OnlineUserItem extends Component {

    render() {
        let prepareAvatar = this.prepareAvatar(this.props.isOnline);

        return (
            <ListItem alignItems="flex-start">
                <ListItemAvatar>
                    {prepareAvatar}
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
                                <Button variant="contained" color="primary" onClick={
                                    (e) =>
                                    {this.props.onClick(this.props.user.userId)}
                                }>
                                    Send Message
                                </Button>
                            </Typography>
                        </React.Fragment>
                    }
                />
            </ListItem>);
    }

    prepareAvatar(isOnline) {
        if(isOnline) {
            return (
                <StyledBadge
                    overlap="circle"
                    anchorOrigin={{
                        vertical: 'bottom',
                        horizontal: 'right',
                    }}
                    variant="dot"
                >
                    <Avatar alt={this.props.user.userName} src={this.props.user.avatar} />
                </StyledBadge>
            );
        } else {
            return (
                <Avatar alt={this.props.user.userName} src={this.props.user.avatar} />
            );
        }
    }
}

export default OnlineUserItem;
