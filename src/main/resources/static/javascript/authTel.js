var answerCode;
window.onload = function(){
    init()
}

function addTelHyphen(target){
    target.value = target.value
                        .replace(/[^0-9]/g, '')
                        .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3")
                        .replace(/(\-{1,2})$/g, "");
}

function JSONTelData(){
    return ({
        to: document.querySelector('#account_tel').value.replace(/-/g, ''),
        content: ""
    });
}

function messageSend(){
    var jsonData = JSONTelData();
    function createCode(){
        axios.get(`/sms/code`)
            .then(response => {
                answerCode = String(response.data);
                jsonData.content = `인증번호: ${response.data}`;
            }).catch(error => {
                console.error(error);
            })
        .then(
            function(){
                if(jsonData.content.length !== 0){
                    sendCode()
                }
            }
        )
    }

    function sendCode(){
        axios.post(`/sms/send`, jsonData)
            .then(response => {

            }).catch(error => {
                console.error(error);
            })
    }

    axios.get(`/sms/check-dup?tel=${jsonData.to}`)
    .then(response => {

        if(response.data === ""){
            createCode();
        }
    })
}

function checkCode(){
    document.querySelector('#account_telAuthResult').innerHTML =
    (answerCode === document.querySelector('#account_telAuth').value)
}