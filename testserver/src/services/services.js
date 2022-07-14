const pool = require('../database/pool');
const myQuery  = require('../queries/queries');
const bcrypt = require('bcryptjs');
const { NULL } = require('mysql/lib/protocol/constants/types');

exports.postlogin = async(Id,Name,Pwd,type) => {
    try{
        let rows = await pool.query(myQuery.login_select,[Id])
        var tem = (rows[Object.keys(rows)[0]]);
        console.log(JSON.stringify(tem) + " postlogin");
        if(JSON.stringify(tem) == "[]"){
            if(type == "kakao"){
                var tohash = Id;
                var token = bcrypt.hashSync(tohash,10);
                let data = await pool.query(myQuery.login_insert,[Id, Name, Pwd, token]);
                return {token : token, Name : Name};
            }
            else{
                return {token : " "}
            }
        }
        if(tem[0].Token != "0"){
            if(type == "kakao"){
                return {token : tem[0].Token};
            }
            if(Pwd == tem[0].Upwd)
                return {token : tem[0].Token, Name : tem[0].Name};
            else{
                return {token : "1", Name : "error"};
            }
        }
        var tohash = Id + Pwd;
        var token = bcrypt.hashSync(tohash,10);
        pool.query(myQuery.login_update_token,[token,Id]);
        return {token : token, Name : tem[0].Name};
    }catch(err){
        console.log(err)
        throw Error(err)
    }
}
exports.postsignup = async(Id) => {
    try{
        let data = await pool.query(myQuery.login_count,[Id]);
        console.log(JSON.stringify(data[0]) + " signup " + typeof(data));
        var tem = (data[Object.keys(data)[0]]);
        console.log(tem[0].cnt);
        if(tem[0].cnt> 0){
            return "false";
        }
        return "true";
    }catch(err){
        console.log(err)
        throw Error(err)
    }
}

exports.postendofsignup = async(Id, Name, Pwd) => {
    try{
        let data = await pool.query(myQuery.login_insert,[Id, Name, Pwd, "0"])
        console.log(JSON.stringify(data) + "end_of_signup");
        return "true"
    }catch(err){
        console.log(err)
        throw Error(err)
    }
}
exports.postrankinfo = async() => {
    try{
        let data = await pool.query(myQuery.rankingfavo)
        console.log(JSON.stringify(data[0]) + "rank");
        return data[0]
    }catch(err){
        console.log(err)
        throw Error(err)
    }
}
exports.postcountry = async(pos) => {
    try{
        if(pos == "0"){
            let data = await pool.query(myQuery.continentSelectall)
            console.log(JSON.stringify(data[0]) + "   all country")
            return data[0]
        }
        else{
            let data = await pool.query(myQuery.continentSelect,[pos])
            console.log(JSON.stringify(data[0]) + "   country" + pos)
            return data[0]
        }
    }catch(err){
        console.log(err)
        throw Error(err)
    }
}
exports.postmysite = async(country,Htoken) => {
    try{
        let data = await pool.query(myQuery.placeswithcountry,[country])
        console.log(JSON.stringify(data[0][3]) + " placeservice")
        var a = [];
        for(i in data[0]){
            let dat = await pool.query(myQuery.isfavorite,[Htoken,data[0][i].PlaceName])
            if(data[0][i].avgstar == null){
                data[0][i].avgstar = 0
            }
            if(JSON.stringify(dat[0]) == "[]"){
                a.push({PlaceName : data[0][i].PlaceName, PicturePath : data[0][i].PicturePath,avgstar : data[0][i].avgstar,favorite : "false"})
            }
            else {
                a.push({PlaceName : data[0][i].PlaceName, PicturePath : data[0][i].PicturePath,avgstar : data[0][i].avgstar,favorite : "true"})
            }
        }
        console.log(a);
        return a;
        return data[0]
    }catch(err){
        console.log(err)
        throw Error(err)
    }
}

exports.postmyfavorite = async(token) => {
    try{
        let data = await pool.query(myQuery.myfavorite,[token])
        var a = [];
        console.log(JSON.stringify(data[0]))
        for(i in data[0]){
            let dat = await pool.query(myQuery.selectavgstar,[data[0][i].PlaceName])
            if(dat[0][0].avgstar == null){
                dat[0][0].avgstar = 0
            }
            console.log(i + "avgstar" + JSON.stringify(dat[0]));
            console.log(i + JSON.stringify({PlaceName : data[0][i].PlaceName, Picturepath : data[0][i].Picturepath,star : dat[0][0].avgstar}));
            a.push({PlaceName : data[0][i].PlaceName, Picturepath : data[0][i].Picturepath,star : dat[0][0].avgstar})
        }
        console.log(JSON.stringify(a) + " myfavor")
        return a
        // return data[0]
    }catch(err){
        console.log(err)
        throw Error(err)
    }
}
exports.postmyreview = async(token) => {
    try{
        let data = await pool.query(myQuery.myreview,[token])
        console.log(JSON.stringify(data[0]) + " myreview")
        return data[0]
    }catch(err){
        console.log(err)
        throw Error(err)
    }
}
exports.postsitereviewlist = async(Place_name) => {
    try{
        let data = await pool.query(myQuery.sitereviewlist,[Place_name])
        console.log(JSON.stringify(data[0]) + " sitereview")
        return data[0]
    }catch(err){
        console.log(err)
        throw Error(err)
    }
}
exports.postwrite = async(Htoken,star,name,Posting,PlaceName) => {
    try{
        let data = await pool.query(myQuery.writeposting,[Htoken,star,name,Posting,PlaceName])
        console.log(star + typeof(star))
        let dat = await pool.query(myQuery.increase_sumstar,[parseint(star),PlaceName])
        let da = await pool.query(myQuery.increase_sumpoeple,[PlaceName])
        return data[0]
    }catch(err){
        console.log(err)
        throw Error(err)
    }
}
exports.postplaces = async(Place_name) => {
    try{
        let data = await pool.query(myQuery.placeinfo,[Place_name])
        console.log(JSON.stringify(data[0]) + " places")
        return data[0]
    }catch(err){
        console.log(err)
        throw Error(err)
    }
}
exports.postisfavor = async(Place_name, Htoken) => {
    try{
        let data = await pool.query(myQuery.isfavorite,[Htoken,Place_name])
        console.log(JSON.stringify(data[0]) + Place_name + Htoken)
        var tem = data[0];
        if(JSON.stringify(tem) == "[]"){
            return {result : "false"}
        }
        else {
            return {result : "true"} 
        }
    }catch(err){
        console.log(err)
        throw Error(err)
    }
}
exports.postcheckfavor = async(Place_name, Htoken) => {
    try{
        let data = await pool.query(myQuery.isfavorite,[Htoken,Place_name])
        var tem = (data[0]);
        if(JSON.stringify(tem) == "[]"){//insert
            let da = await pool.query(myQuery.selectplaceall,[Place_name])
            var temp = (da[0]);
            console.log(JSON.stringify(temp) + " 여기는 insert");
            let dat = await pool.query(myQuery.insertfavorite,[temp[0]._id,Htoken,Place_name,temp[0].Information,temp[0].Picturepath])
            let d = await pool.query(myQuery.increase_favorite,[Place_name])
            return {result : "true"}
        }
        else{//delete
            let dat = await pool.query(myQuery.deletefavorite,[Htoken,Place_name])
            let da = await pool.query(myQuery.decrease_favorite,[Place_name])
            return {result : "true"}
        }
    }catch(err){
        console.log(err)
        throw Error(err)
    }
}
// exports.postavgstar = async(token) => {
//     try{
//         let data = await pool.query(myQuery.selectavgstar,[token])
//         console.log(JSON.stringify(data[0]) + Place_name + Htoken)
//     }catch(err){
//         console.log(err)
//         throw Error(err)
//     }
// }