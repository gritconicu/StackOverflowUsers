public class StackOverflowUser {
	private int account_id;
	private int reputation;
	private int user_id;
	private int answer_count;
	private int question_count;
	private String location;
	private String link;
	private String profile_image;
	private String display_name;
	private String tags;

	public int getAccount_id() {
		return account_id;
	}

	public int getReputation() {
		return reputation;
	}

	public int getUser_id() {
		return user_id;
	}

	public int getAnswer_count() {
		return answer_count;
	}

	public int getQuestion_count() {
		return question_count;
	}

	public String getLocation() {
		return location;
	}

	public String getLink() {
		return link;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return String.format(
				"\n****************************\naccount_id: %d \nreputation:%d \nuser_id=%d \nanswer_count=%d \nquestion_count=%d \nlocation=%s \nlink=%s \nprofile_image=%s \ndisplay_name=%s\ntags:%s",
				account_id, reputation, user_id, answer_count, question_count, location, link, profile_image,
				display_name, tags);
	}

	// Default constructor
	public StackOverflowUser() {

	}

}
