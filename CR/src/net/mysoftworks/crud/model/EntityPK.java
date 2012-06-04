package net.mysoftworks.crud.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class EntityPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Embedded
	private ReadonlyEntityPK id;
	
	public void setId(Long id) {
		this.id.id = id;
	}
	
	public EntityPK() {	}

	public EntityPK(String id) {
		this.id = new ReadonlyEntityPK(id);	
	}

	@Override
	public String toString() {
		return id.toString();
	}
	
}
