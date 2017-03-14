package mobile.phone.company;


public abstract class CarrierBase extends CalcTalkFee{
	
	@Override
	public void exec() {
		
	docomoExec();
	auExec();
	softbankExec(); 
	
	return;
	}

	

	public abstract void docomoExec();
	public abstract void auExec();
	public abstract void softbankExec();
		
}
	
	
	
	
	


