package sacADos;


/**
* @author YE Fangyuan Lisa 204 / MILLO Chelsey 207
* @version Creation time: 15/10/20
*/
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void resoudre_sac_a_dos() throws IOException {
	String chemin = null;
	int poids_maximal = 0;
	int méthode = 0;
	
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);  
	if(scan.hasNextLine())
		chemin = scan.nextLine(); // Enregistrer le chemin
	if(scan.hasNextInt())
		poids_maximal = scan.nextInt(); // Enregistrer le poid maximum de sac
	if(scan.hasNextInt())
		méthode = scan.nextInt();
	
	SacADos sac = new SacADos(chemin, poids_maximal);
	sac.resoudre(méthode);
	}
	 
	public static void main(String[] args) throws IOException {
		
		System.out.println("Vous allez commencer à resoudre le problème du sac à dos");
		System.out.println("Veuillez entrer le chemin(en type .txt), le poid maximal de sac et le methode choisir.");
		System.out.println("(Pour les méthodes vous avez le choix entre : Glutonne(1), Dynamique(2) et PSE(3))\n");

		resoudre_sac_a_dos();
	}
	
	

}
