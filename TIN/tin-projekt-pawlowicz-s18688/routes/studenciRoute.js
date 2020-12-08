const express = require('express');
const router = express.Router();
const studenciControler = require('../controllers/studenciController');
router.get('/', studenciControler.showStudenciList);
//router.get('/add', studenciControler.showAddStudenciForm);
//router.get('/details/:studenciId', studenciControler.showStudenciDetails);
module.exports = router;