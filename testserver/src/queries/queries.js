exports.login_count = "select count(*) as cnt from login where Uid = ?;";
exports.login_insert = "insert into login (Uid, Name, Upwd, Token) values(?, ?, ?, ?);";
exports.login_select = "select * from login where Uid = ?; ";
exports.login_update_token = "update login SET Token = ? Where Uid = ?"

exports.rankingfavo = "select PlaceName,Picturepath,RANK() OVER ( ORDER BY FavorCount desc) as 'ranking' from places where ranking = ?";
exports.myfavorPlaces = "select where Htoken = ?"
exports.mypostingPlaces = "select Place_id From Favorite where Htoken = ?"
