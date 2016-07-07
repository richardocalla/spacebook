package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Leaderboard extends Controller {

	public static void index() {
		List<User> users = User.findAll();
		String userId = session.get("logged_in_userid");
		User user = User.findById(Long.parseLong(userId));

		if (user == null) {
			Accounts.signup();
		}

		render(users);
	}

	public static void socialSort() {
		List<User> users = User.findAll();
		// String userId = session.get("logged_in_userid");
		// User user = User.findById(Long.parseLong(userId));
		MostPopular md = new MostPopular();

		Collections.sort(users, md);
		render(users);
	}

	public static void talkativeSort() {
		List<User> users = User.findAll();
		// String userId = session.get("logged_in_userid");
		// User user = User.findById(Long.parseLong(userId));
		MostTalkative md = new MostTalkative();

		Collections.sort(users, md);
		render(users);
	}
}