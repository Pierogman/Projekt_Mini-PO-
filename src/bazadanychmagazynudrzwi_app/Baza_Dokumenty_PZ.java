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
public class Baza_Dokumenty_PZ
{

    private final String katalog_zapisu = "./Dane";
    private final String lista_PZ_plik = "lista_pz.txt";
    private final String lista_zwolnionych_ID_plik = "wolne_ID_pz.txt";
    private final String ile_dok_pz_plik = "ilosc_pz.txt";

    public ArrayList<Dokument_PZ> lista_PZ = new ArrayList<>();
    public ArrayList<Integer> lista_zwolnionych_ID = new ArrayList<>();

    private int ile_dok_pz = 0;

    public Baza_Dokumenty_PZ()
    {
        wczytaj_stan();
    }

    public void dodaj(Dokument_PZ dokument_pz, Boolean dodaj_ID)
    {
        if (dodaj_ID)
        {
            ile_dok_pz++;
            int nowe_ID = ile_dok_pz + 1000;

            if (!lista_zwolnionych_ID.isEmpty())
            {
                nowe_ID = lista_zwolnionych_ID.getLast();
                lista_zwolnionych_ID.removeLast();
                ile_dok_pz--;

            }

            if (ile_dok_pz >= 999)
            {
                System.err.println("Przekroczono maksymalna liczbe dokumnetow pz!");
                System.out.println("Nie dodano nowego dokumentu");
                return;
            }

            lista_PZ.add(new Dokument_PZ(nowe_ID, dokument_pz));
        } else
        {
            lista_PZ.add(dokument_pz);
        }
    }

    public void wypisz_po_adresie(String adres_firmy)
    {
        for (Dokument_PZ pz : lista_PZ)
        {
            if (pz.compare_adres(adres_firmy))
            {
                System.out.println(pz.toString());
            }
        }
    }

    public void wypisz_po_nazwie(String nazwa_firmy)
    {
        for (Dokument_PZ pz : lista_PZ)
        {
            if (pz.compare_name(nazwa_firmy))
            {
                System.out.println(pz.toString());
            }
        }
    }

    public void wypisz()
    {
        for (Dokument_PZ pz : lista_PZ)
        {
            System.out.println(pz.toString());
        }
    }

    public void usun(String nazwa_firmy)
    {
        for (Dokument_PZ pz : lista_PZ)
        {
            if (pz.compare_name(nazwa_firmy))
            {
                lista_zwolnionych_ID.add(pz.usun());
                lista_PZ.remove(pz);
                break;
            }
        }
    }

    public void usun_po_adresie(String adres_firmy)
    {
        for (Dokument_PZ pz : lista_PZ)
        {
            if (pz.compare_adres(adres_firmy))
            {
                lista_zwolnionych_ID.add(pz.usun());
                lista_PZ.remove(pz);
                break;
            }
        }
    }

    public void usun(int numer_ID)
    {
        for (Dokument_PZ pz : lista_PZ)
        {
            if (pz.compare(numer_ID))
            {
                lista_zwolnionych_ID.add(pz.usun());
                lista_PZ.remove(pz);
                break;
            }
        }
    }

    public Dokument_PZ zwroc(int numer_ID)
    {
        for (Dokument_PZ pz : lista_PZ)
        {
            if (pz.compare(numer_ID))
            {
                return pz;
            }
        }
        return null;
    }

    public void wczytaj_stan()
    {
        try
        {
            Path dpz_baza_scierzka_lista = Paths.get(katalog_zapisu, lista_PZ_plik);
            Path dpz_baza_scierzka_ilosc = Paths.get(katalog_zapisu, ile_dok_pz_plik);
            Path dpz_baza_scierzka_numer = Paths.get(katalog_zapisu, lista_zwolnionych_ID_plik);

            if (!Files.exists(dpz_baza_scierzka_lista) || !Files.exists(dpz_baza_scierzka_ilosc) || !Files.exists(dpz_baza_scierzka_numer))
            {
                return;
            }

            BufferedReader reader_d = Files.newBufferedReader(dpz_baza_scierzka_lista);

            String linia;

            while ((linia = reader_d.readLine()) != null)
            {
                Dokument_PZ nowy_pz = new Dokument_PZ(linia);
                dodaj(nowy_pz, false);
            }

            reader_d.close();

            BufferedReader reader_il = Files.newBufferedReader(dpz_baza_scierzka_ilosc);

            if ((linia = reader_il.readLine()) != null)
            {
                ile_dok_pz = Integer.parseInt(linia);
            }
            reader_il.close();

            BufferedReader reader_nr = Files.newBufferedReader(dpz_baza_scierzka_numer);

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
            Path dwz_baza_scierzka_lista = Paths.get(katalog_zapisu, lista_PZ_plik);
            Path dwz_baza_scierzka_ilosc = Paths.get(katalog_zapisu, ile_dok_pz_plik);
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

            for (Dokument_PZ pz : lista_PZ)
            {
                writer_ld.write(pz.formatuj_do_zapisu());
                writer_ld.newLine();
            }

            writer_ld.close();

            // Zapisywanie ilosci stworzonych wz
            BufferedWriter writer_il = Files.newBufferedWriter(dwz_baza_scierzka_ilosc, StandardCharsets.UTF_8);

            writer_il.write(String.valueOf(ile_dok_pz));
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
            System.err.println("Problem z dostepem");
        } catch (Exception e)
        {
            System.err.println("Nie zapisano bazy!");
            return;
        }

        System.out.println("Zapisano baze dokumentow pz!");

    }

}
