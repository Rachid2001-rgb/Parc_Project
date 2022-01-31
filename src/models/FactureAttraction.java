package models;

public class FactureAttraction {



	
	String txt_nom;
	float txt_duree,txt_prix;
	public FactureAttraction( String txt_nom, float txt_prix) {
		
	
		this.txt_nom = txt_nom;
		
		this.txt_prix = txt_prix;
	}
	public FactureAttraction() {
		super();
	}
	
	public String getTxt_nom() {
		return txt_nom;
	}
	public void setTxt_nom(String txt_nom) {
		this.txt_nom = txt_nom;
	}

	
	public void setTxt_duree(float txt_duree) {
		this.txt_duree = txt_duree;
	}
	public float getTxt_prix() {
		return txt_prix;
	}
	public void setTxt_prix(float txt_prix) {
		this.txt_prix = txt_prix;
	}
	
	
}
