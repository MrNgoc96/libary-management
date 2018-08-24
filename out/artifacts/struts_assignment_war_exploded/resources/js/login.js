$('.carousel.carousel-slider').carousel({
    fullWidth: true,
    duration: 500,
});
$(document).ready(function () {
    setInterval(() => {
        $('.carousel').carousel('next');
    }, 5000);
})

$(document).ready(function () {
    $('.login-form').submit(function () {
        let username = $('#username').val();
        let password = $('#password').val();
        if (username == '') {
            $('#username-error').show();
            $('#username').css({"border-bottom":"red 2px solid"})
            return false;
        } else if (password == '') {
            $('#password-error').show();
            $('#password').css({"border-bottom":"red 2px solid"})
            return false;
        }
    });

    $('#username').change(function () {
        if (username != '') {
            $('#username-error').hide();
            $(this).css({"border-bottom":""})
        }else{
            $('#username-error').show();
            $(this).css({"border-bottom":"red 2px solid"})
        }
    });
    $('#password').change(function () {
        if (username != '') {
            $('#password-error').hide();
            $(this).css({"border-bottom":""})
        }else{
            $('#password-error').show();
            $(this).css({"border-bottom":"red 2px solid"})
        }
    });
});