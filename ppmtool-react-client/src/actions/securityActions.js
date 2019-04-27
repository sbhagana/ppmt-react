import axios from "axios";
import { GET_ERRORS, SET_CURRENT_USER } from "./types";
import setJWTToken from "../securityUnits/setJWTToken";
import jwt_decode from "jwt-decode";
export const createNewUser = (newUser, history) => async dispatch => {
  try {
    await axios.post("/api/users/register", newUser);
    history.push("/login");
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const login = LoginRequest => async dispatch => {
  try {
    // Posts => Login Request
    const res = await axios.post("/api/users/login", LoginRequest);

    // Extract token from res.data
    const { token } = res.data;

    // Store token in localStorage
    localStorage.setItem("jwtToken", token);

    //Set Token in headers ***
    setJWTToken(token);

    // Decode token in React
    const decoded = jwt_decode(token);
    // Dispatch to our security reducer
    dispatch({
      type: SET_CURRENT_USER,
      payload: decoded
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const logout = () => dispatch => {
  localStorage.removeItem("jwtToken");
  setJWTToken(false);
  dispatch({
    type: SET_CURRENT_USER,
    payload: {}
  });
};
