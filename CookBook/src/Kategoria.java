import java.io.Serializable;

class Kategoria implements Serializable {

	private String nazwa;
	private static final long serialVersionUID = 8349144020225863412L;

	public Kategoria(String nazwa) {
		this.nazwa = nazwa;
	}

	@Override
	public String toString() {
//		return "Kategoria {nazwa=" + nazwa + "}";
		return nazwa;
	}

	public String getNazwa() {
		return nazwa;
	}

}
