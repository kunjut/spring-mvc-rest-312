const postsList = document.querySelector('.post-list');
const addPostForm = document.querySelector('.add-post-form');

const usernameValue = document.getElementById('usernameValue');
const emailValue = document.getElementById('emailValue');
const passwordValue = document.getElementById('passwordValue');

const btnSubmit = document.querySelector('.btn')

let output = '';

const url = 'http://localhost:8080/api/users';


// Функция рендеринга добавленных данных--------------------------------------------------------------------------------
const renderUsers = (users) => {
    users.forEach(user => {
        let _roles_ = '';                   // пока отключил, а то не работает когда роли не переданы,
                                            // forEach не работает с null
        user.roles.forEach(role => {
            _roles_ += role.name.substr(5) + ' '
        })

        output += `
                <div class="card col-md-6 mt-4 bg-light">
                    <div class="card-body" data-id=${user.id}>
                        <h5 class="card-username">${user.username}</h5>
                        <h6 class="card-email mb-2 text-muted">${user.email}</h6>
                        <p class="card-password">${user.password}</p>
                        <p class="card-id">${user.id}</p>
                        <p class="card-roles">${_roles_}</p>
                        <a href="#" class="card-link" id="edit-post">Edit</a>
                        <a href="#" class="card-link" id="delete-post">Delete</a>
                    </div>
                 </div>  
            `;
    });
    postsList.innerHTML = output;
}


// GET запрос-----------------------------------------------------------------------------------------------------------
fetch(url)
    .then(res => res.json())
    .then(data => renderUsers(data))


// POST запрос: начинаем с прослушивания события submit-----------------------------------------------------------------
addPostForm.addEventListener('submit', (e) => {
    e.preventDefault();

    fetch(url, { // сам запрос
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify({
            username: usernameValue.value,
            password: passwordValue.value,
            email: emailValue.value,
            roles: [{id: 1, name: 'ROLE_ADMIN'}, {id: 2, name: 'ROLE_USER'}]
        })
    })
        .then(res => res.json())
        .then(data => {
          const dataArr = [];
          dataArr.push(data);

          renderUsers(dataArr);
        })
    usernameValue.value = ''; // reset fields after submit
    passwordValue.value = '';
    emailValue.value = '';
})


// DELETE/PATCH запрос: начинаем с прослушивания события click----------------------------------------------------------
postsList.addEventListener('click', (e) => {
    e.preventDefault();

    let deleteButtonIsPressed = e.target.id === 'delete-post';
    let editButtonIsPressed = e.target.id === 'edit-post';

    let id = e.target.parentElement.dataset.id;

    // реализация удаления
    if(deleteButtonIsPressed) {
        fetch(`${url}/${id}`, { method: 'DELETE' })
            // .then(res => res.json()) //- ругается, попробовать ключить, когда json проавильный будет собираться
            .then(() => location.reload())
    }

    // реализация редактирования ч1 - здесь подтягиваются данные в форму
    if (editButtonIsPressed) {

        const parent = e.target.parentElement;
        let usernameElement = parent.querySelector('.card-username').textContent;
        let passwordElement = parent.querySelector('.card-password').textContent;
        let emailElement = parent.querySelector('.card-email').textContent;


        usernameValue.value = usernameElement;
        passwordValue.value = passwordElement;
        emailValue.value = emailElement;
    }

    // реализация редактирования ч2 - здесь отправка запроса
    btnSubmit.addEventListener('click', () => {

        fetch(`${url}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: id,
                username: usernameValue.value,
                password: passwordValue.value,
                email: emailValue.value,
                roles: [{id: 1, name: 'ROLE_ADMIN'}, {id: 2, name: 'ROLE_USER'}]
            })
        })
            .then(res => res.json())
            .then(data => console.log(data))
            // .then(() => location.reload())
        // console.log(id);
    })

});