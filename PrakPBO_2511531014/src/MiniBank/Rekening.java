package MiniBank;

public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	
	 public Rekening(String nomorRekening, String namaPemilik, double saldo) {
	        this.nomorRekening = nomorRekening;
	        this.namaPemilik = namaPemilik;
	        this.saldo = saldo;
	    }

	    public String getNomorRekening() {
	        return nomorRekening;
	    }


public void setorTunai(double nominal) {
	if (nominal > 0) {
		saldo += nominal;
		System.out.println("Setor tunai Rp" + nominal + " berhasil Saldo saat ini: Rp" + saldo);
	}else {
		System.out.println("Gagal: Nominal setor harus lebih dari 0! ");
	}
}
public void tarikTunai(double nominal) {
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

public void cekInformasi() {
	System.out.println("--- INFO REKENING ---");
	System.out.println("No.Rekening : " + nomorRekening);
	System.out.println("Nama Pemilik : " + namaPemilik);
	System.out.println("Saldo Akhir : Rp" + saldo);
	System.out.println("---------------");
	
}
}
