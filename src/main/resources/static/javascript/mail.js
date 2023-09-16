var code, mail;
function mail(){

}
function sendMail(){
    mail = document.querySelector('#mail').value;
    axios.get(`/account/api/email-dup?email=${mail}`)
        .then(response => {
            if(response.data === ""){
                fp_emailSendMsg.innerHTML = '등록되지 않은 이메일입니다.';
            }
        }).catch(error => {
            console.error(error);
        }).then(
          () => {
            sending()
      });
}

function sending(){
    axios.post(`/mail/send-code`, {email: mail})
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