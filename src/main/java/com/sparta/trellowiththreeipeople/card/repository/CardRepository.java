package com.sparta.trellowiththreeipeople.card.repository;

import com.sparta.trellowiththreeipeople.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {

}
