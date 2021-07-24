package DCEU_ductsizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duct {
	String a_name;
	ArrayList<Fitting> a_fittings;
	boolean a_fixedWidth;
	double a_flow;
	double[] a_size;
	double a_length;
	double a_E;
	TYPE a_type;
	
	enum REF{
		FLOW,
		SIZE,
		LOSS,
		VELOCITY
	}
	
	enum UNiTS{
		IP,
		SI
	}
	
	enum TYPE{
		RECTANGULAR,
		ROUND,
		OVAL
	}
	
	public Duct() {
		this(300., 1.);
	}
	
	public Duct(double diam, double len) {
		this(convertToSize(diam), len, TYPE.ROUND);
	}
	
	public Duct(double[] size, double len, TYPE type) {
		this(size, len, type, 0.09);
	}
	
	public Duct(double[] size, double len, TYPE type, double E) {
		a_name = "default";
		a_flow = 1000.;
		a_type = type;
		a_length = len;
		a_fixedWidth = false;
		switch(type) {
			case RECTANGULAR:{
				a_fixedWidth = true;
				a_size = new double[2];
				a_size[0] = size[0];
				a_size[1] = size[1];
				break;
			}
			case ROUND:{
				a_size = new double[1];
				a_size[0] = size[0];
				break;
			}
			default:{ //OVAL
				a_size = new double[2];
				if (size[0] > size[1]) {
					a_size[0] = size[0];
					a_size[1] = size[1];
				} else {
					a_size[1] = size[0];
					a_size[0] = size[1];
					if (size[0] == size[1]) {
						a_type = TYPE.ROUND;
					}
				}
			}
		}
		a_E = 0.09;
		setRoughness(E);
		a_fittings = new ArrayList<Fitting>();
	}
	
	public boolean setName(String name) {
		a_name = name;
		return true;
	}
	
	public String getName() {
		return a_name;
	}
	
	public boolean addFitting(Fitting fitt) {
		a_fittings.add(fitt);
		return true;
	}
	
	public boolean removeFitting(Fitting fitt) {
		a_fittings.remove(fitt);
		return true;
	}
	
	public boolean fixWidth(boolean yn) {
		if (a_type == TYPE.RECTANGULAR) {
			a_fixedWidth = yn;
			return true;
		} else
			return false;
	}
	
	public boolean IsWidthFixed() {
		return a_fixedWidth;
	}
	
	public boolean setFlow(double flow, REF ref) {
		double refval, e, rate;
		int index;
		if (flow < 0. || flow == getFlow()) {
			return false;
		}
		switch(ref) {
			case LOSS:
				refval = getLossRate();
				setFlow(flow, REF.SIZE);
				e = 1.0;
				rate = 100.;
				index = (a_fixedWidth) ? 1 : 0;
				while (e > 0.0001) {
					if (refval < getLossRate()) {
						a_size[index] += rate;
						if(refval > getLossRate()) {
							a_size[index] -= rate;
							rate /= 10.;
						}
					} else if (refval > getLossRate()) {
						a_size[index] -= rate;
						if (refval < getLossRate() || a_size[index] < 0.) {
							a_size[index] += rate;
							rate /= 10.;
						}
					}
					e = Math.abs(getLossRate() - refval);
				}
				return true;
			case VELOCITY:
				refval = getFluidVelocity();
				setFlow(flow, REF.SIZE);
				e = 1.0;
				rate = 100.;
				index = (a_fixedWidth) ? 1 : 0;
				while (e > 0.0001) {
					if (refval < getFluidVelocity()) {
						a_size[index] += rate;
						if(refval > getFluidVelocity()) {
							a_size[index] -= rate;
							rate /= 10.;
						}
					} else if (refval > getFluidVelocity()) {
						a_size[index] -= rate;
						if (refval < getFluidVelocity() || a_size[index] < 0.) {
							a_size[index] += rate;
							rate /= 10.;
						}
					}
					e = Math.abs(getFluidVelocity() - refval);
				}
				return true;
			case SIZE:
				a_flow = flow;
				return true;
			default: //FLOW
				return false;
		}
	}
	
	public double getFlow() {
		return a_flow;
	}
	
	public boolean setSize(double d) {
		return setDe(d);
	}
	
	public boolean setSize(double[] size, TYPE type) {
		switch(type) {
		case RECTANGULAR:
			//Pending to define
			return true;
		case ROUND:
			//Pending to define
			return true;
		default: //OVAL
			//Pending to define
			return true;
		}
	}
	
	public double[] getSize() {
		return a_size;
	}
	
	public boolean setLength(double l) {
		if (l < 0.)
			return false;
		else {
			a_length = l;
			return true;
		}
	}
	
	public double getLength() {
		return a_length;
	}
	
	public boolean setRoughness(double E) {
		if (E < 0.)
			return false;
		else { 
			a_E = E;
			return true;
		}
	}
	
	public double getRoughness() {
		return a_E;
	}
	
	public double getA() {
		switch (a_type) {
			case RECTANGULAR:{
				return a_size[0] * a_size[1] / 1.e6;
			}
			case ROUND:{
				return Math.PI * a_size[0] * a_size[0] / 4.e6;
			}
			default:{//OVAL
				return Math.PI * a_size[1] * a_size[1] / 4.e6
						+ a_size[1] * (a_size[0] - a_size[1]) / 1.e6;
			}
		}
	}
	
	public double getFluidA() {
		return Math.PI * getDe() * getDe() / 4.e6;
	}
	
	public double getP() {
		switch (a_type) {
			case RECTANGULAR:{
				return 2. * (a_size[0] + a_size[1]);
			}
			case ROUND:{
				return Math.PI * a_size[0];
			}
			default:{//OVAL
				return Math.PI * a_size[1] + 2. * (a_size[0] - a_size[1]);
			}
		}
	}
	
	public double getDh() {
		if (a_type == TYPE.ROUND) {
			return a_size[0];
		} else {
			return 4.e6 * getA() / getP();
		}
	}
	
	public boolean setDe(double d, REF ref) {
		if (d < 0.)
			return false;
		switch(ref) {
		case FLOW:
			// Pending to define
			return true;
		case LOSS:
			// Pending to define
			return true;
		case VELOCITY:
			// Pending to define
			return true;
		default://SIZE
			return false;
		}
	}
	
	public double getDe() {
		switch (a_type) {
			case RECTANGULAR:
				return 1.3 * Math.pow(a_size[0] * a_size[1], 0.625) 
						/ Math.pow(a_size[0] + a_size[1], 0.25);
			case ROUND:
				return a_size[0];
			default://OVAL
				return 1.55 * Math.pow(getA(), 0.625) / Math.pow(getP(), 0.25);
		}
	}
	
	private boolean setDe(double d) {
		if (d < 0.)
			return false;
		double e = 10.0;
		int index = (a_fixedWidth) ? 1 : 0;
		switch(a_type) {
		case RECTANGULAR:
			while(e > 0.01) {
				a_size[index] = d * a_size[index] / getDe();
				e = Math.abs(d - getDe());
			}
			return true;
		case ROUND:
			a_size[0] = d * getP() / getA() / 4.e6;
			return true;
		default: //OVAL
			// To define
			return true;	
		}
	}
	
	public double getVelocity(){
		return a_flow / getA() / 1000.;
	}
	
	public boolean setFluidVelocity(double v, REF ref) {
		if (v < 0.)
			return false;
		switch(ref) {
		case FLOW:
			// Pending to define
			return true;
		case LOSS:
			// Pending to define
			return true;
		case SIZE:
			// Pending to define
			return true;
		default: //VELOCITY
			return false;
		}
	}
	
	public double getFluidVelocity() {
		return a_flow / getFluidA() / 1000.;
	}
	
	public double getVelPressure() {
		return 0.602 * getFluidVelocity() * getFluidVelocity();
	}
	
	public double getffactor() {
		double f = getRoughness() / getDh();
		double e = 10.;
		while(e > 0.0001) {
			f = FColebrook(f);
			e = Math.abs(f - FColebrook(f));
		}
		return f;
	}
	
	public boolean setLossRate(double loss, REF ref) {
		if (loss < 0.0 || loss == getLossRate())
			return false;
		double rate = 100.;
		double e = 100.0;
		switch(ref) {
		case FLOW:
			while (e > 0.00001) {
					if(loss < getLossRate()) {
						setDe(getDe() + rate);
						if(loss > getLossRate()) {
							setDe(getDe() - rate);
							rate /= 10.;
						}
					} else {
						setDe(getDe() - rate);
						if(loss < getLossRate() || getDe() - rate < 0.) {
							setDe(getDe() + rate);
							rate /= 10.;
						}
					}
					e = Math.abs(loss - getLossRate());
			}
			return true;
		case VELOCITY:
			// pending to define
			return true;
		case SIZE:
			while (e > 0.00001) {
				if(loss > getLossRate()) {
					setFlow(getFlow() + rate, REF.SIZE);
					if(loss < getLossRate()) {
						setFlow(getFlow() + rate, REF.SIZE);
						rate /= 10.;
					}
				} else {
					setFlow(getFlow() - rate, REF.SIZE);
					if(loss > getLossRate() || getFlow() - rate < 0.) {
						setFlow(getFlow() + rate, REF.SIZE);
						rate /= 10.;
					}
				}
				e = Math.abs(loss - getLossRate());
			}
			return true;
		default: //LOSS
			return false;
		}
	}
	
	public double getLossRate() {
		return 1000. * getffactor() / getDe() * getVelPressure();
	}
	
	public double getReynolds() {
		return 66.4 * getDh() * getVelocity();
	}
	
	private double FColebrook(double f) {
		return 1. / Math.pow(-2. * Math.log10(getRoughness() / 3.7 / getDh()
				+ 2.51 / getReynolds() / Math.sqrt(f)), 2.);
	}
	
	private static double[] convertToSize(double d) {
		double[] size = new double[1];
		size[0] = Math.abs(d);
		return size;
	}
	
	public static void main(String[] args){
		boolean exit = false;
		int stype = 0;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		double[] size;
		double flow;
		Duct duct1;
		while(exit != true) {
			try {
				System.out.println();
				if (stype == 0) {
					System.out.println("1. Round");
					System.out.println("2. Rectangular");
					System.out.println("3. Oval");
					System.out.print("Select type:");
					stype = Integer.parseInt(in.readLine());
					stype = (stype > 3 || stype < 1) ? 1: stype;
				}
				System.out.print("Flow (L/s): ");
				flow = Double.parseDouble(in.readLine());
				if (stype == 1) {
					size = new double[1];
					System.out.print("Diameter (mm): ");
					size[0] = Double.parseDouble(in.readLine());
					duct1 = new Duct(size, 1.5, TYPE.ROUND);
				} else {
					size = new double[2];
					System.out.print("Width (mm): ");
					size[0] = Double.parseDouble(in.readLine());
					System.out.print("Height (mm): ");
					size[1] = Double.parseDouble(in.readLine());
					if (stype == 2)
						duct1 = new Duct(size, 1.5, TYPE.RECTANGULAR);
					else 
						duct1 = new Duct(size, 1.5, TYPE.OVAL);
				}
				duct1.setFlow(flow, REF.SIZE);
				System.out.println();
				System.out.println("RESULTS:");
				System.out.println("Flow (L/s):      " + duct1.getFlow());
				if (stype == 1)
					System.out.println("Diameter (mm):   " + duct1.getSize()[0]);
				else {
					System.out.println("Width (mm):      " + duct1.getSize()[0]);
					System.out.println("Height (mm):     " + duct1.getSize()[1]);
				}
				System.out.println("Eq. Diam. (mm):  " + duct1.getDe());
				System.out.println("Hid. Diam. (mm): " + duct1.getDh());
				System.out.println("Flow Area (m2):  " + duct1.getFluidA());
				System.out.println("Fluid Vel. (m/s):" + duct1.getFluidVelocity());
				System.out.println("Reynolds (-):    " + duct1.getReynolds());
				System.out.println("Friction fac.(-):" + duct1.getffactor());
				System.out.println("Vel. Press. (Pa):" + duct1.getVelPressure());
				System.out.println("Head loss (Pa/m):" + duct1.getLossRate());
				System.out.println();
				System.out.print("Type '0' to change type: ");
				stype = (Integer.parseInt(in.readLine()) == 0) ? 0: stype;
			} catch (IOException ex) {
					exit = true;
				stype = 0;
			} catch (NumberFormatException ex) {
				//No action required
			}
		} 	
	}
}
