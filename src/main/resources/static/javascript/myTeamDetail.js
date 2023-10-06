let teamId, season = 2023
let selectOption
window.onload = function() {
    teamId = getUrlParam().searchParams.get(`teamId`)
    selectOption = document.querySelector('#myTeamDetail_select')
    getSeason()
    getData() //가장 처음에는 이벤트가 발생되지 않아 데이터를 가져와라는 명령이 필요함
}

function getUrlParam(){
    return new URL(location.href)
}

function getSeason(){
    selectOption.addEventListener(`change`, event => {
        season = event.target.value
        getData() //이벤트 발생할 때마다 데이터 다시 가져와야하니까
    })
}

function getData(){
    axios.get(`/myteam/api/season/all?teamId=${teamId}&season=${season}`)
        .then(response => {
            fillData(response.data)
        }).catch(error => {
            console.error(error)
        })
}

function fillData(data){
    var tbody = document.querySelector('#myTeamDetail_tbody')
    const html = data.map((record) => {
        const url = `/myteam/detail?teamId=${record.userTeamDO.teamDO.id}&num=${record.userTeamDO.backNumber}`;
        return `
          <tr>
            <td><a href="${url}"><span>${record.userTeamDO.userInfoDO.name}</span></a></td>
            <td><span>${record.userTeamDO.backNumber}</span></td>
            <td><span>${record.game}</span></td>
            <td><span>${record.pa}</span></td>
            <td><span>${record.ab}</span></td>
            <td><span>${record.hit}</span></td>
            <td><span>${record.homerun}</span></td>
            <td><span>${record.rbi}</span></td>
            <td><span>${record.bb}</span></td>
            <td><span>${record.kk}</span></td>
            <td><span>${record.run}</span></td>
            <td><span>${record.sb}</span></td>
          </tr>
        `;
      }).join('');

    tbody.innerHTML = html
}