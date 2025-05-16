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
    protected int numer_WZ;
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
        return this.dane_producenta.equals(dane_producenta);
    }

    public boolean compareTo(int id)
    {
        return numer_ID == id;
    }

    public int usun()
    {
        int urzywane_id = this.numer_ID;
        numer_ID = 0;
        numer_WZ = 0;

        return urzywane_id;
    }
}
