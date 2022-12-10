
var mainKeyword; // search에서 가져온 값

$(document).ready(function (){ // 페이지 들어왔을 때 실행

    if(localStorage.getItem('mainKeyword')){

        mainKeyword = localStorage.getItem('mainKeyword'); // search 페이지에서 보낸 값을 받아오기

        search(); // 들어갔을 때 함수 한번 실행
        
    }
    else
    {
        console.log('search 페이지에서 받아온 값이 없습니다');
    }

})


// 엔터키 눌렀을 때 search() 실행
function enterkey() {
    if (window.event.keyCode == 13) {
        search();
        $("#keyword").val(''); // 검색 후 input창 초기화
      }
}


// [국립 중앙 도서관] 도서 검색
// 도서 검색 결과를 가져오기 위한 함수
function search() {


    // view에 붙여줄 도서 데이터 저장 변수
    var bookList = '';

    // 도서 배열 변수
    var booksInt = []; // 불러온 도서의 수
    var titleInfo = []; // 책 제목
    var typeName = []; // 도서 자료 유형
    var placeInfo = []; // 자료 있는 곳 명칭
    var manageName = []; // 자료 있는 곳 명
    var authorInfo = []; // 저작자
    var pubInfo = []; // 발행자
    var menuName = []; // 온라인/오프라인 자료 구분
    var mediaName = []; // 매체 구분
    var id = []; // 종키
    var licText = []; // 저작권 이용 가능 유무
    var regDate = []; // 비치일
    var isbn = []; // isbn 13의 끝자리의 값을 들고 온다.(최신 번호 유지가능)
    var callNo = []; // 청구기호
    var kdcCode1s = []; // 동양서분류기호 대분류 코드
    var kdcName1s = []; // 동양서분류기호 대분류 명칭
    var imageUrl = []; // 이미지 파일


    var keyword; // ajax에 넣어줄 최종 검색 값

    var subKeyword = $("#keyword").val(); // 검색 input 값

    // search에서 가져온 값을 넣지만 subsearch에서 값이 있다면 바꿔서 넣어주기
    if (mainKeyword != '') // search에서 가져온 값이 있다면 검색 keyword 넣기
    {
        keyword = mainKeyword;
    }
    if (subKeyword != '') // subsearch에서 값이 있다면 바꿔넣기
    {
        keyword = subKeyword; // search에서 가져와서 넣어준 값을 subsearch의 값으로 대체
    }


    console.log("검색한 입력 키워드 : " + keyword);

    // 국립중앙도서관 검색 함수 실행
    $.ajax({
        type: "get",
        url: "/auth/api/v1/search",
        data: { keyword: keyword },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        
        success: function (data) {
            console.log("국립중앙 도서관 도서 데이터 받기 성공");
            // 국립중앙 도서관의 var 태그 와 전혀 다른 데이터 태그들이 추출됨 도서 정보 나루의 도서 상세 조회로 출력

            var result = data.result; // 함수의 전체 데이터인 jsons에서 도서의 정보가 있는 result에 들어간 정보를 result 변수에 저장

            for (i in result) // 5개의 도서 데이터를 가지고 있는 result를 하나씩 가져오게 for문으로 실행시키기
            {

                var isbnNum = result[i].isbn.split(' '); // isbn이 여러개거나 이상한 문자 포함일때 잘라주기
        
                booksInt.push(i); // 불러온 도서의 수
                titleInfo.push(result[i].titleInfo.replace(/(<([^>]+)>)|\'/ig,"")); // 도서명 titleInfo를  ( result[i]에 넣음 [i] 리스트 가져오는 값에 달라짐 push는 넣기 ) (replace로 문자열에 있는 태그및 따옴표 삭제)
                typeName.push(result[i].typeName); // 도서 자료 유형
                placeInfo.push(result[i].placeInfo); // 자료 있는 곳 명칭
                manageName.push(result[i].manageName); // 자료 있는 곳 명
                authorInfo.push(result[i].authorInfo.replace(/(<([^>]+)>)/ig,"")); // 저작자
                pubInfo.push(result[i].pubInfo.replace(/(<([^>]+)>)/ig,"")); // 발행자
                menuName.push(result[i].menuName); // 온라인/오프라인 자료 구분
                mediaName.push(result[i].mediaName); // 매체 구분
                id.push(result[i].id); // 종키
                licText.push(result[i].licText); // 저작권 이용 가능 유무
                regDate.push(result[i].regDate); // 비치일 
                isbn.push(isbnNum[isbnNum.length - 1]); // isbn 13의 끝자리의 값을 들고 온다.(최신 번호 유지가능)
                callNo.push(result[i].callNo); // 청구기호
                kdcCode1s.push(result[i].kdcCode1s); // 동양서분류기호 대분류 코드
                kdcName1s.push(result[i].kdcName1s); // 동양서분류기호 대분류 명칭
                if (result[i].isbn == '') // isbn안에 아무것도 없다면
                {
                    imageUrl.push('/images/book.png'); // 임의의 이미지를 넣어준다
                }
                if (result[i].isbn != '') // isbn안에 값이 있다면
                {
                    imageUrl.push(searchDetails(isbnNum[isbnNum.length - 1])); // searchDetails()함수를 실행시켜서 ()안에 isbn을 넣어서 실행시킨 다음 return 값을 imageUrl 변수에 저장
                }
            }
        },

        error: function () {
            console.log("검색 결과 받기 실패 ㅠㅠ");
        }
    })
    

    // html에 분류하여 저장 및 subsearch에 업로드
    for (var i = 0; i < booksInt.length; i++)
    {
        bookList += '<div class="box-container">';
        bookList += '<div class="img-box">';
        bookList += '<img src="' + imageUrl[i] +'" class="book-img">';
        bookList += '</div>';
        bookList += '<div class="info-box">';
        bookList += '<p class="title">' + titleInfo[i] + '</p>';
        bookList += '<p class="con"> 저작자 : ' + authorInfo[i] + '</p>';
        bookList += '<p class="con"> 발행자 : ' + pubInfo[i] + '</p>';
        bookList += '</div>';
        bookList += '<div class="btn-box">';
        bookList += '<a onclick="book(\''+ titleInfo[i] + '\',\'' + typeName[i] + '\',\'' + placeInfo[i] + '\',\'' + manageName[i] + '\',\'' + authorInfo[i] + '\',\'' + pubInfo[i] + '\',\'' + menuName[i] + '\',\'' + mediaName[i] + '\',\'' + id[i] + '\',\'' + licText[i] + '\',\'' + regDate[i] + '\',\'' + isbn[i] + '\',\'' + callNo[i] + '\',\'' + kdcCode1s[i] + '\',\'' + kdcName1s[i] + '\',\'' + imageUrl[i] + '\')" data-bs-toggle="modal" href="#popup_box_book" title="Button fade purple" class="button btnFade btnLightBlue">도서 정보</a>';
        bookList += '<a onclick="library(' + isbn[i] + ')" data-bs-toggle="modal" href="#popup_box_library" title="Button fade lightblue" class="button btnFade btnPurple">도서관 위치</a>';
        bookList += '</div>';
        bookList += '</div>';
    }

    $('#search_body').html(bookList); // 도서 데이터들을 웹페이지에 넣어주기
    
}


// [도서 정보 나루] 도서 상세 조회
// 도서 이미지를 가져오기 위한 함수
function searchDetails(isbn) {
    
    var ex_imageUrl = '';

    $.ajax({
        type: "get",
        url: "/auth/api/v1/searchDetails",
        data: { isbn: isbn }, // 파라미터로 가져온 isbn을 넣어서 실행
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        
        success: function (data) {

            for (y in data)
            {
                var detail = data[y].detail; // 도서 상세 정보인 json 에서 도서 상세 데이터를 가지고 있는 detail로 들어간 정보를 bookData에 저장


                for (z in detail) { // z
                    
                    if (detail[z].book.bookImageURL != '') // 가져온 이미지 url이 있다면
                    {
                        ex_imageUrl = detail[z].book.bookImageURL;
                        return;
                    }

                    if (detail[z].book.bookImageURL == '') // 없다면
                    {
                        ex_imageUrl = '/images/book.png';
                        return;
                    }
                }
            }
        },
        
        error: function () {
            console.log("도서 이미지 받기 실패 ㅠㅠ");
        }
    })

    return ex_imageUrl;
    
}   


// 도서관 데이터 들고 오기
// 해당 도서(isbn)를 가지고 있는 도서관 데이터들을 가져오기 위한 함수
// 지역 코드 : region=00:11;21;22 서울 부산 대구 순으로 정렬됨
function library(isbn) {

    // 지역 코드 변수
    // var mapCode = [11, 21, 22, 23, 24, 25, 26, 29, 31, 32, 33, 34, 35, 36, 37, 38, 39];

    // 팝업에 들어갈 도서관 데이터
    var libraryList = '';

    // for (var i = 0; i < mapCode.length; i++)
    // {
        $.ajax({

            type: "get",
            url: "/auth/api/v1/bookCollection",
            data: { isbn: isbn, region: 21 },
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: false,
            
            success: function (data) {

                var libs = data.response.libs; // 가져온 json에서 도서관 배열만 꺼내기

                for (j in libs)
                {
                    libraryList += '<h5>' + libs[j].lib.libName + '</h5>'; // 도서관명
                    libraryList += '<br>';
                    libraryList += '<p> 도서관 주소 : ' + libs[j].lib.address + '</p>'; // 도서관 주소
                    libraryList += '<p> 전화번호 : ' + libs[j].lib.tel + '</p>'; // 전화번호
                    libraryList += '<p> 팩스 : ' + libs[j].lib.fax + '</p>'; // 팩스
                    libraryList += '<p> 운영 시간 : ' + libs[j].lib.operatingTime + '</p>'; // 운영 시간
                    libraryList += '<p> 닫는 시간 : ' + libs[j].lib.closed + '</p>'; // 닫는 시간
                    libraryList += '<p> 홈페이지 : ' + libs[j].lib.homepage + '</p>'; // 홈페이지
                    libraryList += '<p> 위도 : ' + libs[j].lib.latitude + '</p>'; // 위도
                    libraryList += '<p> 경도 : ' + libs[j].lib.longitude + '</p>'; // 경도
                    libraryList += '<p> 도서관 코드 : ' + libs[j].lib.libCode + '</p>'; // 도서관 코드
                    libraryList += '<p> 도서 소장 및 대출 여부 : ' + booksPossession(isbn, libs[j].lib.libCode) + '</p>'; // 해당 도서의 소장 및 대출 여부
                    libraryList += '<a href="#" role="button" class="btn btn-secondary popover-test" title="Popover title" data-bs-content="Popover body content is set in this attribute.">도서관 검색</a>';
                    libraryList += '<hr>';
                }
                
                $('#library_body').html(libraryList); // 팝업 바디에 도서관 데이터 넣어주기

            },

            error: function () {
                console.log("도서관 데이터 받기 실패 ㅠㅠ");
            }
        })
    // }
}

// [도서 정보 나루] 도서관별 도서 소장여부 및 대출 가능여부 조회
// 도서별 도서관 소장 및 대출 여부를 가져오기 위한 함수
function booksPossession(isbn, libCode) {

    $.ajax({

        type: "get",
        url: "/auth/api/v1/bookExist",
        data: { isbn: isbn, libCode: libCode },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        
        success: function (data) {

            console.log(data);
            console.log(data.result); // 여기서 부터 json에서 나눠서 // y면 가능 x면 불가  어쩌고 만들어놓기

        },

        error: function () {
            console.log("도서 소장 및 대출 여부 받기 실패 ㅠㅠ");
        }
    })
}


// [도서 상세내용] 버튼 클릭시 팝업에 출력
function book(titleInfo, typeName, placeInfo, manageName, authorInfo, pubInfo, menuName, mediaName, id, licText, regDate, isbn, callNo, kdcCode1s, kdcName1s, imageUrl) {

    // 팝업에 들어갈 도서 데이터
    var bookData = '';
    // 팝업에 들어갈 도서 저장 버튼
    var bookSave = '';

    bookData += '<img src="' + imageUrl +'" class="book-img">'; // 이미지 URL
    bookData += '<h5>' + titleInfo + '</h5>'; // 도서명
    bookData += '<br>';
    bookData += '<p> 도서 자료 유형 : ' + typeName + '</p>'; // 도서 자료 유형
    bookData += '<p> 저작자 : ' + authorInfo + '</p>'; // 저작자
    bookData += '<p> 발행자 : ' + pubInfo + '</p>'; // 발행자
    bookData += '<p> 온라인/오프라인 자료 구분 : ' + menuName + '</p>'; // 온라인/오프라인 자료 구분
    bookData += '<p> 매체 구분 : ' + mediaName + '</p>'; // 매체 구분
    // bookData += '<p> 종키 : ' + id + '</p>'; // 종키
    bookData += '<p> 저작권 이용 가능 유무 : ' + licText + '</p>'; // 저작권 이용 가능 유무
    bookData += '<p> 비치일  : ' + regDate + '</p>'; // 비치일
    bookData += '<p> isbn : ' + isbn + '</p>'; // isbn
    bookData += '<p> 청구기호 : ' + callNo + '</p>'; // 청구기호
    bookData += '<p> 동양서분류기호 대분류 코드 : ' + kdcCode1s + '</p>'; // 동양서분류기호 대분류 코드
    bookData += '<p> 동양서분류기호 대분류 명칭 : ' + kdcName1s + '</p>'; // 동양서분류기호 대분류 명칭

    bookSave += '<button onclick="bookSave(\''+ titleInfo + '\',\'' + typeName + '\',\'' + authorInfo + '\',\'' + pubInfo + '\',\'' + menuName + '\',\'' + mediaName + '\',\'' + licText + '\',\'' + regDate + '\',\'' + isbn + '\',\'' + callNo + '\',\'' + kdcCode1s + '\',\'' + kdcName1s + '\',\'' + imageUrl + '\')" type="button" class="btn btn-primary">도서 저장</button>';
    
    $('#book_body').html(bookData); // 팝업 바디에 도서 데이터 넣어주기
    $('#book_save_button').html(bookSave); // 팝업 footer에 도서 저장 버튼 넣어주기
}

function bookSave(titleInfo, typeName, authorInfo, pubInfo, menuName, mediaName, licText, regDate, isbn, callNo, kdcCode1s, kdcName1s, imageUrl) {

    var data = {
        titleInfo: titleInfo,
        typeName: typeName,
        authorInfo: authorInfo,
        pubInfo: pubInfo,
        menuName: menuName,
        mediaName: mediaName,
        licText: licText,
        regDate: regDate,
        isbn: isbn,
        callNo: callNo,
        kdcCode1s: kdcCode1s,
        kdcName1s: kdcName1s,
        imageUrl: imageUrl
    }

    $.ajax({

        type: "post",
        url: "/user/bookSave",
        data: data,
        
        success: function (data) {

            console.log("도서 데이터 저장 성공!!");
            alert('도서를 저장하였습니다!!');
            alert('메인 페이지로 이동합니다!!');
            location.href = "/"; // 메인 페이지로 이동

        },

        error: function () {
            console.log("도서 데이터 저장 실패 ㅠㅠ");
            alert('알수 없는 이유로 저장에 실패하였습니다 ㅠㅠ');
        }
    })
}

