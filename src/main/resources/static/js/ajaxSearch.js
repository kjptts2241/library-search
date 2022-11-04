function getJSON() {

    var keyword = $("#keyword").val();
    console.log(keyword);
    var html = '';
    
    $.ajax({ // 국립중앙도서관 검색 함수 실행
        type: "get",
        url: "/api",
        data: { keyword: keyword },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        
        success: function (jsons) { // 국립정보도서관 검색 함수의 전체 데이터 (jsons)
            console.log("키워드 데이터 받기 성공");

            var result = jsons.result; // 함수의 전체 데이터인 jsons에서 도서의 정보가 있는 result에 들어간 정보를 result 변수에 저장

            for (i in result) // 5개의 도서 데이터를 가지고 있는 result를 하나씩 가져오게 for문으로 실행시키기
            {

                $.ajax({
                    type: "get",
                    url: "/apiDetails",
                    data: { isbn13: result[i].isbn }, // result에서 순서대로 0, 1, 2, 3, 4 번째에 있는 isbn을 빼서 도서정보나루의 도서 상세 정보 함수 실행
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    async: false,
                    
                    success: function (json) { // 도서정보나루 도서 상세 정보 함수의 전체 데이터 (json)
                        console.log(i + " 번째 도서 데이터 받기");

                        for (y in json) {

                            var bookData = json[y].detail; // 도서 상세 정보인 json 에서 도서 상세 데이터를 가지고 있는 detail로 들어간 정보를 bookData에 저장
                            console.log(bookData);

                            for (z in bookData) { // html에 도서 정보들 저장
                                html += '======================================================================'
                                html += '<div>' + i + ' 번째 도서</div><br>';
                                html += '<div> 도서이름 : ' + bookData[z].book.bookname + '</div><br>';
                                html += '<div>' + bookData[z].book.authors + '</div><br>';
                                html += '<div> 출판사 : ' + bookData[z].book.publisher + '</div><br>';
                                html += '<div> 대출건수 : ' + bookData[z].book.class_no + ' 권</div><br>';
                                html += '<div> 분류 : ' + bookData[z].book.class_nm + '</div><br>';
                                html += '<div> 출시년도 : ' + bookData[z].book.publication_year + '</div><br>';
                                html += '<div> 출시일 : ' + bookData[z].book.publication_date + '</div><br>';
                                html += '<div> 도서 이미지 URL : ' + bookData[z].book.bookImageURL + '</div><br>';
                                html += '<div> isbn : ' + bookData[z].book.isbn + '</div><br>';
                                html += '<div> isbn13 : ' + bookData[z].book.isbn13 + '</div><br>';
                                html += '<div> 작품 소개 : ' + bookData[z].book.description + '</div><br>';

                                $('.table_body').html(html); // 웹페이지 붙이기

                            }
                        }
                    }
                })

            }
        },

        error: function () {
            console.log("키워드 데이터 받기 실패");
        }
    })
}