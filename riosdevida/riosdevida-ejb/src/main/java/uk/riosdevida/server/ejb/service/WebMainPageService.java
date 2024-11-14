/**
 * 
 */
package uk.riosdevida.server.ejb.service;

import org.tedros.server.cdi.bo.ITGenericBO;
import org.tedros.server.ejb.service.TEjbService;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import uk.riosdevida.entity.WebMainPage;
import uk.riosdevida.server.cdi.bo.WebMainPageBO;

/**
 * 
 */
@LocalBean
@Stateless(name="WebMainPageService")
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
public class WebMainPageService extends TEjbService<WebMainPage> {

	@Inject
	private WebMainPageBO bo;
	
	@Override
	public ITGenericBO<WebMainPage> getBussinesObject() {
		return bo;
	}
	
	public WebMainPage getMainPage(String domain, String region, String locale) {	
		return bo.getMainPage(domain, region, locale);
	}

}
