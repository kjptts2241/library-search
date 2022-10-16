function getJSON() {

    var keyword = $("#keyword").val();

    $.ajax({
        
    type: "get",
    url: "/api",
    data: { keyword: keyword },
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    
    success: function (json) {

        console.log("키워드 데이터 받기 성공");

        for (i in json) {
            
        var one = json[i].docs;

        for (j in one) {

            var html = '';
            html += '<div>' + one[j].doc.authors + '</div><br>';
            html += '<div>' + one[j].doc.bookname + '</div><br>';
            html += '<div> isbn 코드 : ' + one[j].doc.isbn13 + '</div><br>';
            html += '<div> 대출 횟수 : ' + one[j].doc.loan_count + ' 권</div><br>';
            html += '<div> 저작 년도 : ' + one[j].doc.publication_year + '</div><br>';
            html += '<div>' + one[j].doc.publisher + '</div><br>';
            html += '<div>' + one[j].doc.vol + ' 권</div><br>';

            $('.table_body').html(html);
        }
    }
    },

    error: function () {
        console.log("키워드 데이터 받기 실패");
    }
    })
}