const url_ = 'http://localhost:8080/api/users';
const principalList = document.getElementById('data-principal')
const Principal = document.querySelector('.navbar .principal-id');
const id = Principal.id;
// console.log(Principal.id);
// console.log(principalList);

const _renderUsers = (users) => {
    // console.log(users);
    if (users.length > 0) {
        var temp = "";

        // ----start for loop
        users.forEach((user) => {
            temp += "<tr id=" + user.id + ">";
            temp += "<td>" + user.id + "</td>";
            temp += "<td>" + user.username + "</td>";
            temp += "<td>" + user.password + "</td>";
            temp += "<td>" + user.email + "</td>";
            temp += "<td>";
            user.roles.forEach((role) => {
                temp += role.name.substr(5) + " ";
            })
            temp += "</td></tr>";
        })
        // ----end for loop

        principalList.innerHTML = temp;
    }
}


// GET запрос и заполнение таблицы
fetch(`${url_}/${id}`)
    .then(res => res.json())
    .then(data => {
        const dataArr = [];
        dataArr.push(data);
        _renderUsers(dataArr);
    })
