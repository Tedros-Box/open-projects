/**
 * 
 */
package uk.riosdevida.server.cdi.eao;

import jakarta.enterprise.context.Dependent;

import org.tedros.server.cdi.eao.TGenericEAO;
import org.tedros.server.entity.ITEntity;

/**
 * The generic entity access object.
 * 
 * This use a Dependent context. 
 * 
 * @author Davis
 *
 */
@Dependent
public class RdvEAO<E extends ITEntity> extends TGenericEAO<E> {

}
