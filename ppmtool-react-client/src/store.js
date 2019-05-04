import { createStore, applyMiddleware, compose } from "redux";
import thunk from "redux-thunk";
import rootReducer from "./reducers";

const initalState = {};
const middleware = [thunk];

let store;

const reactReduxDevToolComponent =
  window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__();

if (
  window.navigator.userAgent.includes("Chrome") &&
  reactReduxDevToolComponent
) {
  store = createStore(
    rootReducer,
    initalState,
    compose(
      applyMiddleware(...middleware),
      reactReduxDevToolComponent
    )
  );
} else {
  store = createStore(
    rootReducer,
    initalState,
    compose(applyMiddleware(...middleware))
  );
}

export default store;
