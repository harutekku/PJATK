exports.showPrzedmiotyList = (req, res, next) => {
    res.render('pages/przedmioty/list', { navLocation: 'przedmioty' });
}