package com.akilesh.socialmedia.entity;

import java.io.Serializable;

import com.akilesh.socialmedia.model.requestModel.PostsRequestModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author AkileshVasudevan
 */
@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Posts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="user_id")
    private Long userId;

    @Column(name="post_data")
    private String postData;

    @Column(name="no_of_likes")
    private int noOfLikes;

    @Column(name="no_of_comments")
    private int noOfComments;

    public Posts(PostsRequestModel postsRequestModel) {
        this.userId = postsRequestModel.getUserId();
        this.postData = postsRequestModel.getPostData();
        this.noOfLikes = postsRequestModel.getNoOfLikes();
        this.noOfComments = postsRequestModel.getNoOfComments();
    }
}
