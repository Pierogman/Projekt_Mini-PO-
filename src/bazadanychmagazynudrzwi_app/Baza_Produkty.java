package bazadanychmagazynudrzwi_app;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Tutaj znajdują się metody do bazy danych produktów:
 *
 * @author tymku
 */
public class Baza_Produkty
{

    private final String katalog_zapisu = "./Dane";
    private final String dane_lista_d = "drzwi.txt";
    private final String dane_lista_k = "klamki.txt";
    private final String dane_lista_o = "oscerznice.txt";
    private final String table_format = "%d|%-30s|%-23s|%-10s|%-2s|";

    public ArrayList<Integer> lista_urzytych_ID = new ArrayList<>();

    public ArrayList<Drzwi> lista_drzwi = new ArrayList<>();
    public ArrayList<Klamka> lista_klamek = new ArrayList<>();
    public ArrayList<Oscierznica> lista_oscierznic = new ArrayList<>();

    public Baza_Produkty()
    {
        wczytaj_stan();
    }

    // Funkcje doające 
    public void dodaj()
    {
    }

    public void dodaj(Drzwi drzwi)
    {
        lista_drzwi.add(drzwi);
    }

    public void dodaj(Klamka klamka)
    {
        lista_klamek.add(klamka);
    }

    public void dodaj(Oscierznica oscierznica)
    {
        lista_oscierznic.add(oscierznica);
    }

    // Funkcje usuwające 
    public void usun()
    {
    }

    public void usun(int numer_ID)
    {
        for (Drzwi d : lista_drzwi)
        {
            if (d.compare_ID(numer_ID))
            {
                lista_urzytych_ID.add(d.usun());
                lista_drzwi.remove(d);
                return;
            }
        }

        for (Klamka k : lista_klamek)
        {
            if (k.compare_ID(numer_ID))
            {
                lista_urzytych_ID.add(k.usun());
                lista_klamek.remove(k);
                return;
            }
        }

        for (Oscierznica o : lista_oscierznic)
        {
            if (o.compare_ID(numer_ID))
            {
                lista_urzytych_ID.add(o.usun());
                lista_oscierznic.remove(o);
                return;
            }
        }
    }

    // Funkcje wypisujące
    // Funkcja wypisująca wszysktie produkty na liście
    public void wypisz()
    {
        for (Drzwi d : lista_drzwi)
        {
            System.out.println(d.toFormatedString(table_format));
        }
        for (Klamka k : lista_klamek)
        {
            System.out.println(k.toFormatedString(table_format));
        }
        for (Oscierznica o : lista_oscierznic)
        {
            System.out.println(o.toFormatedString(table_format));
        }
    }

    // Funkcja wypisująca wszystkie produkty o danym numerze PZ
    public void wypisz(int numer_PZ)
    {
        for (Drzwi d : lista_drzwi)
        {
            if (d.compare_PZ(numer_PZ))
            {
                System.out.println(d.toFormatedString(table_format));
            }
        }
        for (Klamka k : lista_klamek)
        {
            if (k.compare_PZ(numer_PZ))
            {
                System.out.println(k.toFormatedString(table_format));
            }

        }
        for (Oscierznica o : lista_oscierznic)
        {
            if (o.compare_PZ(numer_PZ))
            {
                System.out.println(o.toFormatedString(table_format));
            }
        }

    }

    // Funkcja wypisujaca wszytkie produkty od danego porducenta 
    public void wypisz(String nazwa_producenta)
    {
        for (Drzwi d : lista_drzwi)
        {
            if (d.compare_Producent(nazwa_producenta))
            {
                System.out.println(d.toFormatedString(table_format));
            }
        }
        for (Klamka k : lista_klamek)
        {
            if (k.compare_Producent(nazwa_producenta))
            {
                System.out.println(k.toFormatedString(table_format));
            }

        }
        for (Oscierznica o : lista_oscierznic)
        {
            if (o.compare_Producent(nazwa_producenta))
            {
                System.out.println(o.toFormatedString(table_format));
            }
        }

    }

    // Funkcja wypisująca wszystkie porodukty o danych wymiarach
    public void wypisz(Wymiary wymiary)
    {
        for (Drzwi d : lista_drzwi)
        {
            if (d.compareTo(wymiary))
            {
                System.out.println(d.toFormatedString(table_format));
            }
        }
        for (Oscierznica o : lista_oscierznic)
        {
            if (o.compareTo(wymiary))
            {
                System.out.println(o.toFormatedString(table_format));
            }
        }

    }

    // Funkcje zapisujące
    private void wczytaj_stan()
    {
        try
        {
            Path p_baza_scierzka = Paths.get(katalog_zapisu);
            if (!Files.exists(p_baza_scierzka))
            {
                Files.createDirectory(p_baza_scierzka);
                Path p_baza_scierzka_ld = Paths.get(katalog_zapisu, dane_lista_d);
                Path p_baza_scierzka_lk = Paths.get(katalog_zapisu, dane_lista_k);
                Path p_baza_scierzka_lo = Paths.get(katalog_zapisu, dane_lista_o);

                Files.createFile(p_baza_scierzka_ld);
                Files.createFile(p_baza_scierzka_lk);
                Files.createFile(p_baza_scierzka_lo);

            }

        } catch (Exception e)
        {
            System.err.println("Nie znalezieono scierzki");
        }

    }

    public void zapisz_stan()
    {
    }

}
