const sequelize = require('./sequelize');

const Przedmiot = require('../../model/sequelize/przedmioty');
const Student = require('../../model/sequelize/studenci');
const Ocena = require('../../model/sequelize/oceny');

const authUtil = require('../../util/authUtils');


module.exports = () => {
    Przedmiot.hasMany(Ocena, {as: 'ocena', foreignKey: {name: 'przedmiot_id', allowNull: false}, constraints: true, onDelete: 'CASCADE'});
    Ocena.belongsTo(Przedmiot, {as: 'przedmiot', foreignKey: {name: 'przedmiot_id', allowNull: false} } );
    Student.hasMany(Ocena, {as: 'ocena', foreignKey: {name: 'student_id', allowNull: false}, constraints: true, onDelete: 'CASCADE'});
    Ocena.belongsTo(Student, {as: 'student', foreignKey: {name: 'student_id', allowNull: false} });

    let allPrzed, allStud;
    return sequelize
        .sync({force: true})
        .then( () => {
            return Przedmiot.findAll();
        })
        .then(przed => {
            if( !przed || przed.length == 0 ) {
                return Przedmiot.bulkCreate([
                    {name: 'Podstawy programowania w Javie', shortcut: 'PPJ', department: 'Katedra metod programowania', password: authUtil.hashPassword('12345')},
                    {name: 'Programowanie obiektowe i GUI', shortcut: 'GUI', department: 'Katedra metod programowania', password: authUtil.hashPassword('23456')},
                    {name: 'Wstęp do systemów informatycznych', shortcut: 'WSI', department: 'Katedra baz danych', password: authUtil.hashPassword('34567')},
                    {name: 'Wstęp do zarządzania', shortcut: 'WDZ', department: 'Katedra Ekonomii i Zarządzania', password: authUtil.hashPassword('45678')}
                ])
                .then( () => {
                    return Przedmiot.findAll();
                });
            } else {
                return przed;
            }
        })
        .then( przed => {
            allPrzed = przed;
            return Student.findAll();
        })
        .then( stud => {
            if( !stud || stud.length == 0 ) {
                return Student.bulkCreate([
                    { firstName: 'Jan', lastName: 'Kowalski', dateOfBirth: '1998-01-01' },
                    { firstName: 'Staś', lastName: 'Bruno', dateOfBirth: '1999-02-02' },
                    { firstName: 'Asia', lastName: 'Wabik', dateOfBirth: '2000-03-03' },
                    { firstName: 'Marysia', lastName: 'Manio', dateOfBirth: '2001-04-04' }
                ])
                .then( () => {
                    return Przedmiot.findAll();
                });
            } else {
                return stud;
            }
        })
        .then( stud => {
            allStud = stud;
            return Ocena.findAll();
        })
        .then( oce => {
            if( !oce || oce.length == 0 ) {
                return Ocena.bulkCreate([
                    {przedmiot_id: allPrzed[0]._id, student_id: allStud[0]._id, mark: 5, date: '2020-12-07', teacher: 'Robert Makłowicz'},
                    {przedmiot_id: allPrzed[1]._id, student_id: allStud[1]._id, mark: 4, date: '2020-12-07', teacher: 'Robert Makłowicz'},
                    {przedmiot_id: allPrzed[2]._id, student_id: allStud[2]._id, mark: 3, date: '2020-12-07', teacher: 'Robert Makłowicz'}
                ]);
            } else {
                return oce;
            }
        });
};