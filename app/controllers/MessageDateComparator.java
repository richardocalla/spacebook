package controllers;

import java.util.Comparator;

import models.Message;

public class MessageDateComparator implements Comparator<Message> {

	@Override
	public int compare(Message m, Message m1) {
		return m.postedAt.compareTo(m1.postedAt);
	}
}