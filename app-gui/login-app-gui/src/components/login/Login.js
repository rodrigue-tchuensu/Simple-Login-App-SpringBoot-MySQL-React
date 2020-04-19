import React, {Component} from 'react'
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button'
import TextField from '@material-ui/core/TextField'
import Typography from '@material-ui/core/Typography'
import Icon from '@material-ui/core/Icon'
import PropTypes from 'prop-types'
import {withStyles} from '@material-ui/core/styles'
import {Redirect} from 'react-router-dom'

const request = require ('../../resources/request');
const APP_CONSTONSTS = require ('../../ApplicationConstants')

const styles = theme => ({
  card: {
    maxWidth: 600,
    margin: 'auto',
    textAlign: 'center',
    marginTop: theme.spacing(5),
    paddingBottom: theme.spacing(2)
  },
  error: {
    verticalAlign: 'middle'
  },
  title: {
    marginTop: theme.spacing(2),
    color: theme.palette.openTitle
  },
  textField: {
    marginLeft: theme.spacing(),
    marginRight: theme.spacing(),
    width: 300
  },
  login: {
    margin: 'auto',
    marginBottom: theme.spacing(2)
  }
})


class Login extends Component {
  state = {
      username: '',
      password: '',
      error: '',
      redirectToReferrer: false
  }


   handleLoginClick = () => {
    const user = {
      username: this.state.username || undefined,
      password: this.state.password || undefined
    }

    request.login(user, (err, res) => {

      if(err)
      {
        console.log("An error has been encountered")
        console.log(err)
        this.setState({error: "login failed"})
      }
      else
      {
        //console.log("The value of res: " + res)
        request.auth.authenticate(res.headers.authorization.split(' ')[1], () => {
          this.setState({redirectToReferrer: true})
        })
      }
     
    })
  } 




  handleChange = name => event => {
    this.setState({[name]: event.target.value})
  }


  render() {
    const {classes} = this.props
    const {from} = this.props.location.state || {
      from: {
        pathname: APP_CONSTONSTS.WELCOME_URL
      }
    }
    const {redirectToReferrer} = this.state
    if (redirectToReferrer) {
      return (<Redirect to={from}/>)
    }

    return (
      <Card className={classes.card}>
        <CardContent>
          <TextField id="username"  label="Username" className={classes.textField} value={this.state.username} onChange={this.handleChange('username')} margin="normal"/><br/>
          <TextField id="password" type="password" label="Password" className={classes.textField} value={this.state.password} onChange={this.handleChange('password')} margin="normal"/>
          <br/> {
            this.state.error && (<Typography component="p" color="error">
              <Icon color="error" className={classes.error}>error</Icon>
              {this.state.error}
            </Typography>)
          }
        </CardContent>
        <CardActions>
          <Button color="primary" variant="raised" onClick={this.handleLoginClick} className={classes.login}>Log In</Button>
        </CardActions>
      </Card>
    )
  }
}

Login.propTypes = {
  classes: PropTypes.object.isRequired
}

export default withStyles(styles)(Login)