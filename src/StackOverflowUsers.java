import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class StackOverflowUsers {

	private List<StackOverflowUser> items;
	private boolean has_more;
	private int quota_max;
	private int quota_remaining;

	public void setItems(List<StackOverflowUser> items) {
		this.items = items;
	}

	public List<StackOverflowUser> getItems() {
		return items;
	}

	public boolean isHas_more() {
		return has_more;
	}

	public int getQuota_max() {
		return quota_max;
	}

	public int getQuota_remaining() {
		return quota_remaining;
	}

	public List<StackOverflowUser> filterTheUsersByLocation(List<StackOverflowUser> users) {
		if (users.isEmpty()) {
			return null;
		}

		for (int i = 0; i < users.size(); i++) {
			// All users that not contain needed location and tags are deleted from linked
			// list
			var location = users.get(i).getLocation();
			if (location != null) {
				if (!(location.contains("Moldova")) && !(location.contains("Romania"))) {
					users.remove(i);
					i--;
				} else if (users.get(i).getAnswer_count() == 0) {
					users.remove(i);
					i--;
				}
			} else {
				users.remove(i);
				i--;
			}

		}

		return users;
	}

	public List<StackOverflowUser> filterTheUsersByTags(List<StackOverflowUser> users) {
		if (users.isEmpty()) {
			return null;
		}

		for (int i = 0; i < users.size(); i++) {
			var tags = users.get(i).getTags();
			if (tags != null) {
				if (tags.contains("java") || tags.contains(".net") || tags.contains("docker") || tags.contains("c#")) {
					continue;
				} else {
					users.remove(i);
					i--;
				}
			}

		}

		return users;
	}

	// Save a list of users in a txt file
	public void SaveUsers(List<StackOverflowUser> users, String file_name) {
		try {
			FileOutputStream fos = new FileOutputStream(file_name);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			for (int i = 0; i < users.size(); i++) {
				oos.writeObject(users.get(i).toString());
			}
			fos.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
