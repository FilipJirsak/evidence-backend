package net.czela.backend.evidence.ucetnictvi.flexibee;

/**
 * @author Filip Jirsák
 */
public class FlexibeePage {
	private static final FlexibeePage DEFAULT_VALUE = new FlexibeePage(0, 25, true);

	private Integer start;
	private Integer limit;
	private Boolean addRowCount;

	public static FlexibeePage getDefault() {
		return DEFAULT_VALUE;
	}

	public FlexibeePage() {
	}

	public FlexibeePage(Integer start, Integer limit, Boolean addRowCount) {
		this.start = start;
		this.limit = limit;
		this.addRowCount = addRowCount;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Boolean getAddRowCount() {
		return addRowCount;
	}

	public void setAddRowCount(Boolean addRowCount) {
		this.addRowCount = addRowCount;
	}
}
