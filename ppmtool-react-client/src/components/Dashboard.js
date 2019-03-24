import React, { Component } from "react";
import ProjectItem from "./../Project/ProjectItem";
import CreateProjectButton from "./../Project/CreateProjectButton";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { getProjects } from "./../actions/ProjectActions.js";

class Dashboard extends Component {
  componentDidMount() {
    this.props.getProjects();
  }
  componentWillReceiveProps(nextState) {
    this.setState({ projects: nextState.projects });
  }
  render() {
    const projects = this.props.project.projects;
    console.log("projects", projects);
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Projects</h1>
              <br />

              {/* Add PRoject */}
              <CreateProjectButton />

              <br />
              <hr />

              {projects.map(project => (
                <ProjectItem key={project.id} project={project} />
              ))}
              {/* <!-- End of Project Item Component --> */}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Dashboard.propTypes = {
  getProjects: PropTypes.func.isRequired,
  projects: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  project: state.project
});
export default connect(
  mapStateToProps,
  { getProjects }
)(Dashboard);
