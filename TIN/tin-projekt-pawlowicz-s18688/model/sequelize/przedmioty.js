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
        unique: true,
        validate: {
            notEmpty: {
                msg: "Pole jest wymagane"
            },
            len: {
                args: [2,60],
                msg: "Pole powinno zawierać od 2 do 60 znaków"
            },
            // isUnique: (value, next) => {
            //     Przedmiot.findOne({
            //         where: {name: value}
            //     })
            //         .then((user, error) => {
            //             if (error)
            //                 return next(error);

            //             if (user)
            //                 return next('Nazwa musi być unikalna');

            //             next();
            //         });
            // }
        }
    },
    shortcut: {
        type: Sequelize.STRING,
        allowNull: false,
        unique: true,
        validate: {
            notEmpty: {
                msg: "Pole jest wymagane"
            },
            len: {
                args: [2,4],
                msg: "Pole powinno zawierać od 2 do 4 znaków"
            },
            // isUnique: (value, next) => {
            //     Przedmiot.findOne({
            //         where: {shortcut: value}
            //     })
            //         .then((user, error) => {
            //             if (error)
            //                 return next(error);

            //             if (user)
            //                 return next('Skrót musi być unikalny');

            //             next();
            //         });
            // }
        }
    },
    department: {
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
    password: {
        type: Sequelize.STRING,
        allowNull: false,
        validate: {
            notEmpty: {
                msg: "Pole jest wymagane"
            }
        }
    }
});

module.exports = Przedmiot;