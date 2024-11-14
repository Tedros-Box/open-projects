/**
 * 
 */
package uk.riosdevida.server.cdi.bo;

import java.util.List;

import org.tedros.server.cdi.bo.TGenericBO;
import org.tedros.server.cdi.eao.ITGenericEAO;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import uk.riosdevida.entity.WebLanguage;
import uk.riosdevida.entity.WebMainPage;
import uk.riosdevida.entity.WebRegion;
import uk.riosdevida.server.cdi.eao.RdvEAO;
import uk.riosdevida.server.cdi.eao.WebMainPageEAO;

/**
 * 
 */
@RequestScoped
public class WebMainPageBO extends TGenericBO<WebMainPage> {

	@Inject
	private RdvEAO<WebRegion> eaoRegion;
	
	@Inject
	private RdvEAO<WebLanguage> eaoLanguage;
	
	@Inject
	private WebMainPageEAO eao;
	
	@Override
	public ITGenericEAO<WebMainPage> getEao() {		
		return eao;
	}
	
	public WebMainPage getMainPage(String domain, String region, String locale) {	
		try {
			if(domain==null)
				throw new IllegalArgumentException("The argument domain cannot be null");
			
			if(region == null || region.equals("def")) {
				WebRegion reg = new WebRegion();
				reg.setDefaultOnLoad(true);
				reg = eaoRegion.find(reg);
				
				if(reg!=null)
					region = reg.getName();
				else {
					List<WebRegion> lst = eaoRegion.listAll(WebRegion.class);
					if(lst!=null && !lst.isEmpty())
						region = lst.get(0).getName();
					else
						throw new IllegalStateException("None Region was found!");
				}
					
			}
			
			if(locale == null || locale.equals("def")) {
				WebLanguage lan = new WebLanguage();
				lan.setDefaultOnLoad(true);
				lan = eaoLanguage.find(lan);
				if(lan!=null)
					locale = lan.getLocale();
				else {
					List<WebLanguage> lst = eaoLanguage.listAll(WebLanguage.class);
					if(lst!=null && !lst.isEmpty())
						locale = lst.get(0).getLocale();
					else
						throw new IllegalStateException("None Language was found!");
				}
			}
			
			WebMainPage e = eao.getContent(domain, region, locale);
			
			if(e!=null) {
				String reg = region;
				e.getRegion().stream()
				.filter(p->p.getName().equals(reg))
				.forEach(c->{
					if(c.getLogo()!=null && c.getLogo().getByteEntity()!=null)
						eao.loadBytes(c.getLogo());
					if(c.getImage()!=null && c.getImage().getByteEntity()!=null)
						eao.loadBytes(c.getImage());
					c.getContents().stream()
					.forEach(cc->{
						if(cc.getImages()!=null) {
							cc.getImages().forEach(i->{
								if(i.getByteEntity()!=null)
									eao.loadBytes(i);
							});
						}
						if(cc.getItems()!=null) {
							cc.getItems().forEach(t->{
								if(t.getImage()!=null && t.getImage().getByteEntity()!=null)
									eao.loadBytes(t.getImage());
							});
						}
					});
				});
			}
			
			return e;
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}

}
