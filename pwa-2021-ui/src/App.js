import React from 'react';
import './App.css';
import Grid from "@material-ui/core/Grid/Grid";
import Paper from "@material-ui/core/Paper/Paper";
import ChatRoom from "./page/chat/ChatRoom";

function App() {
    return (
        <div>
            <Grid
                container
                direction="row"
                justify="space-between"
                alignItems="center"
            >
                <Grid xs={3}>
                    <Paper>
                        Menu
                    </Paper>
                </Grid>
                <Grid xs={5}>
                        <ChatRoom roomId={"testRoom"}/>
                </Grid>

                <Grid xs={3}>
                    <Paper>
                        Uzivatel
                    </Paper>
                </Grid>
            </Grid>
        </div>
    );
}

export default App;
