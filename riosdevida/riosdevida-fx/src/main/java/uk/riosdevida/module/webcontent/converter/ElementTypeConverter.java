/**
 * 
 */
package uk.riosdevida.module.webcontent.converter;

import org.tedros.fx.converter.TConverter;

import uk.riosdevida.entity.ElementType;

/**
 * 
 */
public class ElementTypeConverter extends TConverter<String, ElementType> {

	@Override
	public ElementType getOut() {
		return ElementType.valueOf(ElementType.class, getIn());
	}

}
