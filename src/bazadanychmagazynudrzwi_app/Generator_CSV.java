
package bazadanychmagazynudrzwi_app;

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
public class Generator_CSV extends Baza_Produkty
{

    private final String dafault_scierzka = "./Dane/CSV.csv";
    private final String save_format = "%d;%d;%s;%s;%s;%s";
    
    public void generuj(Baza_Produkty baza_produkty)
    {
        ArrayList<String>  wybrane_produkty = baza_produkty.generuj_subliste(save_format);
        generuj_plik(wybrane_produkty, dafault_scierzka);
    }
    
    public void generuj(Baza_Produkty baza_produkty, String wybrana_scierzka)
    {
        ArrayList<String>  wybrane_produkty = baza_produkty.generuj_subliste(save_format);
        generuj_plik(wybrane_produkty, wybrana_scierzka);
    }
    
    public void generuj(Baza_Produkty baza_produkty, String wybrana_scierzka, int numer_PZ)
    {
        ArrayList<String>  wybrane_produkty = baza_produkty.generuj_subliste(numer_PZ, save_format );
        generuj_plik(wybrane_produkty, wybrana_scierzka);
    }
    
    public void generuj(Baza_Produkty baza_produkty, String wybrana_scierzka, Dane_Producenta dane_producenta)
    {
        ArrayList<String>  wybrane_produkty = baza_produkty.generuj_subliste(dane_producenta, save_format);
        generuj_plik(wybrane_produkty, wybrana_scierzka);
    }
      
    public void generuj_plik(ArrayList<String> wybrane_produkty, String wybrana_scierzka)
    {
        try
        {
            Path p_baza_scierzka_list = Paths.get(wybrana_scierzka);

            if (!Files.exists(p_baza_scierzka_list))
            {
                Files.createFile(p_baza_scierzka_list);
            }

            BufferedWriter writer_ld = Files.newBufferedWriter(p_baza_scierzka_list, StandardCharsets.UTF_8);

            for (String produkt : wybrane_produkty)
            {
                System.out.println(produkt);
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
