package bazadanychmagazynudrzwi_app;

/**
 *
 * @author ACER Nitro5
 */
public class Dokument_PZ extends Dokument
{

    private static int liczba_stworzonych_PZ = 0;
    private String adres_firmy;

    // Konstruktor z nadanym ID
    public Dokument_PZ(int id, Pracownik p, String firma, String adres, String sciezka)
    {
        super(id, 2, p, firma, sciezka);
        this.adres_firmy = adres;
    }

    public Dokument_PZ(int id, Dokument_PZ dokument_pz)
    {
        super(id, 2, dokument_pz.pracownik, dokument_pz.nazwa_firmy, dokument_pz.sciezka_dokumentu);
        this.adres_firmy = dokument_pz.adres_firmy;
    }

    // Konstruktor z automatycznym ID
    public Dokument_PZ(Pracownik p, String firma, String adres, String sciezka)
    {
        super(0, 2, p, firma, sciezka);
        generuj_ID(2, liczba_stworzonych_PZ++);
        this.adres_firmy = adres;
    }

    // Konstruktor z linii tekstu
    public Dokument_PZ(String linia)
    {
        super(linia, 2);
        String[] pola = linia.split(";");
        this.adres_firmy = pola[4];
    }

    @Override
    public String toString()
    {
        return "PZ #" + super.formatuj_do_zapisu() + ";" + adres_firmy;
    }

    public String formatuj_do_zapisu()
    {
        return super.formatuj_do_zapisu() + ";" + adres_firmy;
    }

    // Porównanie adresu firmy
    public boolean compare_adres(String adres)
    {
        return this.adres_firmy.equalsIgnoreCase(adres);
    }

    
}
