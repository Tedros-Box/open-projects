/**
 * 
 */
package uk.riosdevida.server.cdi.bo;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import org.tedros.server.cdi.bo.TGenericBO;
import org.tedros.server.cdi.eao.ITGenericEAO;
import org.tedros.server.entity.ITEntity;

import uk.riosdevida.server.cdi.eao.RdvEAO;

/**
 * The CDI business object 
 * 
 * @author Davis
 *
 */
@Dependent
public class RdvBO<E extends ITEntity> extends TGenericBO<E> {

	@Inject
	private RdvEAO<E> eao;
	
	@Override
	public ITGenericEAO<E> getEao() {
		return eao;
	}

}
