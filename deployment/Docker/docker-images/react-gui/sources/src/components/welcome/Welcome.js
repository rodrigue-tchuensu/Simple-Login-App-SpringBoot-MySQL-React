import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {withStyles} from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardMedia from '@material-ui/core/CardMedia';
import Typography from '@material-ui/core/Typography';
import welcomePageImage from './../../assets/images/welcome.jpg';
const request = require ('../../resources/request');

const styles = theme => ({
  card: {
    maxWidth: 1100,
    margin: 'auto',
    marginTop: theme.spacing(5)
  },
  title: {
    padding:`${theme.spacing(3)}px ${theme.spacing(2.5)}px ${theme.spacing(2)}px`,
    color: theme.palette.text.secondary
  },
  media: {
    minHeight: 700
  }
})

class Welcome extends Component {


  state = {
      user: {},
  }

  componentDidMount = () => this.fetchData ()

  fetchData = () => { 
      request.get('users', (err, res) => {
          if(err) {
              console.log(err)
          } else {
              this.setState({user: res.body})
          }
      })
  }

  render() {
    const {classes} = this.props
    return (
        <Card className={classes.card}>
          <Typography type="headline" component="h2" className={classes.title}>
          Welcome {this.state.user.username}
          </Typography>
          <CardMedia className={classes.media} image={welcomePageImage} title="User Welcome Page Image"/>
        </Card>
    )
  }
}

Welcome.propTypes = {
  classes: PropTypes.object.isRequired
}

export default withStyles(styles)(Welcome)