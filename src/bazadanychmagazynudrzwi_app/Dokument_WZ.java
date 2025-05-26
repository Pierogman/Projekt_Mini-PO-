package bazadanychmagazynudrzwi_app;

/**
 *
 * @author ACER Nitro5
 */
public class Dokument_WZ extends Dokument
{

    private static int liczba_stworzonych_WZ = 0;

    // Konstruktor z nadanym ID
    public Dokument_WZ(int id, Pracownik p, String firma, String sciezka)
    {
        super(id, 1, p, firma, sciezka);
    }

    public Dokument_WZ(int id, Dokument_WZ dokument_wz)
    {
        super(id, 1, dokument_wz.pracownik, dokument_wz.nazwa_firmy, dokument_wz.sciezka_dokumentu);
    }

    // Konstruktor z automatycznym ID
    public Dokument_WZ(Pracownik p, String firma, String sciezka)
    {
        super(0, 1, p, firma, sciezka);
        generuj_ID(1, liczba_stworzonych_WZ++);
    }

    // Konstruktor z linii tekstu
    public Dokument_WZ(String linia)
    {
        super(linia, 1);
    }

    @Override
    public String toString()
    {
        return "WZ #" + super.formatuj_do_zapisu();
    }

    @Override
    public String formatuj_do_zapisu()
    {
        return super.formatuj_do_zapisu();
    }
}
