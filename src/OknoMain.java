import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class OknoMain extends JFrame {
    private JButton predchoziButton;
    private JButton dalsiButton;
    private JTextField txt2;
    private JTextField txt1;
    private JCheckBox checkBox1;
    private JTextField txt3;
    private JPanel panelMain;
    private File selectedFile;
    private int indexAktualniZamestnanec = 0;
    private final EvidenceZamestnancu evidenceZamestnancu;



    public OknoMain(){
        setSize(300,200);
        setTitle("Menu");
        setContentPane(panelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initMenu();

        evidenceZamestnancu = new EvidenceZamestnancu();
        evidenceZamestnancu.nactiEvidenci();
        

        predchoziButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indexAktualniZamestnanec > 0) {
                    indexAktualniZamestnanec--;
                    zobrazZamestnance();
                }
            }
        });

        dalsiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indexAktualniZamestnanec < evidenceZamestnancu.getSeznam().size() - 1) {
                    indexAktualniZamestnanec++;
                    zobrazZamestnance();
                }
            }
        });

        checkBox1.addActionListener(e -> {
            zobrazZamestnance();
        });

        zobrazZamestnance();
    }
    public void zobrazZamestnance(){
        Zamestnanec zamestnanec = evidenceZamestnancu.getSeznam().get(indexAktualniZamestnanec);
        txt1.setText(zamestnanec.getJmeno());
        txt2.setText(zamestnanec.getPrijmeni());
        txt3.setText(String.valueOf(zamestnanec.getDatum()));
        checkBox1.setSelected(zamestnanec.getPojisteni() == true);
    }
    public void chooser(){
        JFileChooser fc = new JFileChooser(".");
//        fc.setFileFilter(new FileNameExtensionFilter("Project files", "txt")); //Filter který ti nastaví aby šlo pouze otvírat soubory s příponou txt
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {//Kontroluje, zda uživatel vybral soubor
            selectedFile = fc.getSelectedFile();
            StringBuilder content = new StringBuilder();
            try (Scanner sc = new Scanner(new BufferedReader(new FileReader(selectedFile)))) {
                while (sc.hasNextLine()) {
                    content.append(sc.nextLine()).append("\n"); // \ = podrž alt + 9 a 2
                }
                JOptionPane.showMessageDialog(this,content);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "File not found: " + e.getLocalizedMessage());
            }
        }
    }
    public void initMenu(){
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu soubor = new JMenu("Soubor");
        menubar.add(soubor);

        JMenuItem nacti = new JMenuItem("Načti");
        soubor.add(nacti);
        nacti.addActionListener(e->{
            chooser();
        });
    }

    public static void main(String[] args) {
        OknoMain main = new OknoMain();
        main.setVisible(true);
    }
}

