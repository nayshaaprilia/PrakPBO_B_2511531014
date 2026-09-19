package Prak2_Relasi;
import java.util.ArrayList;

public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	
	//Implementasikan Asosiasi (1-to-many)
	ArrayList<Transaksi> riwayatTransaksi;
	
	 public Rekening(String nomorRekening, String namaPemilik, double saldoAwal) {
	        this.nomorRekening = nomorRekening;
	        this.namaPemilik = namaPemilik;
	        this.saldo = saldoAwal;
	      
	        
	        //wajib menginisialisasikan Arraylist di dalam constructor agar tidak NullPointerException
	        this.riwayatTransaksi = new ArrayList<>();
	        
	        System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat.");
	 }

	    public String getNomorRekening() {
	        return nomorRekening;
	    }

public void setorTunai(double nominal) {
	if (nominal > 0) {
		saldo += nominal;
		//Merekam riwayat (Pembuatan objek Transaksi di dalam method)
		String idTrx = "TRX-S-" + System.currentTimeMillis();
		Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
		riwayatTransaksi.add(trxBaru);
		
		System.out.println("Setor tunai Rp" + nominal + " berhasil Saldo saat ini: Rp" + saldo);
	}else {
		System.out.println("Gagal: Nominal setor harus lebih dari 0! ");
	}
}
public void tarikTunai(double nominal) {
	String idTrx = "TRX-T-" + System.currentTimeMillis();
	Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
	riwayatTransaksi.add(trxBaru);
	
		if (nominal < 10000) {	
			System.out.println("Transaksi Gagal: Minimal Penarikan 10.000");
		} else if (nominal > saldo) {
			System.out.println("Transaksi Gagal saldo tidak mencukupi.Saldo Anda: Rp" + saldo);
		}
		else {
			saldo -= nominal;
			System.out.println("Transaksi Berhasil");
		}
		
}
public void cetakMutasi() {
    if (riwayatTransaksi.isEmpty()) {
        System.out.println("Belum ada transaksi pada rekening ini");
    } else {
        for (Transaksi transaksi : riwayatTransaksi) {
            transaksi.cetakDetail();
        }
    }
  
}
public void cekInformasi() {
	System.out.println("--- INFO REKENING ---");
	System.out.println("No.Rekening : " + nomorRekening);
	System.out.println("Nama Pemilik : " + namaPemilik);
	System.out.println("Saldo Akhir : Rp" + saldo);
	System.out.println("---------------");
	
}

}



