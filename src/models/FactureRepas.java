package models;

public class FactureRepas {




	int txt_qteR;
	String txt_nomR;
	float txt_prixR;
	public FactureRepas( String txt_nomR, int txt_qteR, float txt_prixR) {
		
	
		this.txt_nomR = txt_nomR;
		this.txt_qteR = txt_qteR;
		this.txt_prixR = txt_prixR;
	}
	public FactureRepas() {
		super();
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
	public void setTxt_prixR(float txt_prixR) {
		this.txt_prixR = txt_prixR;
	}
	
	
	
}
