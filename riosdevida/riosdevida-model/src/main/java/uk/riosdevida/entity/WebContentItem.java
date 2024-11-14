/**
 * 
 */
package uk.riosdevida.entity;

import java.util.Objects;

import org.tedros.common.model.TFileEntity;
import org.tedros.server.entity.TVersionEntity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import uk.riosdevida.domain.DomainSchema;
import uk.riosdevida.domain.DomainTables;

/**
 * 
 */
@Entity
@Cacheable(false)
@Table(schema = DomainSchema.schema, name = DomainTables.WEB_CONTENT_ITEM)
public class WebContentItem extends TVersionEntity implements Comparable<WebContentItem> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8729976072073097512L;

	@Column(length = 250)
	private String name;
	
	@Column
	private String content;
	
	@Column
	private Boolean enabled;
	
	@Column
	private Integer position;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="image_id")
	private TFileEntity image;

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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public TFileEntity getImage() {
		return image;
	}

	public void setImage(TFileEntity image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(content, enabled, image, name, position);
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
		WebContentItem other = (WebContentItem) obj;
		return Objects.equals(content, other.content) && Objects.equals(enabled, other.enabled)
				&& Objects.equals(image, other.image) && Objects.equals(name, other.name)
				&& Objects.equals(position, other.position);
	}

	@Override
	public String toString() {
		return String.format("%s, %s", position, name);
	}

	@Override
	public int compareTo(WebContentItem o) {
		if(this.position!=null && o.getPosition()!=null)
			return this.position.compareTo(o.getPosition());
		else if(this.position==null && o.getPosition()!=null)
			return -1;
		else if(this.position!=null && o.getPosition()==null)
			return 1;
		else
			return 0;
	} 

}
