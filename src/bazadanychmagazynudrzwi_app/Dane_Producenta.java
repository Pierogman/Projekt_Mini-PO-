package bazadanychmagazynudrzwi_app;

/**
 * To jest klasa przechowywująca informacje od producenta
 *
 * @author tymku
 */
public class Dane_Producenta
{

    private final String nazwa_producenta;
    private final String nazwa_produktu;

    public Dane_Producenta(String nazwa_producenta, String nazwa_produktu)
    {
        this.nazwa_producenta = nazwa_producenta;
        this.nazwa_produktu = nazwa_produktu;
    }

    public Dane_Producenta(Dane_Producenta dane_producenta)
    {
        this.nazwa_producenta = dane_producenta.nazwa_producenta;
        this.nazwa_produktu = dane_producenta.nazwa_produktu;
    }

    @Override
    public String toString()
    {
        return nazwa_producenta + " " + nazwa_produktu;
    }

    public String formatuj_do_zapisu()
    {
        return nazwa_producenta + ";" + nazwa_produktu;
    }

    public boolean compareTo(Dane_Producenta dane_producenta)
    {
        return this.nazwa_producenta.equals(dane_producenta.nazwa_producenta)
                && this.nazwa_produktu.equals(dane_producenta.nazwa_produktu);
    }

    public boolean compare_ProducentName(Dane_Producenta dane_producenta)
    {
        return this.nazwa_producenta.equals(dane_producenta.nazwa_producenta);
    }
}
