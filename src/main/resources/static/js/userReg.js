
//모든 공백 체크 정규식
var empJ = /\s/g;
//아이디 정규식
var idJ = /^[a-z0-9]{4,12}$/;
// 비밀번호 정규식
var pwJ = /^[A-Za-z0-9]{4,12}$/; 
// 이름 정규식
var nameJ = /^[가-힣]{2,6}$/;
// 이메일 검사 정규식
var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
// 휴대폰 번호 정규식
var phoneJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;

// 회원가입 전체 ( 가입 버튼 ) 유효성 검사
var inval_Arr = new Array(6).fill(false);

// $("#reg_submit").removeAttr('href', true); // 처음 접속 시 가입하기 버튼 누르지 못하게 설정

// 아이디 유효성 검사(1 = 중복 / 0 != 중복)
$("#user_id").blur(function() {

    // id = "id_reg" / name = "userId"
    var user_id = $('#user_id').val();

    $.ajax({
        url : '/auth/idCheck?user_id='+ user_id,
        type : 'get',
        success : function(data) {
            console.log("1 = 중복o / 0 = 중복x : "+ data);
            
            if (data == 1) {
                    // 1 : 아이디가 중복되는 문구
                    $("#id_check").text("사용중인 아이디입니다 :p");
                    $("#id_check").css("color", "red");
                    // $("#reg_submit").removeAttr('href', true);
                    inval_Arr[0] = false;
            } else {
                    
                if(idJ.test(user_id)){
                    // 0 : 아이디 길이 / 문자열 검사
                    $("#id_check").text("");
                    inval_Arr[0] = true;
        
                } else if(user_id == ""){
                    
                    $('#id_check').text('아이디를 입력해주세요');
                    $('#id_check').css('color', 'red');
                    inval_Arr[0] = false;		
                    
                } else {
                    
                    $('#id_check').text("아이디는 소문자와 숫자 4~12자리만 가능합니다");
                    $('#id_check').css('color', 'red');
                    inval_Arr[0] = false;
                }
                    
            }
        },
        
        error : function() {
                    console.log("아이디 중복 체크 실패 했습니다");
        }
    });
});


// 비밀번호 유효성 검사
// 정규식 체크
$('#password').blur(function() {
   if (pwJ.test($(this).val())) {
        console.log('비밀번호 유효성 체크 성공');
        $('#pw_check').text('');
        inval_Arr[1] = true;
   } else if($(this).val() == "") {
        $('#pw_check').text('비밀번호를 입력해주세요');
        $('#pw_check').css('color', 'red');
        inval_Arr[1] = false;
   } else {
        $('#pw_check').text('숫자 or 문자로만 4~12자리 입력 해주세요');
        $('#pw_check').css('color', 'red');
        inval_Arr[1] = false;
   }
});

// 패스워드 일치 확인
$('#password2').blur(function() {
    if ($('#password').val() != $(this).val()) {
        $('#pw2_check').text('비밀번호가 일치하지 않습니다');
        $('#pw2_check').css('color', 'red');
        inval_Arr[2] = false;
    } else if ($(this).val() == "") {
        $('#pw2_check').text('비밀번호를 입력해주세요');
        $('#pw2_check').css('color', 'red');
        inval_Arr[2] = false;
    } 
    else {
        console.log('비밀번호가 일치 합니다');
        $('#pw2_check').text('');
        inval_Arr[2] = true;
    }
});


// 이름 유효성 검사
$("#user_name").blur(function() {
    if (nameJ.test($(this).val())) {
        console.log('이름 유효성 체크 성공');
        $("#name_check").text('');
        inval_Arr[3] = true;
    } else if ($(this).val() == "") {
        $('#name_check').text('이름을 입력해주세요');
        $('#name_check').css('color', 'red');
        inval_Arr[3] = false;
    } 
    else {
        $('#name_check').text('이름을 확인해주세요');
        $('#name_check').css('color', 'red');
        inval_Arr[3] = false;
    }
});

	
// 생년월일 유효성 검사
$('#user_birth').blur(function(){
    var dateStr = $(this).val();		
    var year = Number(dateStr.substr(0,4)); // 입력한 값의 0~4자리까지 (연)
    var month = Number(dateStr.substr(4,2)); // 입력한 값의 4번째 자리부터 2자리 숫자 (월)
    var day = Number(dateStr.substr(6,2)); // 입력한 값 6번째 자리부터 2자리 숫자 (일)
    var today = new Date(); // 날짜 변수 선언
    var yearNow = today.getFullYear(); // 올해 연도 가져옴
    
    if (dateStr.length <= 8) {
        // 연도의 경우 1900 보다 작거나 yearNow 보다 크다면 false를 반환합니다.
        if (1900 > year || year > yearNow){
            
            $('#birth_check').text('생년월일을 확인해주세요');
            $('#birth_check').css('color', 'red');
            inval_Arr[4] = false;
            
        } else if (month < 1 || month > 12) {
                
            $('#birth_check').text('생년월일을 확인해주세요');
            $('#birth_check').css('color', 'red'); 
            inval_Arr[4] = false;
        
        } else if (day < 1 || day > 31) {
            
            $('#birth_check').text('생년월일을 확인해주세요');
            $('#birth_check').css('color', 'red'); 
            inval_Arr[4] = false;
            
        } else if ((month==4 || month==6 || month==9 || month==11) && day==31) {
             
            $('#birth_check').text('생년월일을 확인해주세요');
            $('#birth_check').css('color', 'red'); 
            inval_Arr[4] = false;
             
        } else if (month == 2) {
             
               var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
               
             if (day>29 || (day==29 && !isleap)) {
                 
                 $('#birth_check').text('생년월일을 확인해주세요');
                $('#birth_check').css('color', 'red'); 
                inval_Arr[4] = false;
            
            } else {
                $('#birth_check').text('');
                inval_Arr[4] = true;
            }//end of if (day>29 || (day==29 && !isleap))
             
        } else{
            
            console.log('생년월일 유효성 체크 성공');
            $('#birth_check').text(''); 
            inval_Arr[4] = true;
        }//end of if
        
    } else if ($(this).val() == "") {
            $('#birth_check').text('생년월일을 입력해주세요');
            $('#birth_check').css('color', 'red');
            inval_Arr[4] = false; 
    } else {
            //1.입력된 생년월일이 8자 초과할때 :  auth:false
            $('#birth_check').text('생년월일을 확인해주세요');
            $('#birth_check').css('color', 'red');
            inval_Arr[4] = false;
    }
});


// 이메일 유효성 검사
$("#user_email").blur(function() {
    if (mailJ.test($(this).val())) {
        console.log('이메일 유효성 체크 성공');
        $("#email_check").text('');
        inval_Arr[5] = true;
    } else if ($(this).val() == "") {
        $('#email_check').text('이메일을 입력해주세요');
        $('#email_check').css('color', 'red');
        inval_Arr[5] = false;
    } else {
        $('#email_check').text('이메일을 확인해주세요');
        $('#email_check').css('color', 'red');
        inval_Arr[5] = false;
    }
});


// 휴대전화
$('#user_phone').blur(function(){
    if(phoneJ.test($(this).val())){
        console.log('휴대폰 번호 유효성 체크 성공');
        $("#phone_check").text('');
        inval_Arr[6] = true;
    } else if ($(this).val() == "") {
        $('#phone_check').text('휴대폰번호를 입력해주세요');
        $('#phone_check').css('color', 'red');
        inval_Arr[6] = false;
    } else {
        $('#phone_check').text('휴대폰번호를 확인해주세요');
        $('#phone_check').css('color', 'red');
        inval_Arr[6] = false;
    }
});

$('#reg_submit').click(function(){

    if(inval_Arr[0] == true && inval_Arr[1] == true && inval_Arr[2] == true && inval_Arr[3] == true && inval_Arr[4] == true && inval_Arr[5] == true && inval_Arr[6] == true){ // 유효성 모두 통과
        alert($("#user_name").val() + ' 님 회원가입에 성공 하셨습니다!');
        
    } else{
        alert('입력한 정보들을 다시 한번 확인해주세요 :)')
        return false;
    }
});

