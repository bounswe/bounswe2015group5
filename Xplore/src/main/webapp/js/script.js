$(document).ready(function () {
    $("#submit").click(function(e){
        var email = $("#email").val();
        var name = $("#name").val();
        var surname = $("#surname").val();
        var pwd = $("#pwd").val();
        $.post("RegisterUser",{email: email, name: name, surname: surname, pass:pwd},function(responseText){
            window.alert(responseText);
        });
    });
    $("#log-submit").click(function(e){
        var email = $("#log-email").val();
        var pwd = $("#log-pwd").val();
        $.post("Login",{email: email, pass:pwd},function(responseText){
            window.alert(responseText);
        });
    });
});