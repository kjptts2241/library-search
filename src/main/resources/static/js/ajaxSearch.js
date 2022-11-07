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
            console.log("국립중앙 도서관 도서 데이터 받기 성공");

            var result = jsons.result; // 함수의 전체 데이터인 jsons에서 도서의 정보가 있는 result에 들어간 정보를 result 변수에 저장
            console.log(result[0]);
ㅇ
            for (i in result) // 5개의 도서 데이터를 가지고 있는 result를 하나씩 가져오게 for문으로 실행시키기
            {
                console.log(i + '번째 도서')
                html += '======================================================================'
                html += '<div>' + i + ' 번째 도서</div><br>';
                html += '<div> 제목 : ' + result[i].titleInfo + '</div><br>';
                html += '<div> 표제 리스트 : ' + result[i].authorInfo + '</div><br>';
                html += '<div> 자료 유형 : ' + result[i].typeName + '</div><br>';
                html += '<div> 자료 있는 곳 명칭 : ' + result[i].placeInfo + '</div><br>';
                html += '<div> 저작자 : ' + result[i].authorInfo + '</div><br>'; // 응애
                html += '<div> 발행자 : ' + result[i].pubInfo + '</div><br>';
                html += '<div> 메뉴명 : ' + result[i].menuName + '</div><br>';
                html += '<div> 메체 구분 : ' + result[i].mediaName + '</div><br>';
                html += '<div> 자료 있는 곳 명 : ' + result[i].manageName + '</div><br>';
                html += '<div> 발행년도사항 : ' + result[i].docYn + '</div><br>';
                html += '<div> 제어번호 : ' + result[i].orgLink + '</div><br>';
                html += '<div> 종키 : ' + result[i].id + '</div><br>';
                html += '<div> 자료유형코드 : ' + result[i].typeCode + '</div><br>';
                html += '<div> 저작권유무 : ' + result[i].licYn + '</div><br>';
                html += '<div> 저작권 설명 : ' + result[i].licText + '</div><br>';
                html += '<div> 비치일 : ' + result[i].regDate + '</div><br>';
                html += '<div> isbn : ' + result[i].isbn + '</div><br>';
                html += '<div> 청구기호 : ' + result[i].callNo + '</div><br>';
                html += '<div> 동양서분류기호 대분류 코드 : ' + result[i].kdcCode1s + '</div><br>';
                html += '<div> 동양서분류기호 대분류 명칭 : ' + result[i].kdcName1s + '</div><br>';
                if (result[i].isbn == '') // isbn이 null이면 임의의 이미지 파일 넣기
                {
                    html += '<div> 이미지 파일 : 임의의 이미지 파일</div><br>';
                }

                var isbnNum = result[i].isbn.split(' '); // isbn이 여러개거나 이상한 문자 포함일때 잘라주기

                if (isbnNum[0] != '') // 가져온 도서 중 isbn안에 값이 null 아니라면 실행
                {
                    $.ajax({
                        type: "get",
                        url: "/apiDetails",
                        data: { isbn13: isbnNum[0] }, // result에서 순서대로 0, 1, 2, 3, 4 번째에 있는 isbn을 빼서 도서정보나루의 도서 상세 정보 함수 실행
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        async: false,
                        
                        success: function (json) { // 도서정보나루 도서 상세 정보 함수의 전체 데이터 (json)
                            console.log("isbn 가 있는 도서");
    
                            for (y in json) {
    
                                var bookData = json[y].detail; // 도서 상세 정보인 json 에서 도서 상세 데이터를 가지고 있는 detail로 들어간 정보를 bookData에 저장
    
                                for (z in bookData) { // html에 도서 정보들 저장
                                    
                                    if (bookData[z].book.bookImageURL != '') // 이미지 파일이 있으면 넣고
                                    {
                                        html += '<div> 도서 이미지 URL : ' + bookData[z].book.bookImageURL + '</div><br>';
                                    }

                                    if (bookData[z].book.bookImageURL == '') // 없으면 임의의 파일 저장
                                    {
                                        html += '<div> 이미지 파일 : 임의의 이미지 파일2</div><br>';
                                    }

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