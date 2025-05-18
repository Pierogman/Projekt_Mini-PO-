package bazadanychmagazynudrzwi_app;

//To jest główna klasa tego porgramu. Z tego miejsca będą inicjowane pozostałe
//części programu. 

public class BazaDanychMagazynuDrzwi_App
{
    public static void main(String[] args)
    {
        // To jest test działania aplikacj
        
        Dane_Producenta producent_1 = new Dane_Producenta("Dobre Dzwi","Ekobuk dab");
        Dane_Producenta producent_2 = new Dane_Producenta("Zle Dzwi","Bab lux");
        Dane_Producenta producent_3 = new Dane_Producenta("JD Dzwi","Sosna swierk klamka");
        Dane_Producenta producent_4 = new Dane_Producenta("Dobre Dzwi","Serbro oscerznica");
        
        Wymiary wymiary_1 = new Wymiary(80,10,20);
        Wymiary wymiary_2 = new Wymiary(100,300,20);
        Wymiary wymiary_3 = new Wymiary(100,300,20);
        
        Drzwi drzwi_1 = new Drzwi(1,producent_1, wymiary_1, "Dab", 1);
        Drzwi drzwi_2 = new Drzwi(1,producent_2, wymiary_2, "Bialy" ,1);
        
        Oscerznice oscierznica_1 = new Oscerznice(1, producent_4, wymiary_1, "Bialy");
        Oscerznice oscierznica_2 = new Oscerznice(1, producent_3, wymiary_2, "Bialy");
        Oscerznice oscierznica_3 = new Oscerznice(1, producent_4, wymiary_3, "Bialy");
        
        Klamki klamka_1 = new Klamki(1, producent_4, "Srebro");
        Klamki klamka_2 = new Klamki(1, producent_4, "Srebro");
        Klamki klamka_3 = new Klamki(1, producent_4, "Srebro");
        
        
        Baza_Produkty baza_p = new Baza_Produkty();
        
        baza_p.dodaj(drzwi_1);
        baza_p.dodaj(drzwi_2);
        baza_p.dodaj(oscierznica_1);
        baza_p.dodaj(oscierznica_2);
        baza_p.dodaj(oscierznica_3);
        baza_p.dodaj(klamka_1);
        baza_p.dodaj(klamka_2);
        baza_p.dodaj(klamka_3);
        
        System.out.println("To sa wszystkie dodane produkty:");
        baza_p.wypisz();
        
        System.out.println("To sa wszystkie pordukty plrducenta Dobre Dzwi");
        baza_p.wypisz("Dobre Dzwi");
        
        System.out.println("To sa wszystkie produktu o wymiarach: 100 x 300 x 20");
        baza_p.wypisz(wymiary_3);
    }
    
}
