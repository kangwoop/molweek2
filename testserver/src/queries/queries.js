//login_table
exports.login_count = "select count(*) as cnt from login where Uid = ?;";
exports.login_insert = "insert into login (Uid, Name, Upwd, Token) values(?, ?, ?, ?);";
exports.login_select = "select * from login where Uid = ?; ";
exports.login_update_token = "update login SET Token = ? Where Uid = ?"

//favorite,posting table
exports.rankingfavo = "select * From ( select PlaceName, Picturepath,(@rank := @rank + 1) As ranking From places AS a,(select @rank := 0) AS b ORDER BY a.FavorCount DESC)ranked where ranked.ranking < 6";
exports.myfavorite = "select PlaceName, Picturepath, Information From Favorite where Htoken = ?"
exports.myreview = "select PlaceName,Posting, star, Posting_date From PostingList where Htoken = ?"
exports.sitereviewlist = "select name,star,Posting_date,Posting From PostingList where PlaceName = ?"
exports.writeposting = "insert into PostingList(Htoken,star,Posting_date, name,Posting,PlaceName) values(?,?,default,?,?,?)"
exports.isfavorite = "select * from Favorite where Htoken = ? and Placename = ?"
exports.insertfavorite = "insert into Favorite(Place_id,Htoken,Placename,Information,Picturepath) values(?,?,?,?,?)"
exports.deletefavorite = "delete from Favorite where Htoken = ? and Placename = ?"
exports.selectplaceall = "select * from places where PlaceName = ?"
exports.selectavgstar = "select (sumstar/sumpeople) As avgstar From places where PlaceName = ?"

//country table
exports.continentSelect = "select Name,PicturePath,placenames From country where Continent = ?"
exports.continentSelectall = "select Name,PicturePath,placenames From country"

//place table
exports.placeswithcountry = "select PlaceName,PicturePath,(sumstar/sumpeople) As avgstar From places where Country = ?"
exports.placeinfo = "select PlaceName, Country, Picturepath, Information, Address from places where PlaceName = ?"

//update table
exports.increase_favorite = "update places SET FavorCount = FavorCount + 1 Where PlaceName = ?"
exports.decrease_favorite = "update places SET FavorCount = FavorCount - 1 Where PlaceName = ?"
exports.increase_sumstar = "update places Set sumstar = sumstar + ? where PlaceName = ?"
exports.increase_sumpoeple = "update places set sumpeople = sumpeople + 1 where PlaceName = ?"
