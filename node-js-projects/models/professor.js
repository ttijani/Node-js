// P  R  O  F  E  S  S  O  R    M  O  D  E  L

var mongoose = require('mongoose');

var Schema = mongoose.Schema;

// P R O F E S S O R
var professorSchema = new Schema ({
    name: {type: String, required: true},
    status: {type: String, required: true, enum: ['available', 'unavailable'], default: 'available'},
    dateOfBirth: {type: Date},
    experience: {type: String},
});

// P R I N T S   A P P R O X I M A T E   A G E
professorSchema.virtual('age').get(function() {
    var date = new Date();
    return this.dateOfBirth - date.getFullYear();
});

professorSchema.virtual('url').get(function() {
    return '/schedule/professor/' + this._id;
});

module.exports = mongoose.model('prof', professorSchema);
