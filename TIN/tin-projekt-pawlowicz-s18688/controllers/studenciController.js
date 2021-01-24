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
        pageTitle: req.__('stud.form.add.pageTitle'),
        formMode: 'createNew',
        btnLabel: req.__('stud.form.add.btnLabel'),
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
                pageTitle: req.__('stud.form.edit.pageTitle'),
                btnLabel: req.__('stud.form.edit.btnLabel'),
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
                pageTitle: req.__('stud.form.show.pageTitle'),
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
                pageTitle: req.__('stud.form.add.pageTitle'),
                formMode: 'createNew',
                btnLabel: req.__('stud.form.add.btnLabel'),
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
                pageTitle: req.__('stud.form.edit.pageTitle'),
                btnLabel: req.__('stud.form.edit.btnLabel'),
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