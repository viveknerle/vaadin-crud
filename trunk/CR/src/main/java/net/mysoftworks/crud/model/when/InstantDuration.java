package net.mysoftworks.crud.model.when;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("INSTANT")
public class InstantDuration extends Duration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
