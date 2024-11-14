/**
 * 
 */
package uk.riosdevida.entity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

import org.tedros.server.entity.TVersionEntity;

import uk.riosdevida.domain.DomainSchema;
import uk.riosdevida.domain.DomainTables;

/**
 * @author Davis
 *
 */
@Entity
@Cacheable(false)
@Table(name=DomainTables.WEB_LANGUAGE, schema=DomainSchema.schema)
public class WebLanguage extends TVersionEntity {

	private static final long serialVersionUID = -8008690210025662586L;

	@Column(length=60, nullable = false)
	private String name;
	
	@Column(length=3)
	private String locale;

	@Column
	private Boolean defaultOnLoad;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
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
		return String.format("%s", name);
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(defaultOnLoad, locale, name);
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
		WebLanguage other = (WebLanguage) obj;
		return Objects.equals(defaultOnLoad, other.defaultOnLoad) && Objects.equals(locale, other.locale)
				&& Objects.equals(name, other.name);
	}

	

	
	
}
