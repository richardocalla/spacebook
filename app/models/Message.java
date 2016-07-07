package models;

import java.util.Date;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class Message extends Model {

	public String messageText;

	public String subject;

	@ManyToOne
	public User from;

	@ManyToOne
	public User to;

	public Date postedAt;

	public Message(User from, User to, String subject, String messageText) {
		this.from = from;
		this.to = to;
		this.messageText = messageText;
		this.subject = subject;
		this.postedAt = new Date();
	}
}