package uk.riosdevida.riosdevida_web.rest.dtos;

public class ImageDTO {
	
	private String name;
	private String type;
	private String base64;
	private String caption;
	
	public ImageDTO(String name, String type, String caption, String base64) {
		super();
		this.name = name;
		this.type = type;
		this.caption = caption;
		this.base64 = base64;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBase64() {
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}

}
