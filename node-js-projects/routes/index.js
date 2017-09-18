// E X P R E S S   R O U T E R   S E T U P.
var express = require('express');
var router = express.Router();

// H O M E   R O U T E
router.get('/', function(req, res) {
    console.log('redirecting...');
    res.redirect('/schedule');
});

// M A K E   R O U T E R   A V A I L A B L E
module.exports = router;
