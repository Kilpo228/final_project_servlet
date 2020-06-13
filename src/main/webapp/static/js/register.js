$(document).ready(function () {
    $('#en').click(function () {
        $.ajax({
            type : 'POST',
            url : '/app/en',
            success : function () {
                location.reload(true)
            }
        })
    });

    $('#ru').click(function () {
        $.ajax({
            type : 'POST',
            url : '/app/ru',
            success : function () {
                location.reload(true)
            }
        })
    });

    $('form').submit(function (event) {
        if ($('#first-password').val() === $('#second-password').val()) {
            $.ajax({
                type : 'POST',
                data : $(this).serialize(),
                url : '/app/save_new_user',
                success : function () {
                    $('input').val('');
                    $('#ok-msg').fadeIn().delay(3000).fadeOut()
                },
                error : function () {
                    $('#error-msg').fadeIn().delay(3000).fadeOut()
                }
            });
        } else {
            $('#error-msg-password').fadeIn().delay(3000).fadeOut();
        }

        event.preventDefault()
    });

    $('#go-to-login').click(function () {
        window.location.replace("/app")
    })
});
