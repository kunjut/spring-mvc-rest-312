const url = 'http://localhost:8080/api/users';
const addUserForm = document.querySelector('.add-user-form');
const usernameValue = document.getElementById('usernameValue');
const passwordValue = document.getElementById('passwordValue');
const emailValue = document.getElementById('emailValue');
const rolesValue = document.getElementById('rolesValue');


// получаем выбранные роли из селекта
function thatSelected() {
    let select1 = rolesValue;
    let selectedArr = [];

    for (let i = 0; i < select1.length; i++) {
        if (select1.options[i].selected) {
            let selectedHsh = {};
            selectedHsh['id'] = +select1.options[i].value;
            selectedHsh['name'] = select1.options[i].id;
            selectedArr.push(selectedHsh);
        }
    }
    return selectedArr;
}


// слушаем клик по ролям в селекте
rolesValue.addEventListener('click', () => {
    thatSelected();
})


// POST запрос: слушаем submit
addUserForm.addEventListener('submit', (e) => {
    // e.preventDefault();

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            username: usernameValue.value,
            password: passwordValue.value,
            email: emailValue.value,
            roles: thatSelected(),
        })
    })
        .then(res => res.json())
        .then(data => {
            const dataArr = [];
            dataArr.push(data);

            renderUsers(dataArr);
        });

    usernameValue.value = '';
    passwordValue.value = '';
    emailValue.value = '';
    rolesValue.value = '';
})