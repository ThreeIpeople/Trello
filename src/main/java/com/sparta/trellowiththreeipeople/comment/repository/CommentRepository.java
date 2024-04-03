package com.sparta.trellowiththreeipeople.comment.repository;

import com.sparta.trellowiththreeipeople.card.entity.Card;
import com.sparta.trellowiththreeipeople.comment.entity.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    List<Comment> findByCard(Card card);

    Optional<Comment> findByIdAndCard(Long commentId, Card card);

}
