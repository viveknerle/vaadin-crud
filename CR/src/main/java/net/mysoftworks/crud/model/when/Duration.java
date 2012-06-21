package net.mysoftworks.crud.model.when;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import net.mysoftworks.crud.model.common.PrimaryKey;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="DISC", discriminatorType=DiscriminatorType.STRING, length=20)
public abstract class Duration extends PrimaryKey  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
