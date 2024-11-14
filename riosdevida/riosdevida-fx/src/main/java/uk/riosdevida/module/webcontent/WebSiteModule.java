/**
 * 
 */
package uk.riosdevida.module.webcontent;

import org.tedros.core.TModule;
import org.tedros.core.annotation.TItem;
import org.tedros.core.annotation.TView;
import org.tedros.core.annotation.security.TAuthorizationType;
import org.tedros.core.annotation.security.TSecurity;

import uk.riosdevida.RDV_Key;
import uk.riosdevida.domain.DomainApp;
import uk.riosdevida.entity.WebElementPage;
import uk.riosdevida.entity.WebLanguage;
import uk.riosdevida.entity.WebMainPage;
import uk.riosdevida.module.webcontent.model.WebElementMV;
import uk.riosdevida.module.webcontent.model.WebLanguageMV;
import uk.riosdevida.module.webcontent.model.WebMainContentMV;

/**
 * @author Davis
 *
 */
@TView(items = { 
	@TItem(title = RDV_Key.RVIDA_LANGUAGES_VIEW, 
		modelView=WebLanguageMV.class, model=WebLanguage.class), 
	@TItem(title = RDV_Key.RVIDA_ELEMENT_VIEW, 
		modelView=WebElementMV.class, model=WebElementPage.class), 
	@TItem(title = RDV_Key.RVIDA_CONTENT_VIEW, 
		modelView=WebMainContentMV.class, model=WebMainPage.class)
})
@TSecurity(id=DomainApp.R_VIDA_MODULE_ID, 
appName = RDV_Key.APP_RVIDA, 
moduleName = RDV_Key.MODULE_WEB_SITE, 
allowedAccesses=TAuthorizationType.MODULE_ACCESS)
public class WebSiteModule extends TModule {

}
