import React, { Component } from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";

import { BrowserRouter, Route } from "react-router-dom";
import AddProject from "./Project/AddProject";

class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <div className="App">
          <Header />
          <Route exact path="/dashboard" component={Dashboard} />
          <Route exact path="/addProject" component={AddProject} />
        </div>
      </BrowserRouter>
    );
  }
}

export default App;
