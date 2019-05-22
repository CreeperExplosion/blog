package xyz.ghibran.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.ghibran.blog.service.BlogVisitorService;

/**
 * BlogVisitorMVC
 * 
 * handles blog's vistor
 */
@Controller
public class BlogVisitorMVC {

    @Autowired
    BlogVisitorService service;

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String viewHomePage(@RequestParam(defaultValue = "0") int page, Model model) {

        final int postPerPage = 10;

        var posts = service.getPostInPage(page, postPerPage);
        model.addAttribute("firstPost", posts.get(0));
        model.addAttribute("posts", posts.subList(1, posts.size() - 1));

        return "home";
    }

    @GetMapping("/blog/{url}")
    public String viewBlogEntry(Model model, @PathVariable String url) {
        model.addAttribute("post", service.getPost(url));
        return "blog";
    }
}