package affichage;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
<<<<<<< HEAD
import plateau.Case;
import plateau.Plateau;
import plateau.Position;
import reseau.Client;

public class AffichagePlateau {
	
    @FXML
    private Canvas grille;
    private GraphicsContext gc;
    private int tailleBaseImg;
    
    public void initialize() {
        assert grille != null : "fx:id=\"grille\" was not injected: check your FXML file 'AffichagePlateau.fxml'.";
        System.out.println("Initilisation...");
        
        Plateau p = Client.getInstance().getPlateau();
        
        gc = grille.getGraphicsContext2D();
        tailleBaseImg = (int) grille.getWidth() / p.getTaille();
        
        Image herbe = new Image("File:img/grass_tile_1.png", tailleBaseImg, tailleBaseImg, true, true); //taille dynamique en fonction de taille plateau Client
        Image etoile = new Image("File:img/etoile.png", tailleBaseImg, tailleBaseImg, true, true);
        Image longueVue = new Image("File:img/longue-vue.jpg", tailleBaseImg, tailleBaseImg, true, true);
        Image chasseur = new Image("File:img/Chasseur templerun/Idle__000.png", tailleBaseImg, tailleBaseImg, true, true);
        Image monstre = new Image("File:img/Monstre zombie/Idle (1).png",  tailleBaseImg, tailleBaseImg, true, true);
        Image img = herbe;

        for(int i = 0; i < p.getTaille(); i++) { //changer par taille plateau Client
        	for(int j = 0; j < p.getTaille(); j++) { //idem
        		gc.drawImage(herbe, i*herbe.getWidth(), j*herbe.getHeight());
        		int nbImg = 0;
        		
        		if(p.getCase(i, j).hasEtoile()) {
        			img = etoile;
        			afficherImg(img, getNbEntites(p.getCase(i, j), i, j), i, j, nbImg);
        			nbImg++;
        		}	
        		if(p.getCase(i, j).getLongueVue() > 0) {
        			img = longueVue;
        			afficherImg(img, getNbEntites(p.getCase(i, j), i, j), i, j, nbImg);
        			nbImg++;
        			
        			/*if(testAffichagePlateau.p.getCase(i, j).getTourPassage() > -1) {
        				gc.drawImage(new Image(""+testAffichagePlateau.p.getCase(i, j).getTourPassage()), i, j, tailleBaseImg/4, tailleBaseImg/4);
        			}*/
        		}
        		/*if(p.getChasseur().getPosition().equals(new Position(i,j)) && !Client.getInstance().estMonstre) {
        	    	img = chasseur;
        	    	afficherImg(img, getNbEntites(p.getCase(i, j), i, j), i, j, nbImg);
        	    	nbImg++;
        	   	}
        		if(p.getMonstre().getPosition().equals(new Position(i,j)) && Client.getInstance().estMonstre) {
        	   		img = monstre;
        	   		afficherImg(img, getNbEntites(p.getCase(i, j), i, j), i, j, nbImg);
        	   		nbImg++;
        	   	}*/
        			
        	}
		}
    }
    
    private int getNbEntites(Case c, int x, int y) {
    	int nb = 0;
    	
    	if(c.hasEtoile()) {
    		nb++;
    	}
    	if(c.getLongueVue() > 0 && !Client.getInstance().estMonstre) {
    		nb++;
    	}
    	if(Client.getInstance().getPlateau().getMonstre().getPosition().equals(new Position(x,y)) && Client.getInstance().estMonstre) {
    		nb++;
    	}
    	if(Client.getInstance().getPlateau().getChasseur().getPosition().equals(new Position(x,y)) && !Client.getInstance().estMonstre) {
    		nb++;
=======
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import plateau.Case;
import plateau.Position;
import testsPlateau.testAffichagePlateau;

public class AffichagePlateau{
	
    @FXML
    private Canvas grille;
    private GraphicsContext gc;
    private int tailleBaseImg;
    
    public void initialize() {
        assert grille != null : "fx:id=\"grille\" was not injected: check your FXML file 'AffichagePlateau.fxml'.";
        System.out.println("Initilisation...");
        
        gc = grille.getGraphicsContext2D();
        tailleBaseImg = (int) grille.getWidth() / testAffichagePlateau.p.getTaille();
        
        Image herbe = new Image("File:img/grass_tile_1.png", tailleBaseImg, tailleBaseImg, true, true); //taille dynamique en fonction de taille plateau Client
        Image etoile = new Image("File:img/etoile.png", tailleBaseImg, tailleBaseImg, true, true);
        Image longueVue = new Image("File:img/longue-vue.jpg", tailleBaseImg, tailleBaseImg, true, true);
        Image chasseur = new Image("File:img/Chasseur templerun/Idle__000.png", tailleBaseImg, tailleBaseImg, true, true);
        Image monstre = new Image("File:img/Monstre zombie/Idle (1).png",  tailleBaseImg, tailleBaseImg, true, true);
        Image img = herbe;
        
        gc.setFill(Color.YELLOW);
        gc.setFont(new Font(tailleBaseImg/3));
        
      for(int i = 0; i < testAffichagePlateau.p.getTaille(); i++) { //changer par taille plateau Client
        	for(int j = 0; j < testAffichagePlateau.p.getTaille(); j++) { //idem
        		gc.drawImage(herbe, i*herbe.getWidth(), j*herbe.getHeight());
        		int nbImg = 0;
        		
        		if(testAffichagePlateau.p.getCase(i, j).hasEtoile()) {
        			img = etoile;
        			afficherImg(img, getNbEntites(testAffichagePlateau.p.getCase(i, j), i, j), i, j, nbImg);
        			nbImg++;
        		}	
        		if(testAffichagePlateau.p.getCase(i, j).getLongueVue() > 0 && !testAffichagePlateau.estMonstre) {
        			img = longueVue;
        			afficherImg(img, getNbEntites(testAffichagePlateau.p.getCase(i, j), i, j), i, j, nbImg);
        			nbImg++;
        			
        			if(testAffichagePlateau.p.getCase(i, j).getTourPassage() > -1) {
        				gc.fillText(""+testAffichagePlateau.p.getCase(i, j).getTourPassage(), 5*tailleBaseImg + tailleBaseImg*3/8, 5*tailleBaseImg + tailleBaseImg*5/6);
        			}
        		}
        		if(testAffichagePlateau.p.chasseur != null) {
	        		if(testAffichagePlateau.p.chasseur.getPosition().equals(new Position(i,j)) && !testAffichagePlateau.estMonstre) {
	        	    	img = chasseur;
	        	    	afficherImg(img, getNbEntites(testAffichagePlateau.p.getCase(i, j), i, j), i, j, nbImg);
	        	    	nbImg++;
	        	   	}
        		}
        		if(testAffichagePlateau.p.monstre != null) {
	        		if(testAffichagePlateau.p.monstre.getPosition().equals(new Position(i,j)) && testAffichagePlateau.estMonstre) {
	        	   		img = monstre;
	        	   		afficherImg(img, getNbEntites(testAffichagePlateau.p.getCase(i, j), i, j), i, j, nbImg);
	        	   		nbImg++;
	        	   	}
        		}	
        	}
		}
      
      gc.fillText("5", 5*tailleBaseImg + tailleBaseImg*3/8, 5*tailleBaseImg + tailleBaseImg*5/6);
    }
    
    private int getNbEntites(Case c, int x, int y) {
    	int nb = 0;
    	
    	if(c.hasEtoile()) {
    		nb++;
    	}
    	if(c.getLongueVue() > 0 && !testAffichagePlateau.estMonstre) {
    		nb++;
    	}
    	if(testAffichagePlateau.p.monstre != null) {
	    	if(testAffichagePlateau.p.monstre.getPosition().equals(new Position(x,y)) && testAffichagePlateau.estMonstre) {
	    		nb++;
	    	}
    	}
    	if(testAffichagePlateau.p.chasseur != null) {
	    	if(testAffichagePlateau.p.chasseur.getPosition().equals(new Position(x,y)) && !testAffichagePlateau.estMonstre) {
	    		nb++;
	    	}
>>>>>>> refs/remotes/origin/Vincent
    	}
    	
    	return nb;
    }
    
    private void afficherImg(Image img, int nbEntites, int x, int y, int nbImg) {
   		if(nbEntites > 1) {
			gc.drawImage(img, x*tailleBaseImg + nbImg*(tailleBaseImg/2), y*tailleBaseImg, tailleBaseImg/2, tailleBaseImg/2);
		}
		else {
			gc.drawImage(img, x*tailleBaseImg, y*tailleBaseImg);
		}
    }
}
