var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var bodyParser = require('body-parser');
var db = require('./db');
const fs = require('fs');
var retrofitRouter = require('./src/routes/retrofit');
var Crouters = require('./src/routes/router')
var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');
app.get('/getimgmain/:id',function(req, res){

	console.log("이미지요청");
	var register_number = req.params.id;
	console.log(req.params + register_number);
	var filename = register_number+".jpeg";
	var filePath = __dirname + "/images/" + filename;

	fs.readFile(filePath,
		function (err, data)
        {	
        	if(err){
				console.log(err);
				filename = "1.jpeg";
				filePath = __dirname  + "/images/" + filename;
		    	fs.readFile(filePath,
				function (err, data)
				{
					console.log(filePath);
					console.log(data);
					res.end(data);
				});
            }else{
			console.log(filePath);
			console.log(data);
			res.end(data);
			}				
        }
	);		
})
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

// app.use('/', indexRouter);
app.use('/retrofit',retrofitRouter);

app.use('',Crouters);
var register_number_for_img;
var img_cnt;


db.connect(function(err){
  if(err){
    console.log('Cannot connect database');
    throw(err);
  }
});

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
