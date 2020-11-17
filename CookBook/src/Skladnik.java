import java.io.Serializable;

class Skladnik implements Serializable {

	private String ilosc;
	private String nazwa;
	private static final long serialVersionUID = 336286740075600488L;

	public Skladnik(String i, String n) {
		ilosc = i;
		nazwa = n;
	}

	public String getIlosc() {
		return ilosc;
	}

	public String getNazwa() {
		return nazwa;
	}

}
