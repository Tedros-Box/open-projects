/**
 * 
 */
package uk.riosdevida.ejb.controller;

import org.tedros.server.controller.ITEjbController;
import org.tedros.server.result.TResult;

import jakarta.ejb.Remote;
import uk.riosdevida.entity.WebMainPage;

/**
 * @author Davis
 *
 */
@Remote
public interface IMainPageController extends ITEjbController<WebMainPage> {

	static final String JNDI_NAME = "IMainPageControllerRemote";
	
	TResult<WebMainPage> getMainPage(String domain, String region, String locale);
		
}
