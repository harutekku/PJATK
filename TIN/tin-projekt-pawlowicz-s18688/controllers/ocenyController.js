exports.showOcenyList = (req, res, next) => {
    res.render('pages/oceny/list', { navLocation: 'oceny' });
}