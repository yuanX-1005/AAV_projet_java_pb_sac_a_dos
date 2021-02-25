package méthodes;

import java.util.ArrayList;

import sacADos.Objets;

public class Gloutonne {
	float poids_maximal;
	ArrayList<Objets> ListObjet; 
	ArrayList<Objets> ListObjetChoisi;
	TriRapide tri;
	
	public Gloutonne(ArrayList<Objets> ListObjet, ArrayList<Objets> ListObjetChoisi,float poids_maximal) {
		this.ListObjet = ListObjet;
		this.ListObjetChoisi = ListObjetChoisi;
		this.poids_maximal = poids_maximal;
		tri = new TriRapide(ListObjet);	
	}
	
	//Mehode glutone
	public void methodeGlutonne() {
		tri.triRapide();
		ajouterObjetsDansSac();
	}
	
	
	public void ajouterObjetsDansSac() {
		float calcul = 0;
		float a;
		for (int i = 0; i < ListObjet.size(); i++) {
			a = ListObjet.get(i).getPoids();
			if ((calcul += a) <= poids_maximal)
			ListObjetChoisi.add(ListObjet.get(i));
		}
	}
}
