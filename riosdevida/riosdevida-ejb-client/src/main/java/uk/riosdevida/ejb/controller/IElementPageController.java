/**
 * 
 */
package uk.riosdevida.ejb.controller;

import org.tedros.server.controller.ITEjbController;

import jakarta.ejb.Remote;
import uk.riosdevida.entity.WebElementPage;

/**
 * @author Davis
 *
 */
@Remote
public interface IElementPageController extends ITEjbController<WebElementPage> {

	static final String JNDI_NAME = "IElementPageControllerRemote";
		
}
