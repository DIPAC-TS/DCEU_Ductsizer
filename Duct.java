package ductsizer;
import java.util.ArrayList;

public class Duct {
	String a_name;
	ArrayList<Fitting> a_fittings;
	double a_flow;
	double[] a_size;
	double a_length;
	double a_E;
	TYPE a_type;
	
	enum REF{
		FLOW,
		SIZE,
		VELOCITY
	}
	enum TYPE{
		RECTANGULAR,
		ROUND,
		OVAL
	}
	
	public Duct(double size, double len) {
		a_name = "default";
		a_flow = 1000.;
		a_type = TYPE.ROUND;
		a_size = new double[1];
		a_size[0] = size;
		a_length = len;
		a_fittings = new ArrayList<Fitting>();
	}
	
	public Duct(double[] size, double len, TYPE type) {
		a_name = "default";
		a_flow = 1000.;
		a_type = type;
		a_length = len;
		if (type == TYPE.ROUND) {
			a_size = new double[1];
			a_size[0] = size[0];
		} else {
				a_size = new double[2];
				a_size[0]  = Math.max(size[0], size[1]);
				a_size[1]  = Math.max(size[0], size[1]);
		}
		if (type == TYPE.OVAL) {
			if (size[0] == size[1]) {
				a_size = new double[1];
				a_size[0] = size[0];
				a_type = TYPE.ROUND;
			}
		}
		a_fittings = new ArrayList<Fitting>();
	}
	
	public Duct(double[] size, double len, TYPE type, double E) {
		a_name = "default";
		a_flow = 1000.;
		a_type = type;
		a_length = len;
		if (type == TYPE.ROUND) {
			a_size = new double[1];
			a_size[0] = size[0];
		} else {
				a_size = new double[2];
				a_size[0]  = Math.max(size[0], size[1]);
				a_size[1]  = Math.max(size[0], size[1]);
		}
		if (type == TYPE.OVAL) {
			if (size[0] == size[1]) {
				a_size = new double[1];
				a_size[0] = size[0];
				a_type = TYPE.ROUND;
			}
		}
		a_E = 0.09;
		setRoughness(E);
		a_fittings = new ArrayList<Fitting>();
	}
	
	public boolean addFitting(Fitting fitt) {
		a_fittings.add(fitt);
		return true;
	}
	
	public boolean setName(String name) {
		a_name = name;
		return true;
	}
	
	public boolean setRoughness(double E) {
		if (E < 0.)
			return false;
		else { 
			a_E = E;
			return true;
		}
	}
	
	public double getA() {
		switch (a_type) {
			case RECTANGULAR:{
				return a_size[0] * a_size[1];
			}
			case ROUND:{
				return Math.PI * a_size[0] * a_size[0] / 4.;
			}
			default:{//OVAL
				return Math.PI * a_size[1] * a_size[1] / 4.
						+ a_size[1] * (a_size[0] - a_size[1]);
			}
		}
	}
	
	public double getP() {
		switch (a_type) {
			case RECTANGULAR:{
				return 2 * (a_size[0] + a_size[1]);
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
			return 4 * getA() / getP();
		}
	}
	
	public double getDe() {
		switch (a_type) {
			case RECTANGULAR:{
				return 1.3 * Math.pow(a_size[0] * a_size[1], 0.625) 
						/ Math.pow(a_size[0] + a_size[1], 0.25); 
			}
			case ROUND:{
				return a_size[0];
			}
			default:{//OVAL
				return 1.55 * Math.pow(getA(), 0.625) / Math.pow(getP(), 0.25);
			}
		}
	}
	
	public boolean setFlow(double flow, REF ref) {
		if(ref == REF.VELOCITY) {
			//Code no availaible right now
			return true;
		} else {
			a_flow = flow;
			return true;
		}
	}
	
	public double getFlow() {
		return a_flow;
	}
	
	public double getVelocity(){
		return a_flow / getA() * 1000.;
	}
	
	public double getRoughness() {
		return a_E;
	}
	
	public double getVelPressure() {
		return 0.602 * getVelocity() * getVelocity();
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
	
	public double getLossRate() {
		return 1000. * getffactor() / getDh() * getVelPressure();
	}
	
	public double getReynolds() {
		return 66.4 * getDh() * getVelocity();
	}
	
	private double FColebrook(double f) {
		return 1 / Math.pow(-2. * Math.log10(getRoughness() / 3.7 / getDh()
				+ 2.51 / getReynolds() / f), 2.);
	}
	
	public static void main(String[] args){
		double[] ductsize = {600., 600.};
		Duct duct1 = new Duct(315, 1.5);
		duct1.setFlow(950, REF.SIZE);
		duct1.setRoughness(0.09);
		System.out.println(duct1.getDh());
		System.out.println(duct1.getDe());
		System.out.println(duct1.getVelPressure());
		System.out.println(duct1.getLossRate());
	}
}
