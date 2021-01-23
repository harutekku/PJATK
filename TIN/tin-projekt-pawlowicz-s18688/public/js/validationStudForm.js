function validateForm() {
    const firstNameInput = document.getElementById('firstName');
    const lastNameInput = document.getElementById('lastName');
    const dataInput = document.getElementById('dateOfBirth');

    const errorFirstName = document.getElementById('errorFirstName');
    const errorLastName = document.getElementById('errorLastName');
    const errorDateOfBirth = document.getElementById('errorDateOfBirth');
    const errorsSummary = document.getElementById('errorsSummary');


    resetErrors([firstNameInput, lastNameInput, dataInput], [errorFirstName, errorLastName, errorDateOfBirth], errorsSummary);

    let valid = true;
    
    if (!checkRequired(firstNameInput.value)) {
        valid = false;
        firstNameInput.classList.add("error-input");
        errorFirstName.innerText = "Pole jest wymagane";
    } else if (!checkTextLengthRange(firstNameInput.value, 2, 60)) {
        valid = false;
        firstNameInput.classList.add("error-input");
        errorFirstName.innerText = "Pole powinno zawierać od 2 do 60 znaków";
    }

    if (!checkRequired(lastNameInput.value)) {
        valid = false;
        lastNameInput.classList.add("error-input");
        errorLastName.innerText = "Pole jest wymagane";
    } else if (!checkTextLengthRange(lastNameInput.value, 2, 60)) {
        valid = false;
        lastNameInput.classList.add("error-input");
        errorLastName.innerText = "Pole powinno zawierać od 2 do 60 znaków";
    }

    if (!checkRequired(dataInput.value)) {
        valid = false;
        dataInput.classList.add("error-input");
        errorDateOfBirth.innerText = "Pole jest wymagane";
    } else if (!checkDate(new Date(dataInput.value), new Date(1900 - 01 - 01), new Date())) {
        valid = false;
        dataInput.classList.add("error-input");
        errorDateOfBirth.innerText = "Proszę wpisać prawidłową datę";
    }

    if (!valid) {
        errorsSummary.innerText = "Formularz zawiera błędy";
    }

    return valid;
}