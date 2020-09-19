package pl.ubezpieczenia.strona.contact.a_contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.ubezpieczenia.strona.authenticated.CheckAuth;
import javax.validation.Valid;

@Controller
public class ContackMailController {
    private ContactRepository contactRepository;
    private ContactMainServoce contactMainServoce;
    private TemplateEngine templateEngine;
    @Autowired
    ContackMailController(ContactRepository contactRepository, ContactMainServoce contactMainServoce, TemplateEngine templateEngine) {
        this.contactRepository = contactRepository;
        this.contactMainServoce = contactMainServoce;
        this.templateEngine=templateEngine;
    }

    @GetMapping("/kontakt")
    String mailConta(@ModelAttribute("contact") ContactMailDao contactMailDao, Model model){

        model.addAttribute("contact",contactMailDao);
        model.addAttribute("wpis", CheckAuth.checkAuth());

        return "kontakt";
    }


    @RequestMapping("/kontakt")
    public String sendMail(@ModelAttribute("contact") @Valid ContactMailDao contact, BindingResult bindingResult, Model model,  RedirectAttributes redirectAttributes) {



            if(bindingResult.hasErrors() ){

                    return "kontakt";
            }

        Context context = new Context();


        context.setVariable("header", contact.getClientName());
        context.setVariable("title", contact.getClientContent());
        context.setVariable("description", contact.getClientMail());
        String body = templateEngine.process("a", context);


        contactMainServoce.sendContactMail("Nowa prośba o kotnakt", body);
        contactRepository.save(contact);


        redirectAttributes.addFlashAttribute("message", "Prośba o kontakt została wysłana! ");


        return "redirect:/kontakt";
    }

}
