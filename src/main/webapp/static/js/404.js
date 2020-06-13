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

    $('#return').click(function () {
        $.ajax({
            type : 'POST',
            url : '/app/rescue_user',
            success : function (data) {
                console.log(data);
                window.location.replace(data)
            }
        })
    })
});