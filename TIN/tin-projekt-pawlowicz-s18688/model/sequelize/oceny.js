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
        allowNull: false,
        validate: {
            notEmpty: {
                msg: "Pole jest wymagane"
            },
            max: {
                args: [5],
                msg: "Ocena może być maksymalnie 5"
            },
            min: {
                args: [2],
                msg: "Ocena może być minimalnie 2"
            }
        }
    },
    date: {
        type: Sequelize.DATE,
        allowNull: false,
        validate: {
            notEmpty: {
                msg: "Pole jest wymagane"
            },
            isDate: true,
            isAfter: {
                args:["1900-01-01"],
                msg: "Data jest zbyt dawna"
            },
            isBefore: {
                args:[new Date().toISOString()],
                msg: "Data jest z przyszłości"
            },
        }
    },
    teacher: {
        type: Sequelize.STRING,
        allowNull: false,
        validate: {
            notEmpty: {
                msg: "Pole jest wymagane"
            },
            len: {
                args: [2,60],
                msg: "Pole powinno zawierać od 2 do 60 znaków"
            },
        }
    },
    student_id: {
        type: Sequelize.INTEGER,
        allowNull: false,
        validate: {
            notEmpty: {
                msg: "Pole jest wymagane"
            },
        }
    },
    przedmiot_id: {
        type: Sequelize.INTEGER,
        allowNull: false,
        validate: {
            notEmpty: {
                msg: "Pole jest wymagane"
            },
        }
    }
});

module.exports = Ocena;