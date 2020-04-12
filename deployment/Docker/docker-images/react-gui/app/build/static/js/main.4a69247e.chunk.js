(this["webpackJsonpsimple-app-gui"]=this["webpackJsonpsimple-app-gui"]||[]).push([[0],{35:function(e,t,n){"use strict";n.r(t),n.d(t,"get",(function(){return i})),n.d(t,"post",(function(){return s})),n.d(t,"put",(function(){return l})),n.d(t,"remove",(function(){return u})),n.d(t,"login",(function(){return o})),n.d(t,"logout",(function(){return c})),n.d(t,"auth",(function(){return p}));var a=n(81),r="http://127.0.0.1:8090/simple-app-api/",o=function(e,t){a.post(r+"authentication").set("Content-Type","application/json").set("Accept","text/plain").send(e).end((function(e,n){t(e,n)}))},c=function(e){e()},i=function(e,t){a.get(r+e).set("Content-Type","application/json").set("Accept","application/json").set("Authorization","Bearer "+JSON.parse(sessionStorage.getItem("jwt"))).end((function(e,n){t(e,n)}))},s=function(e,t,n){a.post(r+e).set("Content-Type","application/json").set("Accept","application/json").set("Authorization","Bearer "+JSON.parse(sessionStorage.getItem("jwt"))).send(t).end((function(e,t){n(e,t)}))},l=function(e,t,n){a.put(r+e).set("Content-Type","application/json").set("Accept","application/json").set("Authorization","Bearer "+JSON.parse(sessionStorage.getItem("jwt"))).send(t).end((function(e,t){n(e,t)}))},u=function(e,t,n){a.delete(r+e).set("Content-Type","application/json").set("Accept","application/json").set("Authorization","Bearer "+JSON.parse(sessionStorage.getItem("jwt"))).send(t).end((function(e,t){n(e,t)}))},p={isAuthenticated:function(){return!!sessionStorage.getItem("jwt")&&JSON.parse(sessionStorage.getItem("jwt"))},authenticate:function(e,t){sessionStorage.setItem("jwt",JSON.stringify(e)),t()},logout:function(e){sessionStorage.removeItem("jwt"),e()},decodeJWT:function(e){return JSON.parse(atob(e.split(".")[1]))},getSubject:function(){return this.decodeJWT(JSON.parse(sessionStorage.getItem("jwt"))).sub}}},57:function(e,t,n){"use strict";(function(e){var a=n(0),r=n.n(a),o=n(64),c=n(27),i=n(63),s=n(46),l=n(97),u=n(32),p=n(33),m=Object(s.a)({palette:{primary:{light:"#757de8",main:"#3f51b5",dark:"#002984",contrastText:"#fff"},secondary:{light:"#ff79b0",main:"#ff4081",dark:"#c60055",contrastText:"#000"},openTitle:u.a[400],protectedTitle:p.a[400],type:"light"}});t.a=Object(i.hot)(e)((function(){return r.a.createElement(c.a,null,r.a.createElement(l.a,{theme:m},r.a.createElement(o.a,null)))}))}).call(this,n(80)(e))},60:function(e,t,n){e.exports=n.p+"static/media/home.4bce9db7.png"},62:function(e,t,n){e.exports=n.p+"static/media/welcome.ab6dce64.jpg"},64:function(e,t,n){"use strict";var a=n(19),r=n(20),o=n(22),c=n(21),i=n(23),s=n(65),l=n(0),u=n.n(l),p=n(25),m=n(129),f=n(133),g=n(134),d=n(59),h=n.n(d),b=n(135),j=n(27),E=n(35),O=Object(p.g)((function(e){var t=e.history;return u.a.createElement(m.a,{position:"static"},u.a.createElement(f.a,null,u.a.createElement("span",null,u.a.createElement(j.b,{to:"/"},u.a.createElement(g.a,{"aria-label":"Home",style:{color:"#ffffff"}},u.a.createElement(h.a,null)))),!E.auth.isAuthenticated()&&u.a.createElement("span",null,u.a.createElement(j.b,{to:"/login"},u.a.createElement(b.a,{style:{color:"#ffffff"}},"LogIn"))),E.auth.isAuthenticated()&&u.a.createElement("span",null,u.a.createElement(b.a,{style:{color:"#ffffff"},onClick:function(){E.auth.logout((function(){return t.push("/")}))}},"Logout"))))})),y=n(4),v=n(136),S=n(138),w=n(137),x=n(60),T=n.n(x),A=function(e){function t(){return Object(a.a)(this,t),Object(o.a)(this,Object(c.a)(t).apply(this,arguments))}return Object(i.a)(t,e),Object(r.a)(t,[{key:"render",value:function(){var e=this.props.classes;return u.a.createElement(v.a,{className:e.card},u.a.createElement(w.a,{type:"headline",component:"h2",className:e.title},"Welcome to the Simple App Home Page"),u.a.createElement(S.a,{className:e.media,image:T.a,title:"Meshlium Scanner Infrastructure"}))}}]),t}(l.Component),N=Object(y.a)((function(e){return{card:{maxWidth:1100,margin:"auto",marginTop:e.spacing(5)},title:{padding:"".concat(e.spacing(3),"px ").concat(e.spacing(2.5),"px ").concat(e.spacing(2),"px"),color:e.palette.text.secondary},media:{minHeight:700}}}))(A),C=n(61),I=n(141),k=n(139),J=n(142),W=n(140),B=n(35),L=function(e){function t(){var e,n;Object(a.a)(this,t);for(var r=arguments.length,i=new Array(r),s=0;s<r;s++)i[s]=arguments[s];return(n=Object(o.a)(this,(e=Object(c.a)(t)).call.apply(e,[this].concat(i)))).state={username:"",password:"",error:"",redirectToReferrer:!1},n.handleLoginClick=function(){var e={username:n.state.username||void 0,password:n.state.password||void 0};B.login(e,(function(e,t){e?(console.log("An error has been encountered"),console.log(e),n.setState({error:"login error"})):(console.log("The value of res: "+t),B.auth.authenticate(t.text,(function(){n.setState({redirectToReferrer:!0})})))}))},n.handleChange=function(e){return function(t){n.setState(Object(C.a)({},e,t.target.value))}},n}return Object(i.a)(t,e),Object(r.a)(t,[{key:"render",value:function(){var e=this.props.classes,t=(this.props.location.state||{from:{pathname:"/simple-app/user"}}).from;return this.state.redirectToReferrer?u.a.createElement(p.a,{to:t}):u.a.createElement(v.a,{className:e.card},u.a.createElement(k.a,null,u.a.createElement(J.a,{id:"username",label:"Username",className:e.textField,value:this.state.username,onChange:this.handleChange("username"),margin:"normal"}),u.a.createElement("br",null),u.a.createElement(J.a,{id:"password",type:"password",label:"Password",className:e.textField,value:this.state.password,onChange:this.handleChange("password"),margin:"normal"}),u.a.createElement("br",null)," ",this.state.error&&u.a.createElement(w.a,{component:"p",color:"error"},u.a.createElement(W.a,{color:"error",className:e.error},"error"),this.state.error)),u.a.createElement(I.a,null,u.a.createElement(b.a,{color:"primary",variant:"raised",onClick:this.handleLoginClick,className:e.login},"Log In")))}}]),t}(l.Component),z=Object(y.a)((function(e){return{card:{maxWidth:600,margin:"auto",textAlign:"center",marginTop:e.spacing(5),paddingBottom:e.spacing(2)},error:{verticalAlign:"middle"},title:{marginTop:e.spacing(2),color:e.palette.openTitle},textField:{marginLeft:e.spacing(),marginRight:e.spacing(),width:300},login:{margin:"auto",marginBottom:e.spacing(2)}}}))(L),H=n(62),R=n.n(H),D=n(35),F=function(e){function t(){var e,n;Object(a.a)(this,t);for(var r=arguments.length,i=new Array(r),s=0;s<r;s++)i[s]=arguments[s];return(n=Object(o.a)(this,(e=Object(c.a)(t)).call.apply(e,[this].concat(i)))).state={user:{}},n.componentDidMount=function(){return n.fetchData()},n.fetchData=function(){D.get("user",(function(e,t){e?console.log(e):n.setState({user:t.body})}))},n}return Object(i.a)(t,e),Object(r.a)(t,[{key:"render",value:function(){var e=this.props.classes;return u.a.createElement(v.a,{className:e.card},u.a.createElement(w.a,{type:"headline",component:"h2",className:e.title},"Welcome ",this.state.user.username),u.a.createElement(S.a,{className:e.media,image:R.a,title:"User Welcome Page Image"}))}}]),t}(l.Component),P=Object(y.a)((function(e){return{card:{maxWidth:1100,margin:"auto",marginTop:e.spacing(5)},title:{padding:"".concat(e.spacing(3),"px ").concat(e.spacing(2.5),"px ").concat(e.spacing(2),"px"),color:e.palette.text.secondary},media:{minHeight:700}}}))(F),M=n(35),U=function(e){var t=e.component,n=Object(s.a)(e,["component"]);return u.a.createElement(p.b,Object.assign({},n,{render:function(e){return M.auth.isAuthenticated()?u.a.createElement(t,e):u.a.createElement(p.a,{to:{pathname:"/simple-app/login",state:{from:e.location}}})}}))},q=function(e){function t(){return Object(a.a)(this,t),Object(o.a)(this,Object(c.a)(t).apply(this,arguments))}return Object(i.a)(t,e),Object(r.a)(t,[{key:"render",value:function(){return u.a.createElement("div",null,u.a.createElement(O,null),u.a.createElement(p.d,null,u.a.createElement(p.b,{exact:!0,path:"/",component:N}),u.a.createElement(p.b,{path:"/simple-app/login",component:z}),u.a.createElement(U,{path:"/simple-app/user",component:P}),u.a.createElement(p.b,{path:"*",render:function(){return u.a.createElement(p.a,{to:"/simple-app/login"})}})))}}]),t}(l.Component);t.a=q},74:function(e,t,n){e.exports=n(75)},75:function(e,t,n){"use strict";n.r(t);var a=n(0),r=n.n(a),o=n(9),c=n(57);Object(o.render)(r.a.createElement(c.a,null),document.getElementById("root"))}},[[74,1,2]]]);
//# sourceMappingURL=main.4a69247e.chunk.js.map