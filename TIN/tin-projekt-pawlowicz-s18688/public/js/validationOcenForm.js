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

    const eReqMessage = document.getElementById('errorMessage-required').innerText;
    const e2_5Message = document.getElementById('errorMessage-2_5').innerText;
    const eDateMessage = document.getElementById('errorMessage-date').innerText;
    const eMessage = document.getElementById('errorMessage-errors').innerText;


    resetErrors([nameInput, subjectInput, teacherInput, dataInput, markInput], [errorName, errorSubject, errorTeacher, errorData, errorMark], errorsSummary);

    let valid = true;


    if (!checkRequired(nameInput.value)) {
        valid = false;
        nameInput.classList.add("error-input");
        errorName.innerText = eReqMessage;
    }

    if (!checkRequired(subjectInput.value)) {
        valid = false;
        subjectInput.classList.add("error-input");
        errorSubject.innerText = eReqMessage;
    }

    if (!checkRequired(teacherInput.value)) {
        valid = false;
        teacherInput.classList.add("error-input");
        errorTeacher.innerText = eReqMessage;
    }

    if (!checkRequired(dataInput.value)) {
        valid = false;
        dataInput.classList.add("error-input");
        errorData.innerText = eReqMessage;
    } else if (!checkDate(new Date(dataInput.value), new Date(1900 - 01 - 01), new Date())) {
        valid = false;
        dataInput.classList.add("error-input");
        errorData.innerText = eDateMessage;
    }

    if (!checkRequired(markInput.value)) {
        valid = false;
        markInput.classList.add("error-input");
        errorMark.innerText = eReqMessage;
    } else if (markInput.value < 2 || markInput.value > 5) {
        valid = false;
        markInput.classList.add("error-input");
        errorMark.innerText = e2_5Message;
    }

    if (!valid) {
        errorsSummary.innerText = eMessage;
    }

    return valid;
}