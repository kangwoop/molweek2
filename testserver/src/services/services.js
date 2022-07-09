const pool = require('../database/pool');
const myQuery  = require('../queries/queries');
const bcrypt = require('bcryptjs');
const { NULL } = require('mysql/lib/protocol/constants/types');

exports.postlogin = async(Id,Pwd,type) => {
    try{
        let rows = await pool.query(myQuery.login_select,[Id])
        var tem = (rows[Object.keys(rows)[0]]);
        console.log(JSON.stringify(tem) + " postlogin");
        if(JSON.stringify(tem) == "[]"){
            if(type == "kakao"){
                var tohash = Id;
                var token = bcrypt.hashSync(tohash,10);
                let data = await pool.query(myQuery.login_insert,[Id, "kakao", Pwd, token]);
                return {token : token};
            }
            else{
                return {token : " "}
            }
        }
        if(tem[0].Token != "0"){
            if(type == "kakao"){
                return tem[0].Token;
            }
            if(Pwd == tem[0].Upwd)
                return {token : tem[0].Token};
            else{
                return {token : "1"};
            }
        }
        var tohash = Id + Pwd;
        var token = bcrypt.hashSync(tohash,10);
        pool.query(myQuery.login_update_token,[token,Id]);
        return {token : token};
    }catch(err){
        console.log(err)
        throw Error(err)
    }
}
exports.postsignup = async(Id) => {
    try{
        let data = await pool.query(myQuery.login_count,[Id]);
        console.log(data[0] + " signup " + typeof(data));
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

// exports.postrankinfo = async(rank) => {
    
// }