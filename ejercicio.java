package intervalos;

import java.util.*;

public class ejercicio {
	static class trabajo
	{
		int hr_imicio, hr_fin, proriti;
		trabajo(int start, int finish, int profit)
		{
			this.hr_imicio = start;
			this.hr_fin = finish;
			this.proriti = profit;
		}
	}
	static int trabajosSinConflicto(trabajo arreglo[], int i)
	{	
		for (int j = i - 1; j >= 0; j--)
		{
			if (arreglo[j].hr_fin <= arreglo[i - 1].hr_imicio)
				return j;
		}
		return -1;
	}
	static int encontrar_maximo_ganancia_rec(trabajo arreglo[], int n)
	{
	
		if (n == 1) return arreglo[n-1].proriti;

		int inclProf = arreglo[n-1].proriti;
		int i = trabajosSinConflicto(arreglo, n);
		if (i != -1)
		inclProf += encontrar_maximo_ganancia_rec(arreglo, i+1);

		int exclProf = encontrar_maximo_ganancia_rec(arreglo, n-1);

		return Math.max(inclProf, exclProf);
	}

	static int encontrar_maxima_ganancia(trabajo arreglo[], int n)
	{
		Arrays.sort(arreglo,new Comparator<trabajo>(){
		public int compare(trabajo j1,trabajo j2)
			{
			return j1.hr_fin-j2.hr_fin;
			}
		});

		return encontrar_maximo_ganancia_rec(arreglo, n);
	}

	public static void main(String args[])
	{
	int m = 4;
	trabajo arr[] = new trabajo[m];
		arr[0] = new trabajo(3, 10, 20);
		arr[1] = new trabajo(1, 2, 50);
		arr[2] = new trabajo(6, 19, 100);
		arr[3] = new trabajo(2, 100, 200);
		int n =arr.length;
		System.out.println("La ganancia óptima es " + encontrar_maxima_ganancia(arr, n));
	}
}

