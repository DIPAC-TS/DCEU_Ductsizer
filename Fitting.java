package DCEU_ductsizer;
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
		SD5_1,
		CR3_1,
		CR3_6,
		CR3_9,
		CR9_1,
		CR9_3,
		CR9_4,
		ER3_1,
		ER4_1,
		ER4_3,
		ER5_2,
		ER5_3,
		SR3_1,
		SR4_1,
		SR4_3,
		SR5_5,
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
		Damper,
		Unclassified,
		Wye
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
			a_ASHRAE = ASHRAE_DB.CD3_3;
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
		case SD5_1:
			a_type = Type.Wye;
			a_ASHRAE = ASHRAE_DB.SD5_1;
			a_nmparam = new String[3];
			a_nmparam[0] = "Q_b/Q_c";
			a_nmparam[1] = "A_b/A_c";
			a_nmparam[2] = "A_s/A_c";
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
			a_nmparam[0] = "H/W";
			a_nmparam[1] = "th";
			break;
		case CR3_9:
			a_type = Type.Elbow;
			a_ASHRAE = ASHRAE_DB.CR3_9;
			a_nmparam = new String[1];
			a_nmparam[0] = "Miltered th=90° 40 mm vane space";
			break;
		case CR9_1:
			a_type = Type.Damper;
			a_ASHRAE = ASHRAE_DB.CR9_1;
			a_nmparam = new String[2];
			a_nmparam[0] = "th";
			a_nmparam[1] = "H/W";
			break;
		case CR9_3:
			a_type = Type.Damper;
			a_ASHRAE = ASHRAE_DB.CR9_3;
			a_nmparam = new String[1];
			a_nmparam[0] = "Parallel and Opposed 3V Blades, Open";
			break;
		case CR9_4:
			a_type = Type.Damper;
			a_nmparam = new String[1];
			a_nmparam[0] = "Parallel and Opposed Airfoil Blades, Open";
			a_ASHRAE = ASHRAE_DB.CR9_4;
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
		case ER5_2:
			a_type = Type.Tee;
			a_ASHRAE = ASHRAE_DB.ER5_2;
			a_nmparam = new String[2];
			a_nmparam[0] = "Q_b/Q_c";
			a_nmparam[1] = "Q_s/Q_c";
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
			a_type = Type.Transition;
			a_ASHRAE = ASHRAE_DB.SR4_3;
			a_nmparam = new String[2];
			a_nmparam[0] = "th";
			a_nmparam[1] = "A_0/A_1";
			break;
		case SR5_5:
			a_type = Type.Tee;
			a_ASHRAE = ASHRAE_DB.SR5_5;
			a_nmparam = new String[3];
			a_nmparam[0] = "Q_b/Q_c";
			a_nmparam[1] = "A_b/A_c";
			a_nmparam[2] = "Q_s/Q_c";
			break;
		case SR5_11:
			break;
		case SR5_13:
			a_type = Type.Tee;
			a_ASHRAE = ASHRAE_DB.SR5_13;
			a_nmparam = new String[4];
			a_nmparam[0] = "Q_b/Q_c";
			a_nmparam[1] = "A_b/A_c";
			a_nmparam[2] = "Q_s/Q_c";
			a_nmparam[3] = "A_s/A_c";
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
		case SD5_1:
			double[] QbQc = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
			double[] AbAc = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
			double[] AsAc = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
			double[][] datasd51a = {
					{0.38, 0.38, 0.48, 0.45, 0.40, 0.36, 0.32, 0.29, 0.26},
					{2.25, 0.38, 0.31, 0.38, 0.47, 0.48, 0.47, 0.45, 0.42},
					{6.29, 1.02, 0.38, 0.30, 0.33, 0.38, 0.45, 0.48, 0.48},
					{12.41, 2.25, 0.74, 0.38, 0.30, 0.31, 0.35, 0.38, 0.45},
					{20.58, 4.01, 1.37, 0.62, 0.38, 0.30, 0.30, 0.32, 0.36},
					{30.78, 6.29, 2.25, 1.02, 0.56, 0.38, 0.31, 0.30, 0.31},
					{43.02, 9.10, 3.36, 1.57, 0.85, 0.52, 0.38, 0.31, 0.30},
					{57.29, 12.41, 4.71, 2.25, 1.22, 0.74, 0.50, 0.38, 0.32},
					{73.59, 16.24, 6.29, 3.06, 1.69, 1.02, 0.67, 0.48, 0.38},
					{91.92, 20.58, 8.11, 4.01, 2.25, 1.37, 0.90, 0.62, 0.47}
			    };
			double[][] datasd51b = {
					{0.13, 0.24, 0.57, 0.74, 0.74, 0.70, 0.65, 0.60, 0.56},
					{0.20, 0.13, 0.15, 0.16, 0.28, 0.57, 0.69, 0.74, 0.75},
					{0.90, 0.14, 0.13, 0.14, 0.15, 0.16, 0.20, 0.42, 0.57},
					{2.88, 0.20, 0.14, 0.13, 0.14, 0.15, 0.15, 0.16, 0.34},
					{6.25, 0.38, 0.17, 0.14, 0.13, 0.14, 0.14, 0.15, 0.15},
					{11.88, 0.90, 0.20, 0.14, 0.14, 0.13, 0.14, 0.14, 0.15},
					{18.62, 1.72, 0.33, 0.18, 0.16, 0.14, 0.13, 0.15, 0.14},
					{26.88, 2.88, 0.50, 0.20, 0.15, 0.14, 0.13, 0.13, 0.14},
					{36.45, 4.46, 0.90, 0.30, 0.19, 0.16, 0.15, 0.14, 0.13},
					{45.0, 6.25, 1.44, 0.38, 0.20, 0.17, 0.12, 0.13, 0.14},
			    };
			a_loss[0] = DataTool.interpolate2D(QbQc, a_param[0], AbAc, a_param[1], datasd51a);
			a_loss[1] = DataTool.interpolate2D(QbQc, 1 - a_param[0], AsAc, a_param[2], datasd51b);
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
			double[] th1 = {0, 20, 30, 45, 60, 75, 90};
			double[][] data4 = {{0., 0., 0., 0., 0., 0., 0., 0., 0., 0., 0.},
					{0.08, 0.08, 0.08, 0.07, 0.07, 0.07, 0.06, 0.06, 0.05, 0.05, 0.05},
					{0.18, 0.17, 0.17, 0.16, 0.15, 0.15, 0.13, 0.13, 0.12, 0.12, 0.11},
					{0.38, 0.37, 0.36, 0.34, 0.33, 0.31, 0.28, 0.27, 0.26, 0.25, 0.24},
					{0.60, 0.59, 0.57, 0.55, 0.52, 0.49, 0.46, 0.43, 0.41, 0.39, 0.38},
					{0.89, 0.87, 0.84, 0.81, 0.77, 0.73, 0.67, 0.63, 0.61, 0.58, 0.57},
					{1.30, 1.27, 1.23, 1.18, 1.13, 1.07, 0.98, 0.92, 0.89, 0.85, 0.83}};
			a_loss[0] = DataTool.interpolate2D(HW1, a_param[0], th1, a_param[1], data4);
			break;
		case CR3_9:
			a_loss[0] = 0.11;
			break;
		case CR9_1:
			double[] th7 = {0, 10, 20, 30, 40, 50, 60, 70, 90};
			double[] HW2 = {0.1, 0.5, 1.0, 1.5, 2.0};
			double[][] data12 = {{0.04, 0.30, 1.10, 3.0, 8.0, 23., 60.0, 100., 190., 9999.},
					{0.04, 0.30, 1.10, 3.0, 8.0, 23., 60., 100., 190., 9999.},
					{0.04, 0.30, 1.10, 3.0, 8.0, 23., 60., 100., 190., 9999.},
					{0.04, 0.35, 1.25, 3.6, 10., 29., 80., 155., 230., 9999.},
					{0.04, 0.35, 1.25, 3.6, 10., 29., 80., 155., 230., 9999.}};
			a_loss[0] = DataTool.interpolate2D(th7, a_param[0], HW2, a_param[1], data12);
			break;
		case CR9_3:
			a_loss[0] = 0.37;
			break;
		case CR9_4:
			a_loss[0] = 0.18;
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
			break;
		case ER5_2:
			double[] Q_Qc2 = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
			double[] data12_a = {-14., -2.38, -.5, 0.65, 1.03, 1.17, 1.19, 1.33, 1.51, 1.44};
			double[] data12_b = {22.15, 11.91, 6.54, 3.74, 2.23, 1.33, 0.76, 0.38, 0.1, 0.};
			a_loss[0] = DataTool.interpolate(Q_Qc2, a_param[0], data12_a);
			a_loss[1] = DataTool.interpolate(Q_Qc2, a_param[1], data12_b);
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
			double[] thsr43 = {0., 3., 5., 10., 15., 20., 30., 45., 60., 90., 120., 150., 180.,};
			double[] A0A1_sr43 = {0.10, 0.16, 0.25, 0.50, 1.00, 2.00, 4.00, 6.00, 10.0, 16.0};
			double[][] datasr43 = {{0.0, 0.12, 0.09, 0.05, 0.05, 0.05, 0.05, 0.06, 0.08, 0.19, 0.29, 0.37, 0.43},
					{0.0, 0.11, 0.08, 0.05, 0.05, 0.05, 0.05, 0.06, 0.07, 0.19, 0.28, 0.37, 0.42},
					{0.0, 0.10, 0.07, 0.05, 0.05, 0.05, 0.05, 0.06, 0.07, 0.17, 0.27, 0.35, 0.41},
					{0.0, 0.08, 0.07, 0.06, 0.07, 0.06, 0.05, 0.06, 0.07, 0.13, 0.19, 0.23, 0.24},
					{0.0, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
					{0.0, 0.57, 0.55, 0.61, 0.87, 1.00, 1.20, 1.30, 1.30, 1.30, 1.28, 1.24, 1.20},
					{0.0, 2.60, 2.84, 3.92, 5.72, 7.20, 8.32, 9.28, 9.92, 10.24, 10.24, 10.24, 10.24},
					{0.0, 6.57, 6.75, 10.62, 15.84, 18.90, 22.50, 25.74, 27.90, 28.44, 28.44, 28.35, 28.26},
					{0.0, 17.25, 18.75, 30.00, 45.00, 53.00, 63.50, 75.00, 84.0, 89.00, 89.00, 88.50, 88.0},
					{0.0, 42.75, 48.13, 77.57, 116.74, 136.45, 164.10, 196.86, 224.26, 241.92, 241.92, 240.38, 238.59}};
			a_loss[0] = DataTool.interpolate2D(thsr43, a_param[0], A0A1_sr43, a_param[1], datasr43);
			break;
		case SR5_5:
			double[] QbQc1 = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
			double[] AbAc1 = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
			double[] QsQc = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 1.0};
			double[][] data10_a = {{2.06, 1.20, 0.99, 0.87, 0.88, 0.87, 0.87, 0.86, 0.86},
					{5.15, 1.92, 1.29, 1.03, 0.99, 0.94, 0.92, 0.90, 0.89},
					{10.30, 3.12, 1.78, 1.28, 1.16, 1.06, 1.01, 0.97, 0.94},
					{15.90, 4.35, 2.24, 1.48, 1.11, 0.88, 0.80, 0.75, 0.72},
					{24.31, 6.31, 3.04, 1.90, 1.35, 1.03, 0.91, 0.83, 0.78},
					{34.60, 8.70, 4.03, 2.41, 1.65, 1.22, 1.04, 0.94, 0.87},
					{46.75, 11.53, 5.19, 3.01, 2.00, 1.44, 1.20, 1.06, 0.96},
					{60.78, 14.79, 6.53, 3.70, 2.40, 1.69, 1.38, 1.20, 1.07},
					{76.67, 18.49, 8.05, 4.49, 2.86, 1.98, 1.59, 1.36, 1.20}};
			double[] data10_b = {32.4, 6.4, 2.18, 0.9, 0.4, 0.18, 0.07, 0.03, 0.};
			a_loss[0] = DataTool.interpolate2D(QbQc1, a_param[0], AbAc1, a_param[1], data10_a);
			a_loss[1] = DataTool.interpolate(QsQc, 1. - a_param[0], data10_b);
 			break;
		case SR5_11:
			break;
		case SR5_13:
			double[] range = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
			double[][] data11_a = {{0.32, 0.33, 0.32, 0.34, 0.32, 0.37, 0.38, 0.39, 0.4},
					{0.31, 0.32, 0.41, 0.34, 0.32, 0.32, 0.33, 0.34, 0.35},
					{1.86, 1.65, 0.73, 0.47, 0.37, 0.34, 0.32, 0.32, 0.32},
					{3.56, 3.10, 1.28, 0.73, 0.51, 0.41, 0.36, 0.34, 0.32},
					{5.74, 4.93, 2.07, 1.12, 0.73, 0.54, 0.44, 0.38, 0.35},
					{8.48, 7.24, 3.10, 1.65, 1.03, 0.73, 0.56, 0.47, 0.41},
					{11.75, 10.00, 4.32, 3.31, 1.42, 0.98, .73, .58, .49},
					{15.57, 13.22, 5.74, 3.10, 1.90, 1.28, .94, .73, .60},
					{19.92, 16.90, 7.38, 4.02, 2.46, 1.65, 1.19, .91, .73}};
			double[][] data11_b = {{0.04, 0.01, 0., 0., 0., 0., 0., 0., 0.},
			{0.98, 0.04, 0.01, 0., 0., 0., 0., 0., 0.},
			{3.48, 0.31, 0.04, 0.01, 0., 0., 0., 0., 0.},
			{7.55, 0.98, 0.18, 0.04, 0.02, 0., 0., 0., 0.},
			{13.18, 2.03, 0.49, 0.13, 0.04, 0., 0.01, 0., 0.},
			{20.38, 3.48, 0.98, 0.31, 0.10, 0.04, 0.02, 0.01, 0.},
			{29.15, 5.32, 1.64, 0.60, 0.23, 0.09, 0.04, 0.02, 0.01},
			{39.48, 7.55, 2.47, 0.98, 0.42, 0.18, 0.08, 0.04, 0.02},
			{51.37, 10.17, 3.48, 1.46, 0.67, 0.31, 0.15, 0.07, 0.04}};
			a_loss[0] = DataTool.interpolate2D(range, a_param[0], range, a_param[1], data11_a);
			a_loss[1] = DataTool.interpolate2D(range, 1 - a_param[0], range, a_param[3], data11_b);
			break;
		case SR5_15:
			break;
		case CUSTOM:
			break;
		}
		return true;
	}
	public boolean setLoss(double value){
		a_loss = new double[1];
		a_loss[0] = value;
		return true;
	}
	
	public boolean setLoss(double[] value) {
		a_loss = value;
		return true;
	}
	
	public boolean setLoss(double value, int branch) {
		a_loss[branch] = value;
		return true;
	}
	
	public double[] getLoss() {
		return a_loss;
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
		boolean exit = false;
		boolean flag = false;
		int i;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		double D0, D1, D2, H0, H1, H2, W0, W1, W2, rW, th, Qb, Qc, Qs;
		double[] param = new double[4];
		String sel;
		ASHRAE_DB fitt = ASHRAE_DB.CUSTOM;
		try {
			while(exit == false) {
				while(flag == false) {
					i = 0;
					for(ASHRAE_DB val : ASHRAE_DB.values()) {
						if (i < 7){
							i++;
						} else{
							i = 0;
							System.out.println();
						}
						System.out.printf(val.toString().replace('_', '-') + '\t');
					}
					System.out.println();
					System.out.print("Select an option:");
					sel = in.readLine();
					sel = sel.toUpperCase();
					for(ASHRAE_DB val : ASHRAE_DB.values()) {
						if (sel.replace('-', '_').compareTo(val.toString()) == 0) {
							fitt = val;
							flag = true;
							break;
						}
					}
				}
				switch (fitt){
					case CD3_1:{
						System.out.print("D: ");
						D0 = Double.parseDouble(in.readLine());
						param[0] = D0;
						Fitting CD31 = new Fitting(Fitting.ASHRAE_DB.CD3_1, param);
						System.out.println(CD31.report(0));
						break;
					}
					case CD3_3:{
						System.out.print("D: ");
						D0 = Double.parseDouble(in.readLine());
						param[0] = D0;
						Fitting CD33 = new Fitting(Fitting.ASHRAE_DB.CD3_3, param);
						System.out.println(CD33.report(0));
						break;
					}
					case CR3_1:{
						System.out.print("W: ");
						W0 = Double.parseDouble(in.readLine());
						System.out.print("H: ");
						H0 = Double.parseDouble(in.readLine());
						System.out.print("r/W: ");
						rW = Double.parseDouble(in.readLine());
						System.out.print("th: ");
						th = Double.parseDouble(in.readLine());
						param[0] = th;
						param[1] = H0 / W0;
						param[2] = rW;
						Fitting CR31 = new Fitting(Fitting.ASHRAE_DB.CR3_1, param);
						System.out.println(CR31.report(0));
						break;
					}
					case SD4_1:{
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
					case SD4_2:{
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
					case SD5_1:
						System.out.println("D_in: ");
						D0 = Double.parseDouble(in.readLine());
						System.out.println("D_b: ");
						D1 = Double.parseDouble(in.readLine());
						System.out.println("D_c: ");
						D2 = Double.parseDouble(in.readLine());
						System.out.println("Q_in: ");
						Qc = Double.parseDouble(in.readLine());
						System.out.println("Q_b: ");
						Qb = Double.parseDouble(in.readLine());
						param[0] = Qb / Qc;
						param[1] = D1 * D1 / D0 / D0;
						param[2] = D2 * D2 / D0 / D0;
						Fitting SD51 = new Fitting(Fitting.ASHRAE_DB.SD5_1, param);
						System.out.println(SD51.report(0));
						System.out.println(SD51.report(1));
						break;
					case CR3_6:
						System.out.print("W: ");
						W0 = Double.parseDouble(in.readLine());
						System.out.print("H: ");
						H0 = Double.parseDouble(in.readLine());
						System.out.print("th: ");
						th = Double.parseDouble(in.readLine());
						param[0] = H0 / W0;
						param[1] = th;
						Fitting CR36 = new Fitting(Fitting.ASHRAE_DB.CR3_6, param);
						System.out.println(CR36.report(0));
						break;
					case CR3_9:
						param[0] = 1.;
						Fitting CR39 = new Fitting(Fitting.ASHRAE_DB.CR3_9, param);
						System.out.println(CR39.report(0));
						break;
					case CR9_1:
						System.out.print("W: ");
						W0 = Double.parseDouble(in.readLine());
						System.out.print("H: ");
						H0 = Double.parseDouble(in.readLine());
						System.out.print("th: ");
						th = Double.parseDouble(in.readLine());
						param[0] = th;
						param[1] = H0 / W0;
						Fitting CR91 = new Fitting(Fitting.ASHRAE_DB.CR9_1, param);
						System.out.println(CR91.report(0));
						break;
					case CR9_3:
						Fitting CR93 = new Fitting(Fitting.ASHRAE_DB.CR9_3, param);
						System.out.println(CR93.report(0));
						break;
					case CR9_4:
						Fitting CR94 = new Fitting(Fitting.ASHRAE_DB.CR9_4, param);
						System.out.println(CR94.report(0));
						break;
					case ER3_1:{
						System.out.print("W0: ");
						W0 = Double.parseDouble(in.readLine());
						System.out.print("H: ");
						H0 = Double.parseDouble(in.readLine());
						System.out.print("W1: ");
						W1 = Double.parseDouble(in.readLine());
						param[0] = W1 / W0;
						param[1] = H0 / W0;
						Fitting ER31 = new Fitting(Fitting.ASHRAE_DB.ER3_1, param);
						System.out.println(ER31.report(0));
						break;
					}
					case ER4_1:{
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
					case ER4_3:{
						System.out.print("W: ");
						W0 = Double.parseDouble(in.readLine());
						System.out.print("H: ");
						H0 = Double.parseDouble(in.readLine());
						System.out.print("D: ");
						D0 = Double.parseDouble(in.readLine());
						System.out.print("th: ");
						th = Double.parseDouble(in.readLine());
						param[0] = th;
						param[1] = W0 * H0 / D0 / D0 * 4 / Math.PI;
						Fitting ER43 = new Fitting(Fitting.ASHRAE_DB.ER4_3, param);
						System.out.println(ER43.report(0));
						break;
					}
					case ER5_2:{
						System.out.print("Qout: ");
						Qc = Double.parseDouble(in.readLine());
						System.out.print("Qb: ");
						Qb = Double.parseDouble(in.readLine());
						param[0] = Qb / Qc;
						param[1] = 1. - param[0];
						Fitting ER52 = new Fitting(Fitting.ASHRAE_DB.ER5_2, param);
						System.out.println(ER52.report(0));
						System.out.println(ER52.report(1));
						break;
					}
					case ER5_3:{
						System.out.print("Qout: ");
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
					case SR3_1:{
						System.out.print("W0: ");
						W0 = Double.parseDouble(in.readLine());
						System.out.print("H: ");
						H0 = Double.parseDouble(in.readLine());
						System.out.print("W1: ");
						W1 = Double.parseDouble(in.readLine());
						param[0] = W0 / W1;
						param[1] = H0 / W1;
						Fitting SR31 = new Fitting(Fitting.ASHRAE_DB.SR3_1, param);
						System.out.println(SR31.report(0));
						break;
					}
					case SR4_1:{
						System.out.print("Hin: ");
						H1 = Double.parseDouble(in.readLine());
						System.out.print("Hout: ");
						H0 = Double.parseDouble(in.readLine());
						System.out.print("th: ");
						th = Double.parseDouble(in.readLine());
						param[0] = th;
						param[1] = H0 / H1;
						Fitting SR41 = new Fitting(Fitting.ASHRAE_DB.SR4_1, param);
						System.out.println(SR41.report(0));
						break;
					}
					case SR4_3:
						System.out.print("Din: ");
						D1 = Double.parseDouble(in.readLine());
						System.out.print("Wout: ");
						W0 = Double.parseDouble(in.readLine());
						System.out.print("Hout: ");
						H0 = Double.parseDouble(in.readLine());
						System.out.print("th: ");
						th = Double.parseDouble(in.readLine());
						param[0] = th;
						param[1] = W0 * H0 / Math.PI / D1 / D1 * 4.;
						Fitting SR43 = new Fitting(Fitting.ASHRAE_DB.SR4_3, param);
						System.out.println(SR43.report(0));
					    break;
					case SR5_5:
						System.out.print("Win: ");
						W0 = Double.parseDouble(in.readLine());
						System.out.print("Hin: ");
						H0 = Double.parseDouble(in.readLine());
						System.out.print("Qin: ");;
						Qc = Double.parseDouble(in.readLine());
						System.out.print("Wb: ");
						W1 = Double.parseDouble(in.readLine());
						System.out.print("Qout: ");;
						Qs = Double.parseDouble(in.readLine());
						param[0] = 1 - Qs / Qc;
						param[1] = W1 / W0;
						param[2] = Qs / Qc;
						Fitting SR55 = new Fitting(Fitting.ASHRAE_DB.SR5_5, param);
						System.out.println(SR55.report(0));
						System.out.println(SR55.report(1));
						break;
					case SR5_11:
						break;
					case SR5_13:
						System.out.print("Win: ");
						W0 = Double.parseDouble(in.readLine());
						System.out.print("Hin: ");
						H0 = Double.parseDouble(in.readLine());
						System.out.print("Wb: ");
						W1 = Double.parseDouble(in.readLine());
						System.out.print("Hb: ");
						H1 = Double.parseDouble(in.readLine());
						System.out.print("Wout: ");
						W2 = Double.parseDouble(in.readLine());
						System.out.print("Hout: ");
						H2 = Double.parseDouble(in.readLine());
						System.out.print("Qin: ");;
						Qc = Double.parseDouble(in.readLine());
						System.out.print("Qout: ");;
						Qs = Double.parseDouble(in.readLine());
						param[0] = 1 - Qs / Qc;
						param[1] = W1 * H1 / W0 / H0;
						param[2] = Qs / Qc;
						param[3] = W2 * H2 / W0 / H0;
						Fitting SR513 = new Fitting(Fitting.ASHRAE_DB.SR5_13, param);
						System.out.println(SR513.report(0));
						System.out.println(SR513.report(1));
						break;
					case SR5_15:
						break;
					default:
						break;
				}
				System.out.println("Press any key to continue:");
				in.readLine();
				flag = false;
			}
		} catch (IOException ex){
			System.out.println(ex.getMessage());
			exit = true;
		}
	}
}
