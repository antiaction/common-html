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

public class TestHtmlTokenizer {

	public static void main(String[] args) {
		String file = "C:\\dk.alfa-chins.www\\guestbook.html";

		RandomAccessFile ram = null;

		try {
			InputStream in = new FileInputStream( file );
			HtmlTokenizer tokenizer = new HtmlTokenizer();
			tokenizer.init( HtmlStreamInput.getInstance( in ) );
			in.close();

			ram = new RandomAccessFile( "log.txt", "rw" );
			ram.setLength( 0 );
			ram.seek( 0 );

			boolean b = true;
			int type;
			while ( b ) {
				type = tokenizer.next();
				if ( type != HtmlConst.T_EOS ) {
					switch ( type ) {
					case HtmlConst.T_TEXT:
						System.out.println( "...TEXT..." );
						System.out.println( tokenizer.textBuf.toString() );

						ram.writeBytes( tokenizer.textBuf.toString() );
						break;
					case HtmlConst.T_PROCESSING:
						System.out.println( "<?...?>" );
						System.out.println( tokenizer.procBuf.toString() );

						ram.writeBytes( tokenizer.tagBuf.toString() );
						break;
					case HtmlConst.T_EXCLTYPE:
						System.out.println( "<!...>" );
						System.out.println( tokenizer.excltypeBuf.toString() );

						ram.writeBytes( tokenizer.tagBuf.toString() );
						break;
					case HtmlConst.T_COMMENT:
						System.out.println( "<!--...-->" );
						System.out.println( tokenizer.commentBuf.toString() );

						ram.writeBytes( tokenizer.tagBuf.toString() );
						break;
					case HtmlConst.T_ENDTAG:
						System.out.println( "</...>" );
						System.out.println( tokenizer.endtagBuf.toString() );

						ram.writeBytes( tokenizer.tagBuf.toString() );
						break;
					case HtmlConst.T_TAG_START:
						System.out.println( "<..." );
						System.out.println( tokenizer.tagnameBuf.toString() );
						break;
					case HtmlConst.T_TAG_TEXT:
						System.out.println( "..." );
						System.out.println( tokenizer.tagtextBuf.toString() );
						break;
					case HtmlConst.T_TAG_EQ:
						System.out.println( "...=..." );
						break;
					case HtmlConst.T_TAG_TEXT_QUOTED:
						System.out.println( "\"...\"" );
						System.out.println( tokenizer.tagtextBuf.toString() );
						break;
					case HtmlConst.T_TAG_CLOSED:
						System.out.println( ".../>" );
						break;
					case HtmlConst.T_TAG_END:
						System.out.println( "...>" );

						ram.writeBytes( tokenizer.tagBuf.toString() );
						break;
					default:
						System.out.println( type );
						break;
					}
				}
				else {
					b = false;
				}
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
