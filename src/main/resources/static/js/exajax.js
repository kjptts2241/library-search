
// view에 붙여줄 도서 및 도서관 정보 저장 변수
var html = '';

// 도서 배열 변수
var titleInfo = []; // 책 제목
var typeName = []; // 도서 자료 유형
var placeInfo = []; // 자료 있는 곳 명칭
var manageName = []; // 도서관명
var authorInfo = []; // 저작자
var pubInfo = []; // 발행자
var menuName = []; // 온라인/오프라인 자료 구분
var mediaName = []; // 매체 구분
var id = []; // 종키(?)
var licText = []; // 저작권 이용 가능 유무
var regDate = []; // 비치일
var isbn = []; // isbn 13의 끝자리의 값을 들고 온다.(최신 번호 유지가능)
var callNo = []; // 청구기호
var kdcCode1s = []; // 동양서분류기호 대분류 코드
var kdcName1s = []; // 동양서분류기호 대분류 명칭
var imageUrl = []; // 이미지 파일


// [국립 중앙 도서관] 도서 검색
// 도서 검색 결과를 가져오기 위한 함수
function search() {

    var keyword = $("#keyword").val(); // 사용자가 검색한 문자
    console.log("검색한 입력 키워드 : " + keyword);

    
    // 국립중앙도서관 검색 함수 실행
    $.ajax({
        type: "get",
        url: "/api/v1/search",
        data: { keyword: keyword },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        
        success: function (data) {
            console.log("국립중앙 도서관 도서 데이터 받기 성공");

            var result = data.result; // 함수의 전체 데이터인 jsons에서 도서의 정보가 있는 result에 들어간 정보를 result 변수에 저장

            for (i in result) // 5개의 도서 데이터를 가지고 있는 result를 하나씩 가져오게 for문으로 실행시키기
            {

                var isbnNum = result[i].isbn.split(' '); // isbn이 여러개거나 이상한 문자 포함일때 잘라주기

                titleInfo.push(result[i].titleInfo); // 도서명
                typeName.push(result[i].typeName); // 도서 자료 유형
                placeInfo.push(result[i].placeInfo); // 자료 있는 곳 명칭
                manageName.push(result[i].manageName); // 자료 있는 곳 명 (도서관명)
                authorInfo.push(result[i].authorInfo); // 저작자
                pubInfo.push(result[i].pubInfo); // 발행자
                menuName.push(result[i].menuName); // 온라인/오프라인 자료 구분
                mediaName.push(result[i].mediaName); // 매체 구분
                id.push(result[i].id); // 종키(?)
                licText.push(result[i].licText); // 저작권 이용 가능 유무
                regDate.push(result[i].regDate); // 비치일 
                isbn.push(isbnNum[isbnNum.length - 1]); // isbn 13의 끝자리의 값을 들고 온다.(최신 번호 유지가능)
                callNo.push(result[i].callNo); // 청구기호
                kdcCode1s.push(result[i].kdcCode1s); // 동양서분류기호 대분류 코드
                kdcName1s.push(result[i].kdcName1s); // 동양서분류기호 대분류 명칭
                if (result[i].isbn == '') // isbn안에 아무것도 없다면
                {
                    imageUrl.push('<div> 이미지 파일 : 임의의 이미지 파일</div><br>'); // 임의의 이미지를 넣어준다
                }
                if (result[i].isbn != '') // isbn안에 값이 있다면
                {
                    imageUrl.push(searchDetails(isbnNum[isbnNum.length - 1])); // searchDetails()함수를 실행시켜서 ()안에 isbn을 넣어서 실행시킨 다음 return 값을 imageUrl 변수에 저장
                }
            }
        },

        error: function () {
            console.log("검색 결과 받기 실패");
        }
    })

    console.log(JSON.stringify(imageUrl));
}


// [도서 정보 나루] 도서 상세 조회
// 도서 이미지를 가져오기 위한 함수
function searchDetails(isbn) {
    

    $.ajax({
        type: "get",
        url: "/api/v1/searchDetails",
        data: { isbn: isbn }, // 파라미터로 가져온 isbn을 넣어서 실행
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        
        success: function (data) {
            console.log("isbn 가 있는 도서");
            console.log(data);

            for (y in data)
            {
                var bookData = data[y].detail; // 도서 상세 정보인 json 에서 도서 상세 데이터를 가지고 있는 detail로 들어간 정보를 bookData에 저장

                for (z in bookData) {
                    
                    if (bookData[z].book.bookImageURL != ' ') // 가져온 이미지 url이 있다면
                    {
                        return bookData[z].book.bookImageURL; // url을 return
                    }

                    if (bookData[z].book.bookImageURL == '') // 없다면
                    {
                        return '<div> 이미지 파일 : 임의의 이미지 파일</div><br>'; // 임의의 이미지를 return
                    }
                }
            }
        },
        error: function () {
            console.log("도서 이미지 받기 실패");
        }

    })
}

// html 클릭하면 실행시키게
// function bookExist() {}