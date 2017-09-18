var express = require('express');
var router = express.Router();

// A L L O W S   C O U R S E   C O N T R O L L E R   M E T H O D S   H E R E
var courseController = require('../controllers/courseController');
// A L L O W S   C O U R S E   I N S T A N C E   C O N T R O L L E R   M E T H O D S   H E R E
var courseInstanceContr = require('../controllers/courseInstanceContr');
// A L L O W S   P R O F E S S O R   C O N T R O L L E R   M E T H O D S   H E R E
var profContr = require('../controllers/profController');

router.get('/', courseController.courseIndex);

router.get('/course/create', courseController.createCoursesGet);

router.post('/course/create', courseController.createCoursesPost);

router.get('/course/search/:method', courseController.findCourseGet);

router.post('/course/search/:method', courseController.findCoursePost);

router.get('/course/details/:id', courseController.courseInfo);

router.get('/course/:id/delete', courseController.deleteCoursesGet);

router.post('/course/:id/delete', courseController.deleteCoursesPost);

router.get('/course/:id/update', courseController.updateCoursesGet);

router.post('/course/:id/update', courseController.updateCoursesPost);

router.get('/course', courseController.courseList);

router.get('/course/:id', courseController.courseInfo);



// router.get('/', courseInstanceContr.courseIndex);
router.get('/courseInstance', courseInstanceContr.courseInstanceList);

router.get('/courseInstance/create', courseInstanceContr.createCourseInstanceGet);

router.post('/courseInstance/create', courseInstanceContr.createCourseInstancePost);


router.get('/professor', profContr.profIndex);

router.get('/professor/create', profContr.createProfGet);

router.post('/professor/create', profContr.createProfPost);

router.get('/professor/:id', profContr.profInfo);


module.exports = router;
