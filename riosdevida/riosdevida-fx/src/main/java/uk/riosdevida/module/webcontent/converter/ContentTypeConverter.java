/**
 * 
 */
package uk.riosdevida.module.webcontent.converter;

import org.tedros.fx.converter.TConverter;

import uk.riosdevida.entity.ContentType;

/**
 * 
 */
public class ContentTypeConverter extends TConverter<String, ContentType> {

	@Override
	public ContentType getOut() {
		return ContentType.valueOf(ContentType.class, getIn());
	}

}
