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
public class HtmlProcessing extends HtmlItem {

	private String tagText;
	private String processing;

	public HtmlProcessing(String tagText, String processing) {
		this.tagText = tagText;
		this.processing = processing;
	}

	public Object clone() {
		return new HtmlProcessing( tagText, processing );
	}

	public int getType() {
		return HtmlItem.T_PROCESSING;
	}

	public String getText() {
		return tagText;
	}

}
