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
import uk.riosdevida.ejb.controller.ILanguageController;
import uk.riosdevida.entity.WebLanguage;
import uk.riosdevida.server.ejb.service.RdvService;

/**
 * The controller bean
 * 
 * @author Davis
 *
 */
@Stateless(name="ILanguageController")
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
public class LanguageController extends TEjbController<WebLanguage> implements ILanguageController {

	@EJB
	private RdvService<WebLanguage> serv;
	
	@Override
	public ITEjbService<WebLanguage> getService() {
		return serv;
	}
}
	
