package models;

public class Repas {

	int idR,txt_qteR;
	String txt_nomR;
	float txt_prixR;
	public Repas(int idR, String txt_nomR, int txt_qteR, float txt_prixR) {
		
		this.idR = idR;
		this.txt_nomR = txt_nomR;
		this.txt_qteR = txt_qteR;
		this.txt_prixR = txt_prixR;
	}
	public Repas() {
		super();
	}
	public int getIdR() {
		return idR;
	}
	public void setIdR(int idR) {
		this.idR = idR;
	}
	public String getTxt_nomR() {
		return txt_nomR;
	}
	public void setTxt_nomR(String txt_nomR) {
		this.txt_nomR = txt_nomR;
	}
	public int getTxt_qteR() {
		return txt_qteR;
	}
	public void setTxt_qteR(int txt_qteR) {
		this.txt_qteR = txt_qteR;
	}
	public float getTxt_prixR() {
		return txt_prixR;
	}
	public void setTxt_prixR(float txt_prix) {
		this.txt_prixR = txt_prixR;
	}
	
	
	
}
