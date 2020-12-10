package ai.lenna.lennachatmodul.chat.model;

import java.util.List;

public class Response{
	private List<Object> data;
	private String encryption;
	private boolean success;
	private Object message;

	public List<Object> getData(){
		return data;
	}

	public String getEncryption(){
		return encryption;
	}

	public boolean isSuccess(){
		return success;
	}

	public Object getMessage(){
		return message;
	}
}