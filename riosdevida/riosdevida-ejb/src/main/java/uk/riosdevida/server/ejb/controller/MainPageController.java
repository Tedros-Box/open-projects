/**
 * 
 */
package uk.riosdevida.server.ejb.controller;

import org.tedros.server.ejb.controller.TEjbController;
import org.tedros.server.result.TResult;
import org.tedros.server.result.TResult.TState;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import uk.riosdevida.ejb.controller.IMainPageController;
import uk.riosdevida.entity.WebMainPage;
import uk.riosdevida.server.ejb.service.WebMainPageService;

/**
 * The controller bean
 * 
 * @author Davis
 *
 */
@Stateless(name="IMainPageController")
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
public class MainPageController extends TEjbController<WebMainPage> 
implements IMainPageController  {

	@EJB
	private WebMainPageService serv;
	
	@Override
	public WebMainPageService getService() {
		return serv;
	}
	
	@Override
	public TResult<WebMainPage> getMainPage(String domain, String region, String locale) {	
		try {
			WebMainPage e = serv.getMainPage(domain, region, locale);
			return new TResult<WebMainPage>(TState.SUCCESS, e);
		}catch (Exception e) {
			return super.processException(null, e);
		}
	}
	
}
