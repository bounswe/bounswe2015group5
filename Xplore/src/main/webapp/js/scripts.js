$(document).ready(function () {

//    var like = $('#like-btn');
//    var dislike = $('#dislike-btn');
    // in this part I tried to store the contID
    // var comment = $('#comment-btn');
    // var contributionID;
    // comment.click(function(){
    //      contributionID = $("#contid").val();
    // });

//    like.click(function () {
//        if (like.hasClass('btn-default')) {
//            like.removeClass('btn-default');
//            like.addClass('btn-success');
//        } else if (like.hasClass('btn-success')) {
//            like.addClass('btn-default');
//            like.removeClass('btn-success');
//        }
//        if (dislike.hasClass('btn-danger')) {
//            dislike.addClass('btn-default');
//            dislike.removeClass('btn-danger');
//        }
//    });
//
//    dislike.click(function () {
//        if (like.hasClass('btn-success')) {
//            like.addClass('btn-default');
//            like.removeClass('btn-success');
//        }
//        if (dislike.hasClass('btn-default')) {
//            dislike.removeClass('btn-default');
//            dislike.addClass('btn-danger');
//        } else if (dislike.hasClass('btn-danger')) {
//            dislike.addClass('btn-default');
//            dislike.removeClass('btn-danger');
//        }
//    });

    $("#signup-submit").click(function (e) {
        var email = $("#signup-email").val();
        var name = $("#signup-name").val();
        var surname = $("#signup-surname").val();
        var pwd = $("#signup-password").val();
        $.post("RegisterUser", {email: email, name: name, surname: surname, pass: pwd}, function (responseText) {
            window.alert(responseText);
        });
    });
    
    $("#login-submit").click(function (e) {
        var email = $("#login-email").val();
        var pwd = $("#login-password").val();
        $.post("Login", {email: email, pass: pwd}, function (responseText) {
            window.alert(responseText);
            if (responseText === "Login Successful!");
            $.post("UserInfo", {}, function (response) {
                $("#user-info").html(response.name + " " + response.surname + "<strong class=\"caret\"></strong>");
            });
        });
    });
    
    $("#contrib-submit").click(function (e) {
        var title = $("#contrib-title").val();
        var content = $("#contrib-content").val();
        var type = "1";
        $.post("RegisterContribution", {title: title, content: content, type: type}, function (responseText) {
            window.alert(responseText);
        });
    });
    $("#comment-submit").click(function (e) {
        //var contribId = contributionID;
        var contribId = $("#contribution-id").val();
        var content = $("#comment-content").val();
        var type = "1";
        $.post("RegisterComment", {contribId: contribId, content: content, type: type}, function (responseText) {
            window.alert(responseText);
        });
        s
    });

    getAllContributions();
});

function getAllContributions() {
    $.get("contribution.html", function (tmpStr) {
        $.post("AllContributions", {}, function (data) {
            var contributions = JSON.parse(data);
            for (index = 0; index < contributions.length; index++) {
                tmp = $(tmpStr);
                tmp.find(".contrib-title").html(contributions[index].title);
                tmp.find(".contrib-author").html(contributions[index].name + " " + contributions[index].surname);
                tmp.find(".contrib-date").html(contributions[index].date);
                tmp.find(".contrib-content").html(contributions[index].content);
                $("#contrib-lister").append(tmp);
//                document.getElementsByClassName("contribution-id")[index].innerHTML = contributions[index].id;
            }
        });
    });
}
