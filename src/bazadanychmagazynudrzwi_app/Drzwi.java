package bazadanychmagazynudrzwi_app;

/**
 * Ta klasa perzechowuje informacje o drzwiach
 *
 * @author tymku
 */
public class Drzwi extends Produkt
{

    private static int ilosc_stworzonych_drzwi;

    private Wymiary wymiary;
    private String material;
    private int ilosc_skrzydel;

    // Konstruktory:
    public Drzwi(int id, Wymiary wymiary, String material, int ilosc_skrzydel, Dane_Producenta dane_producenta)
    {
        ilosc_stworzonych_drzwi++;

        super.typ = 1;
        super.generuj_ID(id);

        super.dane_producenta = new Dane_Producenta(dane_producenta);

        this.wymiary = new Wymiary(wymiary);
        this.material = material;
        this.ilosc_skrzydel = ilosc_skrzydel;
    }

    public Drzwi(Wymiary wymiary, String material, int ilosc_skrzydel, Dane_Producenta dane_producenta)
    {
        ilosc_stworzonych_drzwi++;

        super.typ = 1;
        super.generuj_ID(ilosc_stworzonych_drzwi - 1, typ);

        super.dane_producenta = new Dane_Producenta(dane_producenta);

        this.wymiary = new Wymiary(wymiary);
        this.material = material;
        this.ilosc_skrzydel = ilosc_skrzydel;
    }

    //Metody:
    public boolean compareTo(Wymiary wymiary)
    {
        return this.wymiary.equals(wymiary);
    }

    public boolean compareTo(Dane_Producenta dane_producenta)
    {
        return this.dane_producenta.equals(dane_producenta);
    }

    public boolean compareTo(int id)
    {
        return super.numer_ID == id;
    }

    
    
}
