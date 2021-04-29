$(() => {
    $.get("/getSession", status => {
        addButtons(status)
    })
})


const addButtons = (loggedIn) => {

    const navbar = $("#navbarButtons");
    if (loggedIn) {
        const logoutButton =
            "<li class='nav-item'>" +
            "<a class='nav-link' href='/' onclick='logOut()'>Log out</a>" +
            "</li>"
        const userButton =
            "<li class='nav-item'>" +
            "<a class='nav-link' href='user.html?name=" + getUserName() + "'>" + getUserName() + "</a>" +
            "</li>"
        navbar.append(logoutButton)
        navbar.append(userButton)
    } else {
        const loginButton =
            "<li class='nav-item'>" +
            "<a class='nav-link' href='login.html'>Log in</a>" +
            "</li>"
        const registerButton =
            "<li class='nav-item'>" +
            "<a class='nav-link' href='register.html'>Register</a>" +
            "</li>"
        navbar.append(registerButton)
        navbar.append(loginButton)
    }
}

const logOut = () => {
    $.get("/logOut", () => {
        window.location.href = "/"
    })
}

const getUserName = () => {
    let username = null;
    $.ajax({
        url: "/sessionUserName",
        dataType: "html",
        async: false,
        success: data => {
            username = data;
        }
    })
    return username;
}
