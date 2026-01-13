import java.util.LinkedList;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Biblioteka {

    static String virknesParbaude(String zinojums, String nokl) {
        String ievade;
        do {
            ievade = JOptionPane.showInputDialog(null, zinojums, nokl);
            if (ievade == null)
                return null;
            ievade = ievade.trim();
        } while (!Pattern.matches("^[\\p{L} ]+$", ievade));
        return ievade;
    }

    static double skaitlaParbaude(String zinojums, int tips) {
        String ievade;
        double skaitlis;
        while (true) {
            ievade = JOptionPane.showInputDialog(null, zinojums,
                    "Skaitļa ievade", JOptionPane.INFORMATION_MESSAGE);

            if (ievade == null)
                return -1;

            try {
                skaitlis = Double.parseDouble(ievade);
                if (skaitlis < 0.1) {
                    JOptionPane.showMessageDialog(null,
                            "Ievadīts negatīvs skaitlis!",
                            "Nekorekti dati",
                            JOptionPane.WARNING_MESSAGE);
                }
                return skaitlis;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Ievadīts nekorekts datu tips",
                        "Kļūda",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    static int meklet(LinkedList<Gramata> saraksts, String nosaukums) {
        for (int i = 0; i < saraksts.size(); i++) {
            if (saraksts.get(i).getNosaukums().equalsIgnoreCase(nosaukums)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String nosaukums, autors, izdevnieciba, izvelne;
        int id = 1;
        int indekss;
        int lppSk;
        int skaits;
        double cena;

        LinkedList<Gramata> plaukts = new LinkedList<>();
        String[] darbibas = {"Pievienot grāmatu", "Noņemt grāmatu",
                "Apskatīt grāmatu", "Iznomāt grāmatu", "Apturēt"};

        do {
            izvelne = (String) JOptionPane.showInputDialog(null,
                    "Izvēlies darbību", "Darbību saraksts",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    darbibas, darbibas[0]);

            if (izvelne == null)
                izvelne = "Apturēt";

            switch (izvelne) {
                case "Pievienot grāmatu":
                    nosaukums = virknesParbaude("Ieraksti grāmatas nosaukumu", "Zaļā pasaka");
                    if (nosaukums == null) break;

                    autors = virknesParbaude("Ieraksti grāmatas autoru", "Kārlis Pūslis");
                    if (autors == null) break;

                    izdevnieciba = virknesParbaude("Norādi grāmatas izdevniecību", "Intera Privāt skola");
                    if (izdevnieciba == null) break;

                    skaits = (int) skaitlaParbaude("Norādi eksemplāru skaitu", 1);
                    if (skaits == -1) break;

                    cena = skaitlaParbaude("Norādi cenu", 1);
                    if (cena == -1.0) break;

                    lppSk = (int) skaitlaParbaude("Norādi lappušu skaitu", 1);

                    plaukts.add(new Gramata(id++, skaits, lppSk, nosaukums, autors, izdevnieciba, cena));
                    break;

                case "Noņemt grāmatu":
                    if (plaukts.isEmpty())
                        JOptionPane.showMessageDialog(null, "Nav plauktā neviena grāmata!",
                                "Brīdinājums", JOptionPane.WARNING_MESSAGE);
                    else {
                        nosaukums = virknesParbaude("Kā sauc grāmatu, kuru vēlies noņemt?", "Baltā grāmata");
                        if (nosaukums == null) break;

                        indekss = meklet(plaukts, nosaukums);
                        if (indekss == -1)
                            JOptionPane.showMessageDialog(null,
                                    "Meklētā grāmata nemaz plauktā neatrodas!", "Brīdinājums", JOptionPane.WARNING_MESSAGE);
                        else {
                            plaukts.remove(indekss);
                            JOptionPane.showMessageDialog(null, "Grāmata dzēsta!", "Paziņojums",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    break;

                case "Apskatīt grāmatu":
                    if (plaukts.isEmpty()) {
                       JOptionPane.showMessageDialog(null, "Plaukts ir tukšs!", "Kļūda", JOptionPane.ERROR_MESSAGE);
                    break;
                    }else {
                    	nosaukums = virknesParbaude("Ievadi grāmatas nosaukumu ko vēlies apskatīt", "Black grāmata");
                    	indekss = meklet(plaukts, nosaukums);
                    	if(indekss == -1) {
                    		JOptionPane.showMessageDialog(null, "Grāmata nav plauktā!", "kļūda!",JOptionPane.ERROR_MESSAGE);
                    	}else {
                    		plaukts.get(indekss).info();
                    	}
                    }
                    break;

                case "Iznomāt grāmatu":
                    if (plaukts.isEmpty())
                        JOptionPane.showMessageDialog(null,
                                "Nav plauktā neviena grāmata!", "Brīdinājums", JOptionPane.WARNING_MESSAGE);
                    else {
                        nosaukums = virknesParbaude("Kuru grāmatu vēlies iznomāt?", "Baltā grāmata");
                        if (nosaukums == null) break;

                        indekss = meklet(plaukts, nosaukums);
                        if (indekss == -1)
                            JOptionPane.showMessageDialog(null, "Šāda grāmata nav atrasta!", "Brīdinājums", JOptionPane.WARNING_MESSAGE);
                        else
                            plaukts.get(indekss).panemtGramatu();
                    }
                    break;

                case "Apturēt":
                    JOptionPane.showMessageDialog(null, "Programma tika apturēta!",
                            "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        } while (!izvelne.equals("Apturēt"));
    }
}