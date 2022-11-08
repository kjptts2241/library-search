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

            for (i in result) // 5개의 도서 데이터를 가지고 있는 result를 하나씩 가져오게 for문으로 실행시키기
            {

                var isbnNum = result[i].isbn.split(' '); // isbn이 여러개거나 이상한 문자 포함일때 잘라주기

                console.log(i + '번째 도서')
                html += '======================================================================'
                html += '<div>' + i + ' 번째 도서</div><br>'; // 도서 순서 확인
                html += '<div> 제목 : ' + result[i].titleInfo + '</div><br>'; // 도서명
                html += '<div> 자료 유형 : ' + result[i].typeName + '</div><br>'; // 도서 자료 유형
                html += '<div> 자료 있는 곳 명칭 : ' + result[i].placeInfo + '</div><br>'; // 자료 있는 곳 명칭
                html += '<div> 자료 있는 곳 명 : ' + result[i].manageName + '</div><br>'; // 자료 있는 곳 명 (도서관명)
                html += '<div> 저작자 : ' + result[i].authorInfo + '</div><br>'; // 저작자
                html += '<div> 발행자 : ' + result[i].pubInfo + '</div><br>'; // 발행자
                html += '<div> 온라인/오프라인 : ' + result[i].menuName + '</div><br>'; // 온라인/오프라인 자료 구분
                html += '<div> 메체 구분 : ' + result[i].mediaName + '</div><br>'; // 매체 구분
                //html += '<div> 원문유무 : ' + result[i].docYn + '</div><br>';
                // html += '<div> 원문링크 : ' + result[i].orgLink + '</div><br>';
                html += '<div> 종키 : ' + result[i].id + '</div><br>'; // 종키(?)
                //html += '<div> 자료유형코드 : ' + result[i].typeCode + '</div><br>';
                //html += '<div> 저작권유무 : ' + result[i].licYn + '</div><br>';
                html += '<div> 저작권 이용 가능 유무 : ' + result[i].licText + '</div><br>'; // 저작권 이용 가능 유무
                html += '<div> 비치일 : ' + result[i].regDate + '</div><br>'; // 비치일 
                html += '<div> isbn : ' + isbnNum[isbnNum.length - 1] + '</div><br>'; // isbn 13의 끝자리의 값을 들고 온다.(최신 번호 유지가능)
                html += '<div> 청구기호 : ' + result[i].callNo + '</div><br>'; // 청구기호
                html += '<div> 동양서분류기호 대분류 코드 : ' + result[i].kdcCode1s + '</div><br>'; // 동양서분류기호 대분류 코드
                html += '<div> 동양서분류기호 대분류 명칭 : ' + result[i].kdcName1s + '</div><br>'; // 동양서분류기호 대분류 명칭
                if (result[i].isbn == '') // isbn안에 아무것도 없다면
                {
                    html += '<div> 이미지 파일 : 임의의 이미지 파일</div><br>'; // 임의의 이미지를 넣어준다
                }
                
                if (isbnNum[0] != '') // 가져온 도서 중 isbn의 값이 있거나 여러개라면
                {
                    $.ajax({ // 도서 대출 여부 확인
                        type: "get",
                        url: "/apibookExist",
                        data: { isbn13: isbnNum[isbnNum.length - 1], libCode: 127058}, // 도서 데이터가 담긴 result안에 isbn을 가져와서 파라미터를 넣어준다(isbn이 여러개라면 split로 잘라준 isbn 마지막 부분을 넣어준다.)
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        async: false,
                        
                        success: function (json) { // 도서정보나루 도서 상세 정보 함수의 전체 데이터 (json)
                            console.log(json);
                        }
                    })

                    $.ajax({ // 도서 정보 나루의 도서 상세 api를 사용하여 도서의 이미지 url를 가져와준다
                        type: "get",
                        url: "/apiDetails",
                        data: { isbn13: isbnNum[isbnNum.length - 1] }, // 도서 데이터가 담긴 result안에 isbn을 가져와서 파라미터를 넣어준다(isbn이 여러개라면 split로 잘라준 isbn 마지막 부분을 넣어준다.)
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        async: false,
                        
                        success: function (json) { // 도서정보나루 도서 상세 정보 함수의 전체 데이터 (json)
                            console.log("isbn 가 있는 도서");
     
                            for (y in json) {
    
                                var bookData = json[y].detail; // 도서 상세 정보인 json 에서 도서 상세 데이터를 가지고 있는 detail로 들어간 정보를 bookData에 저장
    
                                for (z in bookData) {
                                    
                                    if (bookData[z].book.bookImageURL != ' ') // 가져온 이미지 url이 있다면
                                    {
                                        html += '<div> 도서 이미지 URL : ' + bookData[z].book.bookImageURL + '</div><br>'; // 해당 도서의 이미지 url을 넣어준다
                                    }

                                    if (bookData[z].book.bookImageURL == '') // 없다면
                                    {
                                        html += '<div> 이미지 파일 : 임의의 이미지 파일2</div><br>'; // 임의의 이미지를 넣어준다
                                    }

                                    $('.table_body').html(html); // html 변수에 넣어준 도서의 데이터들을 subsearch에 있는 table_body class에 붙여준다.
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