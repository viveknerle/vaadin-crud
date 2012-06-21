package net.mysoftworks.crud.model.when;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("RANGE")
public class RangeDuration extends Duration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date from;
	private Date to;
	
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	
}
