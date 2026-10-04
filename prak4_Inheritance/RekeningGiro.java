package prak4_Inheritance;

public class RekeningGiro extends Rekening {
	private double batasOverdraft;
	
	public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, 
			double batasOverdraft) {
		//memanggil inisialisasi dasar superclass
		super(nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
	}
	
	//Getter khusus Giro
	public double getBatasOverdraft() {
		return batasOverdraft;
	}
 // (Catatan; Penarikan hingga limit overdraft akan diselesaikan dimodul 5)
}
