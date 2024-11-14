package uk.riosdevida.entity;

import java.util.List;
import java.util.Objects;

import org.tedros.common.model.TFileEntity;
import org.tedros.server.entity.TVersionEntity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import uk.riosdevida.domain.DomainSchema;
import uk.riosdevida.domain.DomainTables;

@Entity
@Cacheable(false)
@Table(schema = DomainSchema.schema, name = DomainTables.WEB_REGION)
public class WebRegion extends TVersionEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5159502139890215831L;
	
	@Column(length = 500, nullable = false)
	private String name;
	
	@Column
	private String twitter;
	
	@Column
	private String facebook;
	
	@Column
	private String instagram;
	
	@Column
	private String youtube;
	
	@Column
	private Boolean defaultOnLoad;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name  = "language_id", nullable = false)
	private WebLanguage language;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="image_id")
	private TFileEntity image;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="logo_id")
	private TFileEntity logo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="id_region")
	private List<WebContent> contents;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TFileEntity getImage() {
		return image;
	}

	public void setImage(TFileEntity image) {
		this.image = image;
	}

	public WebLanguage getLanguage() {
		return language;
	}

	public void setLanguage(WebLanguage language) {
		this.language = language;
	}

	public List<WebContent> getContents() {
		return contents;
	}

	public void setContents(List<WebContent> contents) {
		this.contents = contents;
	}


	public TFileEntity getLogo() {
		return logo;
	}

	public void setLogo(TFileEntity logo) {
		this.logo = logo;
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
	
	public boolean isDefaultOnLoad() {
		return defaultOnLoad!=null && defaultOnLoad;
	}
	
	public Boolean getDefaultOnLoad() {
		return defaultOnLoad;
	}

	public void setDefaultOnLoad(Boolean defaultOnLoad) {
		this.defaultOnLoad = defaultOnLoad;
	}

	@Override
	public String toString() {
		return String.format("%s, %s", language, name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(contents, defaultOnLoad, facebook, image, instagram, language, logo,
				name, twitter, youtube);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebRegion other = (WebRegion) obj;
		return Objects.equals(contents, other.contents) && Objects.equals(defaultOnLoad, other.defaultOnLoad)
				&& Objects.equals(facebook, other.facebook) && Objects.equals(image, other.image)
				&& Objects.equals(instagram, other.instagram) && Objects.equals(language, other.language)
				&& Objects.equals(logo, other.logo) && Objects.equals(name, other.name)
				&& Objects.equals(twitter, other.twitter) && Objects.equals(youtube, other.youtube);
	}
	
	

}
