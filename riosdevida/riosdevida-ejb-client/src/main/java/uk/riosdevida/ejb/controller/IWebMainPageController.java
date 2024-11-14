/**
 * 
 */
package uk.riosdevida.ejb.controller;

import org.tedros.server.controller.ITSecureEjbController;

import jakarta.ejb.Remote;
import uk.riosdevida.entity.WebMainPage;

/**
 * @author Davis
 *
 */
@Remote
public interface IWebMainPageController extends ITSecureEjbController<WebMainPage> {

	static final String JNDI_NAME = "IWebMainPageControllerRemote";
		
}
