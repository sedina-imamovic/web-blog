package bih.iths.sedina.webblog.controller;

import bih.iths.sedina.webblog.model.BlogPost;
import bih.iths.sedina.webblog.service.BlogPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/posts")
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }
    
    @GetMapping
    public String viewPosts(Model model) {
        model.addAttribute("posts", blogPostService.getAllPosts());
        return "posts";
    }

    @GetMapping("/create")
    public String showForm(Model model) {
        model.addAttribute("post", new BlogPost());
        return "create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute BlogPost blogPost) {
        blogPost.setCreatedAt(LocalDateTime.now());
        blogPostService.savePost(blogPost);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        model.addAttribute("post", blogPostService.getPostById(id));
        return "post-details";
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        blogPostService.deletePost(id);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String editPost(@PathVariable Long id, Model model) {
        model.addAttribute("post", blogPostService.getPostById(id));
        return "edit-post";
    }

    @PostMapping("/{id}")
    public String updatePost(@PathVariable Long id,
                             @ModelAttribute BlogPost blogPost) {
        blogPostService.updatePost(id, blogPost);
        return "redirect:/posts";
    }

}
