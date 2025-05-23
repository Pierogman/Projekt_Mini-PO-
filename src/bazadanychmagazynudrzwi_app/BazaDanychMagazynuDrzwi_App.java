package bazadanychmagazynudrzwi_app;

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
        int user_resp;
        while (!wyloncz_program)
        {
            System.out.println("""
                               Witam w bazie danych magazynu drzwi! 
                               
                               Wybierz jaka czesc bazy chcialbys edytowac:
                               
                               [1] Narzedzia "Bazy prodoktow" 
                               [2] Narzedzia "Bazy dokumentow WZ"
                               [3] Narzedzia "Bazy dokumentow PZ"
                               
                               [0] Opusc program (Dane zostana zapisane)                               
                               """);
            System.out.print("Wybrana opcja: ");
            user_resp = sc.nextInt();

            switch (user_resp)
            {
                // Narzędzia bazy porduktów:
                case 1:
                    Baza_produktow(baza_p, gen_csv, baza_wz, baza_pz);
                    break;
                case 2:
                    Baza_dokumentow_WZ(baza_p, baza_wz);
                    break;
                case 3:
                    Baza_dokumentow_PZ(baza_p, baza_pz);
                    break;
                case 0:
                    System.out.println("Wylaczanie programu");
                    wyloncz_program = true;
                    break;
                default:
                    System.out.println("Blendny Input!\n\n");
            }
        }

        baza_p.zapisz_stan();
        baza_wz.zapisz_stan();
        baza_pz.zapisz_stan();

        System.out.println("Do widzenia!");

    }

    // Czesc kontroli bazy porduktow
    static void Baza_produktow(Baza_Produkty baza_p, Generator_CSV gen_csv, Baza_Dokumenty_WZ baza_wz, Baza_Dokumenty_PZ baza_pz)
    {
        Boolean wyloncz_segment = false;
        while (!wyloncz_segment)
        {
            System.out.println("""
                           Narzedzia BAZY PRODOKTOW
                           [1] Przegladanie  
                           [2] Generowanie csv 
                           [0] Powrot                     
                           """);
            Scanner sc = new Scanner(System.in);
            System.out.print("Wybrana opcja: ");
            int user_resp = sc.nextInt();
            switch (user_resp)
            {
                case 1:
                    Przeglondaj_P(baza_p);
                    break;
                case 2:
                    Generowanie_csv(gen_csv, baza_p);
                    break;
                case 0:
                    System.out.println("Wracanie od ekranu glownego!");
                    wyloncz_segment = true;
                    break;
                default:
                    System.out.println("Blendny Input!\n\n");
                    break;
            }
        }
    }

    static void Przeglondaj_P(Baza_Produkty baza_p)
    {
        Boolean wyloncz_segment = false;
        while (!wyloncz_segment)
        {
            System.out.println("""
                           Narzedzia BAZY PRODUKTOW -> PRZEGLADAJ
                           Wybierz jakie produkty chcesz wyswietlic?
                           [1] Wszystkie 
                           [0] Powrot        
                           """);
            Scanner sc = new Scanner(System.in);
            System.out.print("Wybrana opcja: ");
            int user_resp = Integer.parseInt(sc.nextLine());

            switch (user_resp)
            {
                case 1:
                    System.out.println("To sa aktualnie wszystkie produkty w bazie:");
                    baza_p.wypisz();
                    break;
                case 0:
                    System.out.println("Wracanie od ekranu glownego!");
                    wyloncz_segment = true;
                    break;
                default:
                    System.out.println("Blendny Input!\n\n");
                    break;

            }
        }
    }

    static void Generowanie_csv(Generator_CSV gen_csv, Baza_Produkty baza_p)
    {
        System.out.println("""
                           Narzedzia danych BAZY PRODUKTOW -> GENEROWANIE CSV
                           Podaj scierzke pliku w ktorym chcesz aby zostal wygenerowany text.
                           """);
        Scanner sc = new Scanner(System.in);
        System.out.print("Scierzka pliku");
        String scierzka = sc.nextLine();
        baza_p.wypisz();
        gen_csv.generuj(baza_p, scierzka);

    }

    // Czensc kontroli bazy dokumentow WZ
    static void Baza_dokumentow_WZ(Baza_Produkty baza_p, Baza_Dokumenty_WZ baza_wz)
    {
        Boolean wyloncz_segment = false;
        while (!wyloncz_segment)
        {
            System.out.println("""
                           Narzedzia BAZY DOKUMENTOW WZ:
                           [1] Dodaj WZ
                           [2] Usun WZ
                           [3] Przegladaj
                           [4] Realizuj WZ
                           [0] Powrot 
                           """);
            Scanner sc = new Scanner(System.in);
            System.out.print("Wybrana opcja: ");
            int user_resp = sc.nextInt();
            switch (user_resp)
            {
                case 1:
                    Dodaj_WZ(baza_wz);
                    break;
                case 2:
                    Usun_WZ(baza_wz);
                    break;
                case 3:
                    Przeglondaj_WZ(baza_wz);
                    break;
                case 4:
                    Realizuj_WZ(baza_p, baza_wz);
                case 0:
                    System.out.println("Wracanie od ekranu glownego!");
                    wyloncz_segment = true;
                    break;
                default:
                    System.out.println("Blendny Input!\n\n");
                    break;
            }

        }
    }

    static void Dodaj_WZ(Baza_Dokumenty_WZ baza_wz)
    {
        System.out.println("""
                           Narzedzia BAZY DOKUMENTOW WZ -> DODAJ WZ
                           Podarzaj za instrukcjami porgramu aby dodac nowy dokument wz do bazy danych.
                           """);
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

    static void Usun_WZ(Baza_Dokumenty_WZ baza_wz)
    {
        Boolean wyloncz_segment = false;
        while (!wyloncz_segment)
        {
            System.out.println("""
                           Narzedzia BAZY DOKUMENTOW WZ -> USUN WZ
                           Wybierz jak chcesz usunac dokument!
                           [1] Po numerze ID
                           [2] Po nazwie firmy
                           [0] Powrot
                           """);
            Scanner sc = new Scanner(System.in);
            System.out.print("Wybrana opcja: ");
            int user_resp = sc.nextInt();

            switch (user_resp)
            {
                case 1:
                    System.out.print("Wybrany numer ID: ");
                    sc.reset();
                    int user_resp_2 = sc.nextInt();
                    baza_wz.usun(user_resp_2);
                    System.out.print("Usunieto wybrany dokument!");
                    break;
                case 2:
                    System.out.print("Dokumenty wybranej firmy: ");
                    sc.reset();
                    String user_resp_3 = sc.nextLine();
                    baza_wz.usun(user_resp_3);
                    System.out.print("Usunieto dokumenty wybranej firmy!");
                    break;
                case 0:
                    wyloncz_segment = true;
                    break;
                default:
                    System.out.println("Blendny Input!");
                    break;

            }
        }

    }

    static void Przeglondaj_WZ(Baza_Dokumenty_WZ baza_wz)
    {
        Boolean wyloncz_segment = false;
        while (!wyloncz_segment)
        {
            System.out.println("""
                           Narzedzia BAZY DOKUMENTOW WZ -> USUN WZ:
                           Wybierz jakie dokumenty chcesz wypisac?
                           [1] Wszystkie 
                           [2] Konkretnej firmy
                           [0] Powrot
                           """);
            Scanner sc = new Scanner(System.in);
            System.out.print("Wybrana opcja: ");
            int user_resp = sc.nextInt();

            switch (user_resp)
            {
                case 1:
                    System.out.println("Wszystkie dokumenty to: ");
                    baza_wz.wypisz();
                    System.out.print("\n");
                    break;
                case 2:
                    System.out.print("Dokumenty wybranej firmy: ");
                    String user_resp_3 = sc.nextLine();
                    baza_wz.wypisz(user_resp_3);
                    System.out.print("\n");
                    break;
                case 0:
                    wyloncz_segment = true;
                    break;
                default:
                    System.out.println("Blendny Input!");
                    break;

            }
        }
    }

    static void Realizuj_WZ(Baza_Produkty baza_p, Baza_Dokumenty_WZ baza_wz)
    {
        System.out.println("""
                           Narzedzia BAZY PRODOKTOW -> REALIZUJ WZ
                           Wybierz numer indetyfikacyjny dokumentu wz do zrealizowania.
                           """);
        System.out.print("Wybrany dokument: ");
        Scanner sc = new Scanner(System.in);

        int user_resp = Integer.parseInt(sc.nextLine());

        Dokument_WZ nowy_dokument_wz = baza_wz.zwroc(user_resp);
        if (nowy_dokument_wz != null)
        {
            baza_p.usun(nowy_dokument_wz);
        } else
        {
            System.err.println("Bledny numer identyfikacyjny dokumentu!");
        }

    }

    // Czesc knontroli bazy dokumentow PZ
    static void Baza_dokumentow_PZ(Baza_Produkty baza_p, Baza_Dokumenty_PZ baza_pz)
    {
        Boolean wyloncz_segment = false;
        while (!wyloncz_segment)
        {
            System.out.println("""
                           Narzedzia BAZY DOKUMENTOW PZ:
                           [1] Dodaj PZ
                           [2] Usun PZ                           
                           [3] Przegladaj  
                           [4] Realizuj PZ
                           [0] Powrot 
                           """);
            Scanner sc = new Scanner(System.in);
            System.out.print("Wybrana opcja: ");
            int user_resp = sc.nextInt();
            switch (user_resp)
            {
                case 1:
                    Dodaj_PZ(baza_pz);
                    break;
                case 2:
                    Usun_PZ(baza_pz);
                    break;
                case 3:
                    Przeglondaj_PZ(baza_pz);
                    break;
                case 4:
                    Realizuj_PZ(baza_p, baza_pz);
                case 0:
                    wyloncz_segment = true;
                    break;
                default:
                    System.out.println("Blendny Input!");
                    break;
            }

        }

    }

    static void Dodaj_PZ(Baza_Dokumenty_PZ baza_pz)
    {
        System.out.println("""
                           Narzedzia BAZY DOKUMENTOW PZ -> DODAJ PZ
                           Podarzaj za instrukcjami porgramu aby dodac nowy dokument wz do bazy danych.
                           """);
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

        System.out.println("Dodano nowy dokument wz!");

    }

    static void Usun_PZ(Baza_Dokumenty_PZ baza_pz)
    {
        Boolean wyloncz_segment = false;
        while (!wyloncz_segment)
        {
            System.out.println("""
                           Narzedzia BAZY DOKUMENTOW PZ -> USUN PZ
                           Wybierz jak chcesz usunac dokument!
                           [1] Po numerze ID
                           [2] Po nazwie firmy
                           [0] Powrot
                           """);
            Scanner sc = new Scanner(System.in);
            System.out.print("Wybrana opcja: ");
            int user_resp = sc.nextInt();

            switch (user_resp)
            {
                case 1:
                    System.out.print("Wybrany numer ID: ");
                    sc.reset();
                    int user_resp_2 = sc.nextInt();
                    baza_pz.usun(user_resp_2);
                    System.out.print("Usunieto wybrany dokument!");
                    break;
                case 2:
                    System.out.print("Dokumenty wybranej firmy: ");
                    sc.reset();
                    String user_resp_3 = sc.nextLine();
                    baza_pz.usun(user_resp_3);
                    System.out.print("Usunieto dokumenty wybranej firmy!");
                    break;
                case 0:
                    wyloncz_segment = true;
                    break;
                default:
                    System.out.println("Blendny Input!");
                    break;

            }
        }

    }

    static void Przeglondaj_PZ(Baza_Dokumenty_PZ baza_pz)
    {
        Boolean wyloncz_segment = false;
        while (!wyloncz_segment)
        {
            System.out.println("""
                           Narzedzia BAZY DOKUMENTOW PZ -> USUN PZ
                           Wybierz jakie dokumenty chcesz wypisac?
                           [1] Wszystkie 
                           [2] Konkretnej firmy
                           [3] Z konkretnego adresu
                           [0] Powrot
                           """);
            Scanner sc = new Scanner(System.in);
            System.out.print("Wybrana opcja: ");
            int user_resp = sc.nextInt();
            sc.nextLine();

            switch (user_resp)
            {
                case 1:
                    System.out.print("\nZnalezione dokumenty:");
                    baza_pz.wypisz();
                    System.out.print("\n");
                    break;
                case 2:
                    System.out.print("\nZnalezione dokumenty podanej firmy:");
                    String user_resp_3 = sc.nextLine();
                    baza_pz.wypisz_po_nazwie(user_resp_3);
                    System.out.print("\n");
                    break;
                case 3:
                    System.out.print("\nZnalezione dokumenty z podanego adresu:");
                    String user_resp_4 = sc.nextLine();
                    baza_pz.wypisz_po_adresie(user_resp_4);
                    System.out.print("\n");
                    break;
                case 0:
                    wyloncz_segment = true;
                    break;
                default:
                    System.out.println("Blendny Input!");
                    break;

            }
        }

    }

    static void Realizuj_PZ(Baza_Produkty baza_p, Baza_Dokumenty_PZ baza_pz)
    {
        System.out.println("""
                           Narzedzia BAZY PRODOKTOW -> REALIZUJ PZ
                           Wybierz numer indetyfikacyjny dokumentu pz do zrealizowania.
                           """);
        System.out.print("Wybrany dokument: ");
        Scanner sc = new Scanner(System.in);

        int user_resp = Integer.parseInt(sc.nextLine());

        Dokument_PZ nowy_dokument_pz = baza_pz.zwroc(user_resp);
        if (nowy_dokument_pz != null)
        {
            baza_p.dodaj(baza_pz.zwroc(user_resp));
        } else
        {
            System.err.println("Bledny numer identyfikacyjny dokumentu!");
        }

    }

}
