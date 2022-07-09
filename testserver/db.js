const mysql = require("mysql");

var pool;

exports.connect = function(done) {
    pool = mysql.createPool({
        connectionLimit: 100,
        host     : 'localhost',
        user     : 'user1',
        password : '',
        database : 'Trip'
    });
}

// get() 함수를 통해 db pool에 접근할 수 있도록 한다
exports.get = function() {
  return pool;
}
