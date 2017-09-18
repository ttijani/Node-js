// C  O  U  R  S  E    C  O  N  T  R  O  L  L  E  R

// R E Q U I R E   T H E   C O U R S E   M O D E L
var course = require('../models/courses');
var courseProf = require('../models/professor');

var mongoose = require('mongoose');

var async = require('async');

// P R I N T S   T H E   N U M B E R   O F   C O U R S E S   O F F E R E D
exports.courseIndex = function(req, res) {
    async.parallel({
        getAllCourses: function(callback) {
            course.count(callback);
        },

        getAllAvailableCourses: function(callback) {
            course.count({status: 'available'}, callback);
        },
    }, function(err, result) {
            if (err) {
                return next(err);
            }
            res.render('courses', {title: 'Courses', error: err, data: result});
        }
    );
};

exports.courseInfo = function(req, res) {
    course.findOne({_id: req.params.id}).populate('prof', 'name').exec(function(err, courseDoc) {
        console.log(courseDoc);
        res.render('courseInfo', {title: 'Course info', data: courseDoc});
    });
};

// LISTS ALL COURSES
exports.courseList = function(req, res) {
    course.find().populate('prof', 'name').exec(function(err, courseList) {
        if (err) {
            return next(err);
        }
        res.render('courseList', {title: 'List of All Courses', data: courseList});
    });
};

// HOME PAGE FOR COURSES
exports.createCoursesGet = function(req, res) {
    res.render('addCourse', {title: 'Create a new course', data: undefined});
};

// ADDING A NEW COURSE
exports.createCoursesPost = function(req, res, next) {

    req.checkBody('courseName', 'courseName must be specified').notEmpty(); //We won't force Alphanumeric, because book titles might have spaces.
    req.checkBody('courseCode', 'courseCode must be specified').notEmpty();
    req.checkBody('professor', 'professor must be specified').notEmpty();

    req.sanitize('courseName').escape();
    req.sanitize('courseCode').escape();
    req.sanitize('professor').escape();
    req.sanitize('courseName').trim();
    req.sanitize('courseCode').trim();
    req.sanitize('professor').trim();

    var errors = req.validationErrors();

    var newCourse;
    courseProf.findOne({name: req.body.professor}, function(err, result) {
        if (err) {
            return next(err);
        }
        newCourse = new course({
            name: req.body.courseName,
            courseCode: req.body.courseCode,
            status: req.body.status,
            creditHours: req.body.creditHours,
            prof: result._id,
        });
    });

    course.findOne({name: 'req.body.courseName'}).exec(function(err, matchingCourse) {
        if (err) {
            return next(err);
        }

        if (matchingCourse){
            res.redirect(matchingCourse.url);
        } else {
            newCourse.save(function(err) {
                if (err) {
                    return next(err);
                }
                res.redirect(newCourse.url);
            });
        }
    });
};

exports.findCourseGet = function(req, res) {
    course.find({}, 'name').exec(function(err, courseList) {
        res.render('updateCourse', {title: 'Find Course you wish to update', data: courseList});
    });
};

exports.findCoursePost = function(req, res, next) {

    req.checkBody('course', 'courseName must be specified').notEmpty(); //We won't force Alphanumeric, because book titles might have spaces.

    req.sanitize('course').escape();
    req.sanitize('course').trim();

    var errors = req.validationErrors();

    course.findOne({name: req.body.course}).populate('prof', 'name').exec(function(err, course2Update) {
        if (err) {
            return next(err);
        }
        if (req.params.method == "update") {
            res.render('updateView', {title: 'Update Course', data: course2Update});
        } else {
            res.render('deleteCourse', {title: 'Delete Course', data: course2Update});
        }

    });
};


// DELETING A COURSE
exports.deleteCoursesGet = function(req, res) {
    res.send('delete-get');
};

// DELETING A COURSE
exports.deleteCoursesPost = function(req, res, next) {
    console.log(req.body.course);
    course.findByIdAndRemove(req.body.course, function(err) {
        if (err) { return next(err); }
        //Success - got to author list
        res.redirect('/schedule/course');
    });
};

// UPDATE COURSE INFO
exports.updateCoursesGet = function(req, res) {

};

// UPDATE COURSE INFO
exports.updateCoursesPost = function(req, res, next) {

    req.checkBody('courseName', 'courseName must be specified').notEmpty(); //We won't force Alphanumeric, because book titles might have spaces.
    req.checkBody('courseCode', 'courseCode must be specified').notEmpty();
    req.checkBody('professor', 'professor must be specified').notEmpty();

    req.sanitize('courseName').escape();
    req.sanitize('courseCode').escape();
    req.sanitize('professor').escape();
    req.sanitize('courseName').trim();
    req.sanitize('courseCode').trim();
    req.sanitize('professor').trim();

    var errors = req.validationErrors();

    async.waterfall([
        function (callback) { // get the professor document.
            courseProf.findOne({name: req.body.professor}, function(err, result) {
                if (err) {return next(err);}
                callback(null, result);
            });
        },
        function (result, callback) { // create the updated course instance.
            var newCourse = new course({
                name: req.body.courseName,
                courseCode: req.body.courseCode,
                status: req.body.status,
                creditHours: req.body.creditHours,
                prof: result._id,
                _id: req.params.id,
            });
            callback(null, newCourse);
        },
        function (newCourse, callback) { // Replace the old course instance.
            course.findByIdAndUpdate(req.params.id, newCourse, { new: true }, function(err, theCourse) {
                if (err) { return next(err); }
                //Success - got to author list
                res.redirect(theCourse.url);
            });
            callback(null, 'done');
        }
    ], function (err) {
        if (err) throw new Error(err);
    });
};
