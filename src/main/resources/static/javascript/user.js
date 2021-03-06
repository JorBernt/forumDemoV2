const username = window.location.href.split("name=")[1];

$(() => {
    $("#userName").html("Posts by: " + username)
    getUserPost();
})

const getUserPost = () => {
    $.get("/getUserPosts?username=" + username, data => {
        formatData(data)
    })
}

const formatData = data => {
    for (const post of data) {
        const out =
            "<tr style='text-align: center'>" +
            "<td><a href='post.html?id=" + post.postID + "'>" + post.title + "</a></td>" +
            "<td>" + post.text + "</td>" +
            "<td>" + post.category + "</td>" +
            "<td>" + post.time + "</td>" +
            "</tr>"
        $("#forumPosts").append(out)
    }
}

