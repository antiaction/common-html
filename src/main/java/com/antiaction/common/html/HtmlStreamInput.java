/*
 * Created on 02/06/2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.antiaction.common.html;

import java.io.IOException;
import java.io.InputStream;

public class HtmlStreamInput implements HtmlInput {

	private InputStream in;

	public static HtmlInput getInstance(InputStream in) {
		HtmlStreamInput input = new HtmlStreamInput();
		input.in = in;
		return input;
	}

	public int read() throws IOException {
		return in.read();
	}

}
