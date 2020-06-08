package ductsizer;
import maths.DataTool;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Fitting {
	private Type a_type;
	private ASHRAE_DB a_ASHRAE;
	private double[] a_loss;
	private String[] a_nmparam;
	private double[] a_param;
	
	public enum ASHRAE_DB{
		CD3_1,
		CD3_3,
		SD4_1,
		SD4_2,
		CR3_1,
		CR3_6,
		ER3_1,
		ER4_1,
		ER4_3,
		ER5_1,
		ER5_3,
		SR3_1,
		SR4_1,
		SR4_3,
		SR5_11,
		SR5_13,
		SR5_15,
		CUSTOM
	}
	
	public enum Type{
		Elbow,
		Tee,
		Cross,
		Transition,
		Unclassified
	}
	public Fitting(ASHRAE_DB type, double[] param) {
		a_loss = new double[3];
		switch (type) {
		case CD3_1:
			a_type = Type.Elbow;
			a_ASHRAE = ASHRAE_DB.CD3_1;
			a_nmparam = new String[1];
			a_nmparam[0] = "D";
			break;
		case CD3_3:
			a_type = Type.Elbow;
			a_ASHRAE = ASHRAE_DB.CD3_1;
			a_nmparam = new String[1];
			a_nmparam[0] = "D";
			break;
		case SD4_1:
			a_type = Type.Transition;
			a_ASHRAE = ASHRAE_DB.SD4_1;
			a_nmparam = new String[2];
			a_nmparam[0] = "th";
			a_nmparam[1] = "A_0/A_1";
			break;
		case SD4_2:
			a_type = Type.Transition;
			a_ASHRAE = ASHRAE_DB.SD4_2;
			a_nmparam = new String[2];
			a_nmparam[0] = "th";
			a_nmparam[1] = "A_0/A_1";
			break;
		case CR3_1:
			a_type = Type.Elbow;
			a_ASHRAE = ASHRAE_DB.CR3_1;
			a_nmparam = new String[3];
			a_nmparam[0] = "th";
			a_nmparam[1] = "H/W";
			a_nmparam[2] = "r/W";
			break;
		case CR3_6:
			a_type = Type.Elbow;
			a_ASHRAE = ASHRAE_DB.CR3_6;
			a_nmparam = new String[2];
			a_nmparam[0] = "th";
			a_nmparam[1] = "H/W";
			break;
		case ER3_1:
			a_type = Type.Elbow;
			a_ASHRAE = ASHRAE_DB.ER3_1;
			a_nmparam = new String[2];
			a_nmparam[0] = "W_1/W_0";
			a_nmparam[1] = "H/W_0";
			break;
		case ER4_1:
			a_type = Type.Transition;
			a_ASHRAE = ASHRAE_DB.ER4_1;
			a_nmparam = new String[2];
			a_nmparam[0] = "th";
			a_nmparam[1] = "A_0/A_1";
			break;
		case ER4_3:
			a_type = Type.Transition;
			a_ASHRAE = ASHRAE_DB.ER4_3;
			a_nmparam = new String[2];
			a_nmparam[0] = "th";
			a_nmparam[1] = "A_0/A_1";
			break;
		case ER5_1:
			break;
		case ER5_3:
			a_type = Type.Tee;
			a_ASHRAE = ASHRAE_DB.ER5_3;
			a_nmparam = new String[2];
			a_nmparam[0] = "Q_b/Q_c";
			a_nmparam[1] = "Q_s/Q_c";
			break;
		case SR3_1:
			a_type = Type.Elbow;
			a_ASHRAE = ASHRAE_DB.SR3_1;
			a_nmparam = new String[2];
			a_nmparam[0] = "W_0/W_1";
			a_nmparam[1] = "H/W_1";
			break;
		case SR4_1:
			a_type = Type.Transition;
			a_ASHRAE = ASHRAE_DB.SR4_1;
			a_nmparam = new String[2];
			a_nmparam[0] = "th";
			a_nmparam[1] = "A_0/A_1";
			break;
		case SR4_3:
			break;
		case SR5_11:
			break;
		case SR5_13:
			break;
		case SR5_15:
			break;
		case CUSTOM:
			break;
		}
		a_param = param;
		setLoss();
	}
	
	public Fitting() {
		a_ASHRAE = ASHRAE_DB.CUSTOM;
		a_loss = new double[3];
		a_param = new double[5];
	}
	
	public boolean setLoss() {
		switch(a_ASHRAE) {
		case CD3_1:
			double[] D = {75, 100, 125, 150, 180, 200, 230, 250};
			double[] data = {0.3, 0.21, 0.16, 0.14, 0.12, 0.11, 0.11, 0.11};
			a_loss[0] = DataTool.interpolate(D, a_param[0], data);
			break;
		case CD3_3:
			double[] D1 = {75, 100, 125, 150, 180, 200, 230, 250};
			double[] data1 = {0.18, 0.13, 0.1, 0.08, 0.07, 0.07, 0.07, 0.07};
			a_loss[0] = DataTool.interpolate(D1, a_param[0], data1);
			break;
		case SD4_1:
			double[] A0A1a = {0.1, 0.167, 0.25, 0.39, 0.5, 0.64, 1., 2., 4., 6, 10., 16.};
			double[] th5 = {0, 3, 5, 10, 15, 20, 30, 45, 60, 90, 120, 150, 180};
			double[][] data10 = {
					{0., 0.12, 0.09, 0.05, 0.05, 0.05, 0.05, 0.06, 0.08, 0.19, 0.29, 0.37, 0.43},
					{0., 0.11, 0.08, 0.05, 0.04, 0.04, 0.04, 0.06, 0.07, 0.18, 0.28, 0.36, 0.42},
					{0., 0.1, 0.07, 0.05, 0.04, 0.04, 0.04, 0.06, 0.07, 0.17, 0.27, 0.35, 0.41},
					{0., 0.1, 0.07, 0.05, 0.05, 0.05, 0.05, 0.06, 0.06, 0.16, 0.25, 0.32, 0.36},
					{0., 0.07, 0.06, 0.05, 0.05, 0.05, 0.05, 0.06, 0.07, 0.13, 0.18, 0.23, 0.24},
					{0., 0.07, 0.07, 0.05, 0.04, 0.04, 0.04, 0.05, 0.06, 0.09, 0.13, 0.17, 0.19},
					{0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.},
					{0., 0.59, 0.51, 0.43, 0.52, 0.76, 1.26, 1.32, 1.3, 1.26, 1.23, 1.21, 1.19},
					{0., 3.15, 2.78, 2.51, 3.38, 4.77, 7.38, 9.7, 10.88, 10.29, 10.08, 9.96, 9.84},
					{0., 6.55, 6.08, 6.44, 9.14, 11.92, 17.35, 23.58, 27.58, 26.71, 26.32, 26.15, 25.99},
					{0., 19.50, 18.25, 20., 27.3, 38., 58.5, 76., 80., 83.4, 84., 83.35, 82.7},
					{0., 45.82, 44.8, 50.18, 73.73, 96.77, 153.6, 215.04, 225.28, 225.28, 225.28, 225.28, 225.28}
			};
			a_loss[0] = DataTool.interpolate2D(th5, a_param[0], A0A1a, a_param[1], data10);
			break;
		case SD4_2:
			double[] A0A1b = {0.1, 0.167, 0.25, 0.5, 1., 2., 4., 6, 10., 16.};
			double[] th6 = {0, 3, 5, 10, 15, 20, 30, 45, 60, 90, 120, 150, 180};
			double[][] data11 = {
					{0., 0.12, 0.09, 0.05, 0.05, 0.05, 0.05, 0.06, 0.08, 0.19, 0.29, 0.37, 0.43},
					{0., 0.11, 0.08, 0.05, 0.05, 0.05, 0.05, 0.06, 0.07, 0.19, 0.28, 0.37, 0.42},
					{0., 0.1, 0.07, 0.05, 0.05, 0.05, 0.05, 0.06, 0.07, 0.17, 0.27, 0.35, 0.41},
					{0., 0.08, 0.07, 0.06, 0.07, 0.06, 0.05, 0.06, 0.07, 0.13, 0.19, 0.23, 0.24},
					{0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.},
					{0., 0.57, 0.55, 0.61, 0.87, 1., 1.2, 1.3, 1.3, 1.3, 1.28, 1.24, 1.2},
					{0., 2.6, 2.84, 3.92, 5.72, 7.2, 8.32, 9.28, 9.92, 10.24, 10.24, 10.24},
					{0., 6.57, 6.75, 10.62, 15.84, 18.9, 22.5, 25.74, 27.9, 28.44, 28.44, 28.35, 28.26},
					{0., 17.25, 18.75, 30., 45., 53.0, 63.5, 75., 84., 89., 89l, 88.5, 88.},
					{0., 42.75, 48.13, 77.57, 116.74, 136.45, 164.1, 196.86, 224.26, 241.92, 241.92, 240.38, 238.59}
			};
			
			a_loss[0] = DataTool.interpolate2D(th6, a_param[0], A0A1b, a_param[1], data11);
			break;
		case CR3_1:
			double[] HW = {0.25, 0.50, 0.75, 1.0, 1.5, 2.0, 3.0, 4.0, 5.0, 6.0, 8.0};
			double[] rW = {0.5, 0.75, 1.00, 1.5, 2.0};
			double[] th = {0., 20., 30., 45., 60., 75., 90., 110., 130., 150., 180.};
			double[][] data2 = {
					{1.53, 1.38, 1.29, 1.18, 1.06, 1.00, 1.00, 1.06, 1.12, 1.16, 1.18},
					{0.57, 0.52, 0.48, 0.44, 0.40, 0.39, 0.39, 0.40, 0.42, 0.43, 0.44},
					{0.27, 0.25, 0.23, 0.21, 0.19, 0.18, 0.18, 0.19, 0.20, 0.21, 0.21},
					{0.22, 0.20, 0.19, 0.17, 0.15, 0.14, 0.14, 0.15, 0.16, 0.17, 0.17},
					{0.20, 0.18, 0.16, 0.15, 0.14, 0.13, 0.13, 0.14, 0.14, 0.15, 0.15}
				};
		double[] data3 = {0., 0.31, 0.45, 0.6, 0.78, 0.9, 1., 1.13, 1.20, 1.28, 1.4};
			a_loss[0] = DataTool.interpolate2D(HW, a_param[1], rW, a_param[2], data2) 
					* DataTool.interpolate(th, a_param[0], data3);
			break;
		case CR3_6:
			double[] HW1 = {0.25, 0.5, 0.75, 1., 1.5, 2.0, 3.0, 4.0, 5.0, 6.0, 8.0};
			double[] th1 = {20, 30, 45, 60, 75, 90};
			double[][] data4 = {{0.08, 0.08, 0.08, 0.07, 0.07, 0.07, 0.06, 0.06, 0.05, 0.05, 0.05},
					{0.18, 0.17, 0.17, 0.16, 0.15, 0.15, 0.13, 0.13, 0.12, 0.12, 0.11},
					{0.38, 0.37, 0.36, 0.34, 0.33, 0.31, 0.28, 0.27, 0.26, 0.25, 0.24},
					{0.60, 0.59, 0.57, 0.55, 0.52, 0.49, 0.46, 0.43, 0.41, 0.39, 0.38},
					{0.89, 0.87, 0.84, 0.81, 0.77, 0.73, 0.67, 0.63, 0.61, 0.58, 0.57},
					{1.30, 1.27, 1.23, 1.18, 1.13, 1.07, 0.98, 0.92, 0.89, 0.85, 0.83}};
			a_loss[0] = DataTool.interpolate2D(HW1, a_param[1], th1, a_param[0], data4);
			break;
		case ER3_1:
			double[] W1W0 = {0.6, 0.8, 1.0, 1.2, 1.4, 1.6, 2.0};
			double[] HW0 = {0.25, 1.0, 4.0, 100.0};
			double[][] data5 = {{1.76, 1.43, 1.24, 1.14, 1.09, 1.06, 1.06},
					{1.70, 1.36, 1.15, 1.02, 0.95, 0.90, 0.84},
					{1.46, 1.10, 0.90, 0.81, 0.76, 0.72, 0.66},
					{1.50, 1.04, 0.79, 0.69, 0.63, 0.60, 0.55}};
			a_loss[0] = DataTool.interpolate2D(W1W0, a_param[0], HW0, a_param[1], data5);
			break;
		case ER4_1:
			double[] th2 = {0., 3., 5., 10., 15., 20., 30., 45., 60., 90., 120., 150., 180.};
			double[] A0A1 = {0.063, 0.10, 0.167, 0.25, 0.50, 1., 2., 4., 6., 10.};
			double[][] data6 = {{0.0, 0.44, 0.27, 0.25, 0.27, 0.36, 0.56, 0.71, 0.86, 0.99, 0.99, 0.98, 0.98},
					{0.0, 0.41, 0.27, 0.23, 0.25, 0.34, 0.53, 0.69, 0.83, 0.94, 0.94, 0.92, 0.91},
					{0.0, 0.34, 0.28, 0.21, 0.23, 0.30, 0.48, 0.65, 0.76, 0.83, 0.83, 0.82, 0.80},
					{0.0, 0.26, 0.29, 0.17, 0.19, 0.25, 0.42, 0.60, 0.68, 0.70, 0.70, 0.68, 0.66},
					{0.0, 0.16, 0.24, 0.14, 0.13, 0.15, 0.24, 0.35, 0.37, 0.38, 0.37, 0.36, 0.35},
					{0.0, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
					{0.0, 0.30, 0.38, 0.25, 0.17, 0.17, 0.17, 0.23, 0.29, 0.49, 0.66, 0.81, 0.88},
					{0.0, 1.66, 1.25, 0.77, 0.70, 0.70, 0.70, 0.90, 1.09, 2.84, 4.36, 5.69, 6.57},
					{0.0, 4.05, 3.14, 1.76, 1.58, 1.58, 1.58, 2.12, 2.66, 6.71, 10.11, 13.13, 15.20},
					{0.0, 12.01, 9.39, 5.33, 5.00, 5.00, 5.00, 6.45, 7.93, 19.10, 28.60, 36.79, 42.79}};
			a_loss[0] = DataTool.interpolate2D(th2, a_param[0], A0A1, a_param[1], data6);
			break;
		case ER4_3:
			double[] th3 = {0., 3., 5., 10., 15., 20., 30., 45., 60., 90., 120., 150., 180.};
			double[] A0A1_1 = {0.063, 0.10, 0.167, 0.25, 0.50, 1., 2., 4., 6., 10.};
			double[][] data7 = {{0.0, 0.17, 0.19, 0.30, 0.46, 0.53, 0.64, 0.77, 0.88, 0.95, 0.95, 0.94, 0.93},
					{0.0, 0.17, 0.19, 0.30, 0.45, 0.53, 0.64, 0.75, 0.84, 0.89, 0.89, 0.89, 0.88},
					{0.0, 0.18, 0.19, 0.30, 0.44, 0.53, 0.63, 0.72, 0.78, 0.79, 0.79, 0.79, 0.79},
					{0.0, 0.16, 0.18, 0.25, 0.36, 0.45, 0.52, 0.58, 0.62, 0.64, 0.64, 0.64, 0.64},
					{0.0, 0.14, 0.14, 0.15, 0.22, 0.25, 0.30, 0.33, 0.33, 0.33, 0.32, 0.31, 0.30},
					{0.0, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
					{0.0, 0.30, 0.27, 0.26, 0.28, 0.25, 0.19, 0.23, 0.27, 0.52, 0.75, 0.91, 0.95},
					{0.0, 1.60, 1.14, 0.84, 0.85, 0.86, 0.76, 0.90, 1.09, 2.78, 4.30, 5.65, 6.55},
					{0.0, 3.89, 3.04, 1.84, 1.77, 1.78, 1.73, 2.18, 2.67, 6.67, 10.07, 13.09, 15.18},
					{0.0, 11.8, 9.31, 5.4, 5.18, 5.15, 5.05, 6.44, 7.94, 19.06, 28.55, 36.75, 42.75}};
			a_loss[0] = DataTool.interpolate2D(th3, a_param[0], A0A1_1, a_param[1], data7);
		case ER5_1:
			break;
		case ER5_3:
			double[] Q_Qc = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
			double[] data5_a = {-19.38, -3.75, -0.74, 0.48, 0.66, 0.75, 0.85, 0.77, 0.83, 0.83};
			double[] data5_b = {22.15, 11.91, 6.54, 3.74, 2.23, 1.33, 0.76, 0.38, 0.1, 0.};
			a_loss[0] = DataTool.interpolate(Q_Qc, a_param[0], data5_a);
			a_loss[1] = DataTool.interpolate(Q_Qc, a_param[1], data5_b);
			break;
		case SR3_1:
			double[] W0W1_1 = {0.6, 0.8, 1.0, 1.2, 1.4, 1.6, 2.0};
			double[] HW1_1 = {0.25, 1.0, 4.0, 100.};
			double[][] data8 = {{0.63, 0.92, 1.24, 1.64, 2.14, 2.71, 4.24},
					{0.61, 0.87, 1.15, 1.47, 1.86, 2.30, 3.36},
					{0.53, 0.70, 0.90, 1.17, 1.49, 1.84, 2.64},
					{0.54, 0.67, 0.79, 0.99, 1.23, 1.54, 2.20}};
			a_loss[0] = DataTool.interpolate2D(W0W1_1, a_param[0], HW1_1, a_param[1], data8);
			break;
		case SR4_1:
			double[] th4 = {0., 3., 5., 10., 15., 20., 30., 45., 60., 90., 120., 150., 180.};
			double[] A0A1_2 = {0.1, 0.167, 0.25, 0.5, 1., 2., 4., 6., 10., 16.};
			double[][] data9 = {{0., 0.12, 0.09, 0.05, 0.05, 0.05, 0.05, 0.06, 0.08, 0.19, 0.29, 0.37, 0.43},
					{0., 0.11, 0.09, 0.05, 0.04, 0.04, 0.04, 0.06, 0.07, 0.19, 0.28, 0.36, 0.42},
					{0., 0.10, 0.08, 0.05, 0.04, 0.04, 0.04, 0.06, 0.07, 0.18, 0.27, 0.36, 0.41},
					{0., 0.08, 0.09, 0.06, 0.04, 0.04, 0.04, 0.06, 0.07, 0.12, 0.17, 0.20, 0.27},
					{0., 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
					{0., 0.64, 0.96, 0.54, 0.52, 0.62, 0.94, 1.40, 1.48, 1.52, 1.48, 1.44, 1.40},
					{0., 4.16, 4.64, 2.72, 3.09, 4.00, 6.72, 9.60, 10.88, 11.2, 11.2, 10.88, 10.56},
					{0., 12.24, 10.08, 7.38, 8.1, 10.8, 17.28, 23.4, 27.36, 29.88, 29.88, 29.34, 28.8},
					{0., 40.5, 27.2, 23.3, 25.1, 34., 52.84, 69., 82.5, 93.5, 93.5, 92.4, 91.3},
					{0., 112.64, 68.35, 63.74, 67.84, 92.93, 142.13, 182.53, 220.16, 254.21, 251.9, 249.6}};
			a_loss[0] = DataTool.interpolate2D(th4, a_param[0], A0A1_2, a_param[1], data9);
			break;
		case SR4_3:
			break;
		case SR5_11:
			break;
		case SR5_13:
			break;
		case SR5_15:
			break;
		case CUSTOM:
			break;
		}
		return true;
	}
	public boolean setLoss(double value){
		return true;
	}
	
	public double getLoss(int branch){
		return a_loss[branch];
	}
	
	public String report(int index) {
		String result = "";
		for(int i = 0; i < a_nmparam.length; i++) {
		result = a_nmparam[i].length() > 0 ? result + a_nmparam[i] + "=" 
				+ String.format("%.2f", a_param[i]) + ";": result;
		}
		result = result.substring(0, result.length() - 1);
		return "" + '\t' + "" + '\t' + a_type.name() + '\t' + a_ASHRAE.name().replace('_', '-') 
				+ '\t' + result + '\t' + String.format("%.2f", a_loss[index])
				+ '\t' + "C_" + String.valueOf(index);
	}	
	
	public static void main(String args[]) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		double[] param = new double[4];
		int sel = 0;
		while(sel < 1 || sel > 13) {
			try {
			System.out.println("1.  CD3-1");
			System.out.println("2.  CR3-1");
			System.out.println("3.  SD4-1");
			System.out.println("4.  SD4-2");
			System.out.println("5.  CR3-6");
			System.out.println("6.  ER3-1");
			System.out.println("7.  ER4-1");
			System.out.println("8.  ER4-3");
			System.out.println("9.  ER5-3");
			System.out.println("10.  SR3-1");
			System.out.println("11.  SR4-1");
			System.out.println("12. SR4-3");
			System.out.println("13. SR5-11");
			System.out.println("14. SR5-13");
			System.out.println("15. SR5-15");
			System.out.print("Select an option:");
			sel = Integer.parseInt(in.readLine());
			} catch (IOException ex) {
				sel = 0;
			}
		}
		try {
			switch (sel){
				case 1:{//CD3-1
					double D;
					System.out.print("D: ");
					D = Double.parseDouble(in.readLine());
					param[0] = D;
					Fitting CD31 = new Fitting(Fitting.ASHRAE_DB.CD3_1, param);
					System.out.println(CD31.report(0));
					break;
				}	
				case 2:{//CR3-1
					double H, W, rW, th;
					System.out.print("W: ");
					W = Double.parseDouble(in.readLine());
					System.out.print("H: ");
					H = Double.parseDouble(in.readLine());
					System.out.print("r/W: ");
					rW = Double.parseDouble(in.readLine());
					System.out.println("th: ");
					th = Double.parseDouble(in.readLine());
					param[0] = th;
					param[1] = H / W;
					param[2] = rW;
					Fitting CR31 = new Fitting(Fitting.ASHRAE_DB.CR3_1, param);
					System.out.println(CR31.report(0));
					break;
				}
				case 3:{//SD4-1
					double D0, D1, th;
					System.out.println("D0: ");
					D0 = Double.parseDouble(in.readLine());
					System.out.println("D1: ");
					D1 = Double.parseDouble(in.readLine());
					System.out.println("th: ");
					th = Double.parseDouble(in.readLine());
					param[0] = th;
					param[1] = D0 * D0 / D1 / D1;
					Fitting SD41 = new Fitting(Fitting.ASHRAE_DB.SD4_1, param);
					System.out.println(SD41.report(0));
					break;
				}
				case 4:{//SD4-2
					double D0, H1, W1, th;	
					System.out.println("D0: ");
					D0 = Double.parseDouble(in.readLine());
					System.out.println("W1: ");
					W1 = Double.parseDouble(in.readLine());
					System.out.println("H1: ");
					H1 = Double.parseDouble(in.readLine());
					System.out.println("th: ");
					th = Double.parseDouble(in.readLine());
					param[0] = th;
					param[1] = Math.PI * D0 * D0 / W1 / H1 / 4.;
					Fitting SD42 = new Fitting(Fitting.ASHRAE_DB.SD4_2, param);
					System.out.println(SD42.report(0));
					break;
				}
				case 5:{//CR3-6
					double H, W, th;
					System.out.print("W: ");
					W = Double.parseDouble(in.readLine());
					System.out.print("H: ");
					H = Double.parseDouble(in.readLine());
					System.out.print("th: ");
					th = Double.parseDouble(in.readLine());
					param[0] = th;
					param[1] = H / W;
					Fitting CR36 = new Fitting(Fitting.ASHRAE_DB.CR3_6, param);
					System.out.println(CR36.report(0));
					break;
				}
				case 6:{//ER3-1
					double H, W0, W1;
					System.out.print("W0: ");
					W0 = Double.parseDouble(in.readLine());
					System.out.print("H: ");
					H = Double.parseDouble(in.readLine());
					System.out.print("W1: ");
					W1 = Double.parseDouble(in.readLine());
					param[0] = W1 / W0;
					param[1] = H / W0;
					Fitting ER31 = new Fitting(Fitting.ASHRAE_DB.ER3_1, param);
					System.out.println(ER31.report(0));
					break;
				}
				case 7:{//ER4-1
					double th, H0, H1;
					System.out.print("Hin: ");
					H0 = Double.parseDouble(in.readLine());
					System.out.print("Hout: ");
					H1 = Double.parseDouble(in.readLine());
					System.out.print("th: ");
					th = Double.parseDouble(in.readLine());
					param[0] = th;
					param[1] = H0 / H1;
					Fitting ER41 = new Fitting(Fitting.ASHRAE_DB.ER4_1, param);
					System.out.println(ER41.report(0));
					break;
				}
				case 8:{//ER4-3
					double th, W, H, D;
					System.out.print("W: ");
					W = Double.parseDouble(in.readLine());
					System.out.print("H: ");
					H = Double.parseDouble(in.readLine());
					System.out.print("D: ");
					D = Double.parseDouble(in.readLine());
					System.out.print("th: ");
					th = Double.parseDouble(in.readLine());
					param[0] = th;
					param[1] = W * H / D / D * 4 / Math.PI;
					Fitting ER43 = new Fitting(Fitting.ASHRAE_DB.ER4_3, param);
					System.out.println(ER43.report(0));
					break;
				}
				case 9:{//ER5-30 
					double Qb, Qc;
					System.out.print("Qc: ");
					Qc = Double.parseDouble(in.readLine());
					System.out.print("Qb: ");
					Qb = Double.parseDouble(in.readLine());
					param[0] = Qb / Qc;
					param[1] = 1. - param[0];
					Fitting ER53 = new Fitting(Fitting.ASHRAE_DB.ER5_3, param);
					System.out.println(ER53.report(0));
					System.out.println(ER53.report(1));
					break;
				}
				case 10:{//SR3-1
					double W0, W1, H;
					System.out.print("W0: ");
					W0 = Double.parseDouble(in.readLine());
					System.out.print("H: ");
					H = Double.parseDouble(in.readLine());
					System.out.print("W1: ");
					W1 = Double.parseDouble(in.readLine());
					param[0] = W0 / W1;
					param[1] = H / W1;
					Fitting SR31 = new Fitting(Fitting.ASHRAE_DB.SR3_1, param);
					System.out.println(SR31.report(0));
					break;
				}
				case 11:{//SR4-1
					double th, H0, H1;
					System.out.print("th: ");
					th = Double.parseDouble(in.readLine());
					System.out.print("Hin: ");
					H1 = Double.parseDouble(in.readLine());
					System.out.print("Hout: ");
					H0 = Double.parseDouble(in.readLine());
					param[0] = th;
					param[1] = H0 / H1;
					Fitting SR41 = new Fitting(Fitting.ASHRAE_DB.SR4_1, param);
					System.out.println(SR41.report(0));
					break;
				}
				case 12:{//SR4-3
					break;
				}
				case 13:{//SR5-11
					break;
				}
				case 14:{//SR5-13
					break;
				}
				case 15:{//SR5-15
					break;
				}
				default:{
					break;
				}
		}
		} catch (IOException ex) {
			
		}
	}
}
