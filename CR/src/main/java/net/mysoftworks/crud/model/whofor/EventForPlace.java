package net.mysoftworks.crud.model.whofor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("LOCATION")
public class EventForPlace extends EventFor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Place place;

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}


}
