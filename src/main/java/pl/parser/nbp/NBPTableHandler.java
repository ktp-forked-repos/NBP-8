package pl.parser.nbp;

import org.xml.sax.helpers.DefaultHandler;

public abstract class NBPTableHandler extends DefaultHandler {
	public abstract NBPTable getNBPTable();
}
