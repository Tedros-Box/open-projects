package uk.riosdevida.riosdevida_web.rest.dtos;

import java.util.List;

public class RegionDTO {
	
	private String name;
	
	private String twitter;
	
	private String facebook;
	
	private String instagram;
	
	private String youtube;
	
	private ImageDTO image;
	
	private ImageDTO logo;
	
	private List<ContentDTO> contents;
	
	public RegionDTO(String name, String twitter, String facebook, String instagram, String youtube, ImageDTO image,
			ImageDTO logo, List<ContentDTO> contents) {
		super();
		this.name = name;
		this.twitter = twitter;
		this.facebook = facebook;
		this.instagram = instagram;
		this.youtube = youtube;
		this.image = image;
		this.logo = logo;
		this.contents = contents;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public ImageDTO getImage() {
		return image;
	}

	public void setImage(ImageDTO image) {
		this.image = image;
	}

	public ImageDTO getLogo() {
		return logo;
	}

	public void setLogo(ImageDTO logo) {
		this.logo = logo;
	}

	public List<ContentDTO> getContents() {
		return contents;
	}

	public void setContents(List<ContentDTO> contents) {
		this.contents = contents;
	}

}
