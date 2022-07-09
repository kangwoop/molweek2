const mysql = require("mysql");

var pool;

exports.connect = function(done) {
    pool = mysql.createPool({
        connectionLimit: 100,
        host     : 'localhost',
        user     : 'user',
        password : 'Rkddn!23',
        database : 'Trip'
    });
}

// get() 함수를 통해 db pool에 접근할 수 있도록 한다
exports.get = function() {
  return pool;
}

// const connection = mysql.createConnection({
//     host:'localhost',
//     user:'user',
//     password:'Rkddn!23',
//     database:'Trip'
// });

// function getAllPLACENAME(callback){
//     connection.query('SELECT NAME FROM PLACE ORDER BY ID DESC',(err,rows,fields)=>{
//         if(err)throw err;
//         callback(rows);
//     });
// }

// module.exports = {
//     getAllPLACENAME
// }