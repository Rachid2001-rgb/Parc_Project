package models;

public class Attraction {

	int id;
	String txt_nom;
	float txt_duree,txt_prix;
	public Attraction(int id, String txt_nom, float txt_duree, float txt_prix) {
		
		this.id = id;
		this.txt_nom = txt_nom;
		this.txt_duree = txt_duree;
		this.txt_prix = txt_prix;
	}
	public Attraction() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTxt_nom() {
		return txt_nom;
	}
	public void setTxt_nom(String txt_nom) {
		this.txt_nom = txt_nom;
	}
	public float getTxt_duree() {
		return txt_duree;
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
