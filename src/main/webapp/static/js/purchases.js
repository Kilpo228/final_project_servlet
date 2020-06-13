$(document).ready(function () {
    $('#en').click(function () {
        $.ajax({
            type: 'POST',
            url: '/app/en',
            success: function () {
                location.reload(true)
            }
        })
    });

    $('#ru').click(function () {
        $.ajax({
            type: 'POST',
            url: '/app/ru',
            success: function () {
                location.reload(true)
            }
        })
    });

    $('#go-to-home').click(function () {
        window.location.replace('/app/admin')
    });

    $('#button-logout').click(function () {
        window.location.replace('/app/logout')
    });
});
