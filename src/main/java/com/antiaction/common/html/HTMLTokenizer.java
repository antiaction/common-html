/*
 * Html tokenizer, splits Html into managable parts.
 * Copyright (C) 2005  Nicholas Clarke
 *
 */

/*
 * History:
 *
 * 12-Jan-2005 : First implementation.
 * 31-Jan-2005 : Tokenizer code seems fairly complete and working.
 * 02-Feb-2005 : Moved main debug method from parser to tokenizer.
 *
 */

package com.antiaction.common.html;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Html tokenizer, splits Html into managable parts.
 *
 * @version 1.00
 * @author Nicholas Clarke <mayhem[at]antiaction[dot]com>
 */
public class HTMLTokenizer {

	private static final int S_TEXT = 0;
	private static final int S_LT = 1;
	private static final int S_PROCESSING = 2;
	private static final int S_PROCESSING_END = 3;
	private static final int S_EXCLAMATION = 4;
	private static final int S_EXCLTYPE = 5;
	private static final int S_COMMENT_START = 6;
	private static final int S_COMMENT = 7;
	private static final int S_COMMENT_END1 = 8;
	private static final int S_COMMENT_END = 9;
	private static final int S_ENDTAG = 10;
	private static final int S_ENDTAG_FILL = 11;
	private static final int S_TAG_START = 12;
	private static final int S_TAG_WHTSPC1 = 13;
	private static final int S_TAG_TEXT = 14;
	private static final int S_TAG_EQ = 15;
	private static final int S_TAG_QUOTED = 16;
	private static final int S_TAG_WHTSPC2 = 17;
	private static final int S_TAG_SLASH = 18;
	private static final int S_TAG_END = 19;
	private static final int S_EOS = 20;

	private int state = S_TEXT;
	private char quote;
	private char missed = 0;

	private InputStream in;

	StringBuffer textBuf = new StringBuffer( 1024 );
	StringBuffer tagBuf = new StringBuffer( 256 );
	StringBuffer procBuf = new StringBuffer( 128 );
	StringBuffer excltypeBuf = new StringBuffer( 128 );
	StringBuffer commentBuf = new StringBuffer( 512 );
	StringBuffer endtagBuf = new StringBuffer( 16 );
	StringBuffer tagnameBuf = new StringBuffer( 16 );
	StringBuffer tagtextBuf = new StringBuffer( 64 );

	public void init(InputStream in) {
		this.in = in;
		missed = 0;
	}

	public int next() throws IOException {
		textBuf.setLength( 0 );
		if ( missed != 0 ) {
			textBuf.append( missed );
			missed = 0;
		}

		boolean loop = true;
		while ( loop ) {
			char c = (char)in.read();
			switch ( state ) {
			case S_TEXT:
				switch ( c ) {
				case 0xffff:
					if ( textBuf.length() > 0 ) {
						return HTMLConst.T_TEXT;
					}
					return HTMLConst.T_EOS;
				case '<':
					state = S_LT;
					//tagBuf.setLength( 0 );
					//tagBuf.append( c );
					if ( textBuf.length() > 0 ) {
						return HTMLConst.T_TEXT;
					}
					break;
				default:
					textBuf.append( c );
					break;
				}
				break;
			case S_LT:
				tagBuf.setLength( 0 );
				tagBuf.append( '<' );
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_EOS;
				case '?':
					state = S_PROCESSING;
					procBuf.setLength( 0 );
					tagBuf.append( c );
					break;
				case '!':
					state = S_EXCLAMATION;
					excltypeBuf.setLength( 0 );
					tagBuf.append( c );
					break;
				case '/':
					state = S_ENDTAG;
					endtagBuf.setLength( 0 );
					tagBuf.append( c );
					break;
				default:
					state = S_TAG_START;
					tagnameBuf.setLength( 0 );
					tagnameBuf.append( c );
					tagBuf.append( c );
					break;
				}
				break;
			case S_PROCESSING:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_PROCESSING;
				case '?':
					state = S_PROCESSING_END;
					tagBuf.append( c );
					break;
				default:
					procBuf.append( c );
					tagBuf.append( c );
					break;
				}
				break;
			case S_PROCESSING_END:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_PROCESSING;
				case '>':
					state = S_TEXT;
					tagBuf.append( c );
					return HTMLConst.T_PROCESSING;
				default:
					procBuf.append( '?' );
					procBuf.append( c );
					state = S_PROCESSING;
					tagBuf.append( c );
					break;
				}
				break;
			case S_EXCLAMATION:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_EXCLTYPE;
				case '-':
					state = S_COMMENT_START;
					commentBuf.setLength( 0 );
					tagBuf.append( c );
					break;
				default:
					state = S_EXCLTYPE;
					excltypeBuf.append( c );
					tagBuf.append( c );
					break;
				}
				break;
			case S_EXCLTYPE:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_EXCLTYPE;
				case '>':
					state = S_TEXT;
					tagBuf.append( c );
					return HTMLConst.T_EXCLTYPE;
				default:
					excltypeBuf.append( c );
					tagBuf.append( c );
					break;
				}
				break;
			case S_COMMENT_START:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_COMMENT;
				case '-':
					state = S_COMMENT;
					tagBuf.append( c );
					break;
				default:
					excltypeBuf.append( '-' );
					state = S_EXCLTYPE;
					tagBuf.append( c );
					break;
				}
				break;
			case S_COMMENT:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_COMMENT;
				case '-':
					state = S_COMMENT_END1;
					tagBuf.append( c );
					break;
				default:
					commentBuf.append( c );
					tagBuf.append( c );
					break;
				}
				break;
			case S_COMMENT_END1:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_COMMENT;
				case '-':
					state = S_COMMENT_END;
					tagBuf.append( c );
					break;
				default:
					commentBuf.append( '-' );
					commentBuf.append( c );
					state = S_COMMENT;
					tagBuf.append( c );
					break;
				}
				break;
			case S_COMMENT_END:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_COMMENT;
				case '>':
					state = S_TEXT;
					tagBuf.append( c );
					return HTMLConst.T_COMMENT;
				default:
					commentBuf.append( '-' );
					commentBuf.append( '-' );
					commentBuf.append( c );
					state = S_COMMENT;
					tagBuf.append( c );
					break;
				}
				break;
			case S_ENDTAG:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_ENDTAG;
				case '>':
					state = S_TEXT;
					tagBuf.append( c );
					return HTMLConst.T_ENDTAG;
				case ' ':
				case '\t':
				case '\n':
				case '\r':
					state = S_ENDTAG_FILL;
					tagBuf.append( c );
					break;
				default:
					endtagBuf.append( c );
					tagBuf.append( c );
					break;
				}
				break;
			case S_ENDTAG_FILL:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_ENDTAG;
				case '>':
					state = S_TEXT;
					tagBuf.append( c );
					return HTMLConst.T_ENDTAG;
				default:
					tagBuf.append( c );
					break;
				}
				break;
			case S_TAG_START:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_TAG_START;
				case '>':
					state = S_TAG_END;
					tagBuf.append( c );
					return HTMLConst.T_TAG_START;
				case '/':
					state = S_TAG_SLASH;
					tagBuf.append( c );
					return HTMLConst.T_TAG_START;
				case ' ':
				case '\t':
				case '\n':
				case '\r':
					state = S_TAG_WHTSPC1;
					tagBuf.append( c );
					return HTMLConst.T_TAG_START;
				default:
					tagnameBuf.append( c );
					tagBuf.append( c );
					break;
				}
				break;
			case S_TAG_WHTSPC1:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_TAG_START;
				case '>':
					state = S_TAG_END;
					tagBuf.append( c );
					return HTMLConst.T_TAG_START;
				case '/':
					state = S_TAG_SLASH;
					tagBuf.append( c );
					break;
				case ' ':
				case '\t':
				case '\n':
				case '\r':
					tagBuf.append( c );
					break;
				case '=':
					state = S_TAG_EQ;
					tagBuf.append( c );
					break;
				case '"':
				case '\'':
					state = S_TAG_QUOTED;
					tagtextBuf.setLength( 0 );
					quote = c;
					tagBuf.append( c );
					break;
				default:
					state = S_TAG_TEXT;
					tagtextBuf.setLength( 0 );
					tagtextBuf.append( c );
					tagBuf.append( c );
					break;
				}
				break;
			case S_TAG_TEXT:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_TAG_TEXT;
				case '>':
					state = S_TAG_END;
					tagBuf.append( c );
					return HTMLConst.T_TAG_TEXT;
				case '/':
					state = S_TAG_SLASH;
					tagBuf.append( c );
					return HTMLConst.T_TAG_TEXT;
				case ' ':
				case '\t':
				case '\n':
				case '\r':
					state = S_TAG_WHTSPC2;
					tagBuf.append( c );
					return HTMLConst.T_TAG_TEXT;
				case '=':
					state = S_TAG_EQ;
					tagBuf.append( c );
					return HTMLConst.T_TAG_TEXT;
/*
				case '"':
				case '\'':
					state = S_TAG_QUOTED;
					tagtextBuf.setLength( 0 );
					quote = c;
					tagBuf.append( c );
					return HTMLConst.T_TAG_TEXT;
*/
				default:
					tagtextBuf.append( c );
					tagBuf.append( c );
					break;
				}
				break;
			case S_TAG_EQ:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_TAG_EQ;
				case '>':
					state = S_TAG_END;
					tagBuf.append( c );
					return HTMLConst.T_TAG_EQ;
				case '/':
					state = S_TAG_SLASH;
					tagBuf.append( c );
					return HTMLConst.T_TAG_EQ;
				case ' ':
				case '\t':
				case '\n':
				case '\r':
					state = S_TAG_WHTSPC2;
					tagBuf.append( c );
					return HTMLConst.T_TAG_EQ;
				case '=':
					state = S_TAG_EQ;
					tagBuf.append( c );
					return HTMLConst.T_TAG_EQ;
				case '"':
				case '\'':
					state = S_TAG_QUOTED;
					tagtextBuf.setLength( 0 );
					quote = c;
					tagBuf.append( c );
					return HTMLConst.T_TAG_EQ;
				default:
					state = S_TAG_TEXT;
					tagtextBuf.setLength( 0 );
					tagtextBuf.append( c );
					tagBuf.append( c );
					return HTMLConst.T_TAG_EQ;
				}
			case S_TAG_QUOTED:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_TAG_TEXT_QUOTED;
				case '"':
				case '\'':
					tagBuf.append( c );
					if ( c == quote ) {
						state = S_TAG_WHTSPC2;
						return HTMLConst.T_TAG_TEXT_QUOTED;
					}
					break;
				default:
					tagtextBuf.append( c );
					tagBuf.append( c );
					break;
				}
				break;
			case S_TAG_WHTSPC2:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_EOS;
				case '>':
					state = S_TAG_END;
					tagBuf.append( c );
					break;
				case '/':
					state = S_TAG_SLASH;
					tagBuf.append( c );
					break;
				case ' ':
				case '\t':
				case '\n':
				case '\r':
					tagBuf.append( c );
					break;
				case '=':
					state = S_TAG_EQ;
					tagBuf.append( c );
					break;
				case '"':
				case '\'':
					state = S_TAG_QUOTED;
					tagtextBuf.setLength( 0 );
					quote = c;
					tagBuf.append( c );
					break;
				default:
					state = S_TAG_TEXT;
					tagtextBuf.setLength( 0 );
					tagtextBuf.append( c );
					tagBuf.append( c );
					break;
				}
				break;
			case S_TAG_SLASH:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_TAG_EQ;
				case '>':
					state = S_TAG_END;
					tagBuf.append( c );
					return HTMLConst.T_TAG_CLOSED;
				case '/':
					tagBuf.append( c );
					break;
				case ' ':
				case '\t':
				case '\n':
				case '\r':
					state = S_TAG_WHTSPC2;
					tagBuf.append( c );
					return HTMLConst.T_TAG_CLOSED;
				case '=':
					state = S_TAG_EQ;
					tagBuf.append( c );
					return HTMLConst.T_TAG_CLOSED;
				case '"':
				case '\'':
					state = S_TAG_QUOTED;
					tagtextBuf.setLength( 0 );
					quote = c;
					tagBuf.append( c );
					return HTMLConst.T_TAG_CLOSED;
				default:
					state = S_TAG_TEXT;
					tagtextBuf.setLength( 0 );
					tagBuf.append( c );
					return HTMLConst.T_TAG_CLOSED;
				}
				break;
			case S_TAG_END:
				switch ( c ) {
				case 0xffff:
					state = S_EOS;
					return HTMLConst.T_TAG_END;
				case '<':
					state = S_LT;
					//tagBuf.setLength( 0 );
					//tagBuf.append( c );
					return HTMLConst.T_TAG_END;
				default:
					state = S_TEXT;
					//textBuf.append( c );
					missed = c;
					return HTMLConst.T_TAG_END;
				}
			case S_EOS:
				return HTMLConst.T_EOS;
			}
		}

		return HTMLConst.T_ERROR;
	}

	public static void main(String[] args) {
		String file = "C:\\dk.alfa-chins.www\\guestbook.html";

		RandomAccessFile ram = null;

		try {
			InputStream in = new FileInputStream( file );
			HTMLTokenizer tokenizer = new HTMLTokenizer();
			tokenizer.init( in );

			ram = new RandomAccessFile( "log.txt", "rw" );
			ram.setLength( 0 );
			ram.seek( 0 );

			boolean b = true;
			int type;
			while ( b ) {
				type = tokenizer.next();
				if ( type != HTMLConst.T_EOS ) {
					switch ( type ) {
					case HTMLConst.T_TEXT:
						System.out.println( "...TEXT..." );
						System.out.println( tokenizer.textBuf.toString() );

						ram.writeBytes( tokenizer.textBuf.toString() );
						break;
					case HTMLConst.T_PROCESSING:
						System.out.println( "<?...?>" );
						System.out.println( tokenizer.procBuf.toString() );

						ram.writeBytes( tokenizer.tagBuf.toString() );
						break;
					case HTMLConst.T_EXCLTYPE:
						System.out.println( "<!...>" );
						System.out.println( tokenizer.excltypeBuf.toString() );

						ram.writeBytes( tokenizer.tagBuf.toString() );
						break;
					case HTMLConst.T_COMMENT:
						System.out.println( "<!--...-->" );
						System.out.println( tokenizer.commentBuf.toString() );

						ram.writeBytes( tokenizer.tagBuf.toString() );
						break;
					case HTMLConst.T_ENDTAG:
						System.out.println( "</...>" );
						System.out.println( tokenizer.endtagBuf.toString() );

						ram.writeBytes( tokenizer.tagBuf.toString() );
						break;
					case HTMLConst.T_TAG_START:
						System.out.println( "<..." );
						System.out.println( tokenizer.tagnameBuf.toString() );
						break;
					case HTMLConst.T_TAG_TEXT:
						System.out.println( "..." );
						System.out.println( tokenizer.tagtextBuf.toString() );
						break;
					case HTMLConst.T_TAG_EQ:
						System.out.println( "...=..." );
						break;
					case HTMLConst.T_TAG_TEXT_QUOTED:
						System.out.println( "\"...\"" );
						System.out.println( tokenizer.tagtextBuf.toString() );
						break;
					case HTMLConst.T_TAG_CLOSED:
						System.out.println( ".../>" );
						break;
					case HTMLConst.T_TAG_END:
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
