package uk.riosdevida.riosdevida_web.rest.dtos;

import java.util.List;

public class ContentDTO {
	
	private String type;

	private String title;
	
	private String content;
	
	private List<ContentItemDTO> items;
	
	private List<ImageDTO> images;

	public ContentDTO(String type, String title, String content, List<ContentItemDTO> items,
			List<ImageDTO> images) {
		super();
		this.type = type;
		this.title = title;
		this.content = content;
		this.items = items;
		this.images = images;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<ContentItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ContentItemDTO> items) {
		this.items = items;
	}

	public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}

}
