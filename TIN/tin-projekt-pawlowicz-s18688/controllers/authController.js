const PrzedmiotyRepository = require('../repository/sequelize/przedmiotyRepository');
const authUtils = require('../util/authUtils');

exports.login = (req, res, next) => {
    const shortcut = req.body.shortcut;
    const password = req.body.password;
    PrzedmiotyRepository.findByShortcut(shortcut)
        .then(przed => {
            if (!przed) {
                res.render('index', {
                    navLocation: '',
                    loginError: "Nieprawidłowy shortcut lub hasło"
                })
            } else if(authUtils.comparePasswords(password, przed.password) === true) {
                req.session.loggedUser = przed;
                res.redirect('/');
            } else {
                res.render('index', {
                    navLocation: '',
                    loginError: "Nieprawidłowy shortcut lub hasło"
                })
            }
        })
        .catch(err => {
            console.log(err);
        });

}

exports.logout = (req, res, next) => {
    req.session.loggedUser = undefined;
    res.redirect('/');
}