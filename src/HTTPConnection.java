import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import com.google.gson.Gson;

public class HTTPConnection {
	private final static String USER_AGENT = "Mozilla/5.0";
	private final static String ACCESS_TOKEN = "u*lpA9G0d3Ui3mda7IG6DA))";
	private final static String KEY = "ZSvhNIiWym373P5sUT588Q((";
	private static int NumberOfRequests;

	public static int getNumberOfRequests() {
		return NumberOfRequests;
	}



	public static void setNumberOfRequests(int numberOfRequests) {
		NumberOfRequests = numberOfRequests;
	}



	public HTTPConnection() {

	}
	
	private  String sendGet(URL url) throws Exception {
		NumberOfRequests++;
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		/* Connection properties */
		connection.setRequestProperty("Accept-Encoding", "gzip");
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		/* Check the connection */
		int responseCode = connection.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		/* Check the response */
		if (responseCode == 200) {
			Reader reader = null;
			if ("gzip".equals(connection.getContentEncoding())) {
				reader = new InputStreamReader(new GZIPInputStream(connection.getInputStream()));
			} else {
				reader = new InputStreamReader(connection.getInputStream());
			}

			StringBuilder str = new StringBuilder();
			while (true) {
				int ch = reader.read();
				if (ch == -1) {
					break;
				}
				str.append(Character.toString(ch));
			}
			return str.toString();
		} else
			return null;
	}

	public List<StackOverflowUser> ReadAllUsers() throws Exception {
		int i = 0;
		List<StackOverflowUser> users = new LinkedList<StackOverflowUser>();
		while (true) {
			URL url = new URL("https://api.stackexchange.com/2.2/users?page=" + (++i)
					+ "&pagesize=100&order=desc&min=223&sort=reputation&site=stackoverflow&filter=!0Z-LvgkSiw7KSmiKj3n1CYF3b&access_token="
					+ ACCESS_TOKEN + "&key=" + KEY);
			String stringToParse = sendGet(url);
			if (stringToParse != null) {
				Gson gson = new Gson();
				StackOverflowUsers us = gson.fromJson(stringToParse, StackOverflowUsers.class);
				users.addAll(us.getItems());
				if (!us.isHas_more()) {
					break;
				}

				if (i % 50 == 0) {
					TimeUnit.SECONDS.sleep(5);
				}

			} else
				break;
		}
		return users;
	}

	public List<StackOverflowUser> CompleteTagsList(List<StackOverflowUser> users) throws Exception {
		if (users.isEmpty()) {
			return null;
		}

		for (int i = 0; i < users.size(); i++) {
			int user_id = users.get(i).getUser_id();
			URL url = new URL(
					"https://api.stackexchange.com/2.2/users/" + user_id + "/tags?order=desc&sort=popular&access_token="
							+ ACCESS_TOKEN + "&key=" + KEY + "&site=stackoverflow");
			String stringToParse = sendGet(url);
			String user_tags = new String();
			if (stringToParse != null) {
				Gson gson = new Gson();
				StackOverflowTags tags = gson.fromJson(stringToParse, StackOverflowTags.class);
				for (int j = 0; j < tags.getItems().size(); j++) {
					user_tags = user_tags + tags.getItems().get(j).getName() + ",";
				}

				users.get(i).setTags(user_tags);
			}
			if (i % 50 == 0) {
				TimeUnit.SECONDS.sleep(5);
			}
		}
		return users;

	}

}
