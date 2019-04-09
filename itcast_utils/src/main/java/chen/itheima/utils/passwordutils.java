package chen.itheima.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class passwordutils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String transfor(String password){

        return bCryptPasswordEncoder.encode(password);

    }

    public static void main(String[] args) {
        String transfor = transfor("123");//$2a$10$N9NqPQ6KGBI1f.FQeIy7aOXvr6c9qdvj6/gBcawbze9fA.AiFOzym
        System.out.println(transfor);
    }
}
