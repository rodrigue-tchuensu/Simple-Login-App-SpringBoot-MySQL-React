import React from 'react'
import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'
import IconButton from '@material-ui/core/IconButton'
import HomeIcon from '@material-ui/icons/Home'
import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button'
import {Link, withRouter} from 'react-router-dom'




const request = require ('../../resources/request');
const APP_CONSTANTS = require ('../../ApplicationConstants')
/*
const styles = {
  menusButton: {
    marginLeft: -12,
    marginRight: 20
  }
}
*/

const Header = withRouter( ({history}) => (
  <AppBar position="static">
    <Toolbar>
      {
        (<span>
          <Link to={APP_CONSTANTS.HOME_URL}>
            <IconButton aria-label="Home" style={{color: '#ffffff'}}>
              <HomeIcon/>
            </IconButton>
          </Link>
        </span>) 
      }
      {
        !request.auth.isAuthenticated() && (<span>
          <Link to={APP_CONSTANTS.LOGIN_URL}>
            <Button style={{color: '#ffffff'}}>
                Login 
            </Button>
          </Link>
        </span>)
      }
      {
        !request.auth.isAuthenticated() && (<span>
          <Link to={APP_CONSTANTS.SIGNUP_URL}>
            <Button style={{color: '#ffffff'}}>
                Sign-Up 
            </Button>
          </Link>
        </span>)
      }
      {
        request.auth.isAuthenticated() && (<span>
          <Button style={{color: '#ffffff'}}
                  onClick = { () => {
                    request.auth.logout(() => history.push(APP_CONSTANTS.HOME_URL))
                  }}
          >
              Logout
          </Button>
        </span>)
      }
    </Toolbar>
  </AppBar>
))

export default (Header)

