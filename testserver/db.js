const mysql = require("mysql");

var pool;

exports.connect = function(done) {
    pool = mysql.createPool({
        connectionLimit: 100,
        host     : 'localhost',
        user     : 'user2',
        password : '',
        database : 'manito'
    });
}

// get() 함수를 통해 db pool에 접근할 수 있도록 한다
exports.get = function() {
  return pool;
}
