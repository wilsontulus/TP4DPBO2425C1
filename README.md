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

Program mendukung pengelolaan data produk toko yang berupa ID produk, nama produk, harga produk, kategori produk, serta kelas / tier dari produk. Program ini memiliki fungsi seperti:

1. Penambahan / input data baru
2. Perubahan / update data yang sudah ada
3. Penghapusan / delete data yang sudah ada
4. Tampilan data yang ada dalam bentuk tabel

Untuk melakukan input data, Anda perlu memasukkan seluruh input yang diminta pada kolom, combo box, dan slider pada menu Data Produk, hingga menekan tombol Add jika sudah yakin agar data baru bisa ditambahkan.

Untuk melakukan update data, pilih data yang ingin di update pada tabel, dan kolom-kolom input data akan terisi dengan data dari baris yang dipilih. Ubah kolom atribut yang ingin diubah, lalu klik tombol Update untuk meng-update data dengan atribut baru yang diisi.

Untuk melakukan delete data, pilih data yang ingin di hapus, lalu klik Delete. Akan ada pesan konfirmasi bahwa data yang dihapus tidak dapat dikembalikan, jika Anda yakin, klik tombol 'Yes'. Anda tetap bisa mencegah penghapusan dengan mengklik tombol 'No'.

## Preview operasional program

_Catatan: Java telah dikonfigurasi untuk menggunakan integrasi GTK yang menyesuaikan tema dari GUI yang ditampilkan._

![Preview Operasional Swing](Dokumentasi/preview.webp)
