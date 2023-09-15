//Front Logics are completed! - 2023-09-14
var passwordResult;
window.onload = function(){
    init()
}

function init(){
    passwordResult = document.querySelector('#passwordResult');
    emailResult = document.querySelector('#account_emailResult')
}

function checkEmailReg(target){
    var regex = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    return regex.test(target.value)
}

function emailCheck(target){
    if(!checkEmailReg(target)){
        emailResult.innerHTML = '이메일 형식을 확인해주세요';
        return ;
    }else{
        emailResult.innerHTML = '';
    }

    axios.get(`/account/api/email-dup?email=${target.value}`)
        .then(response => {
            (response.data === "") ?
                emailResult.innerHTML = '사용가능한 이메일입니다.' : emailResult.innerHTML = '중복된 이메일입니다.';
        }).catch(error => {
            console.error(error);
    });
}

function nameHangul(target){
    target.value = target.value
                        .replace(/[^\uAC00-\uD7AF\u3130-\u318F\u1100-\u11FF\uA960-\uA97F\uAC00-\uD7A3]+/g
                            , '');
}

function isRegularPassword(){
    var regex = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(.{4,})$/; //문자, 숫자, 특수문자 포함한 4자 이상
    return regex.test(document.querySelector('#account_password').value)
}

function isConfirmPassword(){
    return document.querySelector('#account_password').value
                === document.querySelector('#account_c_password').value;
}

function showPasswordResult(){
    if (!isRegularPassword()) {
        passwordResult.innerHTML = '특수문자 포함 4자 이상 작성해주세요';
    } else if (!isConfirmPassword() && document.querySelector('#account_c_password').value.length !== 0) {
        passwordResult.innerHTML = '비밀번호를 다시 확인해주세요';
    } else {
        passwordResult.innerHTML = '';
    }
}

function JSONRegisterData(){
    return ({
        email: document.querySelector('#account_email').value,
        password: document.querySelector('#account_password').value,
        name: document.querySelector('#account_name').value,
        birth: document.querySelector('#account_birth').value,
        tel: document.querySelector('#account_tel').value
    });
}

function registerButton(){
    axios.post(`/account/api/register`, JSONRegisterData())
        .then(response => {
            if(response.data){
                alert('회원가입에 성공했습니다.')
                window.location.href='/login';
            }
        }).catch(error => {
            console.error(error)
        })
}