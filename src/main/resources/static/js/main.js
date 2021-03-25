// наполнение edit модалки
$(document).delegate('.eBtn_', 'click', function (event) {
    event.preventDefault();
    // console.log('было нажатие на edit');

    let href = $(this).attr('href');
    $.get(href, function (user, status) {
        $('.myForm #id_edit').val(user.id);
        $('.myForm #username_edit').val(user.username);
        $('.myForm #password_edit').val();
        $('.myForm #email_edit').val(user.email);
        $('.myForm #role_edit').val(user.roles);
    });




    $('.myForm #exampleModal').modal('hide')
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
    $('.deleteForm #deleteModal').modal('hide');
});

// $(document).on('.deleteForm .delAccept', 'click', function() {
//     if (confirm('Do you really want to delete record?')) {
//         var id = $(this).attr('id');
//         console.log(id);
//         var parent = $(this).parent().parent();
//         console.log(id);
//         $.ajax({
//             type: "DELETE",
//             url: "http://localhost:8080/api/users/" + ${id.}},
//             cache: false,
//             success: function() {
//                 // parent.fadeOut('slow', function() {
//                 //     $(this).remove();
//                 // });
//                 alert("good")
//                 location.reload(true)
//             },
//             error: function() {
//                 // $('#err').html('<span style=\'color:red; font-weight: bold; font-size: 30px;\'>Error deleting record').fadeIn().fadeOut(4000, function() {
//                 //     $(this).remove();
//                 // });
//                 alert("bad")
//             }
//         });
//     }
// });

