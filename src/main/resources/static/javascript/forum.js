$(() => {
    const id = window.location.href.split("catId=")[1]
    getCategoryTitle(id);
    getPosts(id);
})

const getCategoryTitle = id => {
    $.get("/getCategoryTitle?id=" + id, data => {
        $("#categoryTitle").html(data)
    })
}

const getPosts = id => {
    $.get("/getPosts?id=" + id, data => {
        formatData(data);
    })
}

const formatData = data => {
    for (const post of data) {
        const out =
            "<tr style='text-align: center' class='clickable-row' data-href='/'>" +
            "<td>" + post.postID + "</td>" +
            "<td><a href='post.html?id=" + post.postID + "'>" + post.title + "</a></td>" +
            "<td><a href='/user.html?name=" + post.username + "'>" + post.username + "</a></td>" +
            "<td>0</td>" +
            "<td>" + post.time + "</td>" +
            "</tr>"
        $("#forumPosts").append(out)
    }
}

$("#newPostButton").click(() => {
    $.get("getSession", status => {
        if (status) {
            window.location.href = "createPost.html?id=" + window.location.href.split("catId=")[1];
        } else {
            $("#errorOutput").html("You have to log in to create posts");
            $("#errorOutput").css({"color": "red"})
        }
    })
})

