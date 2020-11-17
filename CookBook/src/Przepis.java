import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Przepis implements Serializable {

	private String nazwa;
	private Kategoria kategoria;
	private List<Skladnik> skladniki;
	private String opis;
	private static final long serialVersionUID = -7941150698584901936L;

	public Przepis(String nazwa, Kategoria kategoria) {
		this.nazwa = nazwa;
		this.kategoria = kategoria;
		skladniki = new ArrayList<>();
	}

	@Override
	public String toString() {
//		return "Przepis {nazwa=" + nazwa + ", kategoria=" + kategoria + "}";
		return nazwa + "\n"
			+ "kategoria: " + kategoria + "\n\n"
			+ "składniki:\n"
			+ wypisz(skladniki) + "\n"
			+ "opis:\n"
			+ opis + "\n";
	}

	private String wypisz(List<Skladnik> s) {
		String str = "";
		for (int i = 0; i < s.size(); i++) {
			str += " - " + s.get(i).getIlosc() + " " + s.get(i).getNazwa() + "\n";
		}
		return str;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void dodajSkladnik(String i, String n) {
		skladniki.add(new Skladnik(i, n));
	}

	public void dodajOpis(String przepis) {
		opis = przepis;
	}

	public Kategoria getKategoria() {
		return kategoria;
	}

	public boolean compareSkladniki(String[] as) {
		boolean result;
		for (String s : as) {
			result = false;
			for (Skladnik skladnik : skladniki)
				if (s.compareToIgnoreCase(skladnik.getNazwa()) == 0) {
					result = true;
					break;
				}
			if (result == false) return false;
		}
		return true;
	}

}
