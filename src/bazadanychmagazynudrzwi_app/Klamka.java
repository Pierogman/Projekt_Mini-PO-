package bazadanychmagazynudrzwi_app;

/**
 *
 * @author tymku
 */
public class Klamka extends Produkt
{

    public static int ilosc_stworzonych_klamek;

    private String material;

    public Klamka(int id, int numer_WZ, Dane_Producenta dane_producenta, String material)
    {
        ilosc_stworzonych_klamek++;

        super.typ = 2;
        super.numer_PZ = numer_WZ;
        super.generuj_ID(id);

        super.dane_producenta = new Dane_Producenta(dane_producenta);

        this.material = material;

    }

    public Klamka(int id, Klamka klamka)
    {
        ilosc_stworzonych_klamek++;

        super.typ = 2;
        super.numer_PZ = klamka.numer_PZ;
        super.generuj_ID(id);

        super.dane_producenta = new Dane_Producenta(klamka.dane_producenta);

        this.material = klamka.material;

    }

    public Klamka(int numer_WZ, Dane_Producenta dane_producenta, String material)
    {
        ilosc_stworzonych_klamek++;

        super.typ = 2;
        super.numer_PZ = numer_WZ;
        super.generuj_ID(typ, ilosc_stworzonych_klamek - 1);

        super.dane_producenta = new Dane_Producenta(dane_producenta);

        this.material = material;

    }

    public Klamka(String dane, Boolean dodaj_ID, int numer_PZ)
    {
        try
        {

            String[] osobne_dane = dane.split(";");

            if (!dodaj_ID)
            {
                super.generuj_ID(Integer.parseInt(osobne_dane[0]));
            } else
            {
                ilosc_stworzonych_klamek++;
                super.generuj_ID(typ, ilosc_stworzonych_klamek - 1);
            }

            if (numer_PZ == 0)
            {
                super.numer_PZ = Integer.parseInt(osobne_dane[1]);
            } else
            {
                super.numer_PZ = numer_PZ;
            }
            super.typ = 1;
            super.dane_producenta = new Dane_Producenta(osobne_dane[2], osobne_dane[3]);

            this.material = osobne_dane[7];

        } catch (Exception e)
        {
            System.err.println("Blendny format produktu: ");
            System.out.println(dane);

            throw e;
        }

    }

    //Metody 
    public Boolean compareTo(Klamka klamka)
    {
        return this.material.equals(klamka.material) && this.dane_producenta.compareTo(klamka.dane_producenta);
    }

    public String toFormatedString(String format)
    {
        return String.format(format,
                super.numer_ID, super.numer_PZ, super.dane_producenta.formatuj_do_zapisu(),
                " ; ; ", material, " ");
    }

}
