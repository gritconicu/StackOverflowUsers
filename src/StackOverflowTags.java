import java.util.List;

public class StackOverflowTags {
	private List<StackOverflowTag> items;
	private boolean has_more;
	private int quota_max;
	private int quota_remaining;

	public List<StackOverflowTag> getItems() {
		return items;
	}

	public void setItems(List<StackOverflowTag> items) {
		this.items = items;
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

}
