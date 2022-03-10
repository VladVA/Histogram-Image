package MethodsPackage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Histogram extends PictureToArray{	
	
	//metoda pentru calcularea histogramei si care returneaza nivelul maxim al acesteia
	public int createHistogram(BufferedImage image)
	{
		double[][] a = convertPictureToArray(image); // calculam matricea de niveluri de gri corespunzatoare imaginii
		int max = 0;
		int valMax = 0;
		int m = a.length;
		int n = a[0].length;
		int[] histogram = new int[256]; //initializam un vector care sa retina de cate ori a fost intalnit un nivel de gri
		
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                int intensity = (int) a[i][j]; //este luat nivelul de gri al fiecarui pixel
                histogram[intensity]++; // si contorizata aparitia sa
            }
        }
		
        
		for (int i = 0; i < 256; i++)
			System.out.println(i + " - " + histogram[i]); // afisam valorile histogramei
		
		for (int i = 0; i < 256; i++)
			if(histogram[i] > max) // calculam maximul histogramei(nivelul de gri cu aparitia cea mai mare)
			{
				max = histogram[i];
				valMax = i; 
			}
		System.out.println("Gri maxim: " + valMax); // afisam nivelul maxim al histogramei
		return valMax;
	}
	
	//metoda pentru procesarea imaginii finale, care va contine 11 niveluri de gri – toti pixelii care au 
	//nivelul de gri +/- 5 fata de nivelul de gri al maximului histogramei
	public void histogramImage(BufferedImage image, String outputFile)
	{
		double[][] a = convertPictureToArray(image); // calculam matricea de niveluri de gri corespunzatoare imaginii
		int m = a.length;
		int n = a[0].length;
		int max = createHistogram(image);//calculam nivelul maxim de gri al histogramei
		
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
            	
            	int intensity = (int) a[i][j];
            	if(intensity < max - 5 || intensity > max + 5) // prelucram imaginea initiala in asa fel incat sa ramana doar pixeli
            	{											   // cu nivelul corespunzator cerintei
	            	Color color = new Color(255, 255, 255);
	                image.setRGB(i, j, color.getRGB()); // pixeli care nu corespund cerintei sunt facuti albi                      
            	}
            }
        }

        try {
            File output_file = new File(outputFile);
            //scriem imaginea la locatia data din linie de comanda
            ImageIO.write(image, "bmp", output_file);
  
            System.out.println("Histograma a fost salvata.");
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
	}
	
	//metoda care calculeaza produsul unor numere primite cu varargs
	public int produs (int... a){
		int size = a.length;
		int prod = 1;
		for(int i = 0; i < size; i++)
			prod *= a[i];
		return prod;
	}	
}
