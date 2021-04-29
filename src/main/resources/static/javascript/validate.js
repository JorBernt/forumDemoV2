const validateEmail = email => {
    if (email.includes("@")) return true
    return "You must input a valid email address."
}

const validatePassword = password => {
    const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    if (regex.test(password)) return true
    return "You must input a valid password (Minimum 8 characters, 1 digit, 1 upper- and lower case character."
}

const validatePasswordRecheck = (password, passwordRecheck) => {
    if (password === passwordRecheck) return true
    else return "Passwords do not match."
}

const validateUsername = usernameInput => {
    const regex = /^(?!.*\.\.)(?!.*\.$)[^\W][\w.]{0,29}$/;
    if (regex.test(usernameInput)) {
        if (nameAvailable(usernameInput) == "true") {
            return true;
        } else return "Username taken, choose another."
    } else {
        return "Invalid username."
    }
}

const nameAvailable = username => {
    let available = false;
    $.ajax({
        url: "/usernameAvailable?username=" + username,
        dataType: "html",
        async: false,
        success: data => {
            available = data;
        }
    })
    return available
}