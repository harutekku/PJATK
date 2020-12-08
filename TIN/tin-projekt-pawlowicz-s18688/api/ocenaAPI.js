const OcenaRepository = require('../repository/sequelize/ocenyRepository');

exports.getOceny = (req, res, next) => {
    OcenaRepository.getOceny()
        .then(ocens => {
            res.status(200).json(ocens);
        })
        .catch(err => {
           console.log(err);
        });
};

exports.getOcenaById = (req, res, next) => {
    const ocenId = req.params.ocenId;
    OcenaRepository.getOcenaById(ocenId)
        .then(ocen => {
            if(!ocen) {
                res.status(404).json({
                    message: 'Ocena with id: '+ocenId+' not found'
                })
            } else {
                res.status(200).json(ocen);
            }
        });
};

exports.createOcena = (req, res, next) => {
    OcenaRepository.createOcena(req.body)
        .then(newObj => {
           res.status(201).json(newObj);
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        });
};

exports.updateOcena = (req, res, next) => {
    const ocenId = req.params.ocenId;
    OcenaRepository.updateOcena(ocenId, req.body)
        .then(result => {
           res.status(200).json({message: 'Ocena updated!', ocen: result});
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        });

};

exports.deleteOcena = (req, res, next) => {
    const ocenId = req.params.ocenId;
    OcenaRepository.deleteOcena(ocenId)
        .then(result => {
            res.status(200).json({message: 'Removed ocenent', ocen: result});
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        });
};