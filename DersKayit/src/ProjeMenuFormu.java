import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class ProjeMenuFormu extends JFrame {

    public ProjeMenuFormu() {
        setTitle("Proje Menü Formu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton kayitFormuButton = new JButton("Ders ve Öğrenci Kayıt Formu");
        kayitFormuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DersVeOgrenciKayitFormu kayitFormu = new DersVeOgrenciKayitFormu();
                kayitFormu.setVisible(true);
            }
        });

        JPanel panel = new JPanel();
        panel.add(kayitFormuButton);

        add(panel);
    }
}