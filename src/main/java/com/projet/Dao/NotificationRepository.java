package com.projet.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projet.Entite.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	 @Query(nativeQuery = true, value = "select n from Notification n where n.id_notification=: id_notification  ORDER BY n.id_notification DESC LIMIT 5")
	  public Notification listNotif();
	 
	 @Query("select s from Notification s where s.status=1")
	   public Notification listeParStatus();
	 
}
