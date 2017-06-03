function createElement(usr){
    var link = $("<a>")
            .attr("href", "Bacheca?visit_user="+usr.id)
            .html(usr.name + " " + usr.surname);
    
    var userData = $("<div>")
            .attr("class","icon")
            .append(link);   
    return $("<li>")
            .append(userData);
}

function stateSuccess(data){
    var userListPage = $("#utenti");
    $(userListPage).empty();
    var i=0;
    for(var instance in data){
        $(userListPage).append(createElement(data[instance]));
        i++;
    }
        if(i === 0)
        alert("Utente non trovato");

}
function stateFailure(data, state){
    console.log(state);
}

$(document).ready(function(){
    $("#searchYourFriend").keyup(cerca);
    $("#searchYourFriendByClick").click(cerca);
    function cerca(){
        var wantedUser = $(".searchUsers")[0].value;
        $.ajax({
            url: "filter.json",
            data:{
                cmd:"search",
                q: wantedUser
            },
            dataType:"json",
            success: function(data, state){
                        console.log("successo");
                stateSuccess(data);
            },
            error: function(data, state){
                        console.log("errore");
                stateFailure(data, state);
            }
        });
    }
});
