package uk.riosdevida.start;

import org.tedros.core.ITApplication;
import org.tedros.core.annotation.TApplication;
import org.tedros.core.annotation.TModule;
import org.tedros.core.annotation.TResourceBundle;
import org.tedros.core.annotation.security.TAuthorizationType;
import org.tedros.core.annotation.security.TSecurity;

import uk.riosdevida.RDV_Key;
import uk.riosdevida.domain.DomainApp;
import uk.riosdevida.module.webcontent.WebSiteModule;

/**
 * The app start class.
 * 
 * @author Davis
 * */
@TApplication(name=RDV_Key.APP_RVIDA, 
	module = {	
		@TModule(type=WebSiteModule.class, 
			name=RDV_Key.MODULE_WEB_SITE, 
			menu=RDV_Key.MENU_RVIDA, 
			description=RDV_Key.MODULE_DESC_WEB_SITE,
			icon=TConstant.ICONS_FOLDER + "logo-114x114.png", 
			menuIcon=TConstant.ICONS_FOLDER + "logo-98x98.png")
	}, packageName = "uk.riosdevida", 
	universalUniqueIdentifier=TConstant.UUI)
@TResourceBundle(resourceName={"RDV_"})
@TSecurity(id=DomainApp.MNEMONIC, 
	appName = RDV_Key.APP_RVIDA, 
	allowedAccesses=TAuthorizationType.APP_ACCESS)
public class AppStart implements ITApplication {

	@Override
	public void start() {
		// Run at startup
	}

	@Override
	public void stop() {
		// Executed on exit and logout
	}
	
	
}
