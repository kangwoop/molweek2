const service = require('../services/services');
const fs = require('fs')

exports.postlogin = async(req,res,next) => {
        //signup id have to hashing
    let{ Id, Name, Pwd, type} = req.body
    try{
        let rows = await service.postlogin(Id,Name,Pwd,type);
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

exports.postrankinfo = async(req, res, next) => {
    try{
        let result = await service.postrankinfo()
        console.log(result + "여기는 랭킹")
        return res.json(result)
    }catch(err){
        console.log(err)
        return res.status(500).json(err)
    }
}

exports.postcountry = async(req,res,next) => {
    let{pos} = req.body
    try{
        console.log(pos)
        let result = await service.postcountry(pos)
        console.log(result + " 여기는 컨트롤 입니다.")
        return res.json(result)
    }catch(err){
        console.log(err)
        return res.status(500).json(err)
    }
}

exports.postmysite = async(req,res,next) => {
    let {country, Htoken} = req.body
    try{
        let result = await service.postmysite(country, Htoken)
        console.log(JSON.stringify(result) + "명소 리스트임당")
        return res.json(result)
    }catch(err){
        console.log(err)
        return res.status(500).json(err)
    }
}

exports.postmyfavorite = async(req,res,next) => {
    let{token} = req.body
    try{
        let result = await service.postmyfavorite(token)
        console.log(result + " 마이 페이보릿")
        return res.json(result)
    }catch(err){
        console.log(err)
        return res.status(500).json(err)
    }
}

exports.postmyreview = async(req,res,next) => {
    let{token} = req.body
    try{
        let result = await service.postmyreview(token)
        console.log(result + " 마이 리뷰")
        return res.json(result)
    }catch(err){
        console.log(err)
        return res.status(500).json(err)
    }
}

exports.postsitereviewlist = async(req,res,next) => {
    let{Place_name} = req.body
    try{
        let result = await service.postsitereviewlist(Place_name)
        console.log(result + " 사이트리뷰")
        return res.json(result)
    }catch(err){
        console.log(err)
        return res.status(500).json(err)
    }
}

exports.postwrite = async(req,res,next) => {
    let{Htoken,star,name,Posting,PlaceName} = req.body
    try{
        let result = await service.postwrite(Htoken,star,name,Posting,PlaceName)
        console.log(result + " 리뷰 작성")
        return res.json(result)
    }catch(err){
        console.log(err)
        return res.status(500).json(err)
    }
}
exports.postplaces = async(req,res,next) => {
    let{Place_name} = req.body
    try{
        let result = await service.postplaces(Place_name)
        console.log(JSON.stringify(result) + " 명소 정보")
        return res.json(result[0])
    }catch(err){
        console.log(err)
        return res.status(500).json(err)
    }
}
exports.postisfavor = async(req,res,next) => {
    let{Place_name, Htoken} = req.body
    try{
        let result = await service.postisfavor(Place_name,Htoken)
        return res.json(result)
    }catch(err){
        console.log(err)
        return res.status(500).json(err)
    }
}
exports.postcheckfavor = async(req,res,next) => {
    let{Place_name, Htoken} = req.body
    try{
        let result = await service.postcheckfavor(Place_name,Htoken)
        return res.json(result)
    }catch(err){
        console.log(err)
        return res.status(500).json(err)
    }
}
// exports.postavgstar = async(req,res,next) => {
//     let{token} = req.body
//     try{
//         let result = await service.postavgstar(token)
//         return res.json(result)
//     }catch(err){
//         console.log(err)
//         return res.status(500).json(err)
//     }
// }