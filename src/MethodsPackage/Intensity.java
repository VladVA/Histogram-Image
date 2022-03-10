package MethodsPackage;

import java.awt.Color;

//clasa abstracta ce implementeaza interfata 
//cu metoda abstracta pentru calcularea luminozitatii unui pixel
public abstract class Intensity implements IntensityInterface {
	public abstract double getIntensity (Color color);
	
}
