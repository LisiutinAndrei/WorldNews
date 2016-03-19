$(document).ready(function () {
    $(document).scroll(function () {
        var scroll = $(this).scrollTop();
        var navbar = $("#_mainlayout-navbar");
        var topDist = navbar.position();
        if (scroll > topDist.top) {
            navbar.css({"position": "fixed", "top": "0"});
            $("._mainlayout-navigation-image").css("display", "inline");
        } else {
            navbar.css({"position": "static", "top": "auto"});
            $("._mainlayout-navigation-image").css("display", "none");
        }
    });

    $("#_mainlayout-account").mouseover(function (e) {
        e.preventDefault();
        $(this).children("._mainlayout-account-items").css("display", "block");
    })
    $("#_mainlayout-account").mouseleave(function (e) {
        e.preventDefault();
        $(this).children("._mainlayout-account-items").css("display", "none");
    })
});
