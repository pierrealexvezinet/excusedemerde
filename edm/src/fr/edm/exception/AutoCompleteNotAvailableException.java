package fr.edm.exception;

import android.util.AndroidException;

public class AutoCompleteNotAvailableException extends AndroidException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8519436264271346834L;

	public AutoCompleteNotAvailableException(Exception e){
		super(e);
	}

	public AutoCompleteNotAvailableException(String message){
		super(message);
	}
}
