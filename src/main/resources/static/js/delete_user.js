const usersTable = document.querySelector('.usersTable');
const delAccept = document.querySelector('.delAccept');
let href_ = '';

delAccept.addEventListener('click', (e) => {
    e.preventDefault();

    fetch(href_, {
        method: 'DELETE',
    })
        .then((e) => console.log(e))

    $('#deleteModal').modal('hide');
})

usersTable.addEventListener('click', (e) => {
    href_ = e.target.href;
})

