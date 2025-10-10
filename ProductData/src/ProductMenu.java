import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ProductMenu extends JFrame {
    public static void main(String[] args) {
        // Buat object window
        ProductMenu menu = new ProductMenu();

        // Set ukuran window
        menu.setSize(700, 600);

        // Letakkan window di tengah layar
        menu.setLocationRelativeTo(null);

        // Isikan window
        menu.setContentPane(menu.mainPanel);

        // Ubah warna background
        menu.getContentPane().setBackground(Color.WHITE);

        // Tampilkan window
        menu.setVisible(true);

        // Ubah default agar program ikut berhenti saat window diclose
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Deklarasi index baris yang diklik
    private int selectedIndex = -1;

    // Deklarasi arraylist untuk menampung semua produk
    private ArrayList<Product> listProduct;

    // Deklarasi variabel panel
    private JPanel mainPanel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField hargaField;
    private JTable productTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox<String> kategoriComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel namaLabel;
    private JLabel hargaLabel;
    private JLabel kategoriLabel;

    // Deklarasi constructor
    public ProductMenu() {
        // inisialisasi listProduct
        listProduct = new ArrayList<>();

        // isi listProduct
        populateList();

        // isi tabel produk
        productTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] kategoriData = {"???", "Elektronik", "Makanan", "Minuman", "Pakaian", "Alat Tulis"};
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tampilkan pop-up konfirmasi penghapusan data (variabel integer)
                int confirmResult = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin untuk menghapus data?",
                        "Konfirmasi Hapus Data", JOptionPane.YES_NO_OPTION);

                // jika variabel confirmResult 0, berarti pengguna mengklik Yes (1 berarti No)
                if (confirmResult == 0) {
                    deleteData();
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = productTable.getSelectedRow();

                // simpan value textfield dan combo box
                String curId = productTable.getModel().getValueAt(selectedIndex, 1).toString();
                String curNama = productTable.getModel().getValueAt(selectedIndex, 2).toString();
                String curHarga = productTable.getModel().getValueAt(selectedIndex, 3).toString();
                String curKategori = productTable.getModel().getValueAt(selectedIndex, 4).toString();

                // ubah isi textfield dan combo box
                idField.setText(curId);
                namaField.setText(curNama);
                hargaField.setText(curHarga);
                kategoriComboBox.setSelectedItem(curKategori);

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");

                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] columns = {"No", "ID Produk", "Nama", "Harga", "Kategori"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel tempTable = new DefaultTableModel(null, columns);

        // isi tabel dengan listProduct
        for (int i = 0; i < listProduct.size(); i++) {
            Object[] newRow = { i + 1,
                    listProduct.get(i).getId(),
                    listProduct.get(i).getNama(),
                    String.format("%.2f", listProduct.get(i).getHarga()),
                    listProduct.get(i).getKategori()
            };
            tempTable.addRow(newRow);
        }

        return tempTable;
    }

    public void insertData() {
        // Gunakan error handling
        try {
            // ambil value dari textfield dan combobox
            String id = idField.getText();
            String nama = namaField.getText();
            double harga = Double.parseDouble(hargaField.getText());
            String kategori  = kategoriComboBox.getSelectedItem().toString();

            // tambahkan data ke dalam list
            listProduct.add(new Product(id, nama, harga, kategori));

            // update tabel
            productTable.setModel(setTable());

            // Bersihkan form
            clearForm();

            // feedback
            System.out.println("Insert berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan.");
        } catch (NumberFormatException errorMessage) {
            JOptionPane.showMessageDialog(null, "Harga harus berupa angka.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public void updateData() {
        // Gunakan error handling
        try {
            // ambil data dari form
            String id = idField.getText();
            String nama = namaField.getText();
            double harga = Double.parseDouble(hargaField.getText());
            String kategori  = kategoriComboBox.getSelectedItem().toString();

            // ubah data produk di list
            listProduct.get(selectedIndex).setId(id);
            listProduct.get(selectedIndex).setNama(nama);
            listProduct.get(selectedIndex).setHarga(harga);
            listProduct.get(selectedIndex).setKategori(kategori);

            // update tabel
            productTable.setModel(setTable());

            // bersihkan form
            clearForm();

            // feedback
            System.out.println("Update berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah.");
        } catch (NumberFormatException errorMessage) {
            JOptionPane.showMessageDialog(null, "Harga harus berupa angka.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public void deleteData() {
        // hapus data dari list
        listProduct.remove(selectedIndex);

        // update tabel
        productTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Delete berhasil");
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");

    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box
        idField.setText("");
        namaField.setText("");
        hargaField.setText("");
        kategoriComboBox.setSelectedIndex(0);

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;

    }

    // panggil prosedur ini untuk mengisi list produk
    private void populateList() {
        listProduct.add(new Product("P001", "Laptop Asus", 8500000.0, "Elektronik"));
        listProduct.add(new Product("P002", "Mouse Logitech", 350000.0, "Elektronik"));
        listProduct.add(new Product("P003", "Keyboard Mechanical", 750000.0, "Elektronik"));
        listProduct.add(new Product("P004", "Roti Tawar", 15000.0, "Makanan"));
        listProduct.add(new Product("P005", "Susu UHT", 12000.0, "Minuman"));
        listProduct.add(new Product("P006", "Kemeja Putih", 125000.0, "Pakaian"));
        listProduct.add(new Product("P007", "Celana Jeans", 200000.0, "Pakaian"));
        listProduct.add(new Product("P008", "Pensil 2B", 3000.0, "Alat Tulis"));
        listProduct.add(new Product("P009", "Buku Tulis", 8000.0, "Alat Tulis"));
        listProduct.add(new Product("P010", "Air Mineral", 5000.0, "Minuman"));
        listProduct.add(new Product("P011", "Smartphone Samsung", 4500000.0, "Elektronik"));
        listProduct.add(new Product("P012", "Kue Brownies", 25000.0, "Makanan"));
        listProduct.add(new Product("P013", "Jaket Hoodie", 180000.0, "Pakaian"));
        listProduct.add(new Product("P014", "Pulpen Gel", 5000.0, "Alat Tulis"));
        listProduct.add(new Product("P015", "Teh Botol", 8000.0, "Minuman"));
    }
}