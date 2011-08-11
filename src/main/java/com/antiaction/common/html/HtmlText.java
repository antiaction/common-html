/*
 * Created on 02-02-2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package com.antiaction.common.html;

/**
 * @author Nicholas
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class HtmlText extends HtmlItem {

	private String text;

	public HtmlText(String text) {
		this.text = text;
	}

	public Object clone() {
		return new HtmlText( text );
	}

	public int getType() {
		return HtmlItem.T_TEXT;
	}

	public String getText() {
		return text;
	}

}
