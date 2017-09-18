var express = require('express');

var http = require('http');

var app = express();

//Creating a simple server. 
var server = http.createServer(function(req, res) {
    res.writeHead(200, {'Content-Type': 'text/plain'});
    res.end('Here I am!');
});

server.listen(3000, '127.0.0.1');


app.get('/home', function(req, res) {
    res.writeHead(200, {'Content-Type': "text/plain"});
    res.end('This is home...');
});

