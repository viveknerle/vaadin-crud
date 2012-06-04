package net.mysoftworks.crud.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class ReadonlyEntityPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	
	public Long getId() {
		
		return id;
	}
	
	public ReadonlyEntityPK() {	}

	public ReadonlyEntityPK(String id) {
		this.id = Long.parseLong(id);
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
	
	
}
