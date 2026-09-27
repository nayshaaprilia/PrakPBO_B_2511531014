package prak3_enkapsulasi;

public class Transaksi {
	//mengubah atribut jd private
	private String idTransaksi;
	private String jenis;
	private double nominal;
	
	public Transaksi(String id, String jenis, double nominal) {
		this.idTransaksi = id;
		this.jenis = jenis;
		this.nominal = nominal;
	}
	// Hanya nyediakan getter (Read Only)
	public String getIdTransaksi() { return idTransaksi;}
	public String getJenis() { return jenis; }
	public double getNominal() { return nominal;}
	
	public void cetakDetail() {
		System.out.println("ID: " + idTransaksi + " | Jenis:"
	    + jenis + " | Nominal: Rp" + nominal);
	}

}
