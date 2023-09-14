function sendMail(){
    axios.post(`/mail/send-code`, {email:document.getElementById('mail').value})
    alert('SendMail')
}