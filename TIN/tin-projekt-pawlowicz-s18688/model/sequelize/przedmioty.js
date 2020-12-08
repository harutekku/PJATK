const Sequelize = require('sequelize');
const sequelize = require('../../config/sequelize/sequelize');

const Przedmiot = sequelize.define('Przedmiot', {
    _id: {
        type: Sequelize.INTEGER,
        autoIncrement: true,
        allowNull: false,
        primaryKey: true,
    },
    name: {
        type: Sequelize.STRING,
        allowNull: false,
        unique: true
    },
    shortcut: {
        type: Sequelize.STRING,
        allowNull: false,
        unique: true
    },
    department: {
        type: Sequelize.STRING,
        allowNull: false
    }
});

module.exports = Przedmiot;