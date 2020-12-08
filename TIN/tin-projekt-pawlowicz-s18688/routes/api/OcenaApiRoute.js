const express = require('express');
const router = express.Router();

const ocenApiController = require('../../api/ocenaAPI');

router.get('/', ocenApiController.getOceny);
router.get('/:ocenId', ocenApiController.getOcenaById);
router.post('/', ocenApiController.createOcena);
router.put('/:ocenId', ocenApiController.updateOcena);
router.delete('/:ocenId', ocenApiController.deleteOcena);

module.exports = router;