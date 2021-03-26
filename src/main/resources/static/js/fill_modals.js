// наполнение edit модалки
$(document).delegate('.eBtn_', 'click', function (event) {
    event.preventDefault();
    // console.log('было нажатие на edit');

    let href = $(this).attr('href');
    $.get(href, function (user, status) {
        $('.editForm #id_edit').val(user.id);
        $('.editForm #username_edit').val(user.username);
        $('.editForm #password_edit').val();
        $('.editForm #email_edit').val(user.email);
        $('.editForm #role_edit').val(user.roles);
    });
    $('.editForm #editModal').modal();
});

// наполнение delete модалки
$(document).delegate('.dBtn_', 'click' ,function (event) {
    event.preventDefault();
    // console.log('было нажатие а del');
    let href = $(this).attr('href');

    $.get(href, function (user, status) {
        $('.deleteForm #id_delete').val(user.id);
        $('.deleteForm #username_delete').val(user.username);
        // $('.deleteForm #password_delete').val();
        $('.deleteForm #email_delete').val(user.email);
        $('.deleteForm #role_delete').val(user.roles);
    });
    $('.deleteForm #deleteModal').modal();
});

