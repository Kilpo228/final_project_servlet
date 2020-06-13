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

    $('#go-to-purchases').click(function () {
        window.location.replace('/app/admin/purchases')
    });

    $('#go-to-product').click(function () {
       window.location.replace('/app/admin/new_product')
    });

    $('#button-logout').click(function () {
        window.location.replace('/app/logout')
    });

    $('#a-en-name').click(function () {
        window.location.replace('/app/admin/ingredients?sort=en')
    });

    $('#a-ru-name').click(function () {
        window.location.replace('/app/admin/ingredients?sort=ru')
    });

    $('#a-amount').click(function () {
        window.location.replace('/app/admin/ingredients?sort=amount')
    });

    $('.plus-button').click(function () {
        let name = $('.name' + $(this).attr('id')).text().trim();
        let amount = $('.amount' + $(this).attr('id'));

        $.ajax({
            type : 'POST',
            data : {ingredient_name : name},
            url : '/app/admin/ingredient_increase',
            success : function () {
                $.ajax({
                    type : 'GET',
                    data : {ingredient_name : name},
                    url : '/app/admin/ingredient_amount',
                    success : function (data) {
                        amount.html(data)
                    }
                })
            }
        })
    });

    $('.minus-button').click(function () {
        let name = $('.name' + $(this).attr('id')).text().trim();
        let amount = $('.amount' + $(this).attr('id'));

        $.ajax({
            type : 'POST',
            data : {ingredient_name : name},
            url : '/app/admin/ingredient_decrease',
            success : function () {
                $.ajax({
                    type : 'GET',
                    data : {ingredient_name : name},
                    url : '/app/admin/ingredient_amount',
                    success : function (data) {
                        amount.html(data)
                    }
                })
            }
        })
    });

    $('.ingredient-delete-button').click(function () {
        let name = $('.name' + $(this).attr('id')).text().trim();

        $.ajax({
            type : 'POST',
            data : {ingredient_name : name},
            url : '/app/admin/delete_ingredient',
            success : function () {
                $('tr:contains("' + name +'")').fadeOut()
            }
        })
    });

    $('#modal-new-ingredient-ok-button').click(function () {
        let en_name = $('#modal-new-ingredient-input-en-name');
        let ru_name = $('#modal-new-ingredient-input-ru-name');

        if (en_name.val() === '' || ru_name.val() === '') {
            $('#empty-fields-new-ingredient-error').fadeIn().delay(3000).fadeOut()
        } else {
            $.ajax({
                type : 'POST',
                data : {en_name : en_name.val().trim(), ru_name : ru_name.val().trim()},
                url : '/app/admin/save_new_ingredient',
                success : function (data) {
                    if (data === 'name') {
                        $('#incorrect-new-ingredient-error').fadeIn().delay(3000).fadeOut()
                    } else {
                        en_name.val('');
                        ru_name.val('');

                        $('#ok-new-ingredient-msg').fadeIn().delay(3000).fadeOut()
                    }
                }
            })
        }
    });

    $('#modal-new-ingredient-close-button').click(function () {
        $('#new-ingredient-modal').modal('hide')
    })
});
