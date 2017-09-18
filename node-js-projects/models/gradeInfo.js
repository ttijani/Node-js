var mongoose = require('mongoose');

var Schema = mongoose.Schema;
var gradeInfoSchema = new Schema({
    letterGrade: {type: String, required: true, enum: ['A', 'B', 'C', 'D', 'E', 'F'], default: 'A'},
    numberGrade: {type: Number, required: true},
});

module.exports = mongoose.model('gradeInfo', gradeInfoSchema);
