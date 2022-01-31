package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FactureArticle {

	private StringProperty nom;
    private StringProperty disponible;
    
    private StringProperty prix;
  public FactureArticle(String nom,String disponible,String prix) {
	  this.nom = new SimpleStringProperty(nom);
      this.disponible = new SimpleStringProperty(disponible);
      this.prix = new SimpleStringProperty(prix);
  }
public String getNom() {
	return nom.get();
}
public void setNom(String nom) {
	this.nom.set(nom);
}
public String getDisponible() {
	return disponible.get();
}
public void setDisponible(String disponible) {
	this.disponible.set(disponible);
}
public String getPrix() {
	return prix.get();
}
public void setPrix(String prix) {
	this.prix.set(prix);
}

 

}
