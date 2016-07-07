import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import play.*;
import play.db.jpa.Blob;
import play.jobs.*;
import play.libs.MimeTypes;
import play.test.*;

import models.*;

@OnApplicationStart
public class Bootstrap extends Job {
	public void doJob() throws FileNotFoundException {
		Fixtures.deleteDatabase();
		Fixtures.loadModels("data.yml");

		String photoName = "app/homer.gif";
		Blob blob = new Blob();
		blob.set(new FileInputStream(photoName), MimeTypes.getContentType(photoName));
		User homer = User.findByEmail("homer@simpson.com");
		homer.profilePicture = blob;
		homer.save();

		String photoName1 = "app/bart.gif";
		Blob blob1 = new Blob();
		blob1.set(new FileInputStream(photoName1), MimeTypes.getContentType(photoName1));
		User bart = User.findByEmail("bart@simpson.com");
		bart.profilePicture = blob1;
		bart.save();

		String photoName2 = "app/maggie.gif";
		Blob blob2 = new Blob();
		blob2.set(new FileInputStream(photoName2), MimeTypes.getContentType(photoName2));
		User maggie = User.findByEmail("maggie@simpson.com");
		maggie.profilePicture = blob2;
		maggie.save();

		String photoName3 = "app/marge.gif";
		Blob blob3 = new Blob();
		blob3.set(new FileInputStream(photoName3),
		MimeTypes.getContentType(photoName3));
		User marge = User.findByEmail("marge@simpson.com");
		marge.profilePicture = blob3;
		marge.save();
		
		String photoName4 = "app/barney.gif";
		Blob blob4 = new Blob();
		blob4.set(new FileInputStream(photoName4),
		MimeTypes.getContentType(photoName4));
		User barney = User.findByEmail("barney@gumble.com");
		barney.profilePicture = blob4;
		barney.save();
		
		String photoName5 = "app/lisa.gif";
		Blob blob5 = new Blob();
		blob5.set(new FileInputStream(photoName5),
		MimeTypes.getContentType(photoName5));
		User lisa = User.findByEmail("lisa@simpson.com");
		lisa.profilePicture = blob5;
		lisa.save();
		
		String photoName6 = "app/milhouse.gif";
		Blob blob6 = new Blob();
		blob6.set(new FileInputStream(photoName6),
		MimeTypes.getContentType(photoName6));
		User milhouse = User.findByEmail("milhouse@vanhouten.com");
		milhouse.profilePicture = blob6;
		milhouse.save();
		
		String photoName7 = "app/moe.gif";
		Blob blob7 = new Blob();
		blob7.set(new FileInputStream(photoName7),
		MimeTypes.getContentType(photoName7));
		User moe = User.findByEmail("moe@szyslak.com");
		moe.profilePicture = blob7;
		moe.save();
		
		String photoName8 = "app/ned.gif";
		Blob blob8 = new Blob();
		blob8.set(new FileInputStream(photoName8),
		MimeTypes.getContentType(photoName8));
		User ned = User.findByEmail("ned@flanders.com");
		ned.profilePicture = blob8;
		ned.save();
	}
}