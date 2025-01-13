//package hutnyk.notes_app.Service;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class PasswordGenerator {
//    public static void main(String[] args){
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String[] rawPasswords = {"1234", "qwerty", "pjatk", "asd3", "4234", "123333"};
//
//
//        List<String> hashedPasswords  = Arrays.stream(rawPasswords)
//                .map(password -> encoder.encode(password))
//                .toList();
//
//        hashedPasswords.forEach(System.out::println);
//
//
//
//
//
//
//
//
//
//    }
//}
