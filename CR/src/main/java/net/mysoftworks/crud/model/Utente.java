package net.mysoftworks.crud.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Utente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	
	public Long getId() {
		
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	private String nome;

	private String cognome;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@CollectionMetadata(collectionClass=Regione.class)
	public Set<Regione> regioni;
	
	public Set<Regione> getRegioni() {
		return regioni;
	}

	public void setRegioni(Set<Regione> regioni) {
		this.regioni = regioni;
	}
	/* Fine solo per test */
	
	@ManyToMany(fetch=FetchType.LAZY)
	@CollectionMetadata(collectionClass=Indirizzo.class)
	public Set<Indirizzo> recapiti;
		
	@ManyToOne(fetch=FetchType.LAZY)
	@Embedded
	public Indirizzo indirizzoDiResidenza;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Indirizzo getIndirizzoDiResidenza() {
		return indirizzoDiResidenza;
	}

	public void setIndirizzoDiResidenza(Indirizzo indirizzoDiResidenza) {
		this.indirizzoDiResidenza = indirizzoDiResidenza;
	}

	public Set<Indirizzo> getRecapiti() {
		return recapiti;
	}

	public void setRecapiti(Set<Indirizzo> recapiti) {
		this.recapiti = recapiti;
	}

	@Override
	public String toString() {
		return nome + " " + cognome;
	}
	
}
