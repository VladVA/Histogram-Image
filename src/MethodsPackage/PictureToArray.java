package MethodsPackage;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class PictureToArray extends Intensity{
	
	//metoda pentru crearea unei matrici corespunzatoare matricii imaginii, dar care contine nivelul de gri al fiecarui pixel
	public double[][] convertPictureToArray(BufferedImage image)
	{
		int width = image.getWidth();
        int height = image.getHeight();
        double[][] pixels = new double[width][height]; //construim matricea de aceeasi marime cu cea a imaginii
        
        for (int col = 0; col < width; col++)
        {
            for (int row = 0; row < height; row++)
            {
                Color color = new Color(image.getRGB(col,row));
                pixels[col][row] = getIntensity(color); // luam fiecare pixel si calculam nivelul sau de gri
            }
        }
        return pixels;
	}
	
	@Override
	//metoda pentru calcularea nivelului de gri(a luminozitatii) unui pixel
	public double getIntensity (Color color){
		//preluam valoarea de la fiecare canal de culoare
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		double greyLevel = 0.299*r + 0.587*g + 0.114*b; //calculam nivelul de gri(luminozitatea) pixel-ului
		return greyLevel;
	}

	@Override
	//suprascriem metoda din interfata
	public double getIntensity() {
		return 0;
	}


	
}
