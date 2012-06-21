package net.mysoftworks.crud.model.whofor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import net.mysoftworks.crud.model.common.PrimaryKey;

@Entity
public class Person extends PrimaryKey {

	public enum Sex implements Serializable {
		M("MALE"),F("FEMALE");
		public String description;
		Sex(String description) {
			this.description = description;
		}
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String surname;
	
	@Column(name="PERSON_SEX",length=1)
	@Enumerated(EnumType.STRING)
	private Sex sex; 
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}
}
