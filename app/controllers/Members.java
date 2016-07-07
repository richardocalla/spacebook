package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Members extends Controller {

	public static void index() {
		List<User> users = User.findAll();
		String userId = session.get("logged_in_userid");
		User user = User.findById(Long.parseLong(userId));

		if (user == null) {
			Accounts.signup();
		}

		// Story 5
		User rm = User.findById(Long.parseLong(userId));
		users.remove(rm);
		// Story 5

		render(users);
	}

	public static void follow(Long id) {
		User friend = User.findById(id);

		String userId = session.get("logged_in_userid");
		User me = User.findById(Long.parseLong(userId));

		me.befriend(friend);
		Home.index();
	}
}