public class Main {
	/* Main function */
	public static void main(String[] args) throws Exception {

		StackOverflowUsers users = new StackOverflowUsers();
		HTTPConnection httpConnections = new HTTPConnection();
		users.setItems(httpConnections.ReadAllUsers());
		users.SaveUsers(users.getItems(), "UsersFromMDAndRO.txt");
		users.filterTheUsersByLocation(users.getItems());
		httpConnections.CompleteTagsList(users.getItems());
		users.filterTheUsersByTags(users.getItems());
		users.SaveUsers(users.getItems(), "Users.txt");

	}
}
