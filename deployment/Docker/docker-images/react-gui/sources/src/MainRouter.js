import React, {Component} from 'react'
import {Route, Switch,  Redirect} from 'react-router-dom'

import Header from './components/header/Header'
import Home from './components/home/Home'
import Login from './components/login/Login'

import Welcome from './components/welcome/Welcome'

const request = require ('./resources/request');



//inspired from https://reacttraining.com/react-router/web/example/auth-workflow
const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={props => (
    request.auth.isAuthenticated() ? (
      <Component {...props}/>
    ) : (
      <Redirect to={{
        pathname: '/simple-app/login',
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
        <Route exact path="/" component={Home}/>
        <Route path="/simple-app/login" component={Login}/>
        <PrivateRoute path="/simple-app/user" component={Welcome}/>
        <Route path="*" render={ () => <Redirect to="/simple-app/login"/>}/>
      </Switch>

    </div>)
  }
}

export default MainRouter