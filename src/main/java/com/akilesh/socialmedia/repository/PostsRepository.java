package com.akilesh.socialmedia.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akilesh.socialmedia.entity.Posts;

/**
 * @author AkileshVasudevan
 */
@Repository
public interface PostsRepository extends JpaRepository<Posts,Long> {

    Page<Posts> findByNoOfLikesGreaterThan(int noOfLikes, Pageable pageable);

}
