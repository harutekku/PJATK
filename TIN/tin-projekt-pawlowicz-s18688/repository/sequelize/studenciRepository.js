const Przedmiot = require("../../model/sequelize/przedmioty");
const Student = require("../../model/sequelize/studenci");
const Ocena = require("../../model/sequelize/oceny");

exports.getStudenci = () => {
    return Student.findAll();
};

exports.getStudentById = (studId) => {
    return Student.findByPk(studId,
        {
            include: [{
                model: Ocena,
                as: 'ocena',
                include: [{
                    model: Przedmiot,
                    as: 'przedmiot'
                }]
            }]
        });
};

exports.createStudent = (newStudData) => {
    return Student.create({
        firstName: newStudData.firstName,
        lastName: newStudData.lastName,
        dateOfBirth: newStudData.dateOfBirth
    });
};

exports.updateStudent = (studId, studData) => {
    const firstName = studData.firstName;
    const lastName = studData.lastName;
    const dateOfBirth = studData.dateOfBirth;
    return Student.update(studData, {where: {_id: studId }});
};

exports.deleteStudent = (studId) => {
    return Student.destroy({
        where: { _id: studId }
    });

}; 