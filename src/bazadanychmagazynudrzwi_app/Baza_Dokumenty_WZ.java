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
 *
 * @author tymku
 */
public class Baza_Dokumenty_WZ
{

    private final String katalog_zapisu = "./Dane";
    private final String lista_WZ_plik = "lista_wz.txt";
    private final String lista_zwolnionych_ID_plik = "wolne_ID_wz.txt";
    private final String ile_dok_zw_plik = "ilosc_wz.txt";

    private ArrayList<Dokument_WZ> lista_WZ = new ArrayList<>();
    private ArrayList<Integer> lista_zwolnionych_ID = new ArrayList<>();

    private int ile_dok_wz = 0;

    public Baza_Dokumenty_WZ()
    {
        wczytaj_stan();
    }

    public void dodaj(Dokument_WZ dokument_wz, Boolean dodaj_ID)
    {

        if (dodaj_ID)
        {
            ile_dok_wz++;
            int nowe_ID = ile_dok_wz + 2000;

            if (!lista_zwolnionych_ID.isEmpty())
            {
                nowe_ID = lista_zwolnionych_ID.getLast();
                lista_zwolnionych_ID.removeLast();
                ile_dok_wz--;
            }

            if (ile_dok_wz >= 999)
            {
                System.err.println("Przekroczono maksymalna liczbe dokumnetow pz!");
                System.out.println("Nie dodano nowego dokumentu");
                return;
            }

            lista_WZ.add(new Dokument_WZ(nowe_ID, dokument_wz));
        } else
        {
            lista_WZ.add(dokument_wz);
        }
    }

    public void wypisz(String nazwa_firmy)
    {
        for (Dokument_WZ wz : lista_WZ)
        {
            if (wz.compare_name(nazwa_firmy))
            {
                System.out.println(wz.toString());
            }
        }
    }

    public void wypisz()
    {
        for (Dokument_WZ wz : lista_WZ)
        {
            System.out.println(wz.toString());
        }
    }

    public void usun(String nazwa_firmy)
    {
        for (Dokument_WZ wz : lista_WZ)
        {
            if (wz.compare_name(nazwa_firmy))
            {
                lista_zwolnionych_ID.add(wz.usun());
                lista_WZ.remove(wz);
                break;
            }
        }
    }

    public void usun(int numer_ID)
    {
        for (Dokument_WZ wz : lista_WZ)
        {
            if (wz.compare(numer_ID))
            {
                lista_zwolnionych_ID.add(wz.usun());
                lista_WZ.remove(wz);
                break;
            }
        }
    }

    public Dokument_WZ zwroc(int numer_ID)
    {
        for (Dokument_WZ wz : lista_WZ)
        {
            if (wz.compare(numer_ID))
            {
                return wz;
            }
        }
        return null;
    }

    public void wczytaj_stan()
    {
        try
        {
            Path dwz_baza_scierzka_lista = Paths.get(katalog_zapisu, lista_WZ_plik);
            Path dwz_baza_scierzka_ilosc = Paths.get(katalog_zapisu, ile_dok_zw_plik);
            Path dwz_baza_scierzka_numer = Paths.get(katalog_zapisu, lista_zwolnionych_ID_plik);

            if (!Files.exists(dwz_baza_scierzka_lista) || !Files.exists(dwz_baza_scierzka_ilosc) || !Files.exists(dwz_baza_scierzka_numer))
            {
                return;
            }

            BufferedReader reader_d = Files.newBufferedReader(dwz_baza_scierzka_lista);

            String linia;

            while ((linia = reader_d.readLine()) != null)
            {
                Dokument_WZ nowy_wz = new Dokument_WZ(linia);
                dodaj(nowy_wz, false);
            }

            reader_d.close();

            BufferedReader reader_il = Files.newBufferedReader(dwz_baza_scierzka_ilosc);

            if ((linia = reader_il.readLine()) != null)
            {
                ile_dok_wz = Integer.parseInt(linia);
            }
            reader_il.close();

            BufferedReader reader_nr = Files.newBufferedReader(dwz_baza_scierzka_numer);

            while ((linia = reader_nr.readLine()) != null)
            {
                lista_zwolnionych_ID.add(Integer.parseInt(linia));
            }
            reader_nr.close();

        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void zapisz_stan()
    {
        try
        {
            // Zapisywanie danych do poszcególnych plików z list(w przypadku gdy folder i pliki nie istnieją stworzenie nowych plików)
            Path dwz_baza_scierzka_katalog = Paths.get(katalog_zapisu);
            Path dwz_baza_scierzka_lista = Paths.get(katalog_zapisu, lista_WZ_plik);
            Path dwz_baza_scierzka_ilosc = Paths.get(katalog_zapisu, ile_dok_zw_plik);
            Path dwz_baza_scierzka_numer = Paths.get(katalog_zapisu, lista_zwolnionych_ID_plik);

            if (!Files.exists(dwz_baza_scierzka_katalog))
            {
                Files.createDirectory(dwz_baza_scierzka_katalog);
                Files.createFile(dwz_baza_scierzka_lista);
                Files.createFile(dwz_baza_scierzka_ilosc);
                Files.createFile(dwz_baza_scierzka_numer);

            } else
            {
                Files.deleteIfExists(dwz_baza_scierzka_lista);
                Files.createFile(dwz_baza_scierzka_lista);
                Files.deleteIfExists(dwz_baza_scierzka_ilosc);
                Files.createFile(dwz_baza_scierzka_ilosc);
                Files.deleteIfExists(dwz_baza_scierzka_numer);
                Files.createFile(dwz_baza_scierzka_numer);
            }

            BufferedWriter writer_ld = Files.newBufferedWriter(dwz_baza_scierzka_lista, StandardCharsets.UTF_8);

            for (Dokument_WZ wz : lista_WZ)
            {
                writer_ld.write(wz.formatuj_do_zapisu());
                writer_ld.newLine();
            }

            writer_ld.close();

            // Zapisywanie ilosci stworzonych wz
            BufferedWriter writer_il = Files.newBufferedWriter(dwz_baza_scierzka_ilosc, StandardCharsets.UTF_8);

            writer_il.write(String.valueOf(ile_dok_wz));
            writer_il.close();

            // Zapisywanie identyfikatorów do ponownego urzycia
            BufferedWriter writer_ri = Files.newBufferedWriter(dwz_baza_scierzka_numer, StandardCharsets.UTF_8);

            for (int i : lista_zwolnionych_ID)
            {
                writer_ri.write(String.valueOf(i));
                writer_ri.newLine();
            }

            writer_ri.close();

        } catch (IOException e)
        {
            System.err.println("Problem z zapisaniem pliku");
            System.err.println(e);
        } catch (SecurityException e)
        {
            System.err.println("Problem z dostępem");
        } catch (Exception e)
        {
            System.err.println("Nie zapisano bazy!");
            return;
        }

        System.out.println("Zapisano baze dokumentow wz!");
    }

}
