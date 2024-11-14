package uk.riosdevida.entity;

import java.util.Objects;

import org.tedros.server.entity.TVersionEntity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import uk.riosdevida.domain.DomainSchema;
import uk.riosdevida.domain.DomainTables;

@Entity
@Cacheable(false)
@Table(schema = DomainSchema.schema, name = DomainTables.WEB_ELEMENT_PAGE)
public class WebElementPage extends TVersionEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6807374800416834362L;

	@Column(length = 250)
	private String name;
	
	@Column(length = 60)
	@Enumerated(EnumType.STRING)
	private ElementType type;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name  = "language_id", nullable = false)
	private WebLanguage language;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public WebLanguage getLanguage() {
		return language;
	}

	public void setLanguage(WebLanguage language) {
		this.language = language;
	}

	public ElementType getType() {
		return type;
	}

	public void setType(ElementType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s, %s", language, type, name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(language, name, type);
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
		WebElementPage other = (WebElementPage) obj;
		return Objects.equals(language, other.language) && Objects.equals(name, other.name)
				&& Objects.equals(type, other.type);
	}


}
