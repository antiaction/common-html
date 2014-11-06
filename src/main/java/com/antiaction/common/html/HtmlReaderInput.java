/*
 * Created on 02/06/2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.antiaction.common.html;

import java.io.IOException;
import java.io.Reader;

public class HtmlReaderInput implements HtmlInput {

	private Reader in;

	public static HtmlInput getInstance(Reader in) {
		HtmlReaderInput input = new HtmlReaderInput();
		input.in = in;
		return input;
	}

	public int read() throws IOException {
		return in.read();
	}

}
