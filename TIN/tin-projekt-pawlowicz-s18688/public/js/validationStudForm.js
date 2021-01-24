function validateForm() {
    const firstNameInput = document.getElementById('firstName');
    const lastNameInput = document.getElementById('lastName');
    const dataInput = document.getElementById('dateOfBirth');

    const errorFirstName = document.getElementById('errorFirstName');
    const errorLastName = document.getElementById('errorLastName');
    const errorDateOfBirth = document.getElementById('errorDateOfBirth');
    const errorsSummary = document.getElementById('errorsSummary');

    const eReqMessage = document.getElementById('errorMessage-required').innerText;
    const e2_60Message = document.getElementById('errorMessage-2_60').innerText;
    const eDateMessage = document.getElementById('errorMessage-date').innerText;
    const eMessage = document.getElementById('errorMessage-errors').innerText;


    resetErrors([firstNameInput, lastNameInput, dataInput], [errorFirstName, errorLastName, errorDateOfBirth], errorsSummary);

    let valid = true;
    
    
    if (!checkRequired(firstNameInput.value)) {
        valid = false;
        firstNameInput.classList.add("error-input");
        errorFirstName.innerText = eReqMessage;
    } else if (!checkTextLengthRange(firstNameInput.value, 2, 60)) {
        valid = false;
        firstNameInput.classList.add("error-input");
        errorFirstName.innerText = e2_60Message;
    }

    if (!checkRequired(lastNameInput.value)) {
        valid = false;
        lastNameInput.classList.add("error-input");
        errorLastName.innerText = eReqMessage;
    } else if (!checkTextLengthRange(lastNameInput.value, 2, 60)) {
        valid = false;
        lastNameInput.classList.add("error-input");
        errorLastName.innerText = e2_60Message;
    }

    if (!checkRequired(dataInput.value)) {
        valid = false;
        dataInput.classList.add("error-input");
        errorDateOfBirth.innerText = eReqMessage;
    } else if (!checkDate(new Date(dataInput.value), new Date(1900 - 01 - 01), new Date())) {
        valid = false;
        dataInput.classList.add("error-input");
        errorDateOfBirth.innerText = eDateMessage;
    }

    if (!valid) {
        errorsSummary.innerText = eMessage;
    }

    return valid;
}