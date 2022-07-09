const service = require('../services/services');


exports.postlogin = async(req,res,next) => {
        //signup id have to hashing
    let{ Id, Pwd, type} = req.body
    try{
        let rows = await service.postlogin(Id,Pwd,type);
        return res.json(rows)    
    }catch(err){
        return res.status(500).json(err)
    }
}

exports.postsignup = async(req,res,next) => {
    let{ Id } = req.body
    try{
        console.log(Id + "singipcontrol")
        let rows = await service.postsignup(Id)
        console.log(rows);
        return res.json({result : rows})
    }catch (err){
        return res.status(500).json(err)
    }
}

exports.postendofsignup = async(req,res,next) => {
    let{Id, Name,Pwd} = req.body
    try{
        console.log({Id,Name,Pwd})
        let result = await service.postendofsignup(Id, Name, Pwd)
        console.log("회원가입끝");
        return res.json({result : "true"})
    }catch(err){
        console.log(err)
        return res.status(500).json(err)
    }
}

// exports.postrankinfo = async(req, res, next) => {
//     let{rank} = req.params
//     try{
        
//     }catch(err){
//         return res.status(500).json(err)
//     }
// }