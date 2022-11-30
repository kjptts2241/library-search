// 로그인 id / pw 확인
$('#loginBtn').click(function() {
    var id = $('#inputId').val();
    var pw = $('#inputPassword').val();
    var remember_us = $('#remember_us').is(':checked');
        $.ajax({
        type : 'post',
        url : '/userLogin',
        data : {
            user_id : id,
            user_pw : pw,
            remember_userId : remember_us
        },
        success : function(data) {
            if (data == 0) { //로그인 실패시
                console.log(data);
                $('#spanLoginCheck').text('로그인 정보를 정확히 입력해주세요.');
            } else if (data == -2) { //인증하지 않았다면?
                console.log(data);
                $('#spanLoginCheck').text('이메일 인증을 해주셔야 합니다!');						
            } else if (data == -3) { // 아이디가 사용중이라면?
                console.log(data);
                location.href = '/redundant?user_Id=' + id + '&user_Pw=' + pw + '&remember_userId=' + remember_us;						
            } else { //로그인 성공시
                console.log(data);
                location.href = '/search';
            }
        }
    });
});