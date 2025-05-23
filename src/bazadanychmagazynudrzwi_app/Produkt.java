package bazadanychmagazynudrzwi_app;

/**
 * To jest klasa abstrakcyja z której kasy Drzwi, Klamki, Ościerznice
 *
 * @author tymku
 */
public abstract class Produkt
{

    protected int typ;
    protected int numer_ID;
    protected int numer_PZ;
    protected Dane_Producenta dane_producenta;

    // Generowanie id na podstawie ilosci produktu
    protected void generuj_ID(int typ, int numer)
    {
        this.numer_ID = 100000 * typ + numer;
    }

    // Uzupełanie danych na podstawie podanego id 
    protected void generuj_ID(int id)
    {
        this.numer_ID = id;
    }

    public boolean compareTo(Dane_Producenta dane_producenta)
    {
        return this.dane_producenta.compareTo(dane_producenta);
    }

    public boolean compare_ID(int numer_ID)
    {
        return this.numer_ID == numer_ID;
    }

    public boolean compare_PZ(int numer_PZ)
    {
        return this.numer_PZ == numer_PZ;
    }

    public boolean compare_ProducentName(Dane_Producenta dane_producenta)
    {
        return dane_producenta.compare_ProducentName(dane_producenta);
    }

    public int usun()
    {
        int urzywane_id = this.numer_ID;
        this.numer_PZ = 0;

        return urzywane_id;
    }
}
