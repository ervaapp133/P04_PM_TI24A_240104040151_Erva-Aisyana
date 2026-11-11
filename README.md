# WargaHub P04 - Praktikum Pemrograman Mobile (Lengkap dengan Pengayaan)

**WargaHub** adalah aplikasi pelaporan masalah lingkungan berbasis Android yang dibangun sebagai bagian dari tugas Praktikum 04 Pemrograman Mobile.

---

## 👤 Informasi Mahasiswa
* **Nama:** Erva Aisyana
* **NIM:** 240104040151
* **Program Studi:** Teknologi Informasi
* **Institusi:** Universitas Islam Negeri Antasari Banjarmasin

---

## 🚀 Fitur Ekstra (Pengayaan) yang Diimplementasikan
Sesuai dengan instruksi *Pengayaan Tingkat Menengah* di Modul:
1. **Pencarian Lokal (Search):** Terdapat kolom `EditText` di bagian atas daftar untuk memfilter laporan berdasarkan judul secara *real-time*.
2. **Perbedaan Status Kosong:** Aplikasi membedakan secara visual antara daftar yang memang kosong (tombol *Uji Daftar Kosong*) dan data yang *tidak ditemukan* karena pencarian.
3. **Penanda Laporan Terpilih:** Saat laporan diklik dan kembali dari *Detail*, item pada *RecyclerView* akan ditandai dengan warna *background* yang berbeda. Status terpilih ini tetap bertahan (*state restoration*) meskipun layar diputar (*rotate*).
4. **Tema Gelap (*Dark Mode*):** Warna disesuaikan khusus untuk *Night Mode* agar kontras tetap nyaman di mata (menggunakan `values-night/colors.xml`).

## Cara Menjalankan
1. Ekstrak dan buka folder `WargaHubP04` di Android Studio.
2. Lakukan Gradle Sync.
3. Jalankan aplikasi menggunakan tombol *Run* pada emulator atau perangkat Android Anda.
