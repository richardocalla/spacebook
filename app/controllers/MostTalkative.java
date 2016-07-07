package controllers;

import java.util.Comparator;

import models.Message;
import models.User;

public class MostTalkative implements Comparator<User> {

	@Override
	public int compare(User a, User b) {
		return Integer.compare(b.outbox.size(), a.outbox.size());
	}
}