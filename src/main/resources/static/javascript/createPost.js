const id = window.location.href.split("id=")[1]

$("#createPost").click(()=> {
    const username = getUserName()

    const post = {
        title : $("#postTitle").val(),
        username : username,
        catID : id
    }
    $.post("/createPost", post, id => {
        createComment(id)
    })
})

const createComment = id => {
    const comment = {
        text : $("#postText").val(),
        username : getUserName(),
        postID : id
    }
    $.post("/createComment", comment, () => {
        window.location.href="/post.html?id="+id;
    })
}

$("#backButton").click(()=> {
    window.location.href = "/forum.html?catId="+id
})

