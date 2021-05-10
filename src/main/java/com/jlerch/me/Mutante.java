package com.jlerch.me;

import javax.management.RuntimeErrorException;

public class Mutante {

	
	char getBaseNitrogenada(String[] dna, int fila,int columna){
		
		if (fila<0 || fila>(dna.length-1)) { return '-';};
		
		String d = dna[fila];
			
		if (columna<0 || columna>(d.length()-1)) { return '-';};
			
		char bn = d.charAt(columna);
		return bn;
	}
	
	
	private int check(String[] dna, int fila, int columna, int incFila, int incCol) {
		
		
		char muestra = getBaseNitrogenada(dna,fila,columna);
		
		System.out.print("Check... " + fila + "," + columna + ": " + muestra + " ( " + incFila + "," + incCol + " )  ");
		
		for(int c=1;c<=3;c++) {
			char muestra2 = getBaseNitrogenada(dna,fila+c*incFila,columna+c*incCol);
			System.out.print("..." + muestra2);
			if(muestra2!=muestra) 
				{
				System.out.println(" -> NO" );
				return 0;
				}
		}
		
		System.out.println(" -> SI" );
		return 1; //encontro 4 muestras iguales en la direccion de busqueda;
	}
	
	public boolean isMutant(String[] dna) {
		
		int encontrados=0;
		
		for(int fila=0; fila< dna.length; fila++) 
		{	
			
			String cadena = dna[fila];

			for(int columna=0; columna< dna.length; columna++) {

				encontrados    += check(dna,fila,columna,0,1); //horizontal
				if(encontrados<2) encontrados    += check(dna,fila,columna, 1 ,0);   //vertical
				if(encontrados<2) encontrados    += check(dna,fila,columna, 1, 1);   //oblicuo1
				if(encontrados<2) encontrados    += check(dna,fila,columna,-1,-1);   //oblicuo2
				
				if(encontrados>1) return true;
				
			}
		}
		
		return false;
		
		
	}
	
	
	
}
