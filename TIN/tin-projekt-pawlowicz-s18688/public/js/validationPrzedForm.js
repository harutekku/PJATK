function validateForm() {
    const NameInput = document.getElementById('name');
    const shortcutInput = document.getElementById('shortcut');
    const departmentInput = document.getElementById('department');

    const errorName = document.getElementById('errorName');
    const errorShortcut = document.getElementById('errorShortcut');
    const errorDepartment = document.getElementById('errorDepartment');
    const errorsSummary = document.getElementById('errorsSummary');


    resetErrors([NameInput, shortcutInput, departmentInput], [errorName, errorShortcut, errorDepartment], errorsSummary);

    let valid = true;


    if (!checkRequired(NameInput.value)) {
        valid = false;
        NameInput.classList.add("error-input");
        errorName.innerText = "Pole jest wymagane";
    } else if (!checkTextLengthRange(NameInput.value, 2, 60)) {
        valid = false;
        NameInput.classList.add("error-input");
        errorName.innerText = "Pole powinno zawierać od 2 do 60 znaków";
    }

    if (!checkRequired(shortcutInput.value)) {
        valid = false;
        shortcutInput.classList.add("error-input");
        errorShortcut.innerText = "Pole jest wymagane";
    } else if (!checkTextLengthRange(shortcutInput.value, 3, 3)) {
        valid = false;
        shortcutInput.classList.add("error-input");
        errorShortcut.innerText = "Pole powinno zawierać 3 znaki";
    }

    if (!checkRequired(departmentInput.value)) {
        valid = false;
        departmentInput.classList.add("error-input");
        errorDepartment.innerText = "Pole jest wymagane";
    } else if (!checkTextLengthRange(departmentInput.value, 2, 60)) {
        valid = false;
        departmentInput.classList.add("error-input");
        errorDepartment.innerText = "Pole powinno zawierać od 2 do 60 znaków";
    }

    if (!valid) {
        errorsSummary.innerText = "Formularz zawiera błędy";
    }

    return valid;
}