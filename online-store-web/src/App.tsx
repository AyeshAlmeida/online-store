import React from 'react';
import './App.css';
import createBrowserHistory from "history/createBrowserHistory";
import {ConnectedRouter} from "connected-react-router";
import {store} from "./store/store";
import {Provider} from "react-redux";
import {Route, Switch} from "react-router";
import MainUI from "./view/MainUI";

const history = createBrowserHistory({
  basename: "/online-store"
});

function App() {
  return (
      <Provider store={store(history)}>
        <ConnectedRouter history={history}>
          <Switch>
            <Route exact path={'/'} render={() => <MainUI/>}/>
          </Switch>
        </ConnectedRouter>
      </Provider>
  );
}

export default App;
