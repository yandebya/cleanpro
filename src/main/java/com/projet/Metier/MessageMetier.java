package com.projet.Metier;

import java.util.List;

import com.projet.Entite.Message;

public interface MessageMetier {

	public Message creerMessage(Message m);
	public List<Message> listeMessage();
}
