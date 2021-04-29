const id = window.location.href.split("id=")[1]

$("#createComment").click(() => {
    const comment = {
        text: $("#postText").val(),
        username: getUserName(),
        postID: id
    }
    $.post("/createComment", comment, () => {
        window.location.href = "/post.html?id=" + id;
    })
})

$("#backButton").click(() => {
    window.location.href = "/post.html?id=" + id
})
