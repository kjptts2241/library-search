// 메인 페이지 엔터
// 페이지 이동 및 값 전달 함수
function keyMaster() {
    var mainKeyword = $("#mainKeyword").val(); // 메인 검색 페이지의 값

    location.href="/auth/subsearch"; // 페이지 이동
    localStorage.setItem('mainKeyword', mainKeyword); // 값 전달
    $("#mainKeyword").val(''); // 검색 후 input창 초기화
}

// 클릭 하였을 때
function mainSearch() {
    keyMaster();
}

// 엔터키 눌렀을 때
function enterkey() {
    if (window.event.keyCode == 13) {
        keyMaster();
    }
}