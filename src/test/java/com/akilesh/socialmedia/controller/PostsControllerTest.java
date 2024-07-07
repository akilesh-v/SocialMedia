package com.akilesh.socialmedia.controller;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.akilesh.socialmedia.entity.Posts;
import com.akilesh.socialmedia.model.requestModel.PostsRequestModel;
import com.akilesh.socialmedia.service.PostsService;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author AkileshVasudevan
 */
@WebMvcTest(PostsController.class)
class PostsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostsService postsService;

    private Posts post;


    @BeforeEach
    void setUp() {
        PostsRequestModel postsRequestModel = new PostsRequestModel(1L, 1L, "Test post", 50000, 10000);
        post = new Posts(postsRequestModel);
        post.setId(1L);
    }

    @Test
    void testCreatePost() throws Exception {
        when(postsService.createPost(any(PostsRequestModel.class))).thenReturn(ResponseEntity.ok(post));

        mockMvc.perform(post("/api/create_post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"postData\":\"Test post\",\"noOfLikes\":0,\"noOfComments\":0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.postData").value("Test post"));
    }

    @Test
    void testGetAllPosts() throws Exception {
        Page<Posts> postsPage = new PageImpl<>(Arrays.asList(post));
        when(postsService.getAllPosts(any(Pageable.class))).thenReturn(ResponseEntity.ok(postsPage));

        mockMvc.perform(get("/api/get_all_posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"page\":0,\"size\":10}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].postData").value("Test post"));
    }

    @Test
    void testFindById() throws Exception {
        when(postsService.findPostsById(1L)).thenReturn(ResponseEntity.ok(post));

        mockMvc.perform(get("/api/get_post/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.postData").value("Test post"));
    }
}
