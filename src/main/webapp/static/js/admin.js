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

    $('#go-to-product').click(function () {
       window.location.replace('/app/admin/new_product')
    });

    $('#go-to-ingredient').click(function () {
       window.location.replace('/app/admin/ingredients')
    });

    $('#go-to-purchases').click(function () {
       window.location.replace('/app/admin/purchases')
    });

    $('#button-logout').click(function () {
        window.location.replace('/app/logout')
    });

    $('#a-en').click(function () {
        window.location.replace('/app/admin?sort=en')
    });

    $('#a-ru').click(function () {
        window.location.replace('/app/admin?sort=ru')
    });

    $('#a-amount').click(function () {
        window.location.replace('/app/admin?sort=amount')
    });

    $('#a-category').click(function () {
        window.location.replace('/app/admin?sort=category')
    });

    $('#a-price').click(function () {
        window.location.replace('/app/admin?sort=price')
    });

    let tableAmount;
    let tablePrice;

    $('.edit-button').click(function () {
        tableAmount = $('.amount' + $(this).attr('id'));
        tablePrice = $('.price' + $(this).attr('id'));
        let name;

        if ($(document).attr('title') === 'Admin') {
            name = $('.enName' + $(this).attr('id')).text().trim();
        } else {
            name = $('.ruName' + $(this).attr('id')).text().trim();
        }

        $.ajax({
            type: 'GET',
            data: {product_name: name},
            url: '/app/admin/product_price',
            success: function (data) {
                $('#edit-modal-span-current-price').html(data)
            }
        });

        $.ajax({
            type: 'GET',
            data: {product_name: name},
            url: '/app/admin/product_amount',
            success: function (data) {
                $('#edit-modal-span-current-amount').html(data)
            }
        });

        $('#edit-modal-header-span').text(name)
    });

    $('.delete-button').click(function () {
        let name = $('.enName' + $(this).attr('id')).text().trim();

        $.ajax({
            type : 'POST',
            data : {product_name : name},
            url : '/app/admin/delete_product',
            success : function () {
                $('tr:contains("' + name + '")').fadeOut()
            }
        })
    });

    $('#edit-modal-ok-button').click(function () {
        let modalAmount = $('#edit-modal-input-amount');
        let modalPrice = $('#edit-modal-input-price');

        if (modalAmount.val() === '' || modalPrice.val() === '') {
            $('#empty-fields-error').fadeIn().delay(3000).fadeOut()
        } else if (parseInt(modalAmount.val()) >= 0 && parseFloat(modalPrice.val()) > 0.0) {
            $.ajax({
                type: 'POST',
                data: {amount: modalAmount.val(), price: modalPrice.val(), product_name: $('#edit-modal-header-span').text()},
                url: '/app/admin/update_product',
                success: function () {
                    let header = $('#edit-modal-header-span');

                    $.ajax({
                        type: 'GET',
                        data: {product_name: header.text().trim()},
                        url: '/app/admin/product_price',
                        success: function (data) {
                            $('#edit-modal-span-current-price').html(data);
                            tablePrice.html(data)
                        }
                    });

                    $.ajax({
                        type: 'GET',
                        data: {product_name: header.text().trim()},
                        url: '/app/admin/product_amount',
                        success: function (data) {
                            $('#edit-modal-span-current-amount').html(data);
                            tableAmount.html(data)
                        }
                    });

                    modalAmount.val('');
                    modalPrice.val('');
                    $('#ok-msg').fadeIn().delay(3000).fadeOut()
                }
            })
        } else {
            $('#error-msg').fadeIn().delay(3000).fadeOut()
        }
    });

    $('#edit-modal-close-button').click(function () {
        $('#edit-modal').modal('hide')
    });

    $('#button-ok-modal').click(function () {
        let enName = $('#modal-new-product-en-name-input');
        let ruName = $('#modal-new-product-ru-name-input');
        let category = $('select option:selected').text();
        let price = $('#modal-new-product-price-input');
        let ingredients = [];

        $.each($('input[name="ingredient-checkbox"]:checked'), function () {
            ingredients.push($(this).val())
        });

        if (enName.val() === '' || ruName.val() === '' || price === '') {
            $('#empty-fields-error-modal').fadeIn().delay(3000).fadeOut()
        } else {
            $.ajax({
                type : 'POST',
                data : {
                    en_name : enName.val().trim(),
                    ru_name : ruName.val().trim(),
                    category : category,
                    price : price.val().trim(),
                    ingredients : ingredients
                },
                url : '/app/admin/save_new_product',
                success : function (data) {
                    if (data === 'name') {
                        $('#error-name-msg-modal').fadeIn().delay(3000).fadeOut();
                    } else if (data === 'ingredient') {
                        $('#error-ingredient-msg-modal').fadeIn().delay(3000).fadeOut();
                    } else {
                        $('#ok-msg-modal').fadeIn().delay(3000).fadeOut();
                        enName.val('');
                        ruName.val('');
                        price.val('');
                        $.each($('input[name="ingredient-checkbox"]:checked'), function () {
                            $(this).prop('checked', false)
                        })
                    }
                }
            })
        }
    });

    $('#button-close-modal').click(function () {
        $('#new-product-modal').modal('hide')
    });

    $('select').on('change', function () {
        let val = $('option:selected', this).val();

        if (val === 'TEA' || val === 'JUICE' || val === 'COLD_DRINKS') {
            $('.div-ingredients').fadeOut()
        } else {
            $('.div-ingredients').fadeIn()
        }
    })
});
