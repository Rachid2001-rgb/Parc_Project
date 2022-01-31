package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Commerce {
	
	protected StringProperty id_com ;
	protected StringProperty prix ;
	protected StringProperty categorie ;
	protected StringProperty nom;
	

	protected Commerce(String id ,String prix,String categorie,String nom) {
		 this.id_com = new SimpleStringProperty(id);
		 this.prix = new SimpleStringProperty(prix);
		 this.categorie= new SimpleStringProperty(categorie);
		 this.nom = new SimpleStringProperty(nom);
		
	}


	public String getId_com() {
		return id_com.get();
	}


	public void setId_com(String id_com) {
		this.id_com.set(id_com);
	}



	protected abstract String getPrix() ;


	protected abstract void setPrix(String prix) ;


	protected abstract String getCategorie() ;

	protected abstract void setCategorie(String categorie);


	protected abstract String getNom();


	protected abstract void setNom(String nom);

}