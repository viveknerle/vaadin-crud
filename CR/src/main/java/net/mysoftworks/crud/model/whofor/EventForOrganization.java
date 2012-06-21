package net.mysoftworks.crud.model.whofor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("ORGANIZATION")
public class EventForOrganization extends EventFor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Organization organization;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}


}
