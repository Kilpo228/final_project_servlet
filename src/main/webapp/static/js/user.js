function parseURL () {
    let searchParams = new URLSearchParams(window.location.search);
    let sort = $('#sort-by');
    let url = window.location.href;

    if (searchParams.has('q')) {
        $('#search-input').val(searchParams.get('q'))
    }

    if (searchParams.has('min')) {
        $('#min-range').val(searchParams.get('min'))
    }

    if (searchParams.has('max')) {
        $('#max-range').val(searchParams.get('max'))
    }

    if (url.includes('category=pizza')) {
        $('#filter-pizza').click()
    }

    if (url.includes('category=sushi')) {
        $('#filter-sushi').click()
    }

    if (url.includes('category=burgers')) {
        $('#filter-burgers').click()
    }

    if (url.includes('category=cold_drinks')) {
        $('#filter-cold-drinks').click()
    }

    if (url.includes('category=tea')) {
        $('#filter-tea').click()
    }

    if (url.includes('category=juice')) {
        $('#filter-juice').click()
    }

    if (url.includes('sort=name')) {
        sort.val(1)
    } else if (url.includes('sort=price_bigger')) {
        sort.val(2)
    } else if (url.includes('sort=price_smaller')) {
        sort.val(3)
    }
}

function paintButtons() {
    let enRemoveButtons = $('button:contains("Remove")');
    let ruRemoveButtons = $('button:contains("Убрать")');

    enRemoveButtons.css('background-color', '#778899');
    enRemoveButtons.css('border-color', '#778899');

    ruRemoveButtons.css('background-color', '#778899');
    ruRemoveButtons.css('border-color', '#778899');
}

$(document).ready(function () {
    parseURL();
    paintButtons();

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

    $('#go-to-orders').click(function () {
        window.location.replace('/app/user/orders')
    });

    $('#go-to-cart').click(function () {
        window.location.replace('/app/user/cart')
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

    $('.button-buy').click(function () {
        let button = $(this);
        let text = $(this).text().trim();

        if (text === 'Buy' || text === 'Купить') {
            $.ajax({
                url : '/app/user/add_to_cart',
                data : {product_name : $('.' + $(this).attr('id') + 'item').text().trim()},
                type : 'POST',
                success : function () {
                    button.css('background-color', '#778899');
                    button.css('border-color', '#778899');
                    if (text === 'Buy') {
                        button.html('Remove');
                    } else if (text === 'Купить') {
                        button.html('Убрать');
                    }
                }
            })
        } else {
            $.ajax({
                url : '/app/user/remove_from_cart',
                data : {product_name : $('.' + $(this).attr('id') + 'item').text().trim()},
                type : 'POST',
                success : function () {
                    button.css('background-color', '#6c757d');
                    button.css('border-color', '#6c757d');
                    if (text === 'Remove') {
                        button.html('Buy');
                    } else if (text === 'Убрать') {
                        button.html('Купить');
                    }
                }
            })
        }
    });

    $('.button-details').click(function () {
        let name = $('.' + $(this).attr('id') + 'item').text();
        $('#product-name').text(name);
        $.ajax({
            type : 'GET',
            data : { product_name : name },
            url : '/app/user/details',
            success : function (data) {
                let span = $('#ingredient-span');
                let ingredients = $('#product-ingredients');
                console.log(data);
                if (data === ' ') {
                    span.hide();
                    ingredients.hide()
                } else {
                    span.show();
                    ingredients.show();
                    ingredients.text(data)
                }
            }
        })
    });

    $('#modal-product-details-close-button').click(function () {
        $('#product-details').modal('hide')
    });

    function constructUrlByCategory() {
        let url = '';
        let pizza = $('#filter-pizza').is(':checked');
        let sushi = $('#filter-sushi').is(':checked');
        let burgers = $('#filter-burgers').is(':checked');
        let coldDrinks = $('#filter-cold-drinks').is(':checked');
        let tea = $('#filter-tea').is(':checked');
        let juice = $('#filter-juice').is(':checked');

        if (pizza) {
            url += 'category=pizza&'
        }

        if (sushi) {
            url += 'category=sushi&'
        }

        if (burgers) {
            url += 'category=burgers&'
        }

        if (coldDrinks) {
            url += 'category=cold_drinks&'
        }

        if (tea) {
            url += 'category=tea&'
        }

        if (juice) {
            url += 'category=juice&'
        }

        return url
    }

    $('#search-button').click(function () {
        let urlPaginationParameter = new URLSearchParams(window.location.search);
        let url = '/app/user?';
        let q = $('#search-input').val();
        let min = $('#min-range').val();
        let max = $('#max-range').val();
        let sort = $('#sort-by');
        let sortId = sort.find('option:selected').attr('id');

        if (parseInt(min) < 0) {
            alert('Price cannot be negative');
            return
        }

        if (parseInt(max) < 0) {
            alert('Price cannot be negative');
            return
        }

        if (q !== '') {
            url += 'q=' + q + '&'
        }

        if (min !== '') {
            url += 'min=' + min + '&'
        }

        if (max !== '') {
            url += 'max=' + max + '&'
        }

        url += constructUrlByCategory();

        if (sortId === 'sort-name') {
            url += 'sort=name&'
        } else if (sortId === 'sort-price-bigger') {
            url += 'sort=price_bigger&'
        } else if (sortId === 'sort-price-smaller') {
            url += 'sort=price_smaller&'
        }

        if (urlPaginationParameter.has('page')) {
            url += 'page=' + urlPaginationParameter.get('page')
        }

        if (url.charAt(url.length - 1) === '&') {
            url = url.substring(0, url.length - 1)
        } else if (url === '/app/user?') {
            url = '/app/user'
        }

        window.location.replace(url)
    });

    $('.page-link').click(function () {
        let url = '/app/user?';
        let q = $('#search-input').val();
        let min = $('#min-range').val();
        let max = $('#max-range').val();
        let sort = $('#sort-by');
        let sortId = sort.find('option:selected').attr('id');

        if (parseInt(min) < 0) {
            alert('Price cannot be negative');
            return
        }

        if (parseInt(max) < 0) {
            alert('Price cannot be negative');
            return
        }

        if (q !== '') {
            url += 'q=' + q + '&'
        }

        if (min !== '') {
            url += 'min=' + min + '&'
        }

        if (max !== '') {
            url += 'max=' + max + '&'
        }

        url += constructUrlByCategory();

        if (sortId === 'sort-name') {
            url += 'sort=name&'
        } else if (sortId === 'sort-price-bigger') {
            url += 'sort=price_bigger&'
        } else if (sortId === 'sort-price-smaller') {
            url += 'sort=price_smaller&'
        }

        url += 'page=' + $(this).text();

        if (url.charAt(url.length - 1) === '&') {
            url = url.substring(0, url.length - 1)
        } else if (url === '/app/user?') {
            url = '/app/user'
        }

        window.location.replace(url)
    })
});
