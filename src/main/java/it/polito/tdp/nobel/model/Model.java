package it.polito.tdp.nobel.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.nobel.db.EsameDAO;

public class Model {

	private List<Esame> esami;
	private Set<Esame> soluzione = null;
	private double bestMedia;

	
	public Model() {
		EsameDAO e = new EsameDAO();
		esami = e.getTuttiEsami();
		
	}
	public Set<Esame> calcolaSottoinsiemeEsami(int numeroCrediti) {
		Set<Esame> parziale = new HashSet<>();
		
		
		bestMedia = 0.0;
		soluzione = new HashSet<>();
		
		
		cerca(parziale,0,numeroCrediti);
		
		
		return soluzione;
	}

	private void cerca (Set<Esame> parziale, int livello, int m) {
		int somma = sommaCrediti(parziale);
		
		// casi terminali
		if (somma > m) {
			return;
		}
		
		if (somma == m) {
			//controllo la media
			double media = mediaCrediti(parziale);
			if (media > bestMedia) {
				bestMedia = media;
				soluzione = new HashSet<>(parziale);
			}
			return;
		}
		
		if (livello == esami.size()) {
			return;
		}
		
		parziale.add(esami.get(livello));
		cerca(parziale,livello+1,m);
		parziale.remove(esami.get(livello));
	
		cerca (parziale, livello+1, m);
		
	}
	private double mediaCrediti(Set<Esame> parziale) {
		int somma = 0;
		int crediti = 0;
		
		for (Esame e : parziale) {
			crediti += e.getCrediti();
			somma += (e.getVoto() * e.getCrediti());
		}
		return somma/crediti;
	}
	private int sommaCrediti(Set<Esame> parziale) {
		int somma=0;
		for(Esame e : esami) {
			somma+=e.getCrediti();
		}
		return somma;
	}
}
