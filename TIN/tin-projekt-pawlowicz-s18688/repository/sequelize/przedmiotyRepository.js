const Przedmiot = require("../../model/sequelize/przedmioty");
const Student = require("../../model/sequelize/studenci");
const Ocena = require("../../model/sequelize/oceny");
const authUtil = require('../../util/authUtils');

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
        department: newPrzedData.department,
        password: newPrzedData.password!=""?authUtil.hashPassword(newPrzedData.password):""
    });
};

exports.updatePrzedmiot = (przedId, przedData) => {
    przedData.password=authUtil.hashPassword(przedData.password);
    return Przedmiot.update(przedData, {where: {_id: przedId }});
};

exports.deletePrzedmiot = (przedId) => {
    return Przedmiot.destroy({
        where: { _id: przedId }
    });

}; 

exports.findByShortcut = (shortcut) => {
    return Przedmiot.findOne({
        where: {shortcut: shortcut}
    });
}