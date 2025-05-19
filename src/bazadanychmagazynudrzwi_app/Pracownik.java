package bazadanychmagazynudrzwi_app;

/**
 *
 * @author ACER Nitro5
 */

public class Pracownik {
    private String imie;      // Imię pracownika
    private String nazwisko;  // Nazwisko pracownika

    public Pracownik(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    // Konstruktor z jednym Stringiem (np. z pliku: "Jan,Kowalski")
    public Pracownik(String dane) {
        String[] pola = dane.split(",");
        this.imie = pola[0];
        this.nazwisko = pola[1];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pracownik) {
            Pracownik p = (Pracownik) obj;
            return this.imie.equalsIgnoreCase(p.imie) &&
                   this.nazwisko.equalsIgnoreCase(p.nazwisko);
        }
        return false;
    }

    @Override
    public String toString() {
        return imie + " " + nazwisko;
    }

    public String doPliku() {
        return imie + "," + nazwisko;
    }
}
