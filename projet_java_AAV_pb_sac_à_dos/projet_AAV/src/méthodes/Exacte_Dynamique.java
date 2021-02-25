package méthodes;

import java.util.ArrayList;

import sacADos.Objets;

public class Exacte_Dynamique {
	
	float poids_maximal;
	ArrayList<Objets> ListObjet; 
	ArrayList<Objets> ListObjetChoisi;
	
	public Exacte_Dynamique(ArrayList<Objets> objetsPresents, ArrayList<Objets> objetsSelectionnée, float poids_maximal) {
		this.ListObjet = objetsPresents;
		this.ListObjetChoisi = objetsSelectionnée;
		this.poids_maximal = poids_maximal;
	}

	//méthode dynamique
	public void methodeDynamique() {
		int a = (int)(poids_maximal*10);//mettre les poids_maximal en int (on le multiplie par 10 pour eviter d'avoir le chiffre apres virgule )
		int b = ListObjet.size();
		float[][] tableDynamique = new float[b][a+1];// créer un table de dimension nb objet*(poids_maximal*10)
		
		//on remplir la premier ligne de table
		for(int j = 0; j < tableDynamique[0].length; j++) {
			if(ListObjet.get(0).getPoidsFause() > j) 
				
				tableDynamique[0][j] = 0;
			else
				tableDynamique[0][j] = ListObjet.get(0).getValeur();
			
			}
		
		//remplir les cases restes
		int i= 1, j = 0;
		for(; i < tableDynamique.length; i++) { 
			for(j = 0; j < tableDynamique[0].length; j++) {
				if (ListObjet.get(i).getPoidsFause() > j)
					tableDynamique[i][j] = tableDynamique[i - 1][j];
				else
					tableDynamique[i][j] = max(tableDynamique[i - 1][j],tableDynamique[i - 1][j - ListObjet.get(i).getPoidsFause()] + ListObjet.get(i).getValeur());
			}
		}
		
		i--;
		j--;
		
		// Trouver le meilleur benefice
		while(tableDynamique[i][j] == tableDynamique[i][j-1]) {
			j--;
		}

		//Mettre les objets dans les sac selon leur benefices
		while(j > 0) {
			while(i > 0 && tableDynamique[i][j] == tableDynamique[i - 1][j])
				i--;
			j = j - ListObjet.get(i).getPoidsFause();
			if (j >= 0) {
				ListObjetChoisi.add(ListObjet.get(i));
				i--;
			}
		}
	}
	
	//Trouver le max
	public float max(float a, float b) {
		if(a >= b)
			return a;
		else
			return b;
	}
	
	
}
