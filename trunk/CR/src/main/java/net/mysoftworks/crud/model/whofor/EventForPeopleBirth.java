package net.mysoftworks.crud.model.whofor;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("PEOPLE_BIRTH")
public class EventForPeopleBirth extends EventForPeople {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date birthDate;

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
}