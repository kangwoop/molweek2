var express = require('express');
var router = express.Router();
const Controller = require('../controlloers/control')

router.post('/login',Controller.postlogin);
router.post('/signup',Controller.postsignup);
router.post('/endofsignup',Controller.postendofsignup);
// router.post('/rankinfo'.Controller.postrankinfo);


module.exports = router;