function JSONTelData(){
    return ({
        to: document.querySelector('#account_tel').value,
        content:"Script Test"
    });
}

function messageSend(){
    console.log(JSONTelData())
    axios.post(`/sms/send`, JSONTelData())
        .then(response => {
            console.log(response.data)
        }).catch(error => {
            console.error(error)
        })
}