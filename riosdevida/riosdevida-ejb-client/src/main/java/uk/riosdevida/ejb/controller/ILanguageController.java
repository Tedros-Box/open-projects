/**
 * 
 */
package uk.riosdevida.ejb.controller;

import org.tedros.server.controller.ITEjbController;

import jakarta.ejb.Remote;
import uk.riosdevida.entity.WebLanguage;

/**
 * @author Davis
 *
 */
@Remote
public interface ILanguageController extends ITEjbController<WebLanguage> {

	static final String JNDI_NAME = "ILanguageControllerRemote";
		
}
