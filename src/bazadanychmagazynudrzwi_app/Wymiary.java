package bazadanychmagazynudrzwi_app;

/**
 * To jest klasa przechowywująca wymiary damnego produktu
 *
 * @author tymku
 */
public class Wymiary
{

    private final double szerokosc;
    private final double wysokosc;
    private final double grubosc;

    public Wymiary(double szerokosc, double wysokosc, double grubosc)
    {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.grubosc = grubosc;
    }

    public Wymiary(Wymiary wymiary)
    {
        this.szerokosc = wymiary.szerokosc;
        this.wysokosc = wymiary.wysokosc;
        this.grubosc = wymiary.grubosc;
    }

    @Override
    public String toString()
    {
        return szerokosc + " x " + wysokosc + " x " + grubosc;
    }

    public boolean compareTo(Wymiary wymiary)
    {
        return !((this.szerokosc != wymiary.szerokosc || this.wysokosc != wymiary.wysokosc)
                || this.grubosc != wymiary.grubosc);
    }

    public String formatuj_do_zapisu()
    {
        return szerokosc + ";" + wysokosc + ";" + grubosc ;
    }
}
