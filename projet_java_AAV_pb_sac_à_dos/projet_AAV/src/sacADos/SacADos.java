package sacADos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import méthodes.Exacte_Dynamique;
import méthodes.Exacte_PSE;
import méthodes.Gloutonne;

public class SacADos {

	@SuppressWarnings("unused")
	private String chemin;
	private float poids_maximal;
	private float valeurs_total;
	private float poids_total; 
	ArrayList<Objets> objetsSelectionnée = new ArrayList<Objets>(); // Objet metter dans le sac 
	ArrayList<Objets> objetsPresents;// Objets presents 
	long upTime; // Le temps utilise en ms 
	long startTime, endTime; // Le temps réelle debut et fin d'un methode 

	//Sac à dos 
	public SacADos(String chemin, float poids_maximal) throws IOException {
		this.chemin = chemin;
		this.poids_maximal = poids_maximal;
		this.valeurs_total = 0;
		this.poids_total = 0;
		
		//Lire les fichier et mettre les objets dans le Liste de objets Presente
		@SuppressWarnings("resource")
		BufferedReader text = new BufferedReader(new FileReader(chemin));
		objetsPresents = new ArrayList<Objets>();
		String line;
        while((line = text.readLine()) != null) {
            String[] strArray = line.split(";"); //Les elements de la ligne est separer par symbole ';' 
            Objets obj = new Objets(strArray[0],Float.parseFloat(strArray[1]),Float.parseFloat(strArray[2]));
            objetsPresents.add(obj); // mettre les elements dans la liste de l'objet presente
        }
	}
	
	//Mettre les objet chosi par les méthodes dans le liste ObjetSelectionne
	public void getElementOfList() {
		for (Objets objs : objetsSelectionnée) {
			poids_total += objs.getPoids();
			valeurs_total += objs.getValeur();
			}
	}
	
	//Affichier les Liste des objets mettre dans le sac
	public String toString() { 
		StringBuilder ObjetInsere = new StringBuilder();
		if(this.objetsSelectionnée.size() == 0) {
			ObjetInsere.append("Aucun element present");
			return ObjetInsere.toString();
		}
		else {
			ObjetInsere.append("Objets choisi : \n");
			for(Objets objs : objetsSelectionnée) {
				ObjetInsere.append("\t" + objs.toString()+ "\n");
			}
		getElementOfList();
		ObjetInsere.append( "\nLa valeur totale du sac est égale à " + valeurs_total + " et le poid final est égale à " + poids_total + " kg");
		upTime = endTime - startTime;
		ObjetInsere.append("\nLe temps utilise pour resoudre le probleme de sac = " + upTime +"ms");
		return ObjetInsere.toString();
		}
	}
	

	@SuppressWarnings("unused")
	public void resoudre(int a) {
		switch(a){
		case 1: 
			//methode Glutonne
			startTime = System.currentTimeMillis();
			Gloutonne méthoGlutonne = new Gloutonne(objetsPresents,objetsSelectionnée,poids_maximal);
			méthoGlutonne.methodeGlutonne();
			endTime = System.currentTimeMillis();
			System.out.println(toString());
			break;
		case 2:
			//methode Dynamique
			startTime = System.currentTimeMillis();
			Exacte_Dynamique méthoDynamique = new Exacte_Dynamique(objetsPresents,objetsSelectionnée,poids_maximal);
			méthoDynamique.methodeDynamique();
			endTime = System.currentTimeMillis();
			System.out.println(toString());
			break;
		case 3:
			//methode PSE
			startTime = System.currentTimeMillis();
			Exacte_PSE méthodePSE = new Exacte_PSE(objetsPresents,objetsSelectionnée,poids_maximal);
			méthodePSE.methodePSE();
			endTime = System.currentTimeMillis();
			System.out.println(toString());
			break;
		default :
			System.out.println("valeur inconnue");
		}		
	}
	
	
	public ArrayList<Objets> getObjetsPresents(){
		return objetsPresents;
	}
	
	public ArrayList<Objets> getObjetsSelectionnée(){
		return objetsSelectionnée;
	}


}
