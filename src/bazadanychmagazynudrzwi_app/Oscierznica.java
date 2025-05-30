package bazadanychmagazynudrzwi_app;

/**
 *
 * @author tymku
 */
public class Oscierznica extends Produkt
{

    private static int ilosc_stworzonych_oscerznic;

    private Wymiary wymiary;
    private String material;

    public Oscierznica(int id, int numer_PZ, Dane_Producenta dane_producenta, Wymiary wymiary, String material)
    {
        ilosc_stworzonych_oscerznic++;

        super.typ = 3;
        super.numer_PZ = numer_PZ;
        super.generuj_ID(id);

        super.dane_producenta = new Dane_Producenta(dane_producenta);

        this.wymiary = new Wymiary(wymiary);
        this.material = material;
    }

    public Oscierznica(int id, Oscierznica oscierznica)
    {
        ilosc_stworzonych_oscerznic++;

        super.typ = 3;
        super.numer_PZ = oscierznica.numer_PZ;
        super.generuj_ID(id);

        super.dane_producenta = new Dane_Producenta(oscierznica.dane_producenta);

        this.wymiary = new Wymiary(oscierznica.wymiary);
        this.material = oscierznica.material;
    }

    public Oscierznica(int numer_PZ, Dane_Producenta dane_producenta, Wymiary wymiary, String material)
    {
        ilosc_stworzonych_oscerznic++;

        super.typ = 3;
        super.numer_PZ = numer_PZ;
        super.generuj_ID(typ, ilosc_stworzonych_oscerznic - 1);

        super.dane_producenta = new Dane_Producenta(dane_producenta);

        this.wymiary = new Wymiary(wymiary);
        this.material = material;
    }

    public Oscierznica(String dane, boolean dodaj_ID, int numer_PZ)
    {

        try
        {

            String[] osobne_dane = dane.split(";");

            if (!dodaj_ID)
            {
                super.generuj_ID(Integer.parseInt(osobne_dane[0]));
            } else
            {
                ilosc_stworzonych_oscerznic++;
                super.generuj_ID(typ, ilosc_stworzonych_oscerznic - 1);
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

            this.wymiary = new Wymiary(Double.parseDouble(osobne_dane[4]), Double.parseDouble(osobne_dane[5]), Double.parseDouble(osobne_dane[6]));
            this.material = osobne_dane[7];

        } catch (Exception e)
        {
            System.err.println("Blendny format produktu: ");
            System.out.println(dane);

            throw e;
        }

    }

    // Metody: 
    public boolean compare(Wymiary wymiary)
    {
        return this.wymiary.compareTo(wymiary);
    }

    public boolean compare(Oscierznica oscierznica)
    {
        return super.dane_producenta.compareTo(oscierznica.dane_producenta)
                && this.wymiary.compareTo(oscierznica.wymiary) && this.material.equals(oscierznica.material);
    }

    public String toFormatedString(String format)
    {
        return String.format(format,
                super.numer_ID, super.numer_PZ, super.dane_producenta.formatuj_do_zapisu(),
                wymiary.formatuj_do_zapisu(), material, " ");
    }

}
