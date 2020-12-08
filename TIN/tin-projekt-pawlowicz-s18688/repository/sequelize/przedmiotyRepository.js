const Przedmiot = require("../../model/sequelize/przedmioty");
const Student = require("../../model/sequelize/studenci");
const Ocena = require("../../model/sequelize/oceny");

exports.getPrzedmioty = () => {
    return Przedmiot.findAll();
};

exports.getPrzedmiotById = (przedId) => {
    return Przedmiot.findByPk(przedId,
        {
            include: [{
                model: Ocena,
                as: 'ocena',
                include: [{
                    model: Student,
                    as: 'student'
                }]
            }]
        });
};

exports.createPrzedmiot = (newPrzedData) => {
    return Przedmiot.create({
        name: newPrzedData.name,
        shortcut: newPrzedData.shortcut,
        department: newPrzedData.department
    });
};

exports.updatePrzedmiot = (przedId, przedData) => {
    const name = przedData.name;
    const shortcut = przedData.shortcut;
    const department = przedData.department;
    return Przedmiot.update(przedData, {where: {_id: przedId }});
};

exports.deletePrzedmiot = (przedId) => {
    return Przedmiot.destroy({
        where: { _id: przedId }
    });

}; 