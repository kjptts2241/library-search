function getJSON() {

    var keyword = $("#keyword").val();

    $.ajax({
        type: "post",
        url: "/api/v1/SearchList",
        data: { keyword: keyword },

        success: function (list) {
            console.log('라이브러리 데이터 : ' + list);

        }
    })

    // $.ajax({
    //     type: "get",
    //     url: "/api/v1/librarylist",

    //     success: function (list) {
    //         console.log('라이브러리 데이터 : ' + list);

    //     }
    // })
}


