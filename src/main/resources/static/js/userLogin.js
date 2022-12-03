// 로그인 id / pw 확인
$('#loginBtn').click(function() {
    var id = $('#inputId').val();
    var pw = $('#inputPassword').val();
    var remember_us = $('#remember_us').is(':checked');
        $.ajax({
        type : 'post',
        url : '/userLogin',
        data : {
            userId : id,
            userPw : pw,
            remember_userId : remember_us
        },
        success : function(data) {

            console.log("결과값 : " + data);

            if (data == 0) { //로그인 실패시
                console.log(data);
                $('#spanLoginCheck').text('로그인 정보를 정확히 입력해주세요.');
                $('#spanLoginCheck').css('color', 'red'); 			
            } else { //로그인 성공시
                console.log(data);
                alert('로그인에 성공하셨습니다');
                location.href = '/search';
            }
        }
    });
});