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
                pageTitle: req.__('ocen.form.add.pageTitle'),
                formMode: 'createNew',
                btnLabel: req.__('ocen.form.add.btnLabel'),
                formAction: '/oceny/add',
                navLocation: 'oceny',
                validationErrors: ''
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
        })
        .then(ocena => {
            res.render('pages/oceny/form', {
                ocena: ocena,
                allStuds: allStuds,
                allPrzeds: allPrzeds,
                formMode: 'edit',
                pageTitle: req.__('ocen.form.edit.pageTitle'),
                btnLabel: req.__('ocen.form.edit.btnLabel'),
                formAction: '/oceny/edit',
                navLocation: 'oceny',
                validationErrors: ''
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
        })
        .then(ocena => {
            res.render('pages/oceny/form', {
                ocena: ocena,
                allStuds: allStuds,
                allPrzeds: allPrzeds,
                formMode: 'showDetails',
                pageTitle: req.__('ocen.form.show.pageTitle'),
                formAction: '',
                navLocation: 'oceny',
                validationErrors: ''
            });

        });
}
exports.addOcena = (req, res, next) => {
    const ocenaData = { ...req.body };
    OcenyRepository.createOcena(ocenaData)
        .then(result => {
            res.redirect('/oceny');
        })
        .catch(err => {
            let allStuds, allPrzeds, thisOcena = {
                student: { _id: ocenaData.student_id },
                przedmiot: { _id: ocenaData.przedmiot_id },
                date: ocenaData.date,
                mark: ocenaData.mark,
                teacher: ocenaData.teacher
            };
            StudenciRepository.getStudenci()
                .then(studs => {
                    allStuds = studs;
                    return PrzedmiotyRepository.getPrzedmioty();
                })
                .then(przeds => {
                    allPrzeds = przeds;
                    res.render('pages/oceny/form', {
                        ocena: thisOcena,
                        allStuds: allStuds,
                        allPrzeds: allPrzeds,
                        pageTitle: req.__('ocen.form.add.pageTitle'),
                        formMode: 'createNew',
                        btnLabel: req.__('ocen.form.add.btnLabel'),
                        formAction: '/oceny/add',
                        navLocation: 'oceny',
                        validationErrors: err.errors
                    });
                });
        });
};

exports.updateOcena = (req, res, next) => {
    const ocenaId = req.body._id;
    const ocenaData = { ...req.body };
    OcenyRepository.updateOcena(ocenaId, ocenaData)
        .then(result => {
            res.redirect('/oceny');
        }).catch(err => {
            let allStuds, allPrzeds, thisOcena = {
                _id: ocenaData._id,
                student: { _id: ocenaData.student_id },
                przedmiot: { _id: ocenaData.przedmiot_id },
                date: ocenaData.date,
                mark: ocenaData.mark,
                teacher: ocenaData.teacher
            };
            console.dir(thisOcena,3);
            StudenciRepository.getStudenci()
                .then(studs => {
                    allStuds = studs;
                    return PrzedmiotyRepository.getPrzedmioty();
                })
                .then(przeds => {
                    allPrzeds = przeds;
                    res.render('pages/oceny/form', {
                        ocena: thisOcena,
                        allStuds: allStuds,
                        allPrzeds: allPrzeds,
                        formMode: 'edit',
                        pageTitle: req.__('ocen.form.edit.pageTitle'),
                        btnLabel: req.__('ocen.form.edit.btnLabel'),
                        formAction: '/oceny/edit',
                        navLocation: 'oceny',
                        validationErrors: err.errors
                    });
                });
        });
};

exports.deleteOcena = (req, res, next) => {
    const ocenaId = req.params.ocenaId;
    OcenyRepository.deleteOcena(ocenaId)
        .then(() => {
            res.redirect('/oceny');
        });
};

