$(document).ready(function () {
    $.ajax({
        type : 'GET',
        url : '/app/user/balance',
        success : function (data) {
            $('#balance').text(data)
        },
        error : function () {
            $('#balance').text('ERROR')
        }
    });

    $.ajax({
        type : 'GET',
        url : '/app/user/balance',
        success : function (data) {
            $('#current-balance-span').text(data)
        },
        error : function () {
            $('#current-balance-span').text('ERROR')
        }
    });

    $('#go-to-cart').click(function () {
        window.location.replace('/app/user/cart')
    });

    $('#go-to-menu').click(function () {
        window.location.replace('/app/user')
    });

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

    $('#button-logout').click(function () {
        window.location.replace('/app/logout')
    });

    $('#modal-password-ok-button').click(function () {
        let currentPassword = $('#modal-password-current');
        let newPassword = $('#modal-password-new');

        if (currentPassword.val() === '') {
            $('#empty-fields-password-error').fadeIn().delay(3000).fadeOut();
            currentPassword.focus();
            return
        }

        if (newPassword.val() === '') {
            $('#empty-fields-password-error').fadeIn().delay(3000).fadeOut();
            newPassword.focus();
            return
        }

        $.ajax({
            url : '/app/user/check_password',
            data : {current_password : currentPassword.val()},
            type : 'GET',
            success : function (data) {
                if (data === '0') {
                    $('#incorrect-password-error').fadeIn().delay(3000).fadeOut()
                } else {
                    $.ajax({
                        url : '/app/user/change_password',
                        data : {new_password: newPassword.val()},
                        type : 'POST',
                        success : function () {
                            currentPassword.val('');
                            newPassword.val('');
                            $('#ok-password').fadeIn().delay(3000).fadeOut()
                        }
                    })
                }
            }
        });
    });

    $('#modal-password-close-button').click(function () {
        $('#password-change').modal('hide')
    });

    $('#modal-username-ok-button').click(function () {
        let newUsername = $('#modal-username-new');

        if (newUsername.val() === '') {
            $('#empty-fields-username-error').fadeIn().delay(3000).fadeOut();
            newUsername.focus();
            return
        }

        $.ajax({
            url : '/app/user/username_change',
            data : {new_username : newUsername.val()},
            type : 'POST',
            success : function () {
                $('#ok-username').fadeIn().delay(3000).fadeOut();
                $('#current-username').text(newUsername.val());
                newUsername.val('')
            }
        })
    });

    $('#modal-username-close-button').click(function () {
        $('#username-change').modal('hide')
    });

    $('#modal-balance-replenish-ok-button').click(function () {
        let currentBalance = $('#current-balance-span');
        let amountToReplenish = $('#modal-balance-amount');

        if (amountToReplenish.val() === '') {
            amountToReplenish.focus();
            $('#empty-fields-balance-error').fadeIn().delay(3000).fadeOut();
            return
        }

        if (parseFloat(amountToReplenish.val()) < 0.00 || parseInt(amountToReplenish.val()) < 0) {
            amountToReplenish.focus();
            $('#amount-balance-error').fadeIn().delay(3000).fadeOut();
            return
        }

        $.ajax({
            url : '/app/user/replenish_balance',
            data : {amount : amountToReplenish.val()},
            type : 'POST',
            success : function () {
                $.ajax({
                    type : 'GET',
                    url : '/app/user/balance',
                    success : function (data) {
                        currentBalance.text(data)
                    },
                    error : function () {
                        currentBalance.text('ERROR')
                    }
                });

                $.ajax({
                    type : 'GET',
                    url : '/app/user/balance',
                    success : function (data) {
                        $('#balance').text(data)
                    },
                    error : function () {
                        $('#balance').text('ERROR')
                    }
                });

                $('#ok-balance').fadeIn().delay(3000).fadeOut();
            }
        })
    });

    $('#modal-balance-replenish-close-button').click(function () {
        $('#balance-replenish').modal('hide')
    });


});
