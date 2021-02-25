package méthodes;

import java.util.ArrayList;
import java.util.LinkedList;
import sacADos.Objets;

public class Noeud {

	private float poidsActuelle;
	private int profondeur;
	private float borneSup;
	private float borneInf;
	float poids_maximal;
	private ArrayList<Objets> ListObjet;
	private ArrayList<Objets> ListObjetParNoeud; //Liste d'objet qui presente dans chaque noeud

	// Noeud pour racine
	public Noeud(ArrayList<Objets> ListObjet, float poids_maximal) {
		this.ListObjet = ListObjet;
		this.poidsActuelle = 0;
		this.profondeur = 0;
		this.borneInf = 0;
		this.borneSup = 0;
		this.poids_maximal = poids_maximal;
		this.ListObjetParNoeud = new ArrayList<Objets>();
	}

	//tous les autres noeuds
	public Noeud(Noeud pere) {
		this.poidsActuelle = pere.poidsActuelle;
		this.profondeur = pere.profondeur + 1;
		this.borneInf = pere.borneInf;
		this.borneSup = pere.borneSup;
		this.ListObjet = pere.ListObjet; 
		this.poids_maximal = pere.poids_maximal;
		nouvelleListeObjetParNoeud(pere.ListObjetParNoeud);
	}
	
	//Créer un nouvelle Liste d'objet pour chaque noeud
	private void nouvelleListeObjetParNoeud(ArrayList<Objets> list){
		this.ListObjetParNoeud = new ArrayList<Objets>();
		for(Objets o : list)this.ListObjetParNoeud.add(o);
	}
	
	//Mis à jour les infos pour le noeud qui va ajouter l'objet et le mettre dans le Liste de 
	//noeud presente actuelle(non traite)
	public void noeudAjouterObjet(LinkedList<Noeud> ListNoeud) {
		borneInf += ListObjet.get(profondeur-1).getValeur();
		poidsActuelle += ListObjet.get(profondeur-1).getPoids();
		setBorneSup();
		ListObjetParNoeud.add(ListObjet.get(profondeur-1));
		ListNoeud.add(this);		
	}
	
	//Mis à jour les infos pour le noeud qui n'ajoute pas l'objet et le mettre dans le Liste de 
	//noeud presente actuelle(non traite)
	public void noeudSansAjouterObjet(LinkedList<Noeud> ListNoeud) {
		setBorneSup();
		ListNoeud.add(this);
	}
	
	
	//Verifier si le poid de nouvelle noeud est depassé va depasse le poid maximal (poids de sac)
	public boolean verifierPoids() {
		return this.getPoidsActuelle() + ListObjet.get(profondeur).getPoids() <= poids_maximal;
	}
	
	//Mis à jour le borne superieur du Racine
	public void setBorneSupRacine() { //mis à jour le borne sup de racine
		borneSup = poids_maximal*ListObjet.get(profondeur).getRapport(); 
	}
	
	//Mis a jour le borne superieur du noeud
	public void setBorneSup() { 
		if(profondeur < ListObjet.size())borneSup =  borneInf + ((poids_maximal - poidsActuelle)*ListObjet.get(profondeur).getRapport());
		else borneSup =  borneInf + ((poids_maximal - poidsActuelle)*0);
	}
	
	public float getPoidsActuelle() {
		return this.poidsActuelle;
	}
	
	public float getBorneSup() {
		return this.borneSup;
	}

	public int getProfondeur() {
		return this.profondeur;
	}

	//Obtien la liste d'objet par noeud
	public ArrayList<Objets> getObjetParNoeud() {
		return ListObjetParNoeud;
	}

}
