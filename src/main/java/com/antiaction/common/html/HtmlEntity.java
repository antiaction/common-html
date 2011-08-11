/*
 * Created on 24/04/2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.antiaction.common.html;

public class HtmlEntity {

	public static StringBuffer encodeHtmlEntities(String str) {
		StringBuffer sb = new StringBuffer( str );
		return encodeHtmlEntities( sb );
	}

	public static StringBuffer encodeHtmlEntities(StringBuffer sb) {
		int i = 0;
		while ( i < sb.length() ) {
			switch ( sb.charAt( i ) ) {
			case '\'':
				sb.replace( i, i +1, "&#39;" );		// 39
				i += 5;
				break;
			case '\"':
				sb.replace( i, i +1, "&quot;" );	// 34
				i += 6;
				break;
			case '&':
				sb.replace( i, i +1, "&amp;" );		// 38
				i += 5;
				break;
			case '<':
				sb.replace( i, i +1, "&lt;" );		// 60
				i += 4;
				break;
			case '>':
				sb.replace( i, i +1, "&gt;" );		// 62
				i += 4;
				break;
			default:
				++i;
				break;
			}
		}
		return sb;
	}

}
