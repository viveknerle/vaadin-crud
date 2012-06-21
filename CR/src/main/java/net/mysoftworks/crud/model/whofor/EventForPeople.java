package net.mysoftworks.crud.model.whofor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("PEOPLE")
public class EventForPeople extends EventFor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany
	private Set<Person> persons = new HashSet<Person>();

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

}
