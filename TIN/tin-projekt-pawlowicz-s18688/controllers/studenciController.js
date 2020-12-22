const StudenciRepository = require('../repository/sequelize/studenciRepository');
exports.showStudenciList = (req, res, next) => {
    StudenciRepository.getStudenci()
        .then(studs => {
            res.render('pages/studenci/list', {
                studs: studs,
                navLocation: 'studenci'
            });
        });
}
exports.showAddStudentForm = (req, res, next) => {
    res.render('pages/studenci/form', {
        stud: {},
        pageTitle: 'Nowy student',
        formMode: 'createNew',
        btnLabel: 'Dodaj studenta',
        formAction: '/studenci/add',
        navLocation: 'studenci',
        validationErrors: ''
    });
}
exports.showEditStudentForm = (req, res, next) => {
    const studId = req.params.studId;
    StudenciRepository.getStudentById(studId)
        .then(stud => {
            res.render('pages/studenci/form', {
                stud: stud,
                formMode: 'edit',
                pageTitle: 'Edycja studenta',
                btnLabel: 'Edytuj studenta',
                formAction: '/studenci/edit',
                navLocation: 'studenci',
                validationErrors: ''
            });
        });
};
exports.showStudentDetails = (req, res, next) => {
    const studId = req.params.studId;
    StudenciRepository.getStudentById(studId)
        .then(stud => {
            res.render('pages/studenci/form', {
                stud: stud,
                formMode: 'showDetails',
                pageTitle: 'Szczegóły studenta',
                formAction: '',
                navLocation: 'studenci',
                validationErrors: ''
            });
        });
}
exports.addStudent = (req, res, next) => {
    const studData = { ...req.body };
    StudenciRepository.createStudent(studData)
        .then(result => {
            res.redirect('/studenci');
        })
        .catch(err => {
            res.render('pages/studenci/form', {
                stud: studData,
                pageTitle: 'Nowy student',
                formMode: 'createNew',
                btnLabel: 'Dodaj studenta',
                formAction: '/studenci/add',
                navLocation: 'studenci',
                validationErrors: err.errors
            });
        });
};

exports.updateStudent = (req, res, next) => {
    const studId = req.body._id;
    const studData = { ...req.body };
    StudenciRepository.updateStudent(studId, studData)
        .then(result => {
            res.redirect('/studenci');
        }).catch(err => {
            res.render('pages/studenci/form', {
                stud: studData,
                formMode: 'edit',
                pageTitle: 'Edycja studenta',
                btnLabel: 'Edytuj studenta',
                formAction: '/studenci/edit',
                navLocation: 'studenci',
                validationErrors: err.errors
            });
        });
};

exports.deleteStudent = (req, res, next) => {
    const studId = req.params.studId;
    StudenciRepository.deleteStudent(studId)
        .then(() => {
            res.redirect('/studenci');
        });
};