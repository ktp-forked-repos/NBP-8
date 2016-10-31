package pl.parser.nbp;

import org.xml.sax.SAXException;

public class IncorrectTableTypeException extends SAXException{
	
	public IncorrectTableTypeException(){
		super();
	}
	
	public IncorrectTableTypeException(String message){
		super(message);
	}
}
