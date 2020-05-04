package com.projet.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.Entite.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
