package uk.riosdevida.riosdevida_web.rest.dtos;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.lang3.compare.ComparableUtils;
import org.tedros.server.entity.ITFileEntity;

import uk.riosdevida.entity.WebRegion;

public class DTOConverter {
	
	public static RegionDTO convert(WebRegion r) {
		if(r.getContents()==null) 
			return null;
		List<ContentDTO> contents = new ArrayList<>();		
		r.getContents().sort(null);
		r.getContents().stream()
		.filter(p->p.getEnabled()==null || p.getEnabled())
		.forEach(c->{			
			List<ContentItemDTO> items = (c.getItems()!=null && c.getItems().size()>0) 
					? new ArrayList<ContentItemDTO>()
							: null;
			
			if(items!=null) {
				c.getItems().sort(null);
				c.getItems().stream()
				.filter(p->p.getEnabled()==null || p.getEnabled())
				.forEach(i->items.add(new ContentItemDTO(i.getName(), cleanHtml(i.getContent()), convert(i.getImage()))));
			}	
			
			ContentDTO cdto = new ContentDTO(c.getType().name(), c.getTitle(), cleanHtml(c.getContent()), items, convert(c.getImages()));
			contents.add(cdto);
		});
		
		RegionDTO dto = new RegionDTO(r.getName(), r.getTwitter(), r.getFacebook(), r.getInstagram(), r.getYoutube(), 
				convert(r.getImage()), convert(r.getLogo()), contents);
		
		return dto;
	}
	
	public static String cleanHtml(String html) {
		if(html==null)
			return null;
		String header = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\">";
		String footer = "</body></html>";
		
		return html.replaceAll(header, "").replaceAll(footer, "");
	}
	
	public static List<ImageDTO> convert(List<? extends ITFileEntity> files) {
		if(files==null || files.size()==0)
			return null;
		List<ImageDTO> images = new ArrayList<ImageDTO>();
		files.stream()
		.filter(f->f.getByte()!=null)
		.forEach(c->images.add(convert(c)));
		return images;
	} 
	
	public static ImageDTO convert(ITFileEntity file) {
		if(file==null || file.getByte()==null || file.getByte().getBytes()==null)
			return null;
		String base64 = Base64.getEncoder().encodeToString(file.getByte().getBytes());
		String name = file.getFileName();
		String type = file.getFileExtension();
		String caption = file.getDescription();
		return new ImageDTO(name, type, caption, base64);
	} 

}
