package affichage;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
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
    @FXML
    private Label tourDeQui;
    @FXML
    private Canvas affichageNbEtoiles;
    private GraphicsContext afficheEtoiles;
    @FXML
    private Label tour;
    
    private Image herbe;
    private Image etoile;
    private Image longueVue;
    private Image chasseur;
    private Image monstre;
    private Image img;
    
    public void initialize() {
        assert grille != null : "fx:id=\"grille\" was not injected: check your FXML file 'AffichagePlateau.fxml'.";
        System.out.println("Initilisation...");
        
        gc = grille.getGraphicsContext2D();
        tailleBaseImg = (int) grille.getWidth() / testAffichagePlateau.p.getTaille();
        afficheEtoiles = affichageNbEtoiles.getGraphicsContext2D();
        
        //taille et couleur de l'écriture dans les cases
        gc.setFill(Color.YELLOW);
        gc.setFont(new Font(tailleBaseImg/3));
        
        //paramètres tour
        tour.setFont(new Font("Arial", 28));
        tour.setAlignment(Pos.CENTER);
        
        //paramètres tourDeQui
        tourDeQui.setFont(new Font("Arial", 28));
        tourDeQui.setAlignment(Pos.CENTER);
        
        herbe = new Image("File:img/grass_tile_1.png", tailleBaseImg, tailleBaseImg, true, true); //taille dynamique en fonction de taille plateau Client
        etoile = new Image("File:img/etoile.png", tailleBaseImg, tailleBaseImg, true, true);
        longueVue = new Image("File:img/longue-vue.jpg", tailleBaseImg, tailleBaseImg, true, true);
        chasseur = new Image("File:img/Chasseur templerun/Idle__000.png", tailleBaseImg, tailleBaseImg, true, true);
        monstre = new Image("File:img/Monstre zombie/Idle (1).png",  tailleBaseImg, tailleBaseImg, true, true);
        
        update();
    }
    
    public void update() {
    	//affichage tour
    	tour.setText("Tour "+testAffichagePlateau.p.getTour());
    	
    	//affichage tour de qui
    	changerTourDeQui();
    	
    	//affichage etoiles que le joueur a
        afficherEtoilesJoueur();
    	
        for(int i = 0; i < testAffichagePlateau.p.getTaille(); i++) { //changer par taille plateau Client
        	for(int j = 0; j < testAffichagePlateau.p.getTaille(); j++) { //idem
        		gc.drawImage(herbe, i*herbe.getWidth(), j*herbe.getHeight());
        		int nbImg = 0;
        		
        		if(testAffichagePlateau.p.getCase(i, j).hasEtoile()) {
        			img = etoile;
        			afficherImg(img, getNbEntites(testAffichagePlateau.p.getCase(i, j), i, j), i, j, nbImg, gc);
        			nbImg++;
        		}	
        		if(testAffichagePlateau.p.getCase(i, j).getLongueVue() > 0 && !testAffichagePlateau.estMonstre) {
        			img = longueVue;
        			afficherImg(img, getNbEntites(testAffichagePlateau.p.getCase(i, j), i, j), i, j, nbImg, gc);
        			nbImg++;
        			
        			if(testAffichagePlateau.p.getCase(i, j).getTourPassage() > -1) {
        				gc.fillText(""+testAffichagePlateau.p.getCase(i, j).getTourPassage(), 5*tailleBaseImg + tailleBaseImg*3/8, 5*tailleBaseImg + tailleBaseImg*5/6);
        			}
        		}
        		if(testAffichagePlateau.p.chasseur != null) {
	        		if(testAffichagePlateau.p.chasseur.getPosition().equals(new Position(i,j)) && !testAffichagePlateau.estMonstre) {
	        	    	img = chasseur;
	        	    	afficherImg(img, getNbEntites(testAffichagePlateau.p.getCase(i, j), i, j), i, j, nbImg, gc);
	        	    	nbImg++;
	        	   	}
        		}
        		if(testAffichagePlateau.p.monstre != null) {
	        		if(testAffichagePlateau.p.monstre.getPosition().equals(new Position(i,j)) && testAffichagePlateau.estMonstre) {
	        	   		img = monstre;
	        	   		afficherImg(img, getNbEntites(testAffichagePlateau.p.getCase(i, j), i, j), i, j, nbImg, gc);
	        	   		nbImg++;
	        	   	}
        		}	
        	}
		}
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
    	}
    	
    	return nb;
    }
    
    private void afficherImg(Image img, int nbEntites, int x, int y, int nbImg, GraphicsContext gc) {
   		if(nbEntites > 1) {
			gc.drawImage(img, x*tailleBaseImg + nbImg*(tailleBaseImg/2), y*tailleBaseImg, tailleBaseImg/2, tailleBaseImg/2);
		}
		else {
			afficherImg(img, x, y, gc);
		}
    }
    
    private void afficherImg(Image img, int x, int y, GraphicsContext gc) {
    	gc.drawImage(img, x*tailleBaseImg, y*tailleBaseImg);
    }
    
    private void changerTourDeQui() {
    	if(testAffichagePlateau.p.getTour() % 2 == 0) {
    		if(testAffichagePlateau.estMonstre) {
    			tourDeQui.setText("C'est ton tour !");
    		}
    		else {
    			tourDeQui.setText("Ce n'est pas ton tour");
    		}
    	}
    	else {
    		if(!testAffichagePlateau.estMonstre) {
    			tourDeQui.setText("C'est ton tour !");
    		}
    		else {
    			tourDeQui.setText("Ce n'est pas ton tour");
    		}
    	}
    }
    
    private void afficherEtoilesJoueur() {
    	if(testAffichagePlateau.estMonstre) {
    		for(int i = 0; i < testAffichagePlateau.p.getMonstre().getNbEtoiles(); i++) {
    			//afficherImg(etoile, i*tailleBaseImg, 0, afficheEtoiles);
    			afficheEtoiles.drawImage(etoile, i*(affichageNbEtoiles.getWidth()/3),0, affichageNbEtoiles.getWidth()/3, affichageNbEtoiles.getWidth()/3);
    		}
    	}
    	else {
    		for(int i = 0; i < testAffichagePlateau.p.getChasseur().getNbEtoiles(); i++) {
    			//afficherImg(etoile, i*tailleBaseImg, 0, afficheEtoiles);
    			afficheEtoiles.drawImage(etoile, i*(affichageNbEtoiles.getWidth()/3),0, affichageNbEtoiles.getWidth()/3, affichageNbEtoiles.getWidth()/3);
    		}
    	}
    }
}
