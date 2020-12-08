const express = require('express');
const router = express.Router();
const przedmiotyControler = require('../controllers/przedmiotyController');
router.get('/', przedmiotyControler.showPrzedmiotyList);
//router.get('/add', przedmiotyControler.showAddPrzedmiotyForm);
//router.get('/details/:przedmiotyId', przedmiotyControler.showPrzedmiotyDetails);
module.exports = router;