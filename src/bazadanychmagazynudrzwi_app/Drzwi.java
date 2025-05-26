package bazadanychmagazynudrzwi_app;

/**
 * Ta klasa perzechowuje informacje o drzwiach
 *
 * @author tymku
 */
public class Drzwi extends Produkt
{

    private static int ilosc_stworzonych_drzwi;

    private final Wymiary wymiary;
    private final String material;
    private final int ilosc_skrzydel;

    // Konstruktory:
    public Drzwi(int id, int numer_PZ, Dane_Producenta dane_producenta, Wymiary wymiary, String material, int ilosc_skrzydel)
    {
        ilosc_stworzonych_drzwi++;

        super.typ = 1;
        super.numer_PZ = numer_PZ;
        super.generuj_ID(id);

        super.dane_producenta = new Dane_Producenta(dane_producenta);

        this.wymiary = new Wymiary(wymiary);
        this.material = material;
        this.ilosc_skrzydel = ilosc_skrzydel;
    }

    public Drzwi(int id, Drzwi drzwi)
    {
        ilosc_stworzonych_drzwi++;

        super.typ = 1;
        super.numer_PZ = drzwi.numer_PZ;
        super.generuj_ID(id);

        super.dane_producenta = new Dane_Producenta(drzwi.dane_producenta);

        this.wymiary = new Wymiary(drzwi.wymiary);
        this.material = drzwi.material;
        this.ilosc_skrzydel = drzwi.ilosc_skrzydel;
    }

    public Drzwi(int numer_WZ, Dane_Producenta dane_producenta, Wymiary wymiary, String material, int ilosc_skrzydel) throws Exception
    {
        ilosc_stworzonych_drzwi++;

        super.typ = 1;
        super.numer_PZ = numer_WZ;
        super.generuj_ID(typ, ilosc_stworzonych_drzwi - 1);

        super.dane_producenta = new Dane_Producenta(dane_producenta);

        this.wymiary = new Wymiary(wymiary);
        this.material = material;
        this.ilosc_skrzydel = ilosc_skrzydel;
    }

    public Drzwi(String dane, Boolean dodaj_ID, int numer_pz)
    {
        try
        {
            String[] osobne_dane = dane.split(";");

            if (!dodaj_ID)
            {
                super.generuj_ID(Integer.parseInt(osobne_dane[0]));
            } else
            {
                ilosc_stworzonych_drzwi++;
                super.generuj_ID(typ, ilosc_stworzonych_drzwi - 1);
            }

            super.typ = 1;
            if (numer_pz == 0)
            {
                super.numer_PZ = Integer.parseInt(osobne_dane[1]);

            } else
            {
                super.numer_PZ = numer_pz;
            }
            super.dane_producenta = new Dane_Producenta(osobne_dane[2], osobne_dane[3]);

            this.wymiary = new Wymiary(Double.parseDouble(osobne_dane[4]), Double.parseDouble(osobne_dane[5]), Double.parseDouble(osobne_dane[6]));
            this.material = osobne_dane[7];
            this.ilosc_skrzydel = Integer.parseInt(osobne_dane[8].strip());

        } catch (Exception e)
        {
            System.err.println("Blendny format produktu: ");
            System.out.println(dane);

            throw e;
        }

    }

    //Metody:
    public boolean compare(Wymiary wymiary)
    {
        return this.wymiary.compareTo(wymiary);
    }

    public boolean compare(Drzwi drzwi)
    {
        return this.dane_producenta.compareTo(drzwi.dane_producenta)
                && this.wymiary.compareTo(drzwi.wymiary)
                && this.material.equals(drzwi.material) && ilosc_skrzydel == drzwi.ilosc_skrzydel;
    }

    public String toFormatedString(String format)
    {
        return String.format(format,
                super.numer_ID, super.numer_PZ, super.dane_producenta.formatuj_do_zapisu(),
                wymiary.formatuj_do_zapisu(), material, String.valueOf(ilosc_skrzydel));
    }

}
