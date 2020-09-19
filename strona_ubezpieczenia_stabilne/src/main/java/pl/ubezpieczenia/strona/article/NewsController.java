package pl.ubezpieczenia.strona.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.ubezpieczenia.strona.authenticated.CheckAuth;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class NewsController {

private NewsRepository newsRepository;
@Autowired
    NewsController(NewsRepository newsRepository){
        this.newsRepository=newsRepository;
    }


    @GetMapping("/dodaj_wpis")
    public String newsController(@ModelAttribute NewsDao newsDao, Model model){
    newsDao.setIsAccepted(false);
        model.addAttribute("wpis", CheckAuth.checkAuth());
            return "new_article";

        }

        @PostMapping("/add")
        public String addArticle(@ModelAttribute NewsDao newsDao, Model model) {

            if(checkNotEmpty(newsDao)) {
            newsDao.setFixedName(newsDao.getTitle());

            model.addAttribute("news_added","Nowy wpis o tytule: "+ newsDao.getTitle() +" został dodany");

            newsRepository.save(newsDao);

            model.addAttribute("news", newsDao);

            return "artykul";
        } else
            return "redirect:/";
    }


    @GetMapping("/accepted/{id}")
    public String acceptArt(@PathVariable("id") Long id, Model model){
        NewsDao newsDaoToAccept = newsRepository.findAllById(id).get(0);
        newsDaoToAccept.setIsAccepted(true);
        model.addAttribute("wpis", CheckAuth.checkAuth());
        newsRepository.save(newsDaoToAccept);
    return "home";
    }

    @PutMapping("/accepted/{id}")
    public NewsDao acceptArticle(@RequestBody NewsDao newsDao, @PathVariable("id") Long id){

    return newsRepository.findAllById(id).stream().map(article ->{
        article.setIsAccepted(true);
        return newsRepository.save(article);
    }).collect(Collectors.toList()).get(0);


    }

    @GetMapping("/nowosci")
    String listAllNews(Model model){
        List<NewsDao> allNews = newsRepository.findAllArticles();

        model.addAttribute("all_news", allNews);
        model.addAttribute("wpis", CheckAuth.checkAuth());

        return "allNews";
    }

    @RequestMapping("/deleteNews/{idNews}")
    public String deleteNews(@PathVariable("idNews") Long idNews, RedirectAttributes redirectAttributes){

    newsRepository.deleteById(idNews);
    redirectAttributes.addFlashAttribute("message", "Ostatni wpis został usunięty");

    return "redirect:/dodaj_wpis";
    }

    @RequestMapping("/artykul/{id}/{title}")
    public String showArticle(@PathVariable("id") Long id, @PathVariable("title") String title, Model model){
    NewsDao article = newsRepository.findAllById(id).get(0);

    model.addAttribute("article", article);
        String isHere =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        boolean isAuth = !isHere.equals("anonymousUser");
        model.addAttribute("present", isAuth);
        model.addAttribute("wpis", CheckAuth.checkAuth());
    return "choosen_one";

    }
    @RequestMapping("/deleteById/{id}")
    public String deleteAdmin(@PathVariable("id") Long id){

       newsRepository.deleteById(id);

    return "redirect:/nowosci";
    }


    private boolean checkNotEmpty(NewsDao article) {
        return (article.getTitle()!=null && article.getTitle().length()>0)
                && (article.getContent()!=null && article.getContent().length()>0);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam(value = "search", required = false) String searchPhrase, Model model) {

        List<NewsDao> searchResults = newsRepository.
                findSome(searchPhrase);

        model.addAttribute("search", searchResults);
        model.addAttribute("wpis", CheckAuth.checkAuth());

        return "newsSearch";
    }
}



