# Tugas Praktikum DPBO #4

Dibuat untuk menyelesaikan TP4 Desain Pemrograman Berorientasi Objek (DPBO)

## Janji

Saya Willsoon Tulus Parluhutan Simanjuntak dengan NIM 2404756 mengerjakan evaluasi Tugas Praktikum 4 dalam mata kuliah Desain Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. 

Aamiin.

## Desain program

Diagram UML:

![UML Diagram](Dokumentasi/uml_diagram.svg)

Desain Swing GUI:

![Desain Swing GUI](Dokumentasi/gui_design.png)

## Penjelasan Alur jalan program

Program ini merupakan program pengelolaan stok produk dengan fungsi CRUD yang menggunakan Swing UI sebagai antarmuka pengoperasiannya, serta diawali dengan berbagai data dummy yang telah terisi sejak program dijalankan.

Saat membuka program, maka akan tampil sebuah menu atribut produk, tombol-tombol aksi Create/Update/Delete, serta tabel berisi produk-produk yang telah dimasukkan. Program ini memiliki fungsi sebagai berikut:

1. Penambahan / input data baru
2. Perubahan / update data yang sudah ada
3. Penghapusan / delete data yang sudah ada
4. Tampilan data yang ada dalam bentuk tabel

Untuk melakukan input data: 
1. Pastikan tombol Add tersedia dan tidak ada tombol Update (jika ada, klik Cancel)
2. Masukkan seluruh input yang diminta pada kolom, combo box, dan slider pada menu Data Produk
3. Tekan tombol Add jika sudah yakin agar data baru bisa ditambahkan

Untuk melakukan update data:

1. Pilih data yang ingin di update pada tabel
2. Kolom-kolom input data akan terisi dengan data dari baris yang dipilih
3. Ubah kolom atribut yang ingin diubah
4. Klik tombol Update untuk meng-update data dengan atribut baru yang diisi

Untuk melakukan delete data: 

1. Pilih data yang ingin di hapus
2. Klik tombol Delete
3. Akan ada pesan konfirmasi bahwa data yang dihapus tidak dapat dikembalikan 
4. Klik tombol 'Yes' jika Anda yakin, jika tidak, Anda bisa mengklik tombol 'No'.

## Preview operasional program

_Catatan: Java telah dikonfigurasi untuk menggunakan integrasi GTK yang menyesuaikan tema dari GUI yang ditampilkan._

![Preview Operasional Swing](Dokumentasi/preview.webp)
