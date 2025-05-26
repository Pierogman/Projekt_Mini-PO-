package bazadanychmagazynudrzwi_app;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class BazaDanychMagazynuDrzwi_App
{

    public static void main(String[] args)
    {
        Baza_Produkty baza_p = new Baza_Produkty();
        Baza_Dokumenty_WZ baza_wz = new Baza_Dokumenty_WZ();
        Baza_Dokumenty_PZ baza_pz = new Baza_Dokumenty_PZ();

        Generator_CSV gen_csv = new Generator_CSV();

        Boolean wyloncz_program = false;

        Scanner sc = new Scanner(System.in);
        String[] user_resp;
        System.out.println("Witam w bazie danych magazynu drzwi! \n");

        while (!wyloncz_program)
        {
            System.out.println("""
                               Wpisz:
                               com - aby wypisac wszystkie dostepne komendy
                               end - aby zamkac program (baza zostanie zapisana)
                               """);
            System.out.print("Wybrana opcja: ");
            user_resp = sc.nextLine().split("-");

            switch (user_resp[0])
            {
                // Narzędzia bazy porduktów:
                case "pr":
                    Baza_produktow(baza_p, gen_csv, baza_wz, baza_pz, user_resp);
                    break;
                case "wz":
                    Baza_dokumentow_WZ(baza_p, baza_wz, user_resp);
                    break;
                case "pz":
                    Baza_dokumentow_PZ(baza_p, baza_pz, user_resp);
                    break;
                case "com":
                    Wypisz_komendy();
                    break;
                case "end":
                    System.out.println("Wylaczanie programu");
                    wyloncz_program = true;
                    break;
                default:
                    System.out.println("Blendny Input!\n\n");
            }
            System.out.println();
        }

        baza_p.zapisz_stan();
        baza_wz.zapisz_stan();
        baza_pz.zapisz_stan();

        System.out.println("Do widzenia!");

    }

    static void Wypisz_komendy()
    {
        try
        {
            Path spis_komend = Paths.get("./Dane/komendy.txt");
            
            BufferedReader reader = Files.newBufferedReader(spis_komend);
            
            String line;
            while ((line = reader.readLine()) != null)
            {
                System.out.println(line);
            }
            
            reader.close();
        }
        catch(Exception e)
        {
            System.err.println("Nie znaleziono spisu komend!");
        }
        
    }

    // Czesc kontroli bazy porduktow
    static void Baza_produktow(Baza_Produkty baza_p, Generator_CSV gen_csv, Baza_Dokumenty_WZ baza_wz, Baza_Dokumenty_PZ baza_pz, String[] user_resp)
    {
        switch (user_resp[1])
        {
            case "prz":
                Przeglondaj_P(baza_p, user_resp);
                break;
            case "csv":
                Generowanie_csv(gen_csv, baza_p, user_resp);
                break;
            default:
                System.out.println("Blendny Input!\n\n");
                break;
        }
    }

    static void Przeglondaj_P(Baza_Produkty baza_p, String[] user_resp)
    {

        Scanner sc = new Scanner(System.in);

        switch (user_resp[2])
        {
            case "wszystkie":
                System.out.println("To sa aktualnie wszystkie produkty w bazie:");

                baza_p.wypisz();
                break;
            case "nrpz":
                System.out.print("Podaj numer dokumentu pz: ");
                int user_resp_1 = sc.nextInt();
                System.out.println("To sa wszystkie obecne produkty z dokumentu pz: " + user_resp_1);

                baza_p.wypisz(user_resp_1);
                break;
            case "prod":
                System.out.print("Podaj nazwe producenta: ");
                String user_resp_2 = sc.nextLine();
                System.out.println("To sa wszystkie obecne produkty producenta: " + user_resp_2);

                Dane_Producenta nowy_producent = new Dane_Producenta(user_resp_2, "*");
                baza_p.wypisz(nowy_producent);
                break;
            default:
                System.out.println("Blendny Input!\n\n");
                break;
        }
    }

    static void Generowanie_csv(Generator_CSV gen_csv, Baza_Produkty baza_p, String[] user_resp)
    {
        Scanner sc = new Scanner(System.in);

        switch (user_resp[2])
        {
            case "wszystkie":
                System.out.print("Podaj gdzie ma zostac wygenerowany dokument: ");
                String scierzka_1 = sc.nextLine();

                gen_csv.generuj(baza_p, scierzka_1);
                break;
            case "nrpz":
                System.out.print("Podaj gdzie ma zostac wygenerowany dokument: ");
                String scierzka_2 = sc.nextLine();
                System.out.print("Podaj numer dokumentu pz: ");
                int numer_dokumentu = sc.nextInt();
                System.out.println("Generowanie pliku z obecnymi w bazie danych produktami dokumentu pz:" + numer_dokumentu + "do scierzki" + scierzka_2);

                gen_csv.generuj(baza_p, scierzka_2, numer_dokumentu);
                break;
            case "prod":
                System.out.print("Podaj gdzie ma zostac wygenerowany dokument: ");
                String scierzka_3 = sc.nextLine();
                System.out.print("Podaj nazwe producenta: ");
                String producent = sc.nextLine();
                System.out.println("Generowanie pliku z obecnymi w bazie danych profuktami producenta: " + producent + "do scierzki" + scierzka_3);

                Dane_Producenta nowy_producent = new Dane_Producenta(producent, "*");
                gen_csv.generuj(baza_p, scierzka_3, nowy_producent);
                break;
            default:
                System.out.println("Blendny Input!\n\n");
                break;
        }

    }

    // Czensc kontroli bazy dokumentow WZ
    static void Baza_dokumentow_WZ(Baza_Produkty baza_p, Baza_Dokumenty_WZ baza_wz, String[] user_resp)
    {
        switch (user_resp[1])
        {
            case "dodaj":
                Dodaj_WZ(baza_wz);
                break;
            case "usun":
                Usun_WZ(baza_wz, user_resp);
                break;
            case "prz":
                Przeglondaj_WZ(baza_wz, user_resp);
                break;
            case "realizuj":
                Realizuj_WZ(baza_p, baza_wz);
                break;
            default:
                System.out.println("Blendny Input!\n\n");
                break;
        }
    }

    static void Dodaj_WZ(Baza_Dokumenty_WZ baza_wz)
    {
        String imie, nazwisko, nazwa_firmy, scierzka;
        Scanner sc = new Scanner(System.in);

        System.out.print("Podaj imie pracownika: ");
        imie = sc.nextLine();

        System.out.print("Podaj nazwisko pracownika: ");
        nazwisko = sc.nextLine();

        System.out.print("Podaj nazwe firmy: ");
        nazwa_firmy = sc.nextLine();

        System.out.print("Podaj scierzke do pliku z danymi w formacie CSV: ");
        scierzka = sc.nextLine();

        Pracownik nowy_pracownik = new Pracownik(imie, nazwisko);
        baza_wz.dodaj(new Dokument_WZ(nowy_pracownik, nazwa_firmy, scierzka), true);
    }

    static void Usun_WZ(Baza_Dokumenty_WZ baza_wz, String user_resp[])
    {
        Scanner sc = new Scanner(System.in);

        switch (user_resp[2])
        {
            case "nrid":
                System.out.print("Wpisz numer ID dokumentu do usuniencia: ");
                int numer_dokumentu = sc.nextInt();
                System.out.println("Usuwanie dokumentu: " + baza_wz.zwroc(numer_dokumentu).toString());

                baza_wz.usun(numer_dokumentu);
                break;
            case "firma":
                System.out.print("Wypisz nazwe firmy ktorej dokumenty chcesz usunac: ");
                String nazwa_firmy = sc.nextLine();
                System.out.println("Usuwanie dokumentow firmy: " + nazwa_firmy);

                baza_wz.usun(nazwa_firmy);
                break;
            default:
                System.out.println("Blendny Input!");
                break;
        }

    }

    static void Przeglondaj_WZ(Baza_Dokumenty_WZ baza_wz, String[] user_resp)
    {
        Scanner sc = new Scanner(System.in);

        switch (user_resp[2])
        {
            case "wszystkie":
                System.out.println("Wszystkie dokumenty to: ");

                baza_wz.wypisz();
                break;
            case "firma":
                System.out.print("Podaj dokument nazwe firmy ktorej dokumety chcesz wypisac: ");
                String nazwa_firmy = sc.nextLine();
                System.out.println("Wypisywanie dokumentow firmy: " + nazwa_firmy);

                baza_wz.wypisz(nazwa_firmy);
                break;
            default:
                System.out.println("Blendny Input!");
                break;

        }
    }

    static void Realizuj_WZ(Baza_Produkty baza_p, Baza_Dokumenty_WZ baza_wz)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Podaj numer identyfikacyjny dokumentu wz do zrealizowania: ");
        int numer_dokumentu = Integer.parseInt(sc.nextLine());

        Dokument_WZ nowy_dokument_wz = baza_wz.zwroc(numer_dokumentu);
        if (nowy_dokument_wz != null)
        {
            System.out.println("Realizowanie dokumentu: " + baza_wz.zwroc(numer_dokumentu).toString());
            baza_p.usun(nowy_dokument_wz);
        } else
        {
            System.err.println("Bledny numer identyfikacyjny dokumentu!");
        }

    }

    // Czesc knontroli bazy dokumentow PZ
    static void Baza_dokumentow_PZ(Baza_Produkty baza_p, Baza_Dokumenty_PZ baza_pz, String[] user_resp)
    {
        switch (user_resp[1])
        {
            case "dodaj":
                Dodaj_PZ(baza_pz);
                break;
            case "usun":
                Usun_PZ(baza_pz, user_resp);
                break;
            case "prz":
                Przeglondaj_PZ(baza_pz, user_resp);
                break;
            case "realizuj":
                Realizuj_PZ(baza_p, baza_pz);
                break;
            default:
                System.out.println("Blendny Input!");
                break;
        }

    }

    static void Dodaj_PZ(Baza_Dokumenty_PZ baza_pz)
    {
        String imie, nazwisko, nazwa_firmy, adres, scierzka;
        Scanner sc = new Scanner(System.in);

        System.out.print("Podaj imie pracownika: ");
        imie = sc.nextLine();

        System.out.print("Podaj nazwisko pracownika: ");
        nazwisko = sc.nextLine();

        System.out.print("Podaj nazwe firmy: ");
        nazwa_firmy = sc.nextLine();

        System.out.print("Podaj adres firmy: ");
        adres = sc.nextLine();

        System.out.print("Podaj scierzke do pliku z danymi w formacie CSV: ");
        scierzka = sc.nextLine();

        Pracownik nowy_pracownik = new Pracownik(imie, nazwisko);
        baza_pz.dodaj(new Dokument_PZ(nowy_pracownik, nazwa_firmy, adres, scierzka), true);

    }

    static void Usun_PZ(Baza_Dokumenty_PZ baza_pz, String[] user_resp)
    {
        Scanner sc = new Scanner(System.in);

        switch (user_resp[2])
        {
            case "nrid":
                System.out.print("Wpisz numer ID dokumentu do usuniencia: ");
                int numer_dokumentu = sc.nextInt();
                System.out.println("Usuwanie dokumentu: " + baza_pz.zwroc(numer_dokumentu).toString());

                baza_pz.usun(numer_dokumentu);
                break;
            case "firma":
                System.out.print("Wypisz nazwe firmy ktorej dokumenty chcesz usunac: ");
                String nazwa_firmy = sc.nextLine();
                System.out.println("Usuwanie dokumentow firmy: " + nazwa_firmy);

                baza_pz.usun(nazwa_firmy);
                break;
                
            case "adres":
                System.out.print("Wypisz adres z ktorego dokumenty chcesz usunac: ");
                String adres_firmy = sc.nextLine();
                System.out.println("Usuwanie dokumentow firmy: " + adres_firmy);

                baza_pz.usun_po_adresie(adres_firmy);
                break;
                
                
            default:
                System.out.println("Blendny Input!");
                break;

        }

    }

    static void Przeglondaj_PZ(Baza_Dokumenty_PZ baza_pz, String[] user_resp)
    {
        Scanner sc = new Scanner(System.in);

        switch (user_resp[2])
        {
            case "wszystkie":
                System.out.println("Wszystkie dokumenty to: ");

                baza_pz.wypisz();
                break;
            case "firma":
                System.out.print("Podaj dokument nazwe firmy ktorej dokumety chcesz wypisac: ");
                String nazwa_firmy = sc.nextLine();
                System.out.println("Wypisywanie dokumentow firmy: " + nazwa_firmy);

                baza_pz.wypisz_po_nazwie(nazwa_firmy);
                break;
            case "adres":
                System.out.print("Podaj dokument nazwe firmy ktorej dokumety chcesz wypisac: ");
                String adres_firmy = sc.nextLine();
                System.out.println("Wypisywanie dokumentow z podanego adresu: " + adres_firmy);

                baza_pz.wypisz_po_adresie(adres_firmy);
                break;
            default:
                System.out.println("Blendny Input!");
                break;

        }

    }

    static void Realizuj_PZ(Baza_Produkty baza_p, Baza_Dokumenty_PZ baza_pz)
    {
        System.out.print("Podaj numer identyfikacyjny dokumentu wz do zrealizowania: ");

        Scanner sc = new Scanner(System.in);

        int numer_dokumentu = Integer.parseInt(sc.nextLine());

        Dokument_PZ nowy_dokument_pz = baza_pz.zwroc(numer_dokumentu);
        if (nowy_dokument_pz != null)
        {
            System.out.println("Realizowanie dokumentu: " + baza_pz.zwroc(numer_dokumentu).toString());
            baza_p.dodaj(baza_pz.zwroc(numer_dokumentu));
        } else
        {
            System.err.println("Bledny numer identyfikacyjny dokumentu!");
        }

    }

}
