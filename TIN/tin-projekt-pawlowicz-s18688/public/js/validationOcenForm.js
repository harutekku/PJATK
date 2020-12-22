function validateForm() {
    const nameInput = document.getElementById('student_id');
    const subjectInput = document.getElementById('przedmiot_id');
    const teacherInput = document.getElementById('teacher');
    const dataInput = document.getElementById('date');
    const markInput = document.getElementById('mark');

    const errorName = document.getElementById('errorStudent_id');
    const errorSubject = document.getElementById('errorPrzedmiot_id');
    const errorTeacher = document.getElementById('errorTeacher');
    const errorData = document.getElementById('errorDate');
    const errorMark = document.getElementById('errorMark');


    resetErrors([nameInput, subjectInput, teacherInput, dataInput, markInput], [errorName, errorSubject, errorTeacher, errorData, errorMark], errorsSummary);

    let valid = true;
return true;

    if (!checkRequired(nameInput.value)) {
        valid = false;
        nameInput.classList.add("error-input");
        errorName.innerText = "Pole jest wymagane";
    }

    if (!checkRequired(subjectInput.value)) {
        valid = false;
        subjectInput.classList.add("error-input");
        errorSubject.innerText = "Pole jest wymagane";
    }

    if (!checkRequired(teacherInput.value)) {
        valid = false;
        teacherInput.classList.add("error-input");
        errorTeacher.innerText = "Pole jest wymagane";
    }

    if (!checkRequired(dataInput.value)) {
        valid = false;
        dataInput.classList.add("error-input");
        errorData.innerText = "Pole jest wymagane";
    } else if (!checkDate(new Date(dataInput.value), new Date(1900 - 01 - 01), new Date())) {
        valid = false;
        dataInput.classList.add("error-input");
        errorData.innerText = "Proszę wpisać prawidłową datę";
    }

    if (!checkRequired(markInput.value)) {
        valid = false;
        markInput.classList.add("error-input");
        errorMark.innerText = "Pole jest wymagane";
    } else if (markInput.value < 2 || markInput.value > 5) {
        valid = false;
        markInput.classList.add("error-input");
        errorMark.innerText = "Proszę wpisać prawidłową ocenę z przedziału 2-5";
    }

    if (!valid) {
        errorsSummary.innerText = "Formularz zawiera błędy";
    }

    return valid;
}