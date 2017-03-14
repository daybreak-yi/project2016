package mobile.phone.company;

public abstract class Softbank extends CarrierBase {
	
	@Override
	public void softbankExec(){
		int baseprice = 980;
		int calltime = 0;
		int callcharge = calltime*36;
		int useprice = baseprice+callcharge;
	}

}
