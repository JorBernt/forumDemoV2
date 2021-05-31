$("#logInButton").click(() => {
    const credentials = {
        username: $("#usernameLoginInput").val(),
        password: $("#passwordLoginInput").val()
    }
    $.get("/loginUser", credentials, status => {
        if (status === true) {
            window.location.href = "/"
        } else {
            $("#loginFailOutput").html("Wrong username or password.")
            $("#loginFailOutput").css({"color": "red"})
        }
    })
})

$(document).bind('keypress', e => {
    if(e.keyCode === 13) {
        $("#logInButton").trigger('click')
    }
})