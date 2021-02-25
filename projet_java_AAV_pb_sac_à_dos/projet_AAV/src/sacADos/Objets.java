package sacADos;

public class Objets {
	
	private String nom;
	private float poids;
	private float valeur;
	private float rapport;
	private int poidsFause;
	
	public Objets(String nom, float poids, float valeur) {
		this.nom = nom;
		this.poids = poids;
		this.valeur = valeur; 
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public float getPoids() {
		return this.poids;
	}
	
	public int getPoidsFause() {
		poidsFause = (int)(poids*10);
		return poidsFause;
	}

	public float getValeur() {
		return this.valeur;
	}
	
	public float getRapport() {
		rapport = valeur/poids;
		return rapport;
	}
	
	public String toString() {
		String line;
		line = nom + "a pour poids " + poids + "kg possedant une valeur égale à " + valeur;
		return line;
	}

	


}
