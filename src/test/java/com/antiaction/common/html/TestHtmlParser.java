/*
 * Created on 06/03/2013
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.antiaction.common.html;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.List;

public class TestHtmlParser {

	public static void main(String[] args) {
		String file = "C:\\dk.alfa-chins.www\\guestbook.html";

		RandomAccessFile ram = null;

		try {
			InputStream in = new FileInputStream( file );
			HtmlParser parser = new HtmlParser();
			List result = parser.parse( in );

			ram = new RandomAccessFile( "log2.txt", "rw" );
			ram.setLength( 0 );
			ram.seek( 0 );

			HtmlItem item;
			for ( int i=0; i<result.size(); ++i ) {
				item = (HtmlItem)result.get( i );
				ram.writeBytes( item.getText() );
			}
		}
		catch (FileNotFoundException e) {
		}
		catch (IOException e) {
		}

		try {
			if ( ram != null ) {
				ram.close();
			}
		}
		catch (IOException e) {
		}
	}

}
