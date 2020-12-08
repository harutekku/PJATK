const Sequelize = require('sequelize');

const Ocena = require('../../model/sequelize/oceny');
const Przedmiot = require('../../model/sequelize/przedmioty');
const Student = require('../../model/sequelize/studenci');

exports.getOceny = () => {
    return Ocena.findAll({
        include: [
            {
                model: Przedmiot,
                as: 'przedmiot'
            },
            {
                model: Student,
                as: 'student'
            }]
    });
};


exports.getOcenaById = (ocenaId) => {
    return Ocena.findByPk(ocenaId, {
        include: [
            {
                model: Przedmiot,
                as: 'przedmiot'
            },
            {
                model: Student,
                as: 'student'
            }]
    });
};

exports.createOcena = (data) => {
    console.log(JSON.stringify(data));

    return Ocena.create({
        przedmiot_id: data.przedmiot_id,
        student_id: data.student_id,
        mark: data.mark,
        teacher: data.teacher,
        date: data.date
    });
};

exports.updateOcena = (ocenaId, data) => {
    return Ocena.update(data, { where: { _id: ocenaId } });
}

exports.deleteOcena = (ocenaId) => {
    return Ocena.destroy({
        where: { _id: ocenaId }
    });
}

exports.deleteManyOcenas = (ocenaIds) => {
    return Ocena.find({ _id: { [Sequelize.Op.in]: ocenaIds } })
}