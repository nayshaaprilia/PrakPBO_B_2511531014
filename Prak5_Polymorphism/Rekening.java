package Prak5_Polymorphism;
import java.util.ArrayList;

public class Rekening {
	//Batas atas dari class Rekening
	private String nomorRekening;
	private String namaPemilik;
	private String pin; 
	
	//Gunakan protect agar subclass bisa mengakses langsung
	protected double saldo;
	protected ArrayList<Transaksi> riwayatTransaksi;
	
	//Isi konstruktor dan Method lain sama dengan modul 3
	//2.Modifikasi Consstructor untuk menerima PIN awal
	public Rekening(String nomor, String nama, double saldoAwal, String pinAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		//validasi PIN dalam Constructor
		if (pinAwal.length() == 6) {
			this.pin =pinAwal;
		} else {
		System.out.println("Peringatan: PIN harus 6 digit Menggunakan PIN default 123456");
		this.pin = "123456";
		}
	this.riwayatTransaksi = new ArrayList<>();
	System.out.println("Rekening atas nama " +  namaPemilik  + " berhasil dibuat.");
}
	//3. Getter untuk atribut yang diizinkan dibaca publik
	public String getNomorRekening() { return nomorRekening; }
	public String getNamaPemilik() { return namaPemilik; }
	
	//4. Method Otentikasi Internal (Validasi Enkapsulasi)
	public boolean otentikasi(String inputPin) {
		return this.pin.equals(inputPin);
	}
	// Method setor tunai, tarik tunai dll tetap seperti Prak 2
	
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
	
	public boolean tarikTunai(double nominal) {
		if (nominal <= 0) {
			System.out.println("Gagal: Nominal tarik harus lebih dari 0!");
			return false;
			} else if (nominal > saldo) {
				System.out.println("Transaksi Gagal: Saldo tidak mencukupi, Saldo Anda: Rp" + saldo);
				return false;
			} else {
				saldo -= nominal;
			
		String idTrx = "TRX-T-" + System.currentTimeMillis();
		riwayatTransaksi.add(new Transaksi(idTrx, "Debit", nominal));
		
		System.out.println("Tarik tunai Rp" + nominal + " berhasil. Sisa saldo: Rp" + saldo);
		return true; //transaksi sukses
			}
	}
	
	public void transfer(Rekening tujuan, double nominal, String pinInput) {
	    if (!otentikasi(pinInput)) {
	        System.out.println("PIN salah!");
	        return;
	    }

	    if (this.tarikTunai(nominal)) {
	        tujuan.setorTunai(nominal);
	        System.out.println("Transfer berhasil!");
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



