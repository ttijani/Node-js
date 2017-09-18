// R E Q U I R E   T H E   C O U R S E   M O D E L
var Profs = require('../models/professor');

var async = require('async');

exports.profIndex = function(req, res) {
    async.parallel({
        getAllProfs: function(callback) {
            Profs.count(callback);
        },

        getAllAvailableProfs: function(callback) {
            Profs.count({status: 'available'}, callback);
        }
    }, function(err, profIndex) {
        if (err) {
            return next(err);
        }
        res.render('profIndex', {title: 'Professor Index', data: profIndex});
    });
};

exports.profInfo = function(req, res) {
    res.send('info');
};

// LISTS ALL COURSES
exports.profList = function(req, res) {
    res.send('List');
};

// HOME PAGE FOR COURSES
exports.createProfGet = function(req, res) {
    res.render('createProfessor', {title: 'Create Professor'});
};

// ADDING A NEW COURSE
exports.createProfPost = function(req, res) {
    var newProf = new Profs({
        name: req.body.profName,
        status: req.body.profStatus,
        dateOfBirth: req.body.doB,
        experience: req.body.exp,
    });

    Profs.findOne({name: req.body.profName}).exec(function(err, foundProf) {
        if (err) {
            return next(err);
        }
        if (foundProf) {
            res.redirect(foundProf.url);
        }
        else {
            console.log('*********Creating new course*********');
            newProf.save(function(err) {
                if (err) {
                    return next(err);
                }    
                res.redirect(newProf.url);
            })
        }
    });
};

// DELETING A COURSE
exports.deleteProfGet = function(req, res) {
    res.send('delete-get');
};

// DELETING A COURSE
exports.deleteProfPost = function(req, res) {
    res.send('delete-post');
};

// UPDATE COURSE INFO
exports.updateProfGet = function(req, res) {
    res.send('update-get');
};

// UPDATE COURSE INFO
exports.updateProfPost = function(req, res) {
    res.send('update-post');
};

exports.profInfo = function(req, res) {
    console.log('The prof info is updated');
    Profs.findOne({_id: req.params.id}).exec(function(err, profDoc) {
        res.render('profInfo', {title: 'Professor"s info', data: profDoc});
    });
}
