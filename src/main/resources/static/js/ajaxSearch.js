function getJSON() {

    var keyword = $("#keyword").val();
    
    // 키워드를 집어넣음으로서 api 데이터 추출 (isbn13 코드만 들고오기)
    $.ajax({
        type: "get",
        url: "/api",
        data: { keyword: keyword },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        astnc: false,
        
        success: function (json) {

            console.log("키워드 데이터 받기 성공");

            for (i in json) {
                var one = json[i].docs;

                for (j in one) {
          
                    // isbn13코드를 for문으로 api url에 집어넣음으로서 도서 데이터를 가져오기
                    $.ajax({
                        type: "get",
                        url: "/apiDetails",
                        data: { isbn13: one[j].doc.isbn13 },
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        astnc: false,
                        
                        success: function (json) {
                            console.log("키워드 데이터 받기 성공");
                            console.log(json)

                            for (y in json) {
                                var two = json[y].detail;

                                for (z in two) {
                                    var html = '';
                                    html += '<div>' + two[z].book.bookname + '</div><br>';
                                    html += '<div>' + two[z].book.authors + '</div><br>';
                                    html += '<div> 출판사 : ' + two[z].book.publisher + '</div><br>';
                                    html += '<div> 대출건수 : ' + two[z].book.class_no + ' 권</div><br>';
                                    html += '<div> 분류 : ' + two[z].book.class_nm + '</div><br>';
                                    html += '<div> 출시년도 : ' + two[z].book.publication_year + '</div><br>';
                                    html += '<div> 출시일 : ' + two[z].book.publication_date + '</div><br>';
                                    html += '<div> 이미지 url : ' + two[z].book.bookImageURL + '</div><br>';
                                    html += '<div> isbn : ' + two[z].book.isbn + '</div><br>';
                                    html += '<div> isbn13 : ' + two[z].book.isbn13 + '</div><br>';
                                    html += '<div> 작품 소개 : ' + two[z].book.description + '</div><br>';

                                    $('.table_body').html(html);
                                }
                            }
                        }             
                    })
                }
            }
           
        },

        error: function () {
            console.log("키워드 데이터 받기 실패");
        }
    })
}