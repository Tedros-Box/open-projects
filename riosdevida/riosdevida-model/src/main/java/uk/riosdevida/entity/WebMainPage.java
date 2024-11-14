/**
 * 
 */
package uk.riosdevida.entity;

import java.util.List;
import java.util.Objects;

import org.tedros.person.model.LegalPerson;
import org.tedros.server.entity.TVersionEntity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import uk.riosdevida.domain.DomainSchema;
import uk.riosdevida.domain.DomainTables;

/**
 * 
 */
@Entity
@Cacheable(false)
@Table(schema = DomainSchema.schema, name = DomainTables.WEB_MAIN_PAGE)
public class WebMainPage extends TVersionEntity {

	private static final long serialVersionUID = -7343736085902443443L;
	
	@Column
	private String domain;
	
	@Column
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="id_main_page")
	private List<WebRegion> region;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_person")
	private LegalPerson person;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public List<WebRegion> getRegion() {
		return region;
	}

	public void setRegion(List<WebRegion> region) {
		this.region = region;
	}

	public LegalPerson getPerson() {
		return person;
	}

	public void setPerson(LegalPerson person) {
		this.person = person;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s", domain, region, name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(domain, name, person, region);
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
		WebMainPage other = (WebMainPage) obj;
		return Objects.equals(domain, other.domain) && Objects.equals(name, other.name)
				&& Objects.equals(person, other.person) && Objects.equals(region, other.region);
	}


}
