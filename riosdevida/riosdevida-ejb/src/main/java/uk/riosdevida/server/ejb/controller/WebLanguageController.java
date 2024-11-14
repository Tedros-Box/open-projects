/**
 * 
 */
package uk.riosdevida.server.ejb.controller;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;

import org.tedros.server.ejb.controller.ITSecurityController;
import org.tedros.server.ejb.controller.TSecureEjbController;
import org.tedros.server.security.ITSecurity;
import org.tedros.server.security.TAccessPolicie;
import org.tedros.server.security.TBeanPolicie;
import org.tedros.server.security.TBeanSecurity;
import org.tedros.server.security.TSecurityInterceptor;
import org.tedros.server.service.ITEjbService;

import uk.riosdevida.domain.DomainApp;
import uk.riosdevida.ejb.controller.IWebLanguageController;
import uk.riosdevida.entity.WebLanguage;
import uk.riosdevida.server.ejb.service.RdvService;

/**
 * The controller bean
 * 
 * @author Davis
 *
 */
@TSecurityInterceptor
@Stateless(name="IWebLanguageController")
@TBeanSecurity({@TBeanPolicie(id = DomainApp.WEB_LANGUAGE_FORM_ID, 
policie = { TAccessPolicie.APP_ACCESS, TAccessPolicie.VIEW_ACCESS })})
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
public class WebLanguageController extends TSecureEjbController<WebLanguage> implements IWebLanguageController, ITSecurity  {

	@EJB
	private RdvService<WebLanguage> serv;
	
	@EJB
	private ITSecurityController securityController;
	
	@Override
	public ITEjbService<WebLanguage> getService() {
		return serv;
	}
	
	@Override
	public ITSecurityController getSecurityController() {
		return securityController;
	}
}
