package controllers;

import java.util.Comparator;

import models.Friendship;
import models.Message;
import models.User;

public class MostPopular implements Comparator<User> {

	@Override
	public int compare(User a, User b) {
		return Integer.compare(b.friendships.size(), a.friendships.size());
	}
}