package controllers;

import java.util.Comparator;

import models.Message;

public class MessageUserComparator implements Comparator<Message> {

	@Override
	public int compare(Message m, Message m1) {
		return m.from.firstName.compareTo(m1.from.firstName);
	}
}