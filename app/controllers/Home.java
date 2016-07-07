package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Home extends Controller {

	public static void index() {
		String userId = session.get("logged_in_userid");
		User user = User.findById(Long.parseLong(userId));

		if (user == null) {
			Accounts.signup();
		}

		render(user);
	}

	public static void changedetails() {
		render();
	}

	public static void conversation() {

		String userId = session.get("logged_in_userid");
		User user = User.findById(Long.parseLong(userId));

		ArrayList<ArrayList<Message>> con = new ArrayList<>();
		ArrayList<User> senders = new ArrayList<>();

		for (Message message : user.inbox) {
			if (!senders.contains(message.from)) {
				senders.add(message.from);
			}
		}

		for (Message message : user.outbox) {
			if (!senders.contains(message.to)) {
				senders.add(message.to);
			}
		}

		for (User friend : senders) {
			con.add(User.getConversation(user, friend));
		}
		render(user, con);
	}

	public static void dateSort() {
		String userId = session.get("logged_in_userid");
		User user = User.findById(Long.parseLong(userId));
		MessageDateComparator md = new MessageDateComparator();

		Collections.sort(user.inbox, md);
		// ALT Collections.sort(user.inbox, new MessageDateComparator());
		render(user);
	}

	public static void nameSort() {
		String userId = session.get("logged_in_userid");
		User user = User.findById(Long.parseLong(userId));
		MessageUserComparator md = new MessageUserComparator();

		Collections.sort(user.inbox, md);

		render(user);
	}

	public static void drop(Long id) {
		String userId = session.get("logged_in_userid");
		User user = User.findById(Long.parseLong(userId));

		User friend = User.findById(id);

		user.unfriend(friend);
		Logger.info("Dropping " + friend.email);
		index();
	}

	public static void update(String firstName, String lastName, String nationality, String password, String email) {
		String userId = session.get("logged_in_userid");
		User user = User.findById(Long.parseLong(userId));

		user.firstName = (firstName.equals("")) ? user.firstName : firstName;
		user.lastName = (lastName.equals("")) ? user.lastName : lastName;
		user.nationality = (nationality.equals("")) ? user.nationality : nationality;

		if (user.password.equals(password) && (user.email.equals(email))) {
			user.save();
			Logger.info("Details changed to: " + "First name: " + firstName + " " + "Last Name: " + lastName + " "
					+ "Nationality: " + nationality);
			Home.index();
		} else {
			Logger.info("Details not changed - password incorrect");
			Home.index();
		}
	}

}