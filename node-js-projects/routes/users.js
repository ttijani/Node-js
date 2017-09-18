var express = require('express');
var router = express.Router();

// C O O L  R O U T E
router.get('/cool', function(req, res) {
    res.send("You're so cool");
});

// M A K E   R O U T E R   A V A I L A B L E
module.exports = router;
