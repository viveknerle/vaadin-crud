package net.mysoftworks.crud.model.whofor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("PEOPLE_WEDDING")
public class EventForWedding extends EventForPeople {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Person personA;

	private Person personB;

	public Person getPersonA() {
		return personA;
	}

	public Person getPersonB() {
		return personB;
		
	}

	public EventForWedding(Person he,Person she) {
		this.personA = he;
		this.personB = she;
		getPersons().add(he);
		getPersons().add(she);
	}
	
	
}