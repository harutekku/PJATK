const OcenyRepository = require('../repository/sequelize/ocenyRepository');
const StudenciRepository = require('../repository/sequelize/studenciRepository');
const PrzedmiotyRepository = require('../repository/sequelize/przedmiotyRepository');
exports.showOcenyList = (req, res, next) => {
    let allOceny, allStuds, allPrzeds;
    OcenyRepository.getOceny()
        .then(oceny => {
            allOceny = oceny;
            return StudenciRepository.getStudenci()
        })
        .then(studs => {
            allStuds = studs;
            return PrzedmiotyRepository.getPrzedmioty();
        })
        .then(przeds => {
            allPrzeds = przeds;
            res.render('pages/oceny/list', {
                oceny: allOceny,
                allStuds: allStuds,
                allPrzeds: allPrzeds,
                navLocation: 'oceny'
            });
        });
}
exports.showAddOcenaForm = (req, res, next) => {
    let allStuds, allPrzeds;
    StudenciRepository.getStudenci()
        .then(studs => {
            allStuds = studs;
            return PrzedmiotyRepository.getPrzedmioty();
        })
        .then(przeds => {
            allPrzeds = przeds;
            res.render('pages/oceny/form', {
                ocena: {},
                allStuds: allStuds,
                allPrzeds: allPrzeds,
                pageTitle: 'Nowa ocena',
                formMode: 'createNew',
                btnLabel: 'Dodaj ocenę',
                formAction: '/oceny/add',
                navLocation: 'oceny'
            });
        });
}
exports.showEditOcenaForm = (req, res, next) => {
    const ocenaId = req.params.ocenaId;
    let allStuds, allPrzeds;
    StudenciRepository.getStudenci()
        .then(studs => {
            allStuds = studs;
            return PrzedmiotyRepository.getPrzedmioty();
        })
        .then(przeds => {
            allPrzeds = przeds;
            return OcenyRepository.getOcenaById(ocenaId)
                .then(ocena => {
                    res.render('pages/oceny/form', {
                        ocena: ocena,
                        allStuds: allStuds,
                        allPrzeds: allPrzeds,
                        formMode: 'edit',
                        pageTitle: 'Edycja oceny',
                        btnLabel: 'Edytuj ocene',
                        formAction: '/oceny/edit',
                        navLocation: 'oceny'
                    });
                });
        });
}
exports.showOcenaDetails = (req, res, next) => {
    const ocenaId = req.params.ocenaId;
    let allStuds, allPrzeds;
    StudenciRepository.getStudenci()
        .then(studs => {
            allStuds = studs;
            return PrzedmiotyRepository.getPrzedmioty();
        })
        .then(przeds => {
            allPrzeds = przeds;
            return OcenyRepository.getOcenaById(ocenaId)
                .then(ocena => {
                    res.render('pages/oceny/form', {
                        ocena: ocena,
                        allStuds: allStuds,
                        allPrzeds: allPrzeds,
                        formMode: 'showDetails',
                        pageTitle: 'Szczegóły oceny',
                        formAction: '',
                        navLocation: 'oceny'
                    });
                });
        });
}
exports.addOcena = (req, res, next) => {
    const ocenaData = { ...req.body };
    OcenyRepository.createOcena(ocenaData)
        .then(result => {
            res.redirect('/oceny');
        });
};

exports.updateOcena = (req, res, next) => {
    const ocenaId = req.body._id;
    const ocenaData = { ...req.body };
    OcenyRepository.updateOcena(ocenaId, ocenaData)
        .then(result => {
            res.redirect('/oceny');
        });
};

exports.deleteOcena = (req, res, next) => {
    const ocenaId = req.params.ocenaId;
    OcenyRepository.deleteOcena(ocenaId)
        .then(() => {
            res.redirect('/oceny');
        });
};

