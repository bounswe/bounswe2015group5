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


function bringCommentForm(contributionID) {
   
    $.get("comment-form.html", function (formStr) {
        form = $(formStr);
        form.find("#submit").click( function (event) {
            event.preventDefault();
            var content = $("#content").val();
            var type = "1";
            $.post("RegisterComment", {ContributionID: contributionID, Content: content, Type: type}, function (responseText) {
                window.alert(responseText);
                bringAllCommentsWithID(contributionID);
            });
        });
        $("#main-div").append(form);
    });
}


function bringAllCommentsWithID(contributionID) {
    $("#main-div").html("");
    $.post("UserInfo", {}, function (userObj) {
        if (!isLoggedIn(userObj)) {
        } else {
            bringCommentForm(contributionID);
        }
    });

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
                tags = contributions[index].Tags;
                for (tagIndex = 0; tagIndex < contributions[index].Tags.length; tagIndex++) {
                    tagName = contributions[index].Tags[tagIndex].TagName;
                    tagItem = $("<li class=\"btn\">" + tagName + "</li>");
                    tagItem.click({TagName: tagName}, function (event) {
                        bringAllContributionsWithTag(event.data.TagName);
                    });
                    tmp.find(".contrib-tags").append(tagItem);
                }
                tmp.find(".view-comment-button").click({ContributionID: contributions[index].ID}, function (event) {
                    bringAllCommentsWithID(event.data.ContributionID);
                });
                tmp.find(".contrib-rate").html("Rate: " + contributions[index].Rate);
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
            var tags = $("#tags").val().split(",");
            var TagArray = [];
            for (index = 0; index < tags.length; index++) {
                TagArray.push({TagName: tags[index]});
            }
            var type = "1";
            window.alert(title);
            $.post("RegisterContribution", {Title: title, Content: content, Type: type, Tags: TagArray}, function (responseText) {
                window.alert(responseText);
                bringAllTags();
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