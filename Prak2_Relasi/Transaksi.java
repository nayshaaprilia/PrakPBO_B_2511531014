package Prak2_Relasi;

public class Transaksi {
	String idTransaksi ;
	String jenis;
	double nominal;
	
	//constructor
	public Transaksi(String id, String jenis, double nominal) {
		this.idTransaksi = id;
		this.jenis = jenis;
		this.nominal = nominal;
	}
public void cetakDetail () {
	System.out.println("ID: " + idTransaksi +" | Jenis: " + jenis + " |Nominam: Rp" + nominal);
}
}
