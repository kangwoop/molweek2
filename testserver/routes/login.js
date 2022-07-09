const bcrypt = require('bcrypt');
const saltRounds = 10;

var express = require('express');
var formidable = require('formidable');
var db = require('../db');
var router = express.Router();

//login할 때 만약 처음 로그인하는 것이라면 token 생성하고 return한다. 
router.post('/login',function(req,res,next){
    //signup id have to hashing
    var id = req.body.id;
    var name = req.body.name;
    var pwd = req.body.pwd;
    var tohash = string.concat(id,name,pwd);
    
    console.log({id,name,pwd,token});
    var sql_count = "select count(*) as cnt from login where Uid = ?;";
    var sql_insert = "insert into login (Uid, Name, Upwd, Token) values(?, ?, ?, ?);";
    // var sql_update = "update login set Name = ?, Upwd = ? where Uid = ?; ";
    var sql_select = "select Token from login where Uid = ?; ";
    db.get().query(sql_count,id,function(err,rows){
        console.log(rows);
        console.log(rows[0].cnt);
        //이미 존재하는 계정일 때 token return 
        if(rows[0].cnt > 0){
            db.get().query(sql_select,id,function(err,results){
                if(err){
                    return res.sendStatus(404);
                }
                console.log("이미 존재하는 계정입니다" + results);
                res.send(results)
            })
        }
        //token 생성
        var token = bcrypt.hashSync(tohash,10);
        //id,name,pwd login에 넣기 그리고 생성한 token return 
        db.get().query(sql_insert,[id,name,pwd,token], function(err,result){
            if(err) return res.sendStatus(400);

            return res.send(token);
        });
    });

    db.get().query("insert into myInfo(Nickname, Token) values(?, ?);",[name, token],function(err,result){
        if(err) return res.sendStatus(400);
    });
    //token myInfo에 넣기
});

router.post('/signup',function(req,res,next){
    var id = req.body.id;
    var name = req.body.name;
    var pwd = req.body.pwd;
    var sql_count = "select count(*) as cnt from login where Uid = ?;";
    
    console.log({id,name,pwd});
    db.get().query(sql_count, id,function(err,results){
        console.log(rows);
        if(rows[0].cnt > 0){
            return res.send("Success");
        }
        return res.sendStatus(400);
    });
});

module.exports = router;