$("#registerButton").click(() => {
    clearErrorFields();
    const userInfo = getUserInfo();
    if (userInfo === false) return
    $.post("/registerUser", userInfo, () => {
        logIn(userInfo);
    })
})

const logIn = userInfo => {
    const credentials = {
        username: userInfo.username,
        password: userInfo.password
    }
    $.get("/loginUser", credentials, status => {
        if (status) {
            document.location.href = "/"
        }
    })
}

const getUserInfo = () => {
    const username = $("#usernameRegisterInput").val()
    const email = $("#emailRegisterInput").val()
    const password = $("#passwordRegisterInput").val()
    const passwordRecheck = $("#passwordRecheckRegisterInput").val()
    if (!validateInputs(username, email, password, passwordRecheck)) return false
    const userInfo = {
        username: username,
        email: email,
        password: password
    }
    return userInfo
}

const validateInputs = (username, email, password, passwordRecheck) => {
    const user = validateUsername(username)
    const mail = validateEmail(email)
    const pass = validatePassword(password)
    const passRecheck = validatePasswordRecheck(password, passwordRecheck)
    if (user === true && mail === true && pass === true && passRecheck === true) return true
    else {
        if (user !== true)
            $("#userNameErrorOutput").html(user)
        if (mail !== true)
            $("#emailErrorOutput").html(mail)
        if (pass !== true)
            $("#passwordErrorOutput").html(pass)
        if (passRecheck !== true)
            $("#passwordRecheckErrorOutput").html(passRecheck)
        return false
    }
}

const clearErrorFields = () => {
    $("#userNameErrorOutput").html("")
    $("#emailErrorOutput").html("")
    $("#passwordErrorOutput").html("")
    $("#passwordRecheckErrorOutput").html("")
}