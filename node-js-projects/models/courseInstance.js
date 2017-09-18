// C  O  U  R  S  E    M  O  D  E  L

var mongoose = require('mongoose');

var Schema = mongoose.Schema;

var courseSchema = new Schema({
    course: {type: Schema.ObjectId, ref: 'Course'},
    courseCode: {type: String,  required: true},
    status: {type: String, required: true, enum: ['available', 'unavailable', 'cancelled', 'waitlisted'], default: 'available'},
    prof: {type: Schema.ObjectId, ref: 'Professor', required: true},
});

courseSchema.virtual('url').get(function() {
    return '/courseInstance/' + this._id;
});

module.exports = mongoose.model('courseInstance', courseSchema);
