var code;
function sendMail(){
    axios.post(`/mail/send-code`, {email:document.querySelector('#mail').value})
        .then(response => {
            fp_emailSendMsg.innerHTML = '인증번호를 전송했습니다.';
            code = String(response.data);
        });
}

function checkCode(){
    function success(){
        sessionStorage.setItem("email", document.querySelector('#mail').value);
        window.location.href='/mod-password';
    }
    (document.querySelector('#code').value === code) ?
        success()
        : fp_codeCheckMsg.innerHTML = '인증번호를 다시 확인해주세요';
}