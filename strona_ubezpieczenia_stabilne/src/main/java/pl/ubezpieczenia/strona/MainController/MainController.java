package pl.ubezpieczenia.strona.MainController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.ubezpieczenia.strona.authenticated.CheckAuth;
import pl.ubezpieczenia.strona.security.User;
import pl.ubezpieczenia.strona.security.UserRepository;
import pl.ubezpieczenia.strona.security.UserService;
import javax.validation.Valid;
import java.util.*;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String goHome(Model model){
        model.addAttribute("wpis", CheckAuth.checkAuth());

        return "home";
    }


    @GetMapping("/oferta")
    public String showOffer(Model model){
        model.addAttribute("wpis", CheckAuth.checkAuth());
        return "oferta";
    }
    @GetMapping("/o-mnie")
    public String aboutMe(Model model){
        model.addAttribute("wpis", CheckAuth.checkAuth());
        return "omnie";
    }

    @GetMapping("/login")
    public String goToLogin(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


    @PostMapping("/register")
    public String addUser(@ModelAttribute @Valid User user,
                          BindingResult bindResult) {

        Optional<User> findEmail = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        if(findEmail.isPresent()){ //przypadek kiedy wskazany email juz istnieje w bazie
            ObjectError objectError = new ObjectError(user.getEmail(), "zly mail");
            bindResult.addError(objectError);
            user.setEmail(""); //nie przejdzie przez validacje

        }
        if(bindResult.hasErrors()) {
            return "register";
        }
        else {

            user.setFirstName(user.getFirstName().substring(0,1).toUpperCase() + user.getFirstName().substring(1).toLowerCase());
            user.setLastName(user.getLastName().substring(0,1).toUpperCase() + user.getLastName().substring(1).toLowerCase());

            userService.addWithDefaultRole(user);

            return "redirect:/";
        }


    }
}
