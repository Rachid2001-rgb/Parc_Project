package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Article extends Commerce{
	
	private StringProperty id;
    private StringProperty quantite;
  
  public Article(String id,String nom,String quantite,String categorie,String prix,String id_com) {
	  super(id_com,prix,categorie, nom);
	  this.id = new SimpleStringProperty(id);
      this.quantite = new SimpleStringProperty(quantite);
  }

  public String getId() {
      return id.get();
  }
  
  
  public void setId(String id) {
      this.id.set(id);
  }
  
  public String getQuantite() {
      return quantite.get();
  }
  
  public void setQuantite(String quantite) {
     this.quantite.set(quantite);
  }
 



@Override
public String getPrix() {
	// TODO Auto-generated method stub
	return super.prix.get();
}

@Override
public void setPrix(String prix) {
	// TODO Auto-generated method stub
	super.prix.set(prix);
}

@Override
public String getCategorie() {
	// TODO Auto-generated method stub
	return super.categorie.get();
}

@Override
public void setCategorie(String categorie) {
	// TODO Auto-generated method stub
	super.categorie.set(categorie);
}

@Override
public String getNom() {
	// TODO Auto-generated method stub
	return super.nom.get();
}

@Override
public void setNom(String nom) {
	// TODO Auto-generated method stub
	super.nom.set(nom);
}
}
