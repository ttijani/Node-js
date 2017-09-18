var c = require('../models/courses');
var courseInstance = require('../models/courseInstance');

var async = require('async');

exports.courseInstanceList = function(req, res, next) {
// F I N D   A L L   I N S T A N C E S
  courseInstance.find().populate('course').exec(function(err, instanceList) {
      if (err) {
          return next(err);
      }
      //Successful, so render
      res.render('courseInst', {title: 'Course Instance List', courseInstanceList: instanceList });
    });

};

exports.createCourseInstanceGet = function(req, res, next) {
    res.render('createCourseInst', {title: 'Create A Course Instance'})
}

exports.createCourseInstancePost= function(req, res, next) {

    var newInstance = new courseInstance({
        course: req.body.Course,
        courseCode: req.body.courseCode,
        status: req.body.status,
        prof: req.body.professor,
    });

    courseInstance.find().exec(function(err, result) {
        if (err) {
            return next(err);
        }
        if (result) {
            res.redirect(result.url);
        }
        else {
            newInstance.save(function(err) {
                if (err) {
                    return next(err);
                }
                res.redirect(newInstance.url);
            })
        }
    });
}
