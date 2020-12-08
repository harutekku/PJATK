const Sequelize = require('sequelize');
const sequelize = require('../../config/sequelize/sequelize');

const Ocena = sequelize.define('Ocena', {
    _id: {
        type: Sequelize.INTEGER,
        autoIncrement: true,
        allowNull: false,
        primaryKey: true
    },
    mark: {
        type: Sequelize.DECIMAL(1, 0),
        allowNull: false
    },
    date: {
        type: Sequelize.DATE,
        allowNull: false
    },
    teacher: {
        type: Sequelize.STRING,
        allowNull: false
    },
    student_id: {
        type: Sequelize.INTEGER,
        allowNull: false
    },
    przedmiot_id: {
        type: Sequelize.INTEGER,
        allowNull: false
    }
});

module.exports = Ocena;