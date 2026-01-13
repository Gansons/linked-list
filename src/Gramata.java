import java.time.LocalDate;
import javax.swing.JOptionPane;

public class Gramata {
    private int id, skaits, lppSk;
    private String nosaukums, autors, izdevnieciba;
    private LocalDate panemts, atgriezts;
    private double cena;

    public Gramata(int id, int skaits, int lppSk, String nosaukums, String autors, String izdevnieciba, double cena) {
        this.id = id;
        this.autors = autors;
        this.cena = cena;
        this.izdevnieciba = izdevnieciba;
        this.lppSk = lppSk;
        this.nosaukums = nosaukums;
        this.skaits = skaits;
    }

    public String getNosaukums() {
        return nosaukums;
    }

    public void info() {
        JOptionPane.showMessageDialog(null,
            "ID: " + id +
            "\nNosaukums: " + nosaukums +
            "\nAutors: " + autors +
            "\nIzdevniecība: " + izdevnieciba +
            "\nLappušu skaits: " + lppSk +
            "\nCena: " + cena + " EUR" +
            "\nEksemplāru skaits: " + skaits);
    }

    public void panemtGramatu() {
        if (skaits > 0) {
            skaits--;
            panemts = LocalDate.now();
            atgriezts = panemts.plusMonths(1);
            JOptionPane.showMessageDialog(null, "Grāmata paņemta: " + panemts);
        } else {
            JOptionPane.showMessageDialog(null,
                "Grāmata nav pieejama, visi eksemplāri paņemti!",
                "Brīdinājums", JOptionPane.WARNING_MESSAGE);
        }
    }
}