$(document).ready(function () {
    if (document.cookie.includes('status=logout')) {
        $('#logged-out-msg').fadeIn().delay(3000).fadeOut();
        document.cookie = 'status=; expires=Thu, 01 Jan 1970 00:00:00 GMT'
    }

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
        $.ajax({
            type : 'POST',
            data : $(this).serialize(),
            url : '/app/login',
            success : function (data) {
                window.location.replace(data)
            },
            error : function (data) {
                if (data.responseText === 'exist') {
                    $('#duplicate-msg').fadeIn().delay(3000).fadeOut()
                } else {
                    $('#error-msg').fadeIn().delay(3000).fadeOut()
                }
            }
        });

        event.preventDefault()
    });

    $('#go-to-register').click(function () {
        window.location.replace('register')
    })
});
