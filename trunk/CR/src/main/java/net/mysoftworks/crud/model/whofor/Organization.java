package net.mysoftworks.crud.model.whofor;

import javax.persistence.Entity;

import net.mysoftworks.crud.model.common.PrimaryKey;

@Entity
public class Organization extends PrimaryKey {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
