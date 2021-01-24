function validateForm() {
    const NameInput = document.getElementById('name');
    const shortcutInput = document.getElementById('shortcut');
    const departmentInput = document.getElementById('department');
    const passwordInput = document.getElementById('password');

    const errorName = document.getElementById('errorName');
    const errorShortcut = document.getElementById('errorShortcut');
    const errorDepartment = document.getElementById('errorDepartment');
    const errorPassword = document.getElementById('errorPassword');
    const errorsSummary = document.getElementById('errorsSummary');

    const eReqMessage = document.getElementById('errorMessage-required').innerText;
    const e2_60Message = document.getElementById('errorMessage-2_60').innerText;
    const e2_4Message = document.getElementById('errorMessage-2_4').innerText;
    const eMessage = document.getElementById('errorMessage-errors').innerText;


    resetErrors([NameInput, shortcutInput, departmentInput, passwordInput], [errorName, errorShortcut, errorDepartment,errorPassword], errorsSummary);

    let valid = true;


    if (!checkRequired(NameInput.value)) {
        valid = false;
        NameInput.classList.add("error-input");
        errorName.innerText = eReqMessage;
    } else if (!checkTextLengthRange(NameInput.value, 2, 60)) {
        valid = false;
        NameInput.classList.add("error-input");
        errorName.innerText = e2_60Message;
    }

    if (!checkRequired(shortcutInput.value)) {
        valid = false;
        shortcutInput.classList.add("error-input");
        errorShortcut.innerText = eReqMessage;
    } else if (!checkTextLengthRange(shortcutInput.value, 2, 4)) {
        valid = false;
        shortcutInput.classList.add("error-input");
        errorShortcut.innerText = e2_4Message;
    }

    if (!checkRequired(departmentInput.value)) {
        valid = false;
        departmentInput.classList.add("error-input");
        errorDepartment.innerText = eReqMessage;
    } else if (!checkTextLengthRange(departmentInput.value, 2, 60)) {
        valid = false;
        departmentInput.classList.add("error-input");
        errorDepartment.innerText = e2_60Message;
    }

    if (!checkRequired(passwordInput.value)) {
        valid = false;
        passwordInput.classList.add("error-input");
        errorPassword.innerText = eReqMessage;
    }

    if (!valid) {
        errorsSummary.innerText = eMessage;
    }

    return valid;
}