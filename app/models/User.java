package models;

import javax.persistence.Entity;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.persistence.OneToMany;

import controllers.MessageDateComparator;
import play.db.jpa.Model;
import play.db.jpa.Blob;

@Entity
public class User extends Model {
	public String firstName;
	public String lastName;
	// story 2
	public int age;
	public String nationality;
	// story 2
	public String email;
	public String password;
	public String statusText;
	public Blob profilePicture;

	@OneToMany(mappedBy = "sourceUser")
	public List<Friendship> friendships = new ArrayList<Friendship>();

	@OneToMany(mappedBy = "to")
	public List<Message> inbox = new ArrayList<Message>();

	@OneToMany(mappedBy = "from")
	public List<Message> outbox = new ArrayList<Message>();

	public User(String firstName, String lastName, int age, String nationality, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		// story 2
		this.age = age;
		this.nationality = nationality;
		// story 2
		this.email = email;
		this.password = password;
	}

	public static User findByEmail(String email) {
		return find("email", email).first();
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	public void befriend(User friend) {
		Friendship friendship = new Friendship(this, friend);
		friendships.add(friendship);
		friendship.save();
		save();
	}

	public void unfriend(User friend) {
		Friendship thisFriendship = null;

		for (Friendship friendship : friendships) {
			if (friendship.targetUser == friend) {
				thisFriendship = friendship;
			}
		}
		friendships.remove(thisFriendship);
		thisFriendship.delete();
		save();
	}

	public void sendMessage(User to, String subject, String messageText) {
		Message message = new Message(this, to, subject, messageText);
		outbox.add(message);
		to.inbox.add(message);
		message.save();
	}

	public static ArrayList<Message> getConversation(User user, User friend) {
		ArrayList<Message> conversations = new ArrayList<>();

		for (Message message : user.inbox) {
			if (message.from.equals(friend))
				conversations.add(message);
		}
		for (Message message : user.outbox) {
			if (message.to.equals(friend))
				conversations.add(message);
		}
		Collections.sort(conversations, new MessageDateComparator());
		return conversations;
	}
}