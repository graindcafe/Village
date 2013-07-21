package cappuccino.locales;

import java.util.HashMap;

import me.graindcafe.gls.DefaultLanguage;
import me.graindcafe.gls.Language;

public class Messages {
	static Language lang = new DefaultLanguage();
	static {
		DefaultLanguage.setLocales(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("Message.Full", "The village is full");
				put("Message.AlreadyThere", "You're already in this village");
				put("Message.NonExistingPlayer",
						"The player name you specified doesn't exists");
				put("Message.NonExistingVillage", "The village doesn't exists");
				put("Message.Joined", "You joined the village.");
				put("Message.Created", "You created a new village.");
				put("Message.NotCreated", "You cannot create a new village.");
			}
		});
	}

	private Messages() {

	}

	public static void loadLanguage(String name) {
		lang = Language.init(name);
	}

	public static String getString(String key) {

		String str = lang.get(key);
		if (str != null)
			return str;
		else
			return '!' + key + '!';
	}
}
