import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
        menu.setSize(700, 650);

        // Letakkan window di tengah layar
        menu.setLocationRelativeTo(null);

        // Isikan window
        menu.setContentPane(menu.mainPanel);

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
    private JSlider kelasSlider;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel namaLabel;
    private JLabel hargaLabel;
    private JLabel kategoriLabel;
    private JLabel kelasLabel;
    private JLabel kelasSliderIndicator;

    // Deklarasi constructor
    public ProductMenu() {
        // inisialisasi listProduct
        listProduct = new ArrayList<>();

        // Isi listProduct
        populateList();

        // Isi tabel produk
        productTable.setModel(setTable());

        // Ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // Atur isi combo box
        String[] kategoriData = {"<Pilih kategori>", "Laptop", "PC", "Peripheral", "Aksesoris", "Penyimpanan"};
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        // Atur posisi slider
        String[] kelasData = {"Low-end", "Mid-range", "High-end"};
        kelasSlider.setValue(1);

        // Tampilkan indikator pada slider
        kelasSlider.setPaintTicks(true);
        kelasSlider.setMajorTickSpacing(3);
        kelasSlider.setMinorTickSpacing(1);

        // Buat listener untuk slider
        kelasSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                kelasSliderIndicator.setText(kelasData[kelasSlider.getValue()]);
            }
        });

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
                        "Konfirmasi Hapus Data", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

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
                String curKelas = productTable.getModel().getValueAt(selectedIndex, 5).toString();

                // ubah isi textfield dan combo box
                idField.setText(curId);
                namaField.setText(curNama);
                hargaField.setText(curHarga);
                kategoriComboBox.setSelectedItem(curKategori);

                for (int i = 0; i < kelasData.length; i++) {
                    if (kelasData[i].matches(curKelas)) {
                        kelasSlider.setValue(i);
                    }
                }

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");

                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] columns = {"No", "ID Produk", "Nama", "Harga", "Kategori", "Kelas"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel tempTable = new DefaultTableModel(null, columns);

        // isi tabel dengan listProduct
        for (int i = 0; i < listProduct.size(); i++) {
            Object[] newRow = { i + 1,
                    listProduct.get(i).getId(),
                    listProduct.get(i).getNama(),
                    String.format("%.2f", listProduct.get(i).getHarga()),
                    listProduct.get(i).getKategori(),
                    listProduct.get(i).getKelas()
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
            String kelas = kelasSliderIndicator.getText();

            // tambahkan data ke dalam list
            listProduct.add(new Product(id, nama, harga, kategori, kelas));

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
            String kelas = kelasSliderIndicator.getText();

            // ubah data produk di list
            listProduct.get(selectedIndex).setId(id);
            listProduct.get(selectedIndex).setNama(nama);
            listProduct.get(selectedIndex).setHarga(harga);
            listProduct.get(selectedIndex).setKategori(kategori);
            listProduct.get(selectedIndex).setKelas(kelas);

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
        // kosongkan semua texfield, combo box, dan slider
        idField.setText("");
        namaField.setText("");
        hargaField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        kelasSlider.setValue(1);

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }

    // Isi list produk dengan dummy data
    private void populateList() {
        listProduct.add(new Product("P00" + (listProduct.size()+1), "Asus Vivobook", 8500000.0, "Laptop", "Mid-range"));
        listProduct.add(new Product("P00" + (listProduct.size()+1), "Mouse Logitech M100", 150000.0, "Peripheral", "Low-end"));
        listProduct.add(new Product("P00" + (listProduct.size()+1), "Keyboard Cherry KMX", 750000.0, "Peripheral", "Mid-range"));
    }
}