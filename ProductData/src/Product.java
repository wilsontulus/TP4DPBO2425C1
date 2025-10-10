public class Product {
    private String id;
    private String nama;
    private double harga;
    private String kategori;
    private String kelas;

    public Product(String id, String nama, double harga, String kategori, String kelas) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.kelas = kelas;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getId() {
        return this.id;
    }

    public String getNama() {
        return this.nama;
    }

    public double getHarga() {
        return this.harga;
    }

    public String getKategori() {
        return this.kategori;
    }

    public String getKelas() {
        return this.kelas;
    }
}