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
    $("#contrib-submit").click(function(e){
        var title = $("#title").val();
        var content = $("#content").val();
        var type = "1";
        $.post("RegisterContribution",{title: title, content: content, type: type},function(responseText){
            window.alert(responseText);
        });
    });
    getAllContributions();
});

function getAllContributions(){
    var contribution;
    $.get("contribution.html",function(data){
        contribution = data;
    });
            
    $.post("AllContributions",{},function(data){
       var contributions = JSON.parse(data);
       for(index = 0; index< contributions.length;index++){
           $("#contribution-lister").append($(contribution));
           document.getElementsByClassName("contribution-title")[index].innerHTML = contributions[index].title;
           document.getElementsByClassName("contribution-author")[index].innerHTML = contributions[index].name + " " + contributions[index].surname;
           document.getElementsByClassName("contribution-date")[index].innerHTML = contributions[index].date;
           document.getElementsByClassName("contribution-content")[index].innerHTML = contributions[index].content;
       }
    });
}
