package com.sparta.trellowiththreeipeople.comment.repository;

import com.sparta.trellowiththreeipeople.card.entity.Card;
import com.sparta.trellowiththreeipeople.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByCard(Card card);
}
