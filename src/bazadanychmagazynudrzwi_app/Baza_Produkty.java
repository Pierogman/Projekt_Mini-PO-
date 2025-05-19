package bazadanychmagazynudrzwi_app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
    private final String dane_lista_p = "lista_p.txt";

    private final String table_format = "%d|%d|%-30s|%-23s|%-10s|%-2s|";
    private final String save_format = "%d;%d;%s;%s;%s;%s";

    private ArrayList<Integer> lista_urzytych_ID = new ArrayList<>();

    private ArrayList<Drzwi> lista_drzwi = new ArrayList<>();
    private ArrayList<Klamka> lista_klamek = new ArrayList<>();
    private ArrayList<Oscierznica> lista_oscierznic = new ArrayList<>();

    public Baza_Produkty()
    {
        wczytaj_stan();
    }

    // Funkcje doające =========================================================
    // Funkcja dodająca wszystkie elementy z danego dokumentu
    public void dodaj(Path scierzka_dokumentu, Boolean dodaj_ID) throws IOException
    {
        BufferedReader reader_d = Files.newBufferedReader(scierzka_dokumentu);

        String linia;

        while ((linia = reader_d.readLine()) != null)
        {
            int typ = linia.charAt(0) - '0';
            if (typ == 1)
            {
                Drzwi nowe_drzwi = new Drzwi(linia, dodaj_ID);
                dodaj(nowe_drzwi);
            }
            if (typ == 2)
            {
                Klamka nowa_klamka = new Klamka(linia, dodaj_ID);
                dodaj(nowa_klamka);
            }
            if (typ == 3)
            {
                Oscierznica nowa_oscierznica = new Oscierznica(linia, dodaj_ID);
                dodaj(nowa_oscierznica);
            }
        }
        reader_d.close();
    }

    // Funkcje dodające poszczegulne elementy z osobna
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

    // Funkcje usuwające =======================================================
    public void usun(Path scierzka_dokumentu) throws IOException
    {
        BufferedReader reader_d = Files.newBufferedReader(scierzka_dokumentu);

        String linia;
        Boolean zawiera_elementy = true;

        ArrayList<Drzwi> nowe_drzwi = (ArrayList<Drzwi>) lista_drzwi.clone();
        ArrayList<Klamka> nowe_klamki = (ArrayList<Klamka>) lista_klamek.clone();
        ArrayList<Oscierznica> nowe_oscierznice = (ArrayList<Oscierznica>) lista_oscierznic.clone();

        while ((linia = reader_d.readLine()) != null)
        {
            int typ = linia.charAt(0) - '0';
            if (typ == 1)
            {
                Drzwi n_drzwi = new Drzwi(linia, true);
                Boolean znaleziono_el = false;
                for (Drzwi d : nowe_drzwi)
                {
                    if (d.compareTo(n_drzwi))
                    {
                        nowe_drzwi.remove(d);
                        znaleziono_el = true;
                        break;
                    }
                }
                if (!znaleziono_el)
                {
                    zawiera_elementy = false;
                    break;
                }
            }
            if (typ == 2)
            {
                Klamka n_klamka = new Klamka(linia, true);
                Boolean znaleziono_el = false;
                for (Klamka k : nowe_klamki)
                {
                    if (k.compareTo(n_klamka))
                    {
                        nowe_klamki.remove(k);
                        znaleziono_el = true;
                        break;
                    }
                }
                if (!znaleziono_el)
                {
                    zawiera_elementy = false;
                    break;
                }
            }

            if (typ == 3)
            {
                Oscierznica n_oscierznica = new Oscierznica(linia, true);
                Boolean znaleziono_el = false;
                for (Oscierznica o : nowe_oscierznice)
                {
                    if (o.compareTo(n_oscierznica))
                    {
                        nowe_oscierznice.remove(o);
                        znaleziono_el = true;
                        break;
                    }
                }
                if (!znaleziono_el)
                {
                    zawiera_elementy = false;
                    break;
                }

            }

        }

        if (zawiera_elementy)
        {
            lista_drzwi = nowe_drzwi;
            lista_klamek = nowe_klamki;
            lista_oscierznic = nowe_oscierznice;
        } else
        {
            System.err.println("Brak elementow na liscie!");
        }

        reader_d.close();
    }

    public void usun(int numer_ID)
    {
        for (Drzwi d : lista_drzwi)
        {
            if (d.compare_ID(numer_ID))
            {
                usun(d);
                return;
            }
        }

        for (Klamka k : lista_klamek)
        {
            if (k.compare_ID(numer_ID))
            {
                usun(k);
                return;
            }
        }

        for (Oscierznica o : lista_oscierznic)
        {
            if (o.compare_ID(numer_ID))
            {
                usun(o);
                return;
            }
        }
    }

    public void usun(Drzwi drzwi)
    {
        lista_urzytych_ID.add(drzwi.usun());
        lista_drzwi.remove(drzwi);
    }

    public void usun(Klamka klamka)
    {
        lista_urzytych_ID.add(klamka.usun());
        lista_klamek.remove(klamka);
    }

    public void usun(Oscierznica oscierznica)
    {
        lista_urzytych_ID.add(oscierznica.usun());
        lista_oscierznic.remove(oscierznica);
    }

    // Funkcje wypisujące=======================================================
    // Funkcja wypisująca wszysktie produkty na liście
    public void wypisz()
    {
        ArrayList<String> nowa_lista = generuj_subliste(table_format);
        for (String dane : nowa_lista)
        {
            System.out.println(dane);
        }
    }

    // Funkcja wypisująca wszystkie produkty o danym numerze PZ
    public void wypisz(int numer_PZ)
    {
        ArrayList<String> nowa_lista = generuj_subliste(numer_PZ, table_format);
        for (String dane : nowa_lista)
        {
            System.out.println(dane);
        }
    }

    // Funkcja wypisujaca wszytkie produkty od danego porducenta 
    public void wypisz(Dane_Producenta dane_producenta)
    {
        ArrayList<String> nowa_lista = generuj_subliste(dane_producenta, table_format);
        for (String dane : nowa_lista)
        {
            System.out.println(dane);
        }
    }

    // Funkcja wypisująca wszystkie porodukty o danych wymiarach
    public void wypisz(Wymiary wymiary)
    {
        ArrayList<String> nowa_lista = generuj_subliste(wymiary, table_format);
        for (String dane : nowa_lista)
        {
            System.out.println(dane);
        }

    }
    // Funkcje generujące dane na podstawie zapytań ============================ 

    public ArrayList<String> generuj_subliste(String format)
    {
        ArrayList<String> nowa_sublista = new ArrayList<>();
        for (Drzwi d : lista_drzwi)
        {
            nowa_sublista.add(d.toFormatedString(format));
        }
        for (Klamka k : lista_klamek)
        {
            nowa_sublista.add(k.toFormatedString(format));
        }
        for (Oscierznica o : lista_oscierznic)
        {
            nowa_sublista.add(o.toFormatedString(format));
        }
        return nowa_sublista;
    }

    public ArrayList<String> generuj_subliste(int numer_PZ, String format)
    {
        ArrayList<String> nowa_sublista = new ArrayList<>();
        for (Drzwi d : lista_drzwi)
        {
            if (d.compare_PZ(numer_PZ))
            {
                nowa_sublista.add(d.toFormatedString(format));
            }
        }
        for (Klamka k : lista_klamek)
        {
            if (k.compare_PZ(numer_PZ))
            {
                nowa_sublista.add(k.toFormatedString(format));
            }

        }
        for (Oscierznica o : lista_oscierznic)
        {
            if (o.compare_PZ(numer_PZ))
            {
                nowa_sublista.add(o.toFormatedString(format));
            }
        }
        return nowa_sublista;
    }

    public ArrayList<String> generuj_subliste(Dane_Producenta dane_porducenta, String format)
    {
        ArrayList<String> nowa_sublista = new ArrayList<>();

        for (Drzwi d : lista_drzwi)
        {
            if (d.compare_ProducentName(dane_porducenta))
            {
                nowa_sublista.add(d.toFormatedString(format));
            }
        }
        for (Klamka k : lista_klamek)
        {
            if (k.compare_ProducentName(dane_porducenta))
            {
                nowa_sublista.add(k.toFormatedString(format));
            }

        }
        for (Oscierznica o : lista_oscierznic)
        {
            if (o.compare_ProducentName(dane_porducenta))
            {
                nowa_sublista.add(o.toFormatedString(format));
            }
        }

        return nowa_sublista;
    }

    public ArrayList<String> generuj_subliste(Wymiary wymiary, String format)
    {
        ArrayList<String> nowa_sublista = new ArrayList<>();

        for (Drzwi d : lista_drzwi)
        {
            if (d.compareTo(wymiary))
            {
                nowa_sublista.add(d.toFormatedString(format));
            }
        }
        for (Oscierznica o : lista_oscierznic)
        {
            if (o.compareTo(wymiary))
            {
                nowa_sublista.add(o.toFormatedString(format));
            }
        }
        return nowa_sublista;
    }

    // Funkcje zapisujące ======================================================
    private void wczytaj_stan()
    {
        try
        {
            // Odczytywanie danych z poszczególnych plików do list 
            Path p_baza_scierzka_ld = Paths.get(katalog_zapisu, dane_lista_p);
            dodaj(p_baza_scierzka_ld, false);
            
        } catch (Exception e)
        {
            System.err.println("Nie znalezieono scierzki");
            System.err.println(e);
        }
    }

    public void zapisz_stan()
    {
        try
        {
            // Zapisywanie danych do poszcególnych plików z list(w przypadku gdy folder i pliki nie istnieją stworzenie nowych plików)
            Path p_baza_scierzka = Paths.get(katalog_zapisu);
            Path p_baza_scierzka_list = Paths.get(katalog_zapisu, dane_lista_p);

            if (!Files.exists(p_baza_scierzka))
            {
                Files.createDirectory(p_baza_scierzka);
                Files.createFile(p_baza_scierzka_list);

            } else
            {
                Files.deleteIfExists(p_baza_scierzka_list);
                Files.createFile(p_baza_scierzka_list);
            }

            ArrayList<String> zapisywane_produkty = generuj_subliste(save_format);

            BufferedWriter writer_ld = Files.newBufferedWriter(p_baza_scierzka_list, StandardCharsets.UTF_8);

            for (String produkt : zapisywane_produkty)
            {
                writer_ld.write(produkt);
                writer_ld.newLine();
            }

            writer_ld.close();

        } catch (IOException e)
        {
            System.err.println("Problem z zapisaniem pliku");
            System.err.println(e);
        } catch (SecurityException e)
        {
            System.err.println("Problem z dostępem");
        }
    }

}
