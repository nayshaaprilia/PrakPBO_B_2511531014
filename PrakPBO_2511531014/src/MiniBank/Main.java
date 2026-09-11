package MiniBank;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[]args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Rekening> daftarRekening = new ArrayList<>();
		Rekening akunAktif = null; //null karna objek belum di inisialisasi
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println("\nMenu Utama:");
			System.out.println("1.Buka Rekening Baru");
			System.out.println("2.Setor Tunai");
			System.out.println("3.Tarik tunai");
			System.out.println("4.Cek Informasi Rekening");
			System.out.println("5.Ganti Akun");
			System.out.println("0.Keluar");
			System.out.println("Pilih Menu: ");
			
			int pilihan = input.nextInt();
			input.nextLine(); //Membersihkan buffer enter
			
			switch(pilihan) {
			case 1:
				System.out.print("Masukan No Rekening: ");
				String no = input.nextLine();
				System.out.print("Masukan Nama Pemilik: ");
				String nama = input.nextLine();
				System.out.print("Masukan Saldo Awal: ");
				double saldo = input.nextDouble();
				
				//Instanslasi Object /Menjalankan Constructor
				Rekening rekeningBaru = new Rekening(no, nama, saldo);
				daftarRekening.add(rekeningBaru);
				akunAktif = rekeningBaru;
			break;
			
			case 2:
				if (akunAktif == null) {
					System.out.println("Error: Mohon Maaf, Anda belum memiliki nomor rekening!");
				} else {
					System.out.print("Masukan nominal setor: ");
					double setor = input.nextDouble();
					akunAktif.setorTunai(setor); //Memanggil Method
				}
				break;
		
			case 3:
				if (akunAktif == null) {
					System.out.println("Error: Mohon Maaf, Anda belum memiliki nomor rekening!");
				} else {
					System.out.print("Masukan nominal tarik: ");
					double tarik = input.nextDouble();
					akunAktif.tarikTunai(tarik); //Memanggil Method
				}
				break; 
				
				
			case 4:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening!");
				} else {
					akunAktif.cekInformasi();
				}
				break;
				
			case 5:
				System.out.print("Masukan No Rekening: ");
				String cariNo = input.nextLine();
				boolean ditemukan = false;
				
				for (Rekening rekening : daftarRekening) {
					if (rekening.getNomorRekening().equals(cariNo)) {
						akunAktif = rekening;
						ditemukan = true;
						System.out.println("Akun Berhasil Diganti.");
						break;
					}
				}
				
				if (!ditemukan) {
					System.out.println("Rekening tidak ditemukan.");
				}
				break;
			case 0:
				isRunning = false;
				System.out.println("Sistem ditutup. Terima kasih!");
				break;
				
			default:
				System.out.println("Pilihan tidak valid!");
			}
		}
		input.close();
	}

}
