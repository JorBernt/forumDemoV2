$(()=> {
    $.get("/getCategories", data => {
        formatData(data)
    })
        .fail(status => {
            if(status === 500) {

            }
        })
})

const formatData = data => {
    for(const cat of data) {
        const out =
            "<a href='/forum.html?catId="+cat.catID+"' class='list-group-item list-group-item-action'>" +
            "<div class='d-flex w-100 justify-content-between'>" +
            "<h4>"+cat.title+"</h4>" +
            "</div>" +
            "<p>"+cat.description+"</p>" +
            "</a>"
        $("#forumCategories").append(out)
    }
}

