const PrzedmiotRepository = require('../repository/sequelize/przedmiotyRepository');

exports.getPrzedmioty = (req, res, next) => {
    PrzedmiotRepository.getPrzedmioty()
        .then(przeds => {
            res.status(200).json(przeds);
        })
        .catch(err => {
           console.log(err);
        });
};

exports.getPrzedmiotById = (req, res, next) => {
    const przedId = req.params.przedId;
    PrzedmiotRepository.getPrzedmiotById(przedId)
        .then(przed => {
            if(!przed) {
                res.status(404).json({
                    message: 'Przedmiot with id: '+przedId+' not found'
                })
            } else {
                res.status(200).json(przed);
            }
        });
};

exports.createPrzedmiot = (req, res, next) => {
    PrzedmiotRepository.createPrzedmiot(req.body)
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

exports.updatePrzedmiot = (req, res, next) => {
    const przedId = req.params.przedId;
    PrzedmiotRepository.updatePrzedmiot(przedId, req.body)
        .then(result => {
           res.status(200).json({message: 'Przedmiot updated!', przed: result});
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        });

};

exports.deletePrzedmiot = (req, res, next) => {
    const przedId = req.params.przedId;
    PrzedmiotRepository.deletePrzedmiot(przedId)
        .then(result => {
            res.status(200).json({message: 'Removed przedmiot', przed: result});
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        });
};