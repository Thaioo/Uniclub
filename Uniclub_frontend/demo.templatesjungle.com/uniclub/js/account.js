$(document).ready(function(){

    $('.btn-login').click(function(){
        var email = $('#email').val().toLowerCase()
        var password = $('#password').val().toLowerCase()

        $.ajax({
            url: "http://localhost:8080/authen",
            method: "POST",
            contentType: "application/json",
            data:JSON.stringify({
                email: email,
                password: password
            })
        }).done(function( data ) {
              if(data.statusCode == 200){
                localStorage.setItem("token", data.data)
                alert("Đăng nhập thành công")
              }else{
                alert("Đăng nhập thất bại")
              }
        });
    })

})