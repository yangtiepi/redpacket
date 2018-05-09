 function  is_login(){
         $.ajax({
            type:"POST", 
            url:'http://localhost/index.php/home/Login/is_login', 
            dataType:"json",   
            success:function(result){
              if (result.code==200) {
                 return true;
              }else{
                //window.location.href="http://localhost/index.php/home/login/wxlogin";
				return false;
              }
            } 
         }); 
      }
