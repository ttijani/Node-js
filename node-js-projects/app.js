// E X P R E S S   M O D U L E S
var express = require('express');
var app = express();


// M I D D L E W A R E S
var bodyParser = require('body-parser');
var cookieParser = require('cookie-parser');
var expressValidator = require('express-validator');


// M O N G O O S E   S E T U P
var mongoose = require('mongoose');
var mongo = 'mongodb://node-js-projects:Roboticsisba3@ds159998.mlab.com:59998/node-js-projects';
mongoose.connect(mongo);
var data = mongoose.connection;
data.on('error', console.error.bind(console, 'mongoDB connection error:'));


// T E M P L A T E S
app.set('view engine', 'ejs');

app.use(express.static('./public'));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));
app.use(expressValidator());

// M  I  D  D  L  E    W  A  R  E
// R O U T E S
var index = require('./routes/index.js');
var courses = require('./routes/courses.js');

// M O U N T I N G   R O U T E S'   M I D D L E W A R E
app.use('/', index);
app.use('/schedule', courses);


// B I N D I N G   T O   P O R T - 8000
app.listen(8000, '127.0.0.1', function() {
    console.log('Listening to port 8000');
});
