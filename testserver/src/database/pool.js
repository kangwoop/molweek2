const mysql = require("mysql2/promise");


module.exports = mysql.createPool({
  connectionLimit: 100,
  host     : 'localhost',
  user     : 'user1',
  password : '',
  database : 'Trip'
});
