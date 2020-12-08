const Sequelize = require('sequelize');

const sequelize = new Sequelize('tin-mpp2', 'root', 'root', {
    dialect: 'mysql',
    host: 'localhost'
});

module.exports = sequelize;