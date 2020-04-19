import React, {Component} from 'react'
import {Route, Switch,  Redirect} from 'react-router-dom'

import Header from './components/header/Header'
import Home from './components/home/Home'
import Login from './components/login/Login'
import Welcome from './components/welcome/Welcome'
import SignUp from './components/signUp/SignUp'

const request = require ('./resources/request');

const APP_CONSTANTS = require('./ApplicationConstants');



//inspired from https://reacttraining.com/react-router/web/example/auth-workflow
const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={props => (
    request.auth.isAuthenticated() ? (
      <Component {...props}/>
    ) : (
      <Redirect to={{
        pathname: APP_CONSTANTS.LOGIN_URL,
        state: { from: props.location }
      }}/>
    )
  )}/>
)

class MainRouter extends Component {
  render() {
    return (<div>

      <Header/>      

      <Switch>
        <Route exact path='/' component={Home}/>
        <Route exact path={APP_CONSTANTS.HOME_URL} component={Home}/>
        <Route path={APP_CONSTANTS.LOGIN_URL} component={Login}/>
        <Route path={APP_CONSTANTS.SIGNUP_URL} component={SignUp}/>
        <PrivateRoute path={APP_CONSTANTS.WELCOME_URL} component={Welcome}/>
        <Route path="*" render={ () => <Redirect to={APP_CONSTANTS.LOGIN_URL}/>}/>
      </Switch>

    </div>)
  }
}

export default MainRouter