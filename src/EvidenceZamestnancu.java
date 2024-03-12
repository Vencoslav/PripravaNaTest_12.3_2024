import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EvidenceZamestnancu {
    private final List<Zamestnanec> seznam = new ArrayList<>();

    public List<Zamestnanec> getSeznam() {
        return seznam;
    }
    String nazevSouboru = "C:\\Users\\xdole\\OneDrive\\Plocha\\IntelliJ IDEA\\PripravaNaTest_12.3_2024\\src\\vstup";

    public void nactiEvidenci() {
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(nazevSouboru)))) {
            while (sc.hasNextLine()) {
                String radek = sc.nextLine();
                if (radek.isEmpty()) { // Přeskakuje prázdné řádky
                    continue;
                }
                String[] rozdelovac = radek.split(":");
                if (rozdelovac.length != 4) {
                    throw new RuntimeException("Špatný počet prvků na řádku");
                }
                String jmeno = rozdelovac[0].trim();
                String prijmeni = rozdelovac[1].trim();
                boolean pojisteni = Boolean.parseBoolean(rozdelovac[2].trim());
                LocalDate datum = LocalDate.parse(rozdelovac[3].trim(), DateTimeFormatter.ofPattern("d.M.yyyy"));
                Zamestnanec zamestnanec = new Zamestnanec(jmeno, prijmeni, pojisteni, datum);
                seznam.add(zamestnanec);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Soubor: " + nazevSouboru + " nebyl nalezen.");
        }
    }
}
