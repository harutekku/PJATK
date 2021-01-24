const PrzedmiotyRepository = require('../repository/sequelize/przedmiotyRepository');


exports.showPrzedmiotyList = (req, res, next) => {
    PrzedmiotyRepository.getPrzedmioty()
        .then(przeds => {
            res.render('pages/przedmioty/list', {
                przeds: przeds,
                navLocation: 'przedmioty'
            });
        });
}
exports.showAddPrzedmiotForm = (req, res, next) => {
    res.render('pages/przedmioty/form', {
        przed: {},
        pageTitle: req.__('przed.form.add.pageTitle'),
        formMode: 'createNew',
        btnLabel: req.__('przed.form.add.btnLabel'),
        formAction: '/przedmioty/add',
        navLocation: 'przedmioty',
        validationErrors: ''
    });
}
exports.showEditPrzedmiotForm = (req, res, next) => {
    const przedId = req.params.przedId;
    PrzedmiotyRepository.getPrzedmiotById(przedId)
        .then(przed => {
            res.render('pages/przedmioty/form', {
                przed: przed,
                formMode: 'edit',
                pageTitle: req.__('przed.form.edit.pageTitle'),
                btnLabel: req.__('przed.form.edit.btnLabel'),
                formAction: '/przedmioty/edit',
                navLocation: 'przedmioty',
                validationErrors: ''
            });
        });
};
exports.showPrzedmiotDetails = (req, res, next) => {
    const przedId = req.params.przedId;
    PrzedmiotyRepository.getPrzedmiotById(przedId)
        .then(przed => {
            res.render('pages/przedmioty/form', {
                przed: przed,
                formMode: 'showDetails',
                pageTitle: req.__('przed.form.show.pageTitle'),
                formAction: '',
                navLocation: 'przedmioty',
                validationErrors: ''
            });
        });
}
exports.addPrzedmiot = (req, res, next) => {
    const przedData = { ...req.body };
    PrzedmiotyRepository.createPrzedmiot(przedData)
        .then(result => {
            res.redirect('/przedmioty');
        })
        .catch(err => {
            res.render('pages/przedmioty/form', {
                przed: przedData,
                pageTitle: req.__('przed.form.add.pageTitle'),
                formMode: 'createNew',
                btnLabel: req.__('przed.form.add.btnLabel'),
                formAction: '/przedmioty/add',
                navLocation: 'przedmioty',
                validationErrors: err.errors
            })
        })
};

exports.updatePrzedmiot = (req, res, next) => {
    const przedId = req.body._id;
    const przedData = { ...req.body };
    PrzedmiotyRepository.updatePrzedmiot(przedId, przedData)
        .then(result => {
            res.redirect('/przedmioty');
        }).catch(err => {
            res.render('pages/przedmioty/form', {
                przed: przedData,
                formMode: 'edit',
                pageTitle: req.__('przed.form.edit.pageTitle'),
                btnLabel: req.__('przed.form.edit.btnLabel'),
                formAction: '/przedmioty/edit',
                navLocation: 'przedmioty',
                validationErrors: err.errors
            })
        })
};

exports.deletePrzedmiot = (req, res, next) => {
    const przedId = req.params.przedId;
    PrzedmiotyRepository.deletePrzedmiot(przedId)
        .then(() => {
            res.redirect('/przedmioty');
        });
};