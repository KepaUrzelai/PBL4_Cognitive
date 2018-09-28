package chat;

public interface accessChat {
public abstract void register(accessUser user);
public abstract void sent(String origin,String destination, String msg);
}
