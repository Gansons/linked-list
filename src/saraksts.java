import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class saraksts {
	static boolean jauEksiste(LinkedList<String> saraksts, String elements) {
		for(int i=0; i<saraksts.size(); i++) {
			if(saraksts.get(i).equalsIgnoreCase(elements)) {
			JOptionPane.showMessageDialog(null, 
					"Šāds produkts jau ir pievienots sarakstam!", "kļūda",
					JOptionPane.WARNING_MESSAGE);
			return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		String izvele, koPievienot, koAtrast, koNonemt, arKoAizstāt;
		int kurPievienot, kurNonemt, kuruMainit;
		LinkedList<String> saraksts = new LinkedList<String>();
		
		do {
			do {
				izvele = JOptionPane.showInputDialog("1 -Pievienot produktu"
						+ "\n2 -Produktu skaits"
						+ "\n3 -Izvadīt produktus"
						+ "\n4 -Atrast produktu"
						+ "\n5 -Pievienot konkrētā pozīcijā"
						+ "\n6 -Noņemt produktu"
						+ "\n7 -Noņemt pēc indeksa"
						+ "\n8 -Noņemt pirmo"
						+ "\n9 -Noņemt pēdējo"
						+ "\n10 -Mainīt produktu"
						+ "\n11 -Sakāsrtot alfabētiski"
						+ "\n12 -Nodzēst sarakstu"
						+ "\n0 -Apturēt");
				
				if(izvele == null)
					izvele = "0";
			}while(!izvele.matches("\\d+"));
			
			switch(izvele) {
			
			case  "1":
				
				do {
					
					koPievienot = JOptionPane.showInputDialog("Ievadi produktu ko pievienot:");
					
					if(koPievienot == null)
						break;
					
				}while((jauEksiste(saraksts, koPievienot) == true )|| !koPievienot.matches("^[\\p{L} ]+$"));
				
				if(koPievienot == null)
					break;
				
				saraksts.add(koPievienot.toLowerCase());
				JOptionPane.showMessageDialog(null, "Produkts veiksmigi pievienots sarakstam!");
				
				
				break;
				
				
				
			case "2":
				
				
				JOptionPane.showMessageDialog(null, "Saraksta produktu skaits ir: " + saraksts.size());
				break;
				
			case "3":
				
				if(saraksts.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Saraksts ir tukss!","Informacija",JOptionPane.INFORMATION_MESSAGE);
					}else {
						Iterator<String> izvade = saraksts.iterator();
						String str = "";
						while(izvade.hasNext()) {
							str += izvade.next() + "\n";
						}
						
						JOptionPane.showMessageDialog(null, "Saraksta produkti ir:\n" + str,"Produktu Saraksts",JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				break;
			case "4":
				do {
					koAtrast = JOptionPane.showInputDialog("Ievadi produktu ko atrast:");
					if(koAtrast == null)
						break;
					if(saraksts.contains(koAtrast.toLowerCase())) {
						JOptionPane.showMessageDialog(null, "Produkts atrasts saraksta!");
					}else {
						JOptionPane.showMessageDialog(null, "Produkts nav saraksta!");
					}
					
					
					break;
				}while(!koAtrast.matches("^[\\p{L} ]+$"));
				
				break;
				
			case "5":
				if(saraksts.size() == 0) {
					JOptionPane.showMessageDialog(null, "Nav izveidots saraksts", "Kļūda!", JOptionPane.WARNING_MESSAGE);
					break;
				}
				do {
					koPievienot = 
							JOptionPane.showInputDialog("Kādu produktu pievienot?");
					kurPievienot = 
							Integer.parseInt(JOptionPane.showInputDialog("Kurā pozīcijā pievienot?"));
					
				}while((jauEksiste(saraksts, koPievienot) == true) || !koPievienot.matches("^[\\p{L} ]+$") || 
						saraksts.size() <= kurPievienot || kurPievienot < 0);
				saraksts.add(kurPievienot, koPievienot);
				break;
				
			case "6":
				do {
					koNonemt = JOptionPane.showInputDialog("Kuru produktu noņemt?");
				}while(!koNonemt.matches("^[\\p{L} ]+$") || !jauEksiste(saraksts, koNonemt));
				JOptionPane.showMessageDialog(null, "Produkts noņemts!", "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
				break;
				
				
			case "7":
				kurNonemt = Integer.parseInt(JOptionPane.showInputDialog("Ievadi indeksa vietu kuru nonemt (no 0 lidz " + (saraksts.size()-1) + "):"));
				if(kurNonemt <0 || kurNonemt > saraksts.size()-1) {
					JOptionPane.showMessageDialog(null, "Nepareiza indeksa vieta!", "Kļūda", JOptionPane.ERROR_MESSAGE);
				}else {
					saraksts.remove(kurNonemt);
					JOptionPane.showMessageDialog(null, "Produkts veiksmigi noņemts no saraksta!", "Informacija", JOptionPane.INFORMATION_MESSAGE);
				}
				break;
				
			case "8":
				if(!saraksts.isEmpty()) {
					saraksts.removeFirst();
					JOptionPane.showMessageDialog(null, "Pirmais produkts veiksmigi noņemts no saraksta!", "Informacija", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Saraksts ir tukss!", "Kļūda", JOptionPane.ERROR_MESSAGE);
				}
				break;
				
				case "9":
					if(!saraksts.isEmpty()) {
						saraksts.removeLast();
						JOptionPane.showMessageDialog(null, "Pedejais produkts veiksmigi noņemts no saraksta!", "Informacija", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Saraksts ir tukss!", "Kļūda", JOptionPane.ERROR_MESSAGE);
					}
					break;
					
					case "10":
						
						do {
							
							kuruMainit = Integer.parseInt(JOptionPane.showInputDialog("Ievadi indeksa vietu kuru mainit (no 0 lidz " + (saraksts.size()-1) + "):"));
							arKoAizstāt = JOptionPane.showInputDialog("Ievadi produktu ar ko aizstatit:");
							if(kuruMainit <0 || kuruMainit > saraksts.size()-1) {
								JOptionPane.showMessageDialog(null, "Nepareiza indeksa vieta!", "Kļūda", JOptionPane.ERROR_MESSAGE);
								}else {
									saraksts.set(kuruMainit, arKoAizstāt.toLowerCase());
									JOptionPane.showMessageDialog(null, "Produkts veiksmigi aizstatits!", "Informacija", JOptionPane.INFORMATION_MESSAGE);
								}
							
								break;
						}while(!arKoAizstāt.matches("^[\\p{L} ]+$"));
						
						break;
						
						case "11":
							saraksts.sort(String.CASE_INSENSITIVE_ORDER);
							JOptionPane.showMessageDialog(null, "Saraksts veiksmigi sakartots aflabetiski!", "Informacija", JOptionPane.INFORMATION_MESSAGE);
							break;
							
						case "12":
								saraksts.clear();
								JOptionPane.showMessageDialog(null, "Saraksts veiksmigi nodzests!", "Informacija", JOptionPane.INFORMATION_MESSAGE);
								break;
							
			}
			
		
			
		}while(!izvele.equals("0"));

	}

}
