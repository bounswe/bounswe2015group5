$(document).ready(function () {
    function updatePage() {
        $.post("UserServlet", {operation: "get_users"}, function (responseText) {
            document.getElementById("table-body").innerHTML = responseText;
        });
    }

    $("#navigation-bar li").click(function (e) {
        $("#navigation-bar li").removeClass("active");
        $(this).addClass("active");
    });

    $("#send-user").click(function (e) {
        $.post("UserServlet", {operation: "add_user", username: $("#username").val(), password: $("#password").val()}, function (responseText) {
            updatePage();
        });
    });
    updatePage();
});


