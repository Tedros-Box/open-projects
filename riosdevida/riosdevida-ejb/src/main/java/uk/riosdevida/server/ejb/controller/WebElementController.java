/**
 * 
 */
package uk.riosdevida.server.ejb.controller;

import org.tedros.server.ejb.controller.ITSecurityController;
import org.tedros.server.ejb.controller.TSecureEjbController;
import org.tedros.server.security.ITSecurity;
import org.tedros.server.security.TAccessPolicie;
import org.tedros.server.security.TBeanPolicie;
import org.tedros.server.security.TBeanSecurity;
import org.tedros.server.security.TSecurityInterceptor;
import org.tedros.server.service.ITEjbService;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import uk.riosdevida.domain.DomainApp;
import uk.riosdevida.ejb.controller.IWebElementPageController;
import uk.riosdevida.entity.WebElementPage;
import uk.riosdevida.server.ejb.service.RdvService;

/**
 * The controller bean
 * 
 * @author Davis
 *
 */
@TSecurityInterceptor
@Stateless(name="IWebElementPageController")
@TBeanSecurity({@TBeanPolicie(id = DomainApp.WEB_ELEMENT_FORM_ID, 
policie = { TAccessPolicie.APP_ACCESS, TAccessPolicie.VIEW_ACCESS })})
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
public class WebElementController extends TSecureEjbController<WebElementPage> implements IWebElementPageController, ITSecurity  {

	@EJB
	private RdvService<WebElementPage> serv;
	
	@EJB
	private ITSecurityController securityController;
	
	@Override
	public ITEjbService<WebElementPage> getService() {
		return serv;
	}
	
	@Override
	public ITSecurityController getSecurityController() {
		return securityController;
	}
}
