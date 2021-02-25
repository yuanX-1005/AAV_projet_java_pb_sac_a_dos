package méthodes;

import java.util.ArrayList;
import sacADos.Objets;

//Tri rapide
public class TriRapide {
	ArrayList<Objets> ListObjet; 
	public TriRapide(ArrayList<Objets> ListObjet) {
		this.ListObjet = ListObjet;
	}
	
	public void echanger(Objets x, Objets y) {
		int x1 = ListObjet.indexOf(x);
		int y1 = ListObjet.indexOf(y);
		ListObjet.set(x1, y);
		ListObjet.set(y1, x);
	}
	
	
	public int repartition(int premierObj, int dernierObj, int positionPivot) {
		echanger(ListObjet.get(positionPivot), ListObjet.get(dernierObj));
		int compt = premierObj;
	    for(int i = premierObj; i <= dernierObj - 1; i++){
	        if (ListObjet.get(i).getRapport() > ListObjet.get(dernierObj).getRapport()){ 
	        	echanger(ListObjet.get(i), ListObjet.get(compt));
	        	compt++;
	        }
	    }
	    echanger(ListObjet.get(dernierObj), ListObjet.get(compt));
	    return compt;
    }
	  
	public void triRapide(int premierObj, int dernierObj){
	    if(premierObj < dernierObj){
	    	int positionPivot = choixPivot(premierObj, dernierObj);
	        positionPivot = repartition(premierObj, dernierObj, positionPivot);
	        triRapide(premierObj, positionPivot-1);
	        triRapide(positionPivot+1, dernierObj);
	    }
    }
	
	public int choixPivot(int premierObj, int dernierObj) {
		if((dernierObj - premierObj) % 2 == 0) {
			int p1 = (dernierObj - premierObj)/2;
			return p1+premierObj;
		}
		else {
			int p2 = (dernierObj - 1 - premierObj)/2;
			return p2+premierObj;
			}
	}

	public void triRapide() {
		int longueur = ListObjet.size();
		triRapide(0,longueur-1);
	}

  
}
