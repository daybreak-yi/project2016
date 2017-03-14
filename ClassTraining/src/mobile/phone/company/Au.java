package mobile.phone.company;

public abstract class Au extends CarrierBase {
	
	@Override
	public void auExec(){
		int baseprice = 1300;
		int calltime = 0;
		int callcharge = calltime*36;
		int useprice = baseprice+callcharge;
	}

}
