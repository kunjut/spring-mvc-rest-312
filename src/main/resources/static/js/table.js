// заполнение основной таблицы
fetch("http://localhost:8080/api/users").then(
    res=>{
        res.json().then(
            data=>{
                console.log(data);
                if(data.length > 0) {
                    var temp = "";

                    // ----start for loop
                    data.forEach((user)=>{
                        temp += "<tr>";
                        temp += "<td>" + user.id + "</td>";
                        temp += "<td>" + user.username + "</td>";
                        temp += "<td>" + user.password + "</td>";
                        temp += "<td>" + user.email + "</td>";
                        temp += "<td>";
                        user.roles.forEach((role)=>{temp += role.name.substr(5) + " "; })
                        temp += "</td>";

                        temp += "<td><span style='display: inline'>"
                        temp +=
                            '<a href="api/users/' + user.id + '" class="btn btn-info eBtn_" data-toggle="modal" data-target="#exampleModal">'
                            + 'Edit</a>';
                        temp +=
                            " " +
                            '<a href="api/users/' + user.id + '" class="btn btn-danger dBtn_" data-toggle="modal" data-target="#deleteModal">'
                            + 'Delete</a></span></td>';

                        temp += "</tr>";
                    })
                    // ----end for loop

                    document.getElementById("data").innerHTML = temp;

                }
            }
        )
    }
)