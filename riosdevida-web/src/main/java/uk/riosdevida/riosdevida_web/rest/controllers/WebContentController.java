package uk.riosdevida.riosdevida_web.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jndi.JndiTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tedros.server.result.TResult;
import org.tedros.server.result.TResult.TState;

import jakarta.resource.spi.IllegalStateException;
import jakarta.servlet.http.HttpServletRequest;
import uk.riosdevida.ejb.controller.IElementPageController;
import uk.riosdevida.ejb.controller.ILanguageController;
import uk.riosdevida.ejb.controller.IMainPageController;
import uk.riosdevida.entity.ElementType;
import uk.riosdevida.entity.WebElementPage;
import uk.riosdevida.entity.WebLanguage;
import uk.riosdevida.entity.WebMainPage;
import uk.riosdevida.entity.WebRegion;
import uk.riosdevida.riosdevida_web.rest.dtos.DTOConverter;

/**
 * The Class ExampleController.
 */
@RestController
public class WebContentController {

	@Autowired
	private JndiTemplate jndiTemplate;

	/**
	 * Gets the web site content.
	 *
	 * @return the string
	 */
	@GetMapping("/api/content/{region}/{locale}")
	public Map<String, Object> content(
			@PathVariable String region, 
			@PathVariable String locale, 
            HttpServletRequest request) 
	{
		String domain = request.getServerName();
		Map<String, Object> map = new HashMap<>();
		
		try {
			WebMainPage page = loadPage(domain, region, locale);
			List<WebLanguage> languages = loadlanguages();
			
			String name = (region!=null && region.equals("def"))
				? page.getRegion().stream()
						.filter(p->p.isDefaultOnLoad())
						.findFirst()
						.orElse(page.getRegion().get(0))
						.getName()
					: region;
			
			String lang = (locale!=null && locale.equals("def"))
					? languages.stream()
							.filter(p->p.isDefaultOnLoad())
							.findFirst()
							.orElse(languages.get(0))
							.getLocale()
						: locale;
			
			Optional<WebRegion> op = page.getRegion().stream()
					.filter(p->p.getName().equals(name) 
							&& p.getLanguage().getLocale().equals(lang))
					.findFirst();
			
			WebRegion r = null;
			
			if(op.isPresent()) {
				r = op.get();
				map.put("region", DTOConverter.convert(r));
			}
			
			map.put("info", info(page.getName(), lang, page.getRegion(), r));
			map.put("code", 100);
			
		}catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("code", 300);
			map.put("message", e.getMessage());
		}
		return map;
	} 
		
	private Map<String, Object> info(String page, String lang, List<WebRegion> pageRegions,  WebRegion region) throws Exception {		
		
		Map<String, Object> map = new HashMap<>();
		
		List<WebLanguage> languages = loadlanguages();
		List<WebElementPage> elements = loadElements();	
		List<Map<String, Object>> elemList = new ArrayList<Map<String,Object>>();			
		List<Map<String, String>> lanList = new ArrayList<Map<String,String>>();
		List<Map<String, String>> regList = new ArrayList<Map<String,String>>();
		
		languages.forEach(c->{
			Map<String, String> m = new HashMap<String, String>();
			m.put("name", c.getName());
			m.put("value", c.getLocale());
			lanList.add(m);
		});
		
		if(pageRegions!=null) {
			List<String> regions = new ArrayList<String>();
			pageRegions
			.stream()
			.forEach(c->regions.add(c.getName()));
			
			regions.stream()
			.distinct()
			.forEach(c->{
				Map<String, String> m = new HashMap<String, String>();
				m.put("name", c);
				m.put("value", c);
				regList.add(m);
			});
		}
		
		if(elements!=null)
			elements.stream()
			.filter(p->p.getLanguage().getLocale().equals(lang))
			.forEach(c->{
				Map<String, Object> m = new HashMap<>();
				m.put("name", c.getName());
				m.put("type", c.getType().name());
				if(c.getType().equals(ElementType.LANGUAGE))
					m.put("values", lanList);					
				
				if(c.getType().equals(ElementType.LOCATION))
					m.put("values", regList);
				
				elemList.add(m);
			});
			
		if(region!=null) {
			if(region.getTwitter()!=null) {
				Map<String, Object> tMap = new HashMap<>();
				tMap.put("name", region.getTwitter());
				tMap.put("type", "Twitter");
				elemList.add(tMap);
			}
			if(region.getFacebook()!=null) {
				Map<String, Object> tMap = new HashMap<>();
				tMap.put("name", region.getFacebook());
				tMap.put("type", "Facebook");
				elemList.add(tMap);
			}
			if(region.getInstagram()!=null) {
				Map<String, Object> tMap = new HashMap<>();
				tMap.put("name", region.getInstagram());
				tMap.put("type", "Instagram");
				elemList.add(tMap);
			}
			if(region.getYoutube()!=null) {
				Map<String, Object> tMap = new HashMap<>();
				tMap.put("name", region.getYoutube());
				tMap.put("type", "Youtube");
				elemList.add(tMap);
			}
		}
						
		map.put("elements", elemList);
		map.put("name", page);
		return map;
	}
	
	private IElementPageController getElementPageService() throws NamingException {
		return jndiTemplate.lookup(IElementPageController.JNDI_NAME, IElementPageController.class);
	}
	
	private IMainPageController getMainPageService() throws NamingException {
		return jndiTemplate.lookup(IMainPageController.JNDI_NAME, IMainPageController.class);
	}
	
	private ILanguageController getLanguageService() throws NamingException {
		return jndiTemplate.lookup(ILanguageController.JNDI_NAME, ILanguageController.class);
	}

	private WebMainPage loadPage(String domain, String region, String locale) throws Exception {
	
		IMainPageController serv = getMainPageService();
		
		TResult<WebMainPage> res = serv.getMainPage(domain, region, locale);
		if(res.getState().equals(TState.SUCCESS))
			return res.getValue();
		
		throw new IllegalStateException("None entity found for "+domain+" domain!");
			
	}
	
	private List<WebLanguage> loadlanguages()throws Exception {
		
		ILanguageController serv = getLanguageService();
		
		TResult<List<WebLanguage>> res = serv.listAll(WebLanguage.class);
		if(res.getState().equals(TState.SUCCESS))
			return res.getValue();
		
		throw new IllegalStateException("None Language configured!");		
	}
	
	private List<WebElementPage> loadElements() throws Exception {
		
		IElementPageController serv = getElementPageService();
		
		TResult<List<WebElementPage>> res = serv.listAll(WebElementPage.class);
		if(res.getState().equals(TState.SUCCESS))
			return res.getValue();
		
		return null;
	}
	
}
