/**
 * 
 */
package uk.riosdevida.ejb.controller;

import jakarta.ejb.Remote;

import org.tedros.server.controller.ITSecureEjbController;

import uk.riosdevida.entity.WebLanguage;

/**
 * @author Davis
 *
 */
@Remote
public interface IWebLanguageController extends ITSecureEjbController<WebLanguage> {

	static final String JNDI_NAME = "IWebLanguageControllerRemote";
		
}
