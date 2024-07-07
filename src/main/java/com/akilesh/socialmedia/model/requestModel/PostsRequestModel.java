package com.akilesh.socialmedia.model.requestModel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author AkileshVasudevan
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostsRequestModel {
    private Long postId;
    @NotNull(message = "UserId is Mandatory")
    private Long userId;
    @NotBlank(message = "Post Data is Empty")
    private String postData;
    @Min(0)
    private Integer noOfLikes;
    @Min(0)
    private Integer noOfComments;
}
