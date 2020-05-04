package com.projet.Metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.Dao.MessageRepository;
import com.projet.Entite.Message;
@Service
public class MessageMetierImp implements MessageMetier {
	
	@Autowired
	private MessageRepository mr;
	@Override
	public Message creerMessage(Message m) {
		
		return mr.save(m);
	}

	@Override
	public List<Message> listeMessage() {
		
		return mr.findAll();
	}

}
