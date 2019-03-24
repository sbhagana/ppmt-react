import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import projectsReducer from "./getProjectsReducer";

export default combineReducers({
  errors: errorReducer,
  project: projectsReducer
});
