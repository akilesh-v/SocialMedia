package com.akilesh.socialmedia.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.akilesh.socialmedia.model.requestModel.UserRequestModel;
import com.akilesh.socialmedia.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author AkileshVasudevan
 */
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserRequestModel userRequestModel;

    @BeforeEach
    void setUp() {
        userRequestModel = new UserRequestModel();
        // Set up your UserRequestModel with test data
        userRequestModel.setName("testuser");
        userRequestModel.setEmail("test@example.com");
        userRequestModel.setPassword("password123");
    }

    @Test
    void testRegisterUser() throws Exception {
        Long userId = 1L;
        when(userService.registerUser(any(UserRequestModel.class))).thenReturn(userId);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestModel)))
                .andExpect(status().isOk());
    }

    @Test
    void testRegisterUserWithInvalidData() throws Exception {
        userRequestModel.setEmail("invalid-email");  // Set an invalid email to test validation

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestModel)))
                .andExpect(status().isBadRequest());
    }
}
