package net.mysoftworks.crud.model.when;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
@DiscriminatorValue("MULTIRANGE")
public class MultiRangeDuration extends Duration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@OneToMany(orphanRemoval=true,cascade={CascadeType.REMOVE,CascadeType.MERGE})
	private Set<RangeDuration> ranges;

	public Set<RangeDuration> getRanges() {
		return ranges;
	}

	public void setRanges(Set<RangeDuration> ranges) {
		this.ranges = ranges;
	}
	
}
