package pl.ubezpieczenia.strona.authenticated;

import org.springframework.security.core.context.SecurityContextHolder;

public class CheckAuth {
    public static boolean checkAuth(){

        return !SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()
                .equals("anonymousUser");
    }
}
