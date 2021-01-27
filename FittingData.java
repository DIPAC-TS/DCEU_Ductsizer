package DCEU_ductsizer;

import java.util.ArrayList;

import maths.DataTool;

public class FittingData {
	private double[] a_range;
	private double[] a_data;
	
	public enum ASHRAE_Database{
		CD3_1,
		CR3_1,
		CR3_6,
		ER3_1,
		ED5_1,
		ER5_3
		
	}
	
	public FittingData(ASHRAE_Database fitting) {
		
	}
	
	public FittingData() {
		a_range = new double[1];
		a_data = new double[1];
	}
	
	public void addData(double[] range, double[] data) {
			a_range = range;
			a_data = data;
	}
	
	private double getPLoss(double x) {
		int i = 0;
		boolean exit = false;
		while(i < a_range.length && exit == false) {
			if(x < a_range[i + 1]) {
				exit = true;
			}
			i++;
		}
		double ratio = (x - a_range[i - 1]) / (a_range[i] - a_range[i - 1]);
		return (a_data[i] - a_data[i - 1]) * ratio + a_data[i - 1];
	}
	
	public static void main(String[] args){
		/*
		 * ER5-3
		;
		double[] data = {-19.38, -3.75, -0.74, 0.48, 0.66, 0.75, 0.85, 0.77, 0.83, 0.83};
		System.out.println(DataTool.interpolate(QbQc, 0.25, data));
		*/
		
		/*
		 * ED5-1
		double[] QbQc = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
		double[] AsAc = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
		double[] AbAc = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
		double[][][] data = {
				{
					{  -0.18,   0.72,  0.84,  0.87,  0.88, 0.89, 0.90, 0.92, 0.98},
					{  -3.52,   0.12,  0.64,  0.78,  0.82, 0.85, 0.88, 0.93, 1.12},
					{  -9.34,  -0.95,  0.28,  0.60,  0.71, 0.76, 0.80, 0.87, 1.10},
					{ -17.96,  -2.70, -0.40,  0.22,  0.43, 0.50, 0.53, 0.54, 0.60},
					{ -28.58,  -4.65, -1.05, -0.07,  0.26, 0.37, 0.40, 0.41, 0.42},
					{ -41.45,  -6.97, -1.77, -0.35,  0.12, 0.28, 0.32, 0.32, 0.32},
					{ -56.61,  -9.66, -2.58, -0.65,  0.00, 0.21, 0.27, 0.26, 0.24},
					{ -74.08, -12.74, -3.49, -0.97, -0.12, 0.16, 0.23, 0.22, 0.18},
					{ -93.84, -16.21, -4.50, -1.30, -0.23, 0.13, 0.21, 0.19, 0.14},
					{-114.85, -20.06, -5.61, -1.66, -0.34, 0.11, 0.21, 0.18, 0.11}
				},{
					{  -0.17,   0.71, 0.83, }
					{}
				},{}}};
		*/
		/*
		 * CR3_1
		 */
		/*
		
		*/
		
	}
}
