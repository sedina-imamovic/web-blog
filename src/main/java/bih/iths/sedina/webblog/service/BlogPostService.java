package bih.iths.sedina.webblog.service;

import bih.iths.sedina.webblog.model.BlogPost;
import bih.iths.sedina.webblog.repository.BlogPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAllByOrderByCreatedAtDesc();
    }

    public BlogPost getPostById(Long id) {
        return blogPostRepository.findById(id).orElseThrow();
    }

    public BlogPost savePost(BlogPost blogPost) {
        //blogPost.setCreatedAt(LocalDateTime.now());
        return blogPostRepository.save(blogPost);
    }

    public void deletePost(Long id) {
        blogPostRepository.deleteById(id);
    }

    public void updatePost(Long id, BlogPost updatedPost) {
        BlogPost existing = getPostById(id);

        existing.setTitle(updatedPost.getTitle());
        existing.setContent(updatedPost.getContent());
        existing.setImageUrl(updatedPost.getImageUrl());

        blogPostRepository.save(existing);
    }
}
