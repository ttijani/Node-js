// M O N G O O S E   S E T U P
var mongoose = require('mongoose');
// S C H E M A   F O R   T H E   D A T A
var someInfoSchema = new mongoose.Schema({
    name: string,
    age: number,
    sex: string
});

someInfoSchema.virtual('fullInfo').get(function() {
    return this.name + ", " + this.age + ", " + this.sex;
});

someInfoSchema.virtual('url').get(function() {
    return '/catalog/info/' + this.name;
});

// C R E A T E S   A N D   E X P O R T S   T H E   M O D E L
module.exports = mongoose.model('someInfo', someInfoSchema);
