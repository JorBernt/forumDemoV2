const id = document.location.href.split("id=")[1];
$(()=>{
    getTitle();
    getComments();
})

const getTitle = () => {
    $.get("/getPostTitle?id="+id, title => {
        $("#postTitle").html(title)
    })
}

const getComments = () => {
    $.get("/getComments?id="+id, data => {
        formatData(data);
    })
}

const formatData = data => {
    for(const comment of data) {
        const out =
            "<tr>" +
            "<td><a href='/user.html?name="+comment.username+"'>"+comment.username+"</a></td>" +
            "<td style='word-wrap: break-word;min-width: 160px;max-width: 160px;'>"+comment.text+"</td>" +
            "<td>"+comment.time+"</td>" +
            "</tr>"
        $("#comments").append(out)
    }
}

$("#newCommentButton").click(()=>{
    $.get("getSession", status => {
        if(status) {
            window.location.href = "/comment.html?id="+id;
        }
        else {
            $("#errorOutput").html("You have to log in to comment.");
            $("#errorOutput").css({"color":"red"})
        }
    })
})

$("#backButton").click(()=> {
    window.location.href = "/forum.html?catId="+id
})

