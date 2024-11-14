/**
 * 
 */
package uk.riosdevida.entity;

import java.util.List;
import java.util.Objects;

import org.tedros.common.model.TFileEntity;
import org.tedros.server.entity.TVersionEntity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import uk.riosdevida.domain.DomainSchema;
import uk.riosdevida.domain.DomainTables;

/**
 * 
 */
@Entity
@Cacheable(false)
@Table(schema = DomainSchema.schema, name = DomainTables.WEB_CONTENT)
public class WebContent extends TVersionEntity implements Comparable<WebContent>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6458045627490026223L;
	
	@Column(length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private ContentType type;

	@Column(length = 500)
	private String title;
	
	@Column()
	private String content;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="id_content")
	private List<WebContentItem> items;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="WEB_CONTENT_IMAGE", schema = DomainSchema.schema,
	    joinColumns = @JoinColumn(name="id_content", referencedColumnName="id"),
	    inverseJoinColumns = @JoinColumn(name="id_image", referencedColumnName="id"))
	private List<TFileEntity> images;
	
	@Column
	private Integer position;
	
	@Column
	private Boolean enabled;

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
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

	public ContentType getType() {
		return type;
	}

	public void setType(ContentType type) {
		this.type = type;
	}

	public List<TFileEntity> getImages() {
		return images;
	}

	public void setImages(List<TFileEntity> images) {
		this.images = images;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public List<WebContentItem> getItems() {
		return items;
	}

	public void setItems(List<WebContentItem> items) {
		this.items = items;
	}

	@Override
	public int compareTo(WebContent o) {
		if(this.position!=null && o.getPosition()!=null)
			return this.position.compareTo(o.getPosition());
		else if(this.position==null && o.getPosition()!=null)
			return -1;
		else if(this.position!=null && o.getPosition()==null)
			return 1;
		else
			return 0;
	} 

	@Override
	public String toString() {
		return String.format("%s, %s, %s", position, type, title);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(content, enabled, images, items, position, title, type);
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
		WebContent other = (WebContent) obj;
		return Objects.equals(content, other.content) && Objects.equals(enabled, other.enabled)
				&& Objects.equals(images, other.images) && Objects.equals(items, other.items)
				&& Objects.equals(position, other.position) && Objects.equals(title, other.title) && type == other.type;
	}

}
