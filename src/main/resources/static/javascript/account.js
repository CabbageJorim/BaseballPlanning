//TODO: 2023-09-11 정규식 체크 필요(이름, 전화번호) -> account.html

var passwordResult;
window.onload = function(){
    init()
}

function init(){
    passwordResult = document.querySelector('#passwordResult');
}

function isRegularPassword(){
    var regex = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(.{4,})$/;
    passwordResult.innerHTML = regex.test(document.querySelector('#account_password').value)
}

function isConfirmPassword(){
    passwordResult.innerHTML =
        document.querySelector('#account_password').value === document.querySelector('#account_c_password').value;
}

function JSONData(){
    return ({
        email: document.querySelector('#account_email').value,
        password: document.querySelector('#account_password').value,
        name: document.querySelector('#account_name').value,
        birth: document.querySelector('#account_birth').value,
        tel: document.querySelector('#account_tel').value
    });
}

function registerButton(){
    console.log(JSONData())
    axios.post(`/register`, JSONData())
        .then(response => {

        }).catch(error => {
            console.error(error)
        })
}
