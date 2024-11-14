/**
 * 
 */
package uk.riosdevida.server.cdi.eao;

import org.tedros.common.model.TByteEntity;
import org.tedros.common.model.TFileEntity;
import org.tedros.server.cdi.eao.TGenericEAO;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.Query;
import uk.riosdevida.entity.WebMainPage;

/**
 * 
 */
@RequestScoped
public class WebMainPageEAO extends TGenericEAO<WebMainPage> {
	
	public WebMainPage getContent(String domain, String region, String locale) {
		
		StringBuilder sql = new StringBuilder("select e from WebMainPage e ");
		sql.append("inner join e.region r ");
		sql.append("inner join r.language l ");
		sql.append("where e.domain = :domain ");
		sql.append("and r.name = :region ");
		sql.append("and l.locale = :locale ");
		
		Query qry = getEntityManager().createQuery(sql.toString());
		
		qry.setParameter("domain", domain);
		qry.setParameter("region", region);
		qry.setParameter("locale", locale);
			
		return (WebMainPage) qry.getSingleResult();
	}
	
	public void loadBytes(final TFileEntity entity) {
		Query qry = getEntityManager().createQuery("select entity.bytes from "+TByteEntity.class.getSimpleName()+" entity where entity.id = "+entity.getByteEntity().getId());
		byte[] bytes = (byte[]) qry.getSingleResult();
			
		entity.getByteEntity().setBytes(bytes);
	}

}
