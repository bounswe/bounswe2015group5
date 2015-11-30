$(document).ready(function () {
    $("#signup").click(function (event) {
        bringSignupForm(event);
    });

    $("#login").click(function (event) {
        bringLoginForm(event);
    });

    $("#contribute").click(function (event) {
        bringContributeForm(event);
    });

    bringAllContributions();
});

function bringAllContributions() {
    $("#content").html("");
    $.get("contribution.html", function (tmpStr) {
        $.post("AllContributions", {}, function (contributions) {
            for (index = 0; index < contributions.length; index++) {
                tmp = $(tmpStr);
                tmp.find(".contrib-title").html(contributions[index].Title);
                tmp.find(".contrib-author").html(contributions[index].Name + " " + contributions[index].Surname);
                tmp.find(".contrib-date").html(contributions[index].Date);
                tmp.find(".contrib-content").html(contributions[index].Content);
                $("#content").append(tmp);
            }
        });
    });
}


function bringSignupForm(e) {
    $("#content").load("signup-form.html", function () {
        $("#submit").click(function (event) {
            event.preventDefault();
            var email = $("#email").val();
            var name = $("#name").val();
            var surname = $("#surname").val();
            var password = $("#password").val();
            $.post("RegisterUser", {Email: email, Name: name, Surname: surname, Password: password}, function (responseText) {
                $.post("UserInfo", {}, function (userObj) {
                    if (isLoggedIn(userObj)) {
                        window.alert(responseText);
                    } else {
                        window.alert("You're registered successfully!");
                        displayUserInfo(userObj);
                        bringAllContributions();
                    }
                });
            });
        });
    });
}


function bringLoginForm(e) {
    $("#content").load("login-form.html", function () {
        $("#submit").click(function (event) {
            event.preventDefault();
            var email = $("#email").val();
            var password = $("#password").val();
            $.post("Login", {Email: email, Password: password}, function (responseText) {
                $.post("UserInfo", {}, function (userObj) {
                    if (isLoggedIn(userObj)) {
                        window.alert(responseText);
                    } else {
                        displayUserInfo(userObj);
                        bringAllContributions();
                    }
                });
            });
        });
    });
}

function bringContributeForm(e) {
    $("#content").load("contribute-form.html", function () {
        $("#submit").click(function (event) {
            event.preventDefault();
            var title = $("#title").val();
            var content = $("#content").val();
            var type = "1";
            $.post("RegisterContribution", {Title: title, Content: content, Type: type}, function (responseText) {
                window.alert(responseText);
            });
        });
    });
}

function displayUserInfo(userObj) {
    $("#user-info").html(userObj.Name + " " + userObj.Surname + "<strong class=\"caret\"></strong>");
}

function isLoggedIn(userObj) {
    return (typeof userObj.Email === 'undefined');
}