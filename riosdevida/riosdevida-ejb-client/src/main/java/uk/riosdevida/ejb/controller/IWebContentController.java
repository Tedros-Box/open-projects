/**
 * 
 */
package uk.riosdevida.ejb.controller;

import org.tedros.server.controller.ITSecureEjbController;

import jakarta.ejb.Remote;
import uk.riosdevida.entity.WebContent;

/**
 * @author Davis
 *
 */
@Remote
public interface IWebContentController extends ITSecureEjbController<WebContent> {

	static final String JNDI_NAME = "IWebContentControllerRemote";
		
}
