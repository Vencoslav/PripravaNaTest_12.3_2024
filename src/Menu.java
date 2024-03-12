import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Menu extends JFrame{
    private JTextField textField2;
    private JCheckBox checkBox1;
    private JTextField textField1;
    private JPanel panelMain;
    private File selectedFile;

    public Menu(){
        setSize(300,200);
        setTitle("Menu");
        setContentPane(panelMain);

        initMenu();
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
}
