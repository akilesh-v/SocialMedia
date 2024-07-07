package com.akilesh.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akilesh.socialmedia.common.exceptions.UserDefinedException;
import com.akilesh.socialmedia.entity.Posts;
import com.akilesh.socialmedia.dto.PageRequestDto;
import com.akilesh.socialmedia.model.requestModel.PostsRequestModel;
import com.akilesh.socialmedia.service.PostsService;
import jakarta.validation.Valid;

/**
 * @author AkileshVasudevan
 */
@RestController
@RequestMapping("/api")
public class PostsController {

    @Autowired
    private PostsService postsService;

    @GetMapping("/hello")
    public String hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Hello " + authentication.getName();
    }

    @PostMapping("/create_post")
    public ResponseEntity<Posts> createPost(@RequestBody @Valid PostsRequestModel posts) {
        return postsService.createPost(posts);
    }

    @PutMapping("/update_post")
    public ResponseEntity<Posts> updatePost(@RequestBody @Valid PostsRequestModel posts)
            throws UserDefinedException {
        return postsService.updatePost(posts);
    }

    @DeleteMapping("/delete_post/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "postId") Long postId) {
       return postsService.deletePost(postId);
    }

    @GetMapping("/get_all_posts")
    public ResponseEntity<Page<Posts>> getAllPosts(@RequestBody PageRequestDto pageRequestDto)
            throws UserDefinedException {
        Pageable pageable = pageRequestDto.getPageable(pageRequestDto);
        return postsService.getAllPosts(pageable);
    }

    @GetMapping("/query_method/{noOfLikes}")
    public ResponseEntity<Page<Posts>> getValueBasedPosts(@RequestBody PageRequestDto pageRequestDto, @PathVariable(value = "noOfLikes") int noOfLikes) {
        Pageable pageable = pageRequestDto.getPageable(pageRequestDto);
        return postsService.getValueBasedPosts(noOfLikes,pageable);
    }

    @GetMapping("/get_post/{postId}")
    public ResponseEntity<Posts> findById(@PathVariable(value = "postId") Long postId) {
        return postsService.findPostsById(postId);
    }

}
