// usersTable     // уже есть в delete_user.js
// let href_      // уже есть в delete_user.js
// const editUserForm = document.querySelector('.editForm #roles_edit');
const saveChanges = document.querySelector('.save-changes');
const _rolesValue = document.querySelector('.editForm #roles_edit');

// отслеживаем какие роли выбираются
function roleSelected() {
    let select1 = _rolesValue;
    let selectedArr = [];

    for (let i = 0; i < select1.length; i++) {
        if (select1.options[i].selected) {
            let selectedHsh = {};
            selectedHsh['id'] = +select1.options[i].value;
            selectedHsh['name'] = 'ROLE_' + select1.options[i].textContent;
            selectedArr.push(selectedHsh);
        }
    }
    return selectedArr;
}

// подстановка значений и PUT запрос
saveChanges.addEventListener('click', (e) => {
    e.preventDefault();
    // console.log('клик')
    const parent = e.target.parentElement.parentElement;

    // получаем поля модалки
    let _usernameValue = parent.querySelector('.modal-body #username_edit');
    let _idValue = parent.querySelector('.modal-body #id_edit');
    let _emailValue = parent.querySelector('.modal-body #email_edit');
    let _passwordValue = parent.querySelector('.modal-body #password_edit');

    fetch(href_, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: _idValue.value,
            username: _usernameValue.value,
            email: _emailValue.value,
            password: _passwordValue.value,
            roles: roleSelected()
        })
    })
        .then((e) => console.log(e))
    $('#editModal').modal('hide');
})

// получаем ссылку по которой будет работать rest
usersTable.addEventListener('click', (e) => {
    href_ = e.target.href;
    console.log(href_)
})
