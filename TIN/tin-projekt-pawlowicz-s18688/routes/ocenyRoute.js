const express = require('express');
const router = express.Router();
const ocenyControler = require('../controllers/ocenyController');
router.get('/', ocenyControler.showOcenyList);
//router.get('/add', ocenyControler.showAddOcenyForm);
//router.get('/details/:przedId', ocenyControler.showOcenyDetails);
module.exports = router;