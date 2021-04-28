$("#registerButton").click(() => {
    const userInfo = getUserInfo();
    $.post("/registerUser", userInfo, () => {
        logIn(userInfo);
    })
})

const logIn = userInfo => {
    const credentials = {
        username : userInfo.username,
        password : userInfo.password
    }
    $.get("/loginUser", credentials, status => {
        if(status) {
            document.location.href = "/"
        }
    })
}

const getUserInfo = () => {
    if(!validateInputs()) return false
    const userInfo = {
        username : $("#usernameRegisterInput").val(),
        email : $("#emailRegisterInput").val(),
        password : $("#passwordRegisterInput").val()
    }
    return userInfo
}

const validateInputs = () => {
    return true
}