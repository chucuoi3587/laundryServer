package nhan.natc.laundry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DefaultException extends RuntimeException{

	public DefaultException(String message) {
		super(message);
	}
	
	public DefaultException(String message, Throwable cause) {
		super(message, cause);
	}
}
