package com.ceica.Controladores;

public class LoginController {
   public static boolean login(String user, String password){
       if("admin".equals(user) & "1234".equals(password)){
           return true;
       }else {
           return false;
       }
   }
}
