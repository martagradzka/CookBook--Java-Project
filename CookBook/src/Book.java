import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**	Project: CookBook
 * 	Projekt książki kucharskiej na zaliczenie z przedmiotu Programowanie Obiektowe (UPH dr J.Skaruz)
 */

/**
 * @author Admin
 *
 */

class Book implements Serializable {

	private String tytul;
	private List<Przepis> przepisy;

	private static Book cookbook;
	private static Scanner scan = new Scanner(System.in);
	private static final long serialVersionUID = -1816600989530895287L;

	public Book(String tytuł) {
		tytul = tytuł;
		przepisy = new ArrayList<>();
	}

	public void dodajPrzepis(Przepis przepis) {
		przepisy.add(przepis);
	}

	@Override
	public String toString() {
//		return "Book {list=" + list + "}";
		return "Książka kucharska \"" + tytul + "\"\n"
			+  " - aktualna ilość przepisów: " + przepisy.size() + "\n\n"
//			+  list;
			+ wypisz(przepisy);
	}

	private String wypisz(List<Przepis> l) {
		String s = "";

		for (int i = 0; i < l.size(); i++) {
			s += "#"+ (i+1) + ": " + l.get(i) + "\n";
		}
		return s;
	}

	private String toString2() {
		return "Książka kucharska \"" + tytul + "\"\n"
				+  " - aktualna ilość przepisów: " + przepisy.size() + "\n\n"
				+ wypisz2();
	}

	private String wypisz2() {
		String s = "";

		for (int i = 0; i < przepisy.size(); i++)
			s += "#"+ (i+1) + ": " + przepisy.get(i).getNazwa() + "\n";
		return s;
	}

	public static void main(String[] args) {
//		Book cookbook = new Book("Praktyczne przepisy na kaca");
//		cookbook = new Book("Praktyczne przepisy na kaca");

		Przepis p;
		p = new Przepis("Jajecznica", new Kategoria("śniadania"));
		p.dodajSkladnik("3", "jaja");
		p.dodajSkladnik("10 dkg", "wędzonego boczku");
		p.dodajSkladnik("2 łyżki", "oleju");
		p.dodajOpis("Jaja umyć, sparzyć, obrać ze skórki, posiekać ze szczypiorkiem w drobną kostkę.\nUsmażyć. Smacznego.");
//		cookbook.dodajPrzepis(p);

		p = new Przepis("Devolay a'la Biedronka", new Kategoria("obiady"));
		p.dodajSkladnik("1", "wizyta w Biedronce");
		p.dodajSkladnik("4", "ziemniaczki");
		p.dodajOpis("Postępuj wg przepisu na etykiecie opakowania.\nBon apetit ;-)");
//		cookbook.dodajPrzepis(p);

//		System.out.println(cookbook);		//	Book@15db9742

		menu();
//		test();
	}

	private static void readBook() {
		ObjectInputStream file;

		try {
			file = new ObjectInputStream(new FileInputStream("CookBook.data"));
			cookbook = (Book) file.readObject();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void saveBook() {
		ObjectOutputStream file;

		try {
			file = new ObjectOutputStream(new FileOutputStream("CookBook.data"));
			file.writeObject(cookbook);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void showBook() {
		System.out.print(cookbook.toString2());
	}

	private static void showMenu() {
		System.err.println("Program e-książka kucharska");
		System.out.println(" === Main menu: ===");
		System.out.println(" 1 - pokaż menu");
		System.out.println(" 2 - listuj książkę");
		System.out.println(" 3 - pokaż przepis");
		System.out.println(" 4 - dodaj przepis");
		System.out.println(" 5 - zmodyfikuj przepis *");
		System.out.println(" 6 - usuń przepis");
		System.out.println(" 7 - filtruj po nazwie");
		System.out.println(" 8 - filtruj po składnikach");
		System.out.println(" 9 - filtruj po kategorii");
		System.out.println(" 0 - wyjście z programu");
	}

	private static void menu() {
//		Scanner scan = new Scanner(System.in);
		boolean exit = false;

		readBook();
		System.out.print(cookbook);
		showMenu();
                

		do {
			System.out.print("\nWybierz operację: ");
			switch (scan.nextInt()) {
			case 0:
				exit = true;
				break;
			case 1:
				showMenu();
				break;
			case 2:
				showBook();
				break;
			case 3:
				pokazPrzepis();
				break;
			case 4:
				dodajPrzepis();
				break;
			case 5:
				zmodyfikujPrzepis();
				break;
			case 6:
				usunPrzepis();
				break;
			case 7:
				filtrujPoNazwie();
				break;
			case 8:
				filtrujPoSkladnikach();
				break;
			case 9:
				filtrujPoKategorii();
				break;
			default:
				break;
			}
			
		} while (!exit);

		scan.close();
		System.out.println(cookbook);
		System.out.flush();
		System.err.println("Dziękujemy na użycie naszego programu");
		saveBook();
	}

	private static void zmodyfikujPrzepis() {
		// TODO Auto-generated method stub
		
	}

	private static void pokazPrzepis() {
		System.out.print("Podaj nr przepisu: ");
//		Scanner scan = new Scanner(System.in);
		System.out.println(cookbook.przepisy.get(scan.nextInt()-1));
//		scan.close();
	}

	private static void usunPrzepis() {
		System.out.print("Podaj nr przepisu: ");
		cookbook.przepisy.remove(scan.nextInt()-1);
	}

	private static void dodajPrzepis() {
//		Scanner scan = new Scanner(System.in);
		scan.nextLine();	//	czyszczenie zabłąkanego <Enter'a>

		System.out.print("Podaj nazwę przepisu: ");
		String nazwa = scan.nextLine();

		System.out.print("Podaj kategorię przepisu: ");
		String kategoria = scan.nextLine();

		Przepis p = new Przepis(nazwa, new Kategoria(kategoria));

		int i = 1;
		String ilosc;
		do {
			System.out.print("Podaj ilość składnika " + i + ": ");
			ilosc = scan.nextLine();
			System.out.print("Podaj nazwę składnika " + i + ": ");
			nazwa = scan.nextLine();
			if (ilosc.isEmpty()) break;
			p.dodajSkladnik(ilosc, nazwa);
			i++;
		} while (!ilosc.isEmpty());
		System.out.print("Podaj opis przepisu: ");
		String opis = scan.nextLine();
		p.dodajOpis(opis);
		System.out.println(p);
		cookbook.dodajPrzepis(p);
//		scan.close();
	}

	private static void filtrujPoNazwie() {
		System.out.print("Podaj frazę z nazwy przepisu: ");
		scan.nextLine();	//	czyszczenie zabłąkanego <Enter'a>
		String fraza = scan.nextLine();
//		System.out.println("'" + fraza + "'");
		for (Przepis p: cookbook.przepisy) {
//			System.out.println(p.getNazwa());
//			if (fraza.equals(p.getNazwa())) p.getNazwa();
			if (fraza.compareToIgnoreCase(p.getNazwa()) == 0)
				System.out.println(p.getNazwa());
		}
	}

	private static void filtrujPoKategorii() {
		System.out.print("Podaj frazę z kategorii przepisu: ");
		scan.nextLine();	//	czyszczenie zabłąkanego <Enter'a>
		String fraza = scan.nextLine();
		for (Przepis p: cookbook.przepisy) {
			if (fraza.compareToIgnoreCase(p.getKategoria().getNazwa()) == 0)
				System.out.println(p.getNazwa());
		}
	}

	private static void filtrujPoSkladnikach() {
		System.out.print("Podaj składnik(i): ");
		scan.nextLine();	//	czyszczenie zabłąkanego <Enter'a>
		String str = scan.nextLine();
		String as[] = str.split(" ");
//		for (String s : as)
//			System.out.println(s);
		for (Przepis p: cookbook.przepisy) {
			if (p.compareSkladniki(as))
				System.out.println(p.getNazwa());
		}
	}

	private static void test() {
		// test druku double'a:
//		double d = 2;
//		System.out.println(d);
//		d = .5;
//		System.out.println(d);

//		Scanner scan = new Scanner(System.in);
//		String s = scan.nextLine();
//		System.out.println(s);

		FileWriter fw;
		try {
			fw = new FileWriter("kategoria.txt");
//			fw.write(new Kategoria("kategoria\n piwa").toString());
//			fw.write(p.toString());
			fw.write(cookbook.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("przepis.txt");
			fos.write(61);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("Przepis.txt"));
//			oos.writeObject(p);
			oos.writeObject(cookbook);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream("Przepis.txt"));
//			p = (Przepis) ois.readObject();
			cookbook = (Book) ois.readObject();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
//		System.out.println(p);
	}

}

//	https://www.baeldung.com/string/split
//	http://www.jkozak.pl/przedmioty/podstawy-i-jezyki-programowania/materialy-do-cwiczen/zapisywanie-dopisywanie-i-odczytywanie-pojedynczych-obiektow-do-pliku-oraz-odczytywanie-wielu-obiektow-z-pliku-2/
