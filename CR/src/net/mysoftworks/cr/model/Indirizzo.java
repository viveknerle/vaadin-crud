package net.mysoftworks.cr.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Indirizzo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return via + ", "
				+ numero + " " + cap + " " + comune;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	
	public Long getId() {
		
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}


	@ManyToOne(fetch=FetchType.LAZY)
	@CollectionMetadata(collectionClass=Comune.class)
	private Comune comune;
	
	private String via;
	
	private String numero;
	
	private String cap;

	public Comune getComune() {
		return comune;
	}

	public void setComune(Comune comune) {
		this.comune = comune;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}
	
	
	
}
