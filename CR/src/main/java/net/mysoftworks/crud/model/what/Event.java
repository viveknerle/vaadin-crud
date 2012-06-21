package net.mysoftworks.crud.model.what;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import net.mysoftworks.crud.model.common.PrimaryKey;
import net.mysoftworks.crud.model.when.Duration;
import net.mysoftworks.crud.model.whofor.EventFor;

@Entity
public class Event extends PrimaryKey {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Column(name="EVENT_TYPE")
	@Enumerated(EnumType.STRING)
	private EventType eventType;
	
	@Column(name="DURATION")
	private Duration duration;
	
	@Column(name="EVENT_FOR")
	private EventFor eventFor; 

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public EventFor getEventFor() {
		return eventFor;
	}

	public void setEventFor(EventFor eventFor) {
		this.eventFor = eventFor;
	}
	
}
