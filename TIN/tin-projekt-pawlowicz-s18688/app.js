var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
const przedmiotyRouter = require('./routes/przedmiotyRoute');
const studenciRouter = require('./routes/studenciRoute');
const ocenyRouter = require('./routes/ocenyRoute');
const studApiRouter = require('./routes/api/StudentApiRoute');
const przedApiRouter = require('./routes/api/PrzedmiotApiRoute');
const ocenApiRouter = require('./routes/api/OcenaApiRoute');
const sequelizeInit = require('./config/sequelize/init');
sequelizeInit()
    .catch(err => {
        console.log(err);
    });

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/favicon.ico', express.static('./public/images/logo.png'));
app.use('/', indexRouter);
app.use('/przedmioty', przedmiotyRouter);
app.use('/studenci', studenciRouter);
app.use('/oceny', ocenyRouter);
app.use('/api/studenci', studApiRouter);
app.use('/api/przedmioty', przedApiRouter);
app.use('/api/oceny', ocenApiRouter);

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

