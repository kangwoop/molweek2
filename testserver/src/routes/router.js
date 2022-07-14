var express = require('express');
var router = express.Router();
const Controller = require('../controlloers/control')

router.post('/login',Controller.postlogin);
router.post('/signup',Controller.postsignup);
router.post('/endofsignup',Controller.postendofsignup);
router.post('/rankinfo',Controller.postrankinfo);
router.post('/countrywithpos',Controller.postcountry)
router.post('/mysite',Controller.postmysite);
router.post('/myfavorite',Controller.postmyfavorite);
router.post('/sitereviewlist',Controller.postsitereviewlist);
router.post('/myreview',Controller.postmyreview);
router.post('/write',Controller.postwrite);
router.post('/places',Controller.postplaces);
router.post('/isfavorite',Controller.postisfavor);
router.post('/checkfavorite',Controller.postcheckfavor);
// router.post('/avgstar',Controller.postavgstar);



module.exports = router;