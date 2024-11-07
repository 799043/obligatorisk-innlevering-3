package no.hvl.dat100.oppgave3;

import no.hvl.dat100.common.TODO;
import no.hvl.dat100.oppgave1.*;

public class Blogg {

	// TODO: objektvariable
	private Innlegg[] innleggtabell;
	private int nesteledig;

	public Blogg() {
		this(20);
	}

	public Blogg(int lengde) {
		innleggtabell = new Innlegg[lengde];
        nesteledig = 0;
	}

	public int getAntall() {
		return nesteledig;
	}
	
	public Innlegg[] getSamling() {
		return innleggtabell;
	}
	
	public int finnInnlegg(Innlegg innlegg) {
		int innleggNr = -1;
		for (int i = 0; i < nesteledig; i++) {
			if (innleggtabell[i].erLik(innlegg)) {
				innleggNr = i;
				break;
			}
		}
		return innleggNr;
	}

	public boolean finnes(Innlegg innlegg) {
		return finnInnlegg(innlegg) != -1;
	}

	public boolean ledigPlass() {
		return nesteledig < innleggtabell.length;
	}
	
	public boolean leggTil(Innlegg innlegg) {
		if (finnes(innlegg) || !ledigPlass()) {
			return false;
		}
		innleggtabell[nesteledig] = innlegg;
		nesteledig++;
		return true;
	}
	
	public String toString() {
		String str = nesteledig + "\n";
		for (Innlegg innlegg : innleggtabell) {
			str += innlegg.toString();
		}
		return str;
	}

	// valgfrie oppgaver nedenfor
	
	public void utvid() {
		Innlegg[] nyTabell = new Innlegg[innleggtabell.length * 2];
		for (int i = 0; i < nesteledig; i++) {
			nyTabell[i] = innleggtabell[i];
		}
		innleggtabell = nyTabell;
	}
	
	public boolean leggTilUtvid(Innlegg innlegg) {
		if (finnes(innlegg)) {
			return false;
		}
		if (!ledigPlass()) {
			utvid();
		}
		innleggtabell[nesteledig] = innlegg;
		nesteledig++;
		return true;
	}
	
	public boolean slett(Innlegg innlegg) {
		int innleggNr = finnInnlegg(innlegg);
		if (innleggNr == -1) {
			return false;
		}
		nesteledig--;
		for (int i = innleggNr; i < nesteledig; i++) {
			innleggtabell[i] = innleggtabell[i + 1];
		}
		innleggtabell[nesteledig] = null;
		return true;
	}
	
	public int[] search(String keyword) {
		int[] innleggNr = new int[nesteledig];
		int antall = 0;
		for (int i = 0; i < nesteledig; i++) {
			if (innleggtabell[i].toString().contains(keyword)) {
				innleggNr[antall] = i;
				antall++;
			}
		}
		int[] result = new int[antall];
		for (int i = 0; i < antall; i++) {
			result[i] = innleggNr[i];
		}
		return result;
	}
}