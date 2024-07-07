package com.akilesh.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akilesh.socialmedia.entity.Users;

/**
 * @author AkileshVasudevan
 */
@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

}
