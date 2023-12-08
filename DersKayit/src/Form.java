import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Form extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ProjeMenuFormu projeMenuFormu = new ProjeMenuFormu();
                projeMenuFormu.setVisible(true);
            }
        });
    }
}

class DersVeOgrenciKayitFormu extends JFrame {
    private JTextField dersKoduField;
    private JTextField dersAdField;
    private JTextField dersDonemField;

    private JTextField ogrenciNoField;
    private JTextField ogrenciAdField;
    private JTextField ogrenciSoyadField;
    private JTextField ogrenciBolumField;

    private JComboBox<String> derslerComboBox;

    public DersVeOgrenciKayitFormu() {
        setTitle("Ders ve Öğrenci Kayıt Formu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        dersKoduField = new JTextField(20);
        dersAdField = new JTextField(20);
        dersDonemField = new JTextField(20);

        ogrenciNoField = new JTextField(20);
        ogrenciAdField = new JTextField(20);
        ogrenciSoyadField = new JTextField(20);
        ogrenciBolumField = new JTextField(20);

        derslerComboBox = new JComboBox<>(getSavedDersler());

        JButton dersKaydetButton = new JButton("Ders Kaydet");
        dersKaydetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ders ders = new Ders(
                        dersKoduField.getText(),
                        dersAdField.getText(),
                        dersDonemField.getText()
                );
                saveToJSON(ders, "dersler.json");
            }
        });

        JButton ogrenciKaydetButton = new JButton("Öğrenci Kaydet");
        ogrenciKaydetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ogrenci ogrenci = new Ogrenci(
                        ogrenciNoField.getText(),
                        ogrenciAdField.getText(),
                        ogrenciSoyadField.getText(),
                        ogrenciBolumField.getText(),
                        (String) derslerComboBox.getSelectedItem()
                );
                saveToJSON(ogrenci, "ogrenciler.json");
            }
        });

        JPanel panel = new JPanel(new GridLayout(10, 2));
        panel.add(new JLabel("Ders Kodu:"));
        panel.add(dersKoduField);
        panel.add(new JLabel("Ders Adı:"));
        panel.add(dersAdField);
        panel.add(new JLabel("Ders Dönemi:"));
        panel.add(dersDonemField);

        panel.add(new JLabel("Öğrenci No:"));
        panel.add(ogrenciNoField);
        panel.add(new JLabel("Öğrenci Adı:"));
        panel.add(ogrenciAdField);
        panel.add(new JLabel("Öğrenci Soyadı:"));
        panel.add(ogrenciSoyadField);
        panel.add(new JLabel("Öğrenci Bölüm:"));
        panel.add(ogrenciBolumField);

        panel.add(new JLabel("Dersler:"));
        panel.add(derslerComboBox);

        panel.add(dersKaydetButton);
        panel.add(ogrenciKaydetButton);

        add(panel);
    }

    private String[] getSavedDersler() {
        return new String[]{"Matematik", "Fizik", "Diferansiyel Denklemler"};
    }

    private void saveToJSON(Object obj, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            String jsonString = createJSONString(obj);
            fileWriter.write(jsonString);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String createJSONString(Object obj) {
        if (obj instanceof Ders) {
            Ders ders = (Ders) obj;
            return "{"
                    + "\"dersKodu\":\"" + ders.getDersKodu() + "\","
                    + "\"dersAd\":\"" + ders.getDersAd() + "\","
                    + "\"dersDonem\":\"" + ders.getDersDonem() + "\""
                    + "}";
        } else if (obj instanceof Ogrenci) {
            Ogrenci ogrenci = (Ogrenci) obj;
            return "{"
                    + "\"ogrenciNo\":\"" + ogrenci.getOgrenciNo() + "\","
                    + "\"ogrenciAd\":\"" + ogrenci.getOgrenciAd() + "\","
                    + "\"ogrenciSoyad\":\"" + ogrenci.getOgrenciSoyad() + "\","
                    + "\"ogrenciBolum\":\"" + ogrenci.getOgrenciBolum() + "\","
                    + "\"ogrenciDersler\":\"" + ogrenci.getOgrenciDersler() + "\""
                    + "}";
        } else {
            return "{}";
        }
    }

    class Ogrenci {
        private String ogrenciNo;
        private String ogrenciAd;
        private String ogrenciSoyad;
        private String ogrenciBolum;
        private String ogrenciDersler;

        public Ogrenci(String ogrenciNo, String ogrenciAd, String ogrenciSoyad, String ogrenciBolum, String ogrenciDersler) {
            this.ogrenciNo = ogrenciNo;
            this.ogrenciAd = ogrenciAd;
            this.ogrenciSoyad = ogrenciSoyad;
            this.ogrenciBolum = ogrenciBolum;
            this.ogrenciDersler = ogrenciDersler;
        }

        public String getOgrenciNo() {
            return ogrenciNo;
        }

        public void setOgrenciNo(String ogrenciNo) {
            this.ogrenciNo = ogrenciNo;
        }

        public String getOgrenciAd() {
            return ogrenciAd;
        }

        public void setOgrenciAd(String ogrenciAd) {
            this.ogrenciAd = ogrenciAd;
        }

        public String getOgrenciSoyad() {
            return ogrenciSoyad;
        }

        public void setOgrenciSoyad(String ogrenciSoyad) {
            this.ogrenciSoyad = ogrenciSoyad;
        }

        public String getOgrenciBolum() {
            return ogrenciBolum;
        }

        public void setOgrenciBolum(String ogrenciBolum) {
            this.ogrenciBolum = ogrenciBolum;
        }

        public String getOgrenciDersler() {
            return ogrenciDersler;
        }

        public void setOgrenciDersler(String ogrenciDersler) {
            this.ogrenciDersler = ogrenciDersler;
        }

    }

    class Ders {
        private String dersKodu;
        private String dersAd;
        private String dersDonem;


        public Ders(String dersKodu, String dersAd, String dersDonem) {
            this.dersKodu = dersKodu;
            this.dersAd = dersAd;
            this.dersDonem = dersDonem;
        }

        public String getDersKodu() {
            return dersKodu;
        }

        public void setDersKodu(String dersKodu) {
            this.dersKodu = dersKodu;
        }

        public String getDersAd() {
            return dersAd;
        }

        public void setDersAd(String dersAd) {
            this.dersAd = dersAd;
        }

        public String getDersDonem() {
            return dersDonem;
        }

        public void setDersDonem(String dersDonem) {
            this.dersDonem = dersDonem;
        }
    }
}
