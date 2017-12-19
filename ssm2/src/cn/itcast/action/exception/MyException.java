package cn.itcast.action.exception;

public class MyException extends Exception{
	

	public MyException(String exception) {
		
		this.exception = exception;
	}

	String exception;

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
	
}
