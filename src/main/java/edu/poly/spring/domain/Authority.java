/**
 * 
 */
package edu.poly.spring.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Huu Duan
 *
 */
@Entity
@Table(name = "nhd_authority")
public class Authority implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Authority() {
		
	}
	
	public Authority(String name) {
		this.name = name;
	}
}
