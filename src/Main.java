public class Main {

	
	
	//private static List<StackOverflowUser> users = new LinkedList<StackOverflowUser>();

	/* Main function */
	public static void main(String[] args) throws Exception {
		
		StackOverflowUsers usr = new StackOverflowUsers();
		HTTPConnection httpCon = new HTTPConnection();
		usr.setItems(httpCon.ReadAllUsers());
		//ReadAllUsers();
		usr.SaveUsers(usr.getItems() ,"1.txt");
		//CompleteTagsList();
		
		usr.filterTheUsersByLocation(usr.getItems());
		httpCon.CompleteTagsList(usr.getItems());
		usr.filterTheUsersByTags(usr.getItems());
		usr.SaveUsers(usr.getItems() ,"2.txt");

	}
}
