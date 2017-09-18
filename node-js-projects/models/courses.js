// C  O  U  R  S  E    M  O  D  E  L

var mongoose = require('mongoose');

var Schema = mongoose.Schema;

var courseSchema = new Schema({
    name: {type: String, required: true},
    courseCode: {type: String,  required: true},
    status: {type: String, required: true, enum: ['available', 'unavailable', 'cancelled', 'waitlisted'], default: 'available'},
    creditHours: {type: Number, required: true},
    prof: {type: Schema.ObjectId, ref: 'prof', required: true},
});

courseSchema.virtual('fullCourseInfo').get(function() {
    return this.name + ", " + this.courseCode + ", " + this.creditHours + ", " +
    this.prof;
});

courseSchema.virtual('url').get(function() {
    return '/schedule/course/' + this._id;
});

module.exports = mongoose.model('courses', courseSchema);
