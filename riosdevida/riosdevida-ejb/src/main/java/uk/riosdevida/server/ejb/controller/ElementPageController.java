/**
 * 
 */
package uk.riosdevida.server.ejb.controller;

import org.tedros.server.ejb.controller.TEjbController;
import org.tedros.server.service.ITEjbService;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import uk.riosdevida.ejb.controller.IElementPageController;
import uk.riosdevida.entity.WebElementPage;
import uk.riosdevida.server.ejb.service.RdvService;

/**
 * The controller bean
 * 
 * @author Davis
 *
 */
@Stateless(name="IElementPageController")
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
public class ElementPageController extends TEjbController<WebElementPage> 
implements IElementPageController  {

	@EJB
	private RdvService<WebElementPage> serv;
	
	@Override
	public ITEjbService<WebElementPage> getService() {
		return serv;
	}
	
}
