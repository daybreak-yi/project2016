package mobile.phone.company;

public abstract class Docomo extends CarrierBase {
	
	
	@Override
	public void docomoExec(){
		int baseprice = 1500;
		int calltime = 0;
		int callcharge = calltime*36;
		int useprice = baseprice+callcharge;
	}

}
