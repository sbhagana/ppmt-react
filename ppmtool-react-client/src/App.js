import React, { Component } from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";

import { BrowserRouter, Route } from "react-router-dom";
import AddProject from "./Project/AddProject";
import { Provider } from "react-redux";
import store from "./store";

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <BrowserRouter>
          <div className="App">
            <Header />
            <Route exact path="/dashboard" component={Dashboard} />
            <Route exact path="/addProject" component={AddProject} />
          </div>
        </BrowserRouter>
      </Provider>
    );
  }
}

export default App;
