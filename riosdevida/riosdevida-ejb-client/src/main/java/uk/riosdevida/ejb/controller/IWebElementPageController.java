/**
 * 
 */
package uk.riosdevida.ejb.controller;

import org.tedros.server.controller.ITSecureEjbController;

import jakarta.ejb.Remote;
import uk.riosdevida.entity.WebElementPage;

/**
 * @author Davis
 *
 */
@Remote
public interface IWebElementPageController extends ITSecureEjbController<WebElementPage> {

	static final String JNDI_NAME = "IWebElementPageControllerRemote";
		
}
