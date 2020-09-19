package pl.ubezpieczenia.strona;

import org.springframework.security.core.context.SecurityContextHolder;

public class Test {
    public static void main(String [] args){
        SecurityContextHolder.getContext().getAuthentication();
        System.out.println(
                SecurityContextHolder.getContext().getAuthentication());
    }
}
