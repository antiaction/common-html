/*
 * Created on 06/03/2013
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.antiaction.common.html;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestHtmlTokenizer extends TestCase {

	@Test
	public void testHtmlTokenizer() {
		HtmlTokenizer tokenizer = new HtmlTokenizer();
		Assert.assertNotNull( tokenizer );

		Object[][] cases;

		cases = new Object[][] {
				{ "", new Object[][] {
						{ HtmlConst.T_EOS }
				}},
				{ "text", new Object[][] {
						{ HtmlConst.T_TEXT, "text" },
						{ HtmlConst.T_EOS }
				}},
				{ "text<html>", new Object[][] {
						{ HtmlConst.T_TEXT, "text" },
						{ HtmlConst.T_TAG_START, "html" },
						{ HtmlConst.T_TAG_END, "<html>" },
						{ HtmlConst.T_EOS }
				}},
				{ "<html>", new Object[][] {
						{ HtmlConst.T_TAG_START, "html" },
						{ HtmlConst.T_TAG_END, "<html>" },
						{ HtmlConst.T_EOS }
				}},
				{ "<", new Object[][] {
						{ HtmlConst.T_EOS }
				}},
				{ "<?", new Object[][] {
						{ HtmlConst.T_PROCESSING, "" },
						{ HtmlConst.T_EOS }
				}},
				{ "<?Monkeys?>", new Object[][] {
						{ HtmlConst.T_PROCESSING, "Monkeys" },
						{ HtmlConst.T_EOS }
				}},
				{ "<?Monkeys?", new Object[][] {
						{ HtmlConst.T_PROCESSING, "Monkeys" },
						{ HtmlConst.T_EOS }
				}},
				{ "<?Monkeys? no way?", new Object[][] {
						{ HtmlConst.T_PROCESSING, "Monkeys? no way" },
						{ HtmlConst.T_EOS }
				}},
				{ "<!", new Object[][] {
						{ HtmlConst.T_EXCLTYPE, "" },
						{ HtmlConst.T_EOS }
				}},
				{ "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">", new Object[][] {
						{ HtmlConst.T_EXCLTYPE, "DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"" },
						{ HtmlConst.T_EOS }
				}},
				{ "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"", new Object[][] {
						{ HtmlConst.T_EXCLTYPE, "DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"" },
						{ HtmlConst.T_EOS }
				}},
				{ "<!-", new Object[][] {
						{ HtmlConst.T_COMMENT, "" },
						{ HtmlConst.T_EOS }
				}},
				// Strange excltypeBuf.
				{ "<!- ", new Object[][] {
						{ HtmlConst.T_EXCLTYPE, "-" },
						{ HtmlConst.T_EOS }
				}},
				{ "<!--", new Object[][] {
						{ HtmlConst.T_COMMENT, "" },
						{ HtmlConst.T_EOS }
				}},
				{ "<!-- ", new Object[][] {
						{ HtmlConst.T_COMMENT, " " },
						{ HtmlConst.T_EOS }
				}},
				{ "<!-- -", new Object[][] {
						{ HtmlConst.T_COMMENT, " " },
						{ HtmlConst.T_EOS }
				}},
				{ "<!-- - ", new Object[][] {
						{ HtmlConst.T_COMMENT, " - " },
						{ HtmlConst.T_EOS }
				}},
				{ "<!-- --", new Object[][] {
						{ HtmlConst.T_COMMENT, " " },
						{ HtmlConst.T_EOS }
				}},
				{ "<!-- -- ", new Object[][] {
						{ HtmlConst.T_COMMENT, " -- " },
						{ HtmlConst.T_EOS }
				}},
				{ "<!-- -->", new Object[][] {
						{ HtmlConst.T_COMMENT, " " },
						{ HtmlConst.T_EOS }
				}},
				{ "</", new Object[][] {
						{ HtmlConst.T_ENDTAG, "" },
						{ HtmlConst.T_EOS }
				}},
				{ "</>", new Object[][] {
						{ HtmlConst.T_ENDTAG, "" },
						{ HtmlConst.T_EOS }
				}},
				{ "</a>", new Object[][] {
						{ HtmlConst.T_ENDTAG, "a" },
						{ HtmlConst.T_EOS }
				}},
				{ "</  \t \r \n", new Object[][] {
						{ HtmlConst.T_ENDTAG, "" },
						{ HtmlConst.T_EOS }
				}},
				{ "</  \t \r \n>", new Object[][] {
						{ HtmlConst.T_ENDTAG, "" },
						{ HtmlConst.T_EOS }
				}},
		};

		testCases( tokenizer, cases );

		cases = new Object[][] {
				{ "<@", new Object[][] {
						{ HtmlConst.T_DIRECTIVE_START, "" },
						{ HtmlConst.T_EOS }
				}},
				{ "<@>", new Object[][] {
						{ HtmlConst.T_DIRECTIVE_START, "" },
						{ HtmlConst.T_TAG_END, "<@>" },
						{ HtmlConst.T_EOS }
				}},
				{ "<@ >", new Object[][] {
						{ HtmlConst.T_DIRECTIVE_START, "" },
						{ HtmlConst.T_TAG_START, "" },
						{ HtmlConst.T_TAG_END, "<@ >" },
						{ HtmlConst.T_EOS }
				}},
				{ "<@\t>", new Object[][] {
						{ HtmlConst.T_DIRECTIVE_START, "" },
						{ HtmlConst.T_TAG_START, "" },
						{ HtmlConst.T_TAG_END, "<@\t>" },
						{ HtmlConst.T_EOS }
				}},
				{ "<@\n>", new Object[][] {
						{ HtmlConst.T_DIRECTIVE_START, "" },
						{ HtmlConst.T_TAG_START, "" },
						{ HtmlConst.T_TAG_END, "<@\n>" },
						{ HtmlConst.T_EOS }
				}},
				{ "<@\r>", new Object[][] {
						{ HtmlConst.T_DIRECTIVE_START, "" },
						{ HtmlConst.T_TAG_START, "" },
						{ HtmlConst.T_TAG_END, "<@\r>" },
						{ HtmlConst.T_EOS }
				}},
				{ "<@/", new Object[][] {
						{ HtmlConst.T_DIRECTIVE_START, "" },
						{ HtmlConst.T_TAG_CLOSED, ""  },
						{ HtmlConst.T_EOS }
				}},
				{ "<@master", new Object[][] {
						{ HtmlConst.T_DIRECTIVE_START, "master" },
						{ HtmlConst.T_EOS }
				}},
				// "<@master file=\"master.html\">"
		};

		testCases( tokenizer, cases );

		cases = new Object[][] {
				{ "<a", new Object[][] {
						{ HtmlConst.T_TAG_START, "a" },
						{ HtmlConst.T_EOS }
				}},
				{ "<html", new Object[][] {
						{ HtmlConst.T_TAG_START, "html" },
						{ HtmlConst.T_EOS },
				}},
				{ "<a>", new Object[][] {
						{ HtmlConst.T_TAG_START, "a" },
						{ HtmlConst.T_TAG_END, "<a>" },
						{ HtmlConst.T_EOS }
				}},
				{ "<html>", new Object[][] {
						{ HtmlConst.T_TAG_START, "html" },
						{ HtmlConst.T_TAG_END, "<html>" },
						{ HtmlConst.T_EOS },
				}},
				{ "<a/>", new Object[][] {
						{ HtmlConst.T_TAG_START, "a" },
						{ HtmlConst.T_TAG_CLOSED, "a"  },
						{ HtmlConst.T_TAG_END, "<a/>" },
						{ HtmlConst.T_EOS }
				}},
				{ "<html//>", new Object[][] {
						{ HtmlConst.T_TAG_START, "html" },
						{ HtmlConst.T_TAG_CLOSED, "html" },
						{ HtmlConst.T_TAG_END, "<html//>" },
						{ HtmlConst.T_EOS },
				}},
		};

		testCases( tokenizer, cases );

		cases = new Object[][] {
				{ "<  ", new Object[][] {
						{ HtmlConst.T_TAG_START, " " },
						{ HtmlConst.T_EOS }
				}},
				/*
				{ "<html", new Object[][] {
						{ HtmlConst.T_TAG_START, "html" },
						{ HtmlConst.T_EOS },
				}},
				{ "<a>", new Object[][] {
						{ HtmlConst.T_TAG_START, "a" },
						{ HtmlConst.T_TAG_END, "<a>" },
						{ HtmlConst.T_EOS }
				}},
				{ "<html>", new Object[][] {
						{ HtmlConst.T_TAG_START, "html" },
						{ HtmlConst.T_TAG_END, "<html>" },
						{ HtmlConst.T_EOS },
				}},
				{ "<a/>", new Object[][] {
						{ HtmlConst.T_TAG_START, "a" },
						{ HtmlConst.T_TAG_CLOSED, "a"  },
						{ HtmlConst.T_TAG_END, "<a/>" },
						{ HtmlConst.T_EOS }
				}},
				{ "<html//>", new Object[][] {
						{ HtmlConst.T_TAG_START, "html" },
						{ HtmlConst.T_TAG_CLOSED, "html" },
						{ HtmlConst.T_TAG_END, "<html//>" },
						{ HtmlConst.T_EOS },
				}},
				*/
		};

		testCases( tokenizer, cases );
	}

	public void testCases(HtmlTokenizer tokenizer, Object[][] cases) {
		String html;
		Object[][] expected;
		int token;
		try {
			for ( int i=0; i<cases.length; ++i ) {
				html = (String)cases[ i ][ 0 ];
				expected = (Object[][])cases[ i ][ 1 ];

				// debug
				System.out.println( "**** " + i + " ****" );

				tokenizer.init( HtmlStreamInput.getInstance( new ByteArrayInputStream( html.getBytes( "UTF-8"  ) ) ) );

				int j = 0;
				boolean bLoop = true;
				while ( bLoop ) {
					token = tokenizer.next();
					System.out.println( token + " " + expected[ j ][ 0 ] );
					switch ( token ) {
					case HtmlConst.T_ERROR:
						bLoop = false;
						break;
					case HtmlConst.T_EOS:
						bLoop = false;
						break;
					case HtmlConst.T_TEXT:
						Assert.assertEquals( expected[ j ][ 1 ], tokenizer.textBuf.toString() );
						break;
					case HtmlConst.T_PROCESSING:
						Assert.assertEquals( expected[ j ][ 1 ], tokenizer.procBuf.toString() );
						break;
					case HtmlConst.T_EXCLTYPE:
						Assert.assertEquals( expected[ j ][ 1 ], tokenizer.excltypeBuf.toString() );
						break;
					case HtmlConst.T_COMMENT:
						Assert.assertEquals( expected[ j ][ 1 ], tokenizer.commentBuf.toString() );
						break;
					case HtmlConst.T_ENDTAG:
						Assert.assertEquals( expected[ j ][ 1 ], tokenizer.endtagBuf.toString() );
						break;
					case HtmlConst.T_TAG_START:
						Assert.assertEquals( expected[ j ][ 1 ], tokenizer.tagnameBuf.toString() );
						break;
					case HtmlConst.T_TAG_TEXT:
						break;
					case HtmlConst.T_TAG_EQ:
						break;
					case HtmlConst.T_TAG_TEXT_QUOTED:
						break;
					case HtmlConst.T_TAG_CLOSED:
						Assert.assertEquals( expected[ j ][ 1 ], tokenizer.tagnameBuf.toString() );
						break;
					case HtmlConst.T_TAG_END:
						Assert.assertEquals( expected[ j ][ 1 ], tokenizer.tagBuf.toString() );
						break;
					case HtmlConst.T_DIRECTIVE_START:
						Assert.assertEquals( expected[ j ][ 1 ], tokenizer.tagnameBuf.toString() );
						break;
					}
					++j;
				}
			}
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Assert.fail( "Unexpected exception!" );
		}
		catch (IOException e) {
			e.printStackTrace();
			Assert.fail( "Unexpected exception!" );
		}
	}

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
