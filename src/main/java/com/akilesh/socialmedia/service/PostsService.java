package com.akilesh.socialmedia.service;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.akilesh.socialmedia.common.exceptions.UserDefinedException;
import com.akilesh.socialmedia.common.exceptions.advice.RestExceptionHandling;
import com.akilesh.socialmedia.entity.Posts;
import com.akilesh.socialmedia.model.requestModel.PostsRequestModel;
import com.akilesh.socialmedia.repository.PostsRepository;

/**
 * @author AkileshVasudevan
 */

@Service
public class PostsService {

    private static final Log logger = LogFactory.getLog(RestExceptionHandling.class);

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private RedisTemplate<String, Posts> redisTemplate;

    public ResponseEntity<Page<Posts>> getAllPosts(Pageable pageable) {
        try {
            return ResponseEntity.ok(postsRepository.findAll(pageable));
        } catch (Exception ex) {
            logger.error(ex);
            throw new RuntimeException(ex);
        }
    }

    public ResponseEntity<Page<Posts>> getValueBasedPosts(int noOfLikes,Pageable pageable) {
        return ResponseEntity.ok(postsRepository.findByNoOfLikesGreaterThan(noOfLikes, pageable));
    }

    public ResponseEntity<Posts> createPost(PostsRequestModel postModel) {
        Posts post = new Posts(postModel);
        return ResponseEntity.ok(postsRepository.save(post));
    }

    @CachePut(value = "posts", key = "#post.id")
    public ResponseEntity<Posts> updatePost(PostsRequestModel postModel) throws UserDefinedException {
        Optional<Posts> existingPosts = postsRepository.findById(postModel.getPostId());
        if (existingPosts.isPresent()) {
            Posts post = existingPosts.get();
            post.setPostData(postModel.getPostData());
            post.setUserId(postModel.getUserId());
            post.setNoOfLikes(postModel.getNoOfLikes());
            post.setNoOfComments(postModel.getNoOfComments());
            redisTemplate.opsForValue().set("posts:" + postModel.getPostId(), post);
            return ResponseEntity.ok(postsRepository.save(post));
        } else {
            throw new UserDefinedException("Id Not Found::::" + postModel.getPostId());
        }
    }

    @Cacheable(value = "posts",key = "#id")
    public ResponseEntity<Posts> findPostsById(Long id) {
        return ResponseEntity.ok(postsRepository.findById(id).orElseThrow());
    }

    @CacheEvict(value = "posts", key = "#post.id")
    public ResponseEntity<?> deletePost(Long postId) {
        postsRepository.deleteById(postId);
        return ResponseEntity.ok("Successfully deleted post");
    }


/*    public Page<Posts> getAllListPosts(PageRequestDto pageRequestDto) {
        List<Posts> posts = postsRepository.findAll();

        PagedListHolder<Posts> pagedListHolder= new PagedListHolder<>(posts);
        pagedListHolder.setPage(pageRequestDto.getPageNo());
        pagedListHolder.setPageSize(pageRequestDto.getPageSize());

        List<Posts> pageSlice = pagedListHolder.getPageList();
        PropertyComparator.sort(pageSlice,new MutableSortDefinition("id",true,false));

        Page<Posts> pagedData = new PageImpl<>(pageSlice,
                new PageRequestDto().getPageable(pageRequestDto),
                posts.size());
        return pagedData;
    }*/
}
