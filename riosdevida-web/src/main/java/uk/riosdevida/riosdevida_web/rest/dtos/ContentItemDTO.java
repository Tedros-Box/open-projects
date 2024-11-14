package uk.riosdevida.riosdevida_web.rest.dtos;

public class ContentItemDTO {
	
	private String name;
	
	private String content;
	
	private ImageDTO image;

	public ContentItemDTO(String name, String content, ImageDTO image) {
		super();
		this.name = name;
		this.content = content;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ImageDTO getImage() {
		return image;
	}

	public void setImage(ImageDTO image) {
		this.image = image;
	}

}
