package net.mysoftworks.cr.model;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	private String cognome;
	
	@ManyToMany
	@CollectionMetadata(collectionClass=Regione.class)
	public Set<Regione> regioni;
	
	public Set<Regione> getRegioni() {
		return regioni;
	}

	public void setRegioni(Set<Regione> regioni) {
		this.regioni = regioni;
	}
	/* Fine solo per test */
	
	@ManyToMany
	@CollectionMetadata(collectionClass=Indirizzo.class)
	public Set<Indirizzo> recapiti;
		
	@ManyToOne(fetch=FetchType.LAZY)
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
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome
				+  ", recapiti=" + recapiti + "]";
	}
	
}
