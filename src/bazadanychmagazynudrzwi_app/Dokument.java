package bazadanychmagazynudrzwi_app;

/**
 *
 * @author ACER Nitro5
 */
public abstract class Dokument
{

    public int numer_ID;
    protected final int typ; // 1 - WZ, 2 - PZ
    protected Pracownik pracownik;
    protected String sciezka_dokumentu;
    protected String nazwa_firmy;

    // Konstruktor z nadanym ID
    protected Dokument(int numer_ID, int typ, Pracownik pracownik, String nazwa_firmy, String sciezka_dokumentu)
    {
        this.numer_ID = numer_ID;
        this.typ = typ;
        this.pracownik = pracownik;
        this.nazwa_firmy = nazwa_firmy;
        this.sciezka_dokumentu = sciezka_dokumentu;
    }

    // Konstruktor tworzony na podstawie linii tekstu (np. z pliku)
    protected Dokument(String linia, int typ)
    {
        String[] pola = linia.split(";");
        this.numer_ID = Integer.parseInt(pola[0]);
        this.typ = typ;
        this.pracownik = new Pracownik(pola[1]);
        this.nazwa_firmy = pola[2];
        this.sciezka_dokumentu = pola[3];
    }

    // Przypisanie ID (gdy tworzymy obiekt z gotowym numerem)
    protected void generuj_ID(int id)
    {
        this.numer_ID = id;
    }

    // Generowanie ID na podstawie typu i liczby dokumentów
    protected void generuj_ID(int typ, int liczba)
    {
        this.numer_ID = this.typ * 1000 + liczba;
    }

    // Porównanie nazw firm
    public boolean compare_name(String firma)
    {
        return this.nazwa_firmy.equalsIgnoreCase(firma);
    }

    // Porównanie pracowników
    public boolean compare(Pracownik p)
    {
        return this.pracownik.equals(p);
    }

    public boolean compare(int numer_ID)
    {
        return this.numer_ID == numer_ID;
    }

    // Usuwanie zawartości dokumentu (czyści dane i zwraca ID do ewentualnego ponownego użycia)
    public int usun()
    {
        int temp = this.numer_ID;
//        this.pracownik = null;
//        this.nazwa_firmy = null;
//        this.sciezka_dokumentu = null;
        return temp;
    }

    // Pomocnicze do zapisu (dla klas pochodnych)
    protected String formatuj_do_zapisu()
    {
        return numer_ID + ";" + pracownik.doPliku() + ";" + nazwa_firmy + ";" + sciezka_dokumentu;
    }
}
