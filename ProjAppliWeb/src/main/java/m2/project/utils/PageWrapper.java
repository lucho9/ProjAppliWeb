package m2.project.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Order;

public class PageWrapper<T> {
	public static final int MAX_PAGE_ITEM_DISPLAY = 3;
	private Page<T> page;
	private List<PageItem> items;
	private int currentNumber;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PageWrapper(Page<T> page, String url) {
		this.page = page;
		this.url = url;
		items = new ArrayList<PageItem>();
		currentNumber = this.page.getNumber(); // start from 1 to match page.page
		int start, size;
		if (this.page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY) {
			start = 0;
			size = this.page.getTotalPages();
		} else {
			if (currentNumber < MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY / 2) {
				start = 0;
				size = MAX_PAGE_ITEM_DISPLAY;
			} else if (currentNumber >= this.page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY / 2) {
				start = this.page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY;
				size = MAX_PAGE_ITEM_DISPLAY;
			} else {
				start = currentNumber - MAX_PAGE_ITEM_DISPLAY / 2;
				size = MAX_PAGE_ITEM_DISPLAY;
			}
		}
		for (int i = 0; i < size; i++) {
			items.add(new PageItem(start + i, (start + i) == currentNumber));
		}
	}

	public List<PageItem> getItems() {
		return items;
	}

	public int getNumber() {
		return currentNumber;
	}

	public List<T> getContent() {
		return page.getContent();
	}

	public int getSize() {
		return page.getSize();
	}

	public int getTotalPages() {
		return page.getTotalPages();
	}

	public boolean isFirstPage() {
		return page.isFirst();
	}

	public boolean isLastPage() {
		return page.isLast();
	}

	public boolean hasPreviousPage() {
		return page.hasPrevious();
	}

	public boolean hasNextPage() {
		return page.hasNext();
	}

	public Order getOrderFor(String property) {
		return page.getSort().getOrderFor(property);
	}
	
	public class PageItem {
		private int number;
		private boolean current;

		public PageItem(int number, boolean current) {
			this.number = number;
			this.current = current;
		}

		public int getNumber() {
			return this.number;
		}

		public boolean isCurrent() {
			return this.current;
		}
	}
}