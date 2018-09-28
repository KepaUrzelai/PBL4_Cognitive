package chat;

public interface accessUser {	
	public void receive(String origin, String msg );
	public void sent(String destination, String msg);
	public String getNombre();
}
