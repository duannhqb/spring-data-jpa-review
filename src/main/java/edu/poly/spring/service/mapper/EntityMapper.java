/**
 * 
 */
package edu.poly.spring.service.mapper;

/**
 * @author Huu Duan
 *
 */
public interface EntityMapper<E, D> {

	D toDto(E entity);

	E toEntity(D dto);
}
