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

    $("#logout").click(function (event) {
        logOut(event);
    });

    $.post("UserInfo", {}, function (userObj) {
        if (isLoggedIn(userObj)) {
            loggedInActions(userObj);
        } else {
            loggedOutActions();
        }
    });

    bringAllTags();
});


function bringAllTags() {
    $("#main-div").html("");
    $.get("tag.html", function (tmpStr) {
        $.post("AllTags", {}, function (tags) {
            for (index = 0; index < tags.length; index++) {
                tmp = $(tmpStr);
                tmp.find(".tag-name").html(tags[index].TagName);
                tmp.find(".view-contribution-button").click({TagName: tags[index].TagName}, function (event) {
                    bringAllContributionsWithTag(event.data.TagName);
                });
                $("#main-div").append(tmp);
            }
        });
    });
}


function bringAllContributions() {
    $("#main-div").html("");
    $.get("contribution.html", function (tmpStr) {
        $.post("AllContributions", {}, function (contributions) {
            for (index = 0; index < contributions.length; index++) {
                tmp = $(tmpStr);
                tmp.find(".contrib-title").html(contributions[index].Title);
                tmp.find(".contrib-author").html(contributions[index].Name + " " + contributions[index].Surname);
                tmp.find(".contrib-date").html(contributions[index].Date);
                tmp.find(".contrib-content").html(contributions[index].Content);
                $("#main-div").append(tmp);
            }
        });
    });
}


function bringAllCommentsWithID(contributionID) {
    $("#main-div").html("");
    $.get("comment.html", function (tmpStr) {
        $.post("CommentsByContributionID", {ContributionID: contributionID}, function (comments) {
            for (index = 0; index < comments.length; index++) {
                tmp = $(tmpStr);
                tmp.find(".comment-author").html(comments[index].Name + " " + comments[index].Surname);
                tmp.find(".comment-date").html(comments[index].Date);
                tmp.find(".comment-content").html(comments[index].Content);
                $("#main-div").append(tmp);
            }
        });
    });
}

function bringAllContributionsWithTag(TagName) {
    $("#main-div").html("");
    $.get("contribution.html", function (tmpStr) {
        $.post("SearchByTag", {Tag: TagName}, function (contributions) {
            for (index = 0; index < contributions.length; index++) {
                tmp = $(tmpStr);
                tmp.find(".contrib-title").html(contributions[index].Title);
                tmp.find(".contrib-author").html(contributions[index].Name + " " + contributions[index].Surname);
                tmp.find(".contrib-date").html(contributions[index].Date);
                tmp.find(".contrib-content").html(contributions[index].Content);
                tmp.find(".view-comment-button").click({ContributionID: contributions[index].ID}, function (event) {
                    bringAllCommentsWithID(event.data.ContributionID);
                });
                $("#main-div").append(tmp);
            }
        });
    });
}


function logOut(e) {
    $.post("Logout", {}, function (userObj) {
        loggedOutActions();
    });
}

function bringSignupForm(e) {
    $("#main-div").load("signup-form.html", function () {
        $("#submit").click(function (event) {
            event.preventDefault();
            var email = $("#email").val();
            var name = $("#name").val();
            var surname = $("#surname").val();
            var password = $("#password").val();
            $.post("RegisterUser", {Email: email, Name: name, Surname: surname, Password: password}, function (responseText) {
                $.post("UserInfo", {}, function (userObj) {
                    if (!isLoggedIn(userObj)) {
                        window.alert(responseText);
                        loggedOutActions();
                    } else {
                        window.alert("You're registered successfully!");
                        loggedInActions(userObj);
                        bringAllTags();
                    }
                });
            });
        });
    });
}


function bringLoginForm(e) {
    $("#main-div").load("login-form.html", function () {
        $("#submit").click(function (event) {
            event.preventDefault();
            var email = $("#email").val();
            var password = $("#password").val();
            $.post("Login", {Email: email, Password: password}, function (responseText) {
                $.post("UserInfo", {}, function (userObj) {
                    if (!isLoggedIn(userObj)) {
                        window.alert(responseText);
                        loggedOutActions();
                    } else {
                        loggedInActions(userObj);
                        bringAllTags();
                    }
                });
            });
        });
    });
}

function bringContributeForm(e) {
    $("#main-div").load("contribute-form.html", function () {
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

function loggedInActions(userObj) {
    displayUserInfo(userObj);
    $("#signup").hide();
    $("#login").hide();
    $("#contribute").show();
    $("#logout").show();
}

function loggedOutActions() {
    $("#user-info").html("User<strong class=\"caret\"></strong>");
    $("#signup").show();
    $("#login").show();
    $("#contribute").hide();
    $("#logout").hide();
}

function displayUserInfo(userObj) {
    $("#user-info").html(userObj.Name + " " + userObj.Surname + "<strong class=\"caret\"></strong>");
}

function isLoggedIn(userObj) {
    return (typeof userObj.Email !== 'undefined');
}

function doIfLoggedIn(thenFunction, elseFunction) {
    $.post("UserInfo", {}, function (userObj) {
        if (typeof userObj.Email !== 'undefined') {
            thenFunction(userObj);
        } else {
            elseFunction(userObj);
        }
    });
}