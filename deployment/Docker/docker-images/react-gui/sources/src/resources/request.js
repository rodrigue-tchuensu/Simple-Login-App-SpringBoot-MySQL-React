
const request = require('superagent');


const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8090/api/';


const login = (user, callback) => {
    request.post(API_BASE_URL  + 'authentication')
            .set('Content-Type', 'application/json')
            .set('Accept', 'text/plain')
            .send(user)
            .end((err, res) =>{
                callback(err, res)
            }) 
}

const logout = (callback)=> {
    callback()
}

const get = (url, callback) => {
    request.get(API_BASE_URL  + url)
           .set('Content-Type', 'application/json')
           .set('Accept', 'application/json')
           .set('Authorization', "Bearer " + JSON.parse(sessionStorage.getItem('jwt')))
           .end((err, res) => {
                callback(err, res)
           })
}



const post = (url,body, callback) => {
    request.post(API_BASE_URL  + url)
           .set('Content-Type', 'application/json')
           .set('Accept', 'application/json')
           .set('Authorization', "Bearer " + JSON.parse(sessionStorage.getItem('jwt')))
           .send(body)
           .end((err, res) => {
               callback(err,res)
           })
}


const put = (url, body, callback) => {
    request.put(API_BASE_URL  + url)
           .set('Content-Type', 'application/json')
           .set('Accept', 'application/json')
           .set('Authorization', "Bearer " + JSON.parse(sessionStorage.getItem('jwt')))
           .send(body)
           .end((err, res) => {
                callback(err,res)
           })
}


const remove = (url, body, callback) => {
    request.delete(API_BASE_URL  + url)
           .set('Content-Type', 'application/json')
           .set('Accept', 'application/json')
           .set('Authorization', "Bearer " + JSON.parse(sessionStorage.getItem('jwt')))
           .send(body)
           .end((err, res) => {
                callback(err,res)
           })
}

const auth = {
    isAuthenticated() {
      if (sessionStorage.getItem('jwt'))
        return JSON.parse(sessionStorage.getItem('jwt'))
      else
        return false
    },
    authenticate(jwt, cb) {
      sessionStorage.setItem('jwt', JSON.stringify(jwt))
      cb()
    },
    logout(cb) {
      sessionStorage.removeItem('jwt')
      cb()
    },
    decodeJWT(token){
        var JwtToken = JSON.parse(atob(token.split('.')[1]));
        return JwtToken  
    },
    getSubject(){
        const tokenString = this.decodeJWT(JSON.parse(sessionStorage.getItem('jwt')))
        return tokenString.iss
    }
  }


export{
    get,
    post,
    put,
    remove,
    login,
    logout,
    auth
}