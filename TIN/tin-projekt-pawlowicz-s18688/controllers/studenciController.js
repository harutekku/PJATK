exports.showStudenciList = (req, res, next) => {
    res.render('pages/studenci/list', { navLocation: 'studenci' });
}