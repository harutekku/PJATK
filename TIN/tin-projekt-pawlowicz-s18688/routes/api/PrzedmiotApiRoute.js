const express = require('express');
const router = express.Router();

const przedApiController = require('../../api/przedmiotAPI');

router.get('/', przedApiController.getPrzedmioty);
router.get('/:przedId', przedApiController.getPrzedmiotById);
router.post('/', przedApiController.createPrzedmiot);
router.put('/:przedId', przedApiController.updatePrzedmiot);
router.delete('/:przedId', przedApiController.deletePrzedmiot);

module.exports = router;