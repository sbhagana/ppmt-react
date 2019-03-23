import React, { Component } from "react";
import ProjectItem from "./../Project/ProjectItem";
class Dashboard extends Component {
  render() {
    return (
      <div>
        <h2 className="alert alert-warning">Welcome to Dashboard</h2>
        <ProjectItem />
        <ProjectItem />
        <ProjectItem />
      </div>
    );
  }
}

export default Dashboard;
