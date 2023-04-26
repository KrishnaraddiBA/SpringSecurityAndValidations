package com.SpringFirstApplication.repository;

import com.SpringFirstApplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//markerInterface
@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
