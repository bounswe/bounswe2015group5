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

    init();
});

/**
 * Initializes the page.
 * @returns {undefined}
 */
function init() {
    bringAllTags();
}

/**
 * Displays all tags.
 * @returns {undefined}
 */
function bringAllTags() {
    $("#main-div").html("");
    $.get("tag.html", function (tagTemplateStr) {
        $.post("AllTags", {}, function (tags) {
            for (index = 0; index < tags.length; index++) {
                var tagTemplate = $(tagTemplateStr);
                tagTemplate.find(".tag-name").html(tags[index].TagName);
                tagTemplate.find(".view-contribution-button").click({TagName: tags[index].TagName}, function (event) {
                    bringAllContributionsWithTag(event.data.TagName);
                });
                $("#main-div").append(tagTemplate);
            }
        });
    });
}


/**
 * Upvotes the contribution with contributionID.
 * @param {number} contributionID - ID of the contribution.
 * @returns {undefined}
 */
function upvoteContributionWithID(contributionID) {
    postToServer("RateContribution", {ContributionID: contributionID, Rate: 1}, function (responseText) {
        window.alert(responseText.responseText);
    });
}


/**
 * Downvotes the contribution with contributionID.
 * @param {number} contributionID - ID of the contribution.
 * @returns {undefined}
 */
function downvoteContributionWithID(contributionID) {
    postToServer("RateContribution", {ContributionID: contributionID, Rate: -1}, function (responseText) {
        window.alert(responseText.responseText);
    });
}


/**
 * Loads comment entry form for contribution with contributionID.
 * @param {number} contributionID - ID of the contribution that will be commented.
 * @returns {undefined}
 */
function bringCommentForm(contributionID) {

    $.get("comment-form.html", function (formStr) {
        var form = $(formStr);
        form.find("#submit").click(function (event) {
            event.preventDefault();
            var content = $("#content").val();
            var type = "1";
            var msgObj = {ContributionID: contributionID, Content: content, Type: type};
            postToServer("RegisterComment", msgObj, function (responseText) {
                window.alert(responseText.responseText);
                bringAllCommentsWithID(contributionID);
            });
        });
        $("#main-div").append(form);
    });
}


/**
 * Displays comments of the contribution whose ID is given.
 * @param {number} contributionID - ID of the contribution.
 * @returns {undefined}
 */
function bringAllCommentsWithID(contributionID) {
    $("#main-div").html("");
    $.post("UserInfo", {}, function (userObj) {
        if (!isLoggedIn(userObj)) {
        } else {
            bringCommentForm(contributionID);
        }
    });

    $.get("comment.html", function (commentTemplateStr) {
        $.post("CommentsByContributionID", {ContributionID: contributionID}, function (comments) {
            for (index = 0; index < comments.length; index++) {
                var commentTemplate = $(commentTemplateStr);
                commentTemplate.find(".comment-author").html(comments[index].Name + " " + comments[index].Surname);
                commentTemplate.find(".comment-date").html(comments[index].Date);
                commentTemplate.find(".comment-content").html(comments[index].Content);
                $("#main-div").append(commentTemplate);
            }
        });
    });
}

function upvoteContributionWithID(contributionID, tagName) {
    postToServer("RateContribution", {ContributionID: contributionID, Rate: 1}, function (responseText) {
        window.alert(responseText.responseText);
        bringAllContributionsWithTag(tagName);
    });
}

/**
 * Displays all contributions with a specific tag.
 * @param {string} TagName - Name of the tag.
 * @returns {undefined}
 */
function bringAllContributionsWithTag(TagName) {
    $("#main-div").html("");
    $.get("contribution.html", function (contributionTemplateStr) {
        $.post("SearchByTag", {Tag: TagName}, function (contributions) {
            for (index = 0; index < contributions.length; index++) {
                var contributionTemplate = $(contributionTemplateStr);
                contributionTemplate.find(".contrib-title").html(contributions[index].Title);
                contributionTemplate.find(".contrib-author").html(contributions[index].Name + " " + contributions[index].Surname);
                contributionTemplate.find(".contrib-date").html(contributions[index].Date);
                contributionTemplate.find(".contrib-content").html(contributions[index].Content);
                tags = contributions[index].Tags;
                for (tagIndex = 0; tagIndex < contributions[index].Tags.length; tagIndex++) {
                    tagName = contributions[index].Tags[tagIndex].TagName;
                    tagItem = $("<li class=\"btn\">" + tagName + "</li>");
                    tagItem.click({TagName: tagName}, function (event) {
                        bringAllContributionsWithTag(event.data.TagName);
                    });
                    contributionTemplate.find(".contrib-tags").append(tagItem);
                }
                contributionTemplate.find(".view-comment-button").click({ContributionID: contributions[index].ID}, function (event) {
                    bringAllCommentsWithID(event.data.ContributionID);
                });
                contributionTemplate.find(".like-button").click({ContributionID: contributions[index].ID, TagName: TagName}, function (event) {
                    upvoteContributionWithID(event.data.ContributionID, event.data.TagName);
                });
                contributionTemplate.find(".dislike-button").click({ContributionID: contributions[index].ID}, function (event) {
                    upvoteContributionWithID(event.data.ContributionID);
                });
                contributionTemplate.find(".contrib-rate").html("Rate: " + contributions[index].Rate);
                $("#main-div").append(contributionTemplate);
            }
        });
    });
}


/**
 * Logs out the user
 * @param {type} e
 * @returns {undefined}
 */
function logOut(e) {
    $.post("Logout", {}, function (userObj) {
        loggedOutActions();
    });
}


/**
 * Displays sign-up form.
 * @param {type} e
 * @returns {undefined}
 */
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


/**
 * Displays login form.
 * @param {type} e
 * @returns {undefined}
 */
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


/**
 * Displays contribution form.
 * @param {type} e
 * @returns {undefined}
 */
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
            var msgObj = {Title: title, Content: content, Type: type, Tags: TagArray};
            postToServer("RegisterContribution", msgObj, function (responseText) {
                window.alert(responseText.responseText);
                bringAllTags();
            });
        });
    });
}

function postToServer(url, data, func) {
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'text',
        data: JSON.stringify(data),
        contentType: 'application/json',
        mimeType: 'application/json',
        complete: func
    });
}


/**
 * Sequence of actions after logging in.
 * @param {type} userObj
 * @returns {undefined}
 */
function loggedInActions(userObj) {
    displayUserInfo(userObj);
    $("#signup").hide();
    $("#login").hide();
    $("#contribute").show();
    $("#logout").show();
}


/**
 * Sequence of actions after logging out.
 * @returns {undefined}
 */
function loggedOutActions() {
    $("#user-info").html("User<strong class=\"caret\"></strong>");
    $("#signup").show();
    $("#login").show();
    $("#contribute").hide();
    $("#logout").hide();
}


/**
 * Displays user info.
 * @param {type} userObj
 * @returns {undefined}
 */
function displayUserInfo(userObj) {
    $("#user-info").html(userObj.Name + " " + userObj.Surname + "<strong class=\"caret\"></strong>");
}


/**
 * Checks whether logged in.
 * @param {type} userObj
 * @returns {Boolean} True if logged in.
 */
function isLoggedIn(userObj) {
    return (typeof userObj.Email !== 'undefined');
}


/**
 * A callback wrapper for login dependent actions.
 * @param {type} thenFunction - Action taken if logged in.
 * @param {type} elseFunction - Action taken if logged out.
 * @returns {undefined}
 */
function doIfLoggedIn(thenFunction, elseFunction) {
    $.post("UserInfo", {}, function (userObj) {
        if (typeof userObj.Email !== 'undefined') {
            thenFunction(userObj);
        } else {
            elseFunction(userObj);
        }
    });
}
