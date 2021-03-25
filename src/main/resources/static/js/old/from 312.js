$(document).ready(function () {
    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (user, status) {
            $('.myForm #id_edit').val(user.id);
            $('.myForm #username_edit').val(user.username);
            $('.myForm #password_edit').val();
            $('.myForm #email_edit').val(user.email);
            $('.myForm #role_edit').val(user.roles);
        });
        $('.myForm #exampleModal').modal();
    })
});

$(document).ready(function () {
    $('.table .dBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (user, status) {
            $('.deleteForm #id_delete').val(user.id);
            $('.deleteForm #username_delete').val(user.username);
            // $('.deleteForm #password_delete').val();
            $('.deleteForm #email_delete').val(user.email);
            $('.deleteForm #role_delete').val(user.roles);
        });
        $('.deleteForm #deleteModal').modal();
    })
});
