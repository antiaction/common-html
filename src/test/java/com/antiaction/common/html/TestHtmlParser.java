/*
 * Created on 06/03/2013
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.antiaction.common.html;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Assert;

public class TestHtmlParser extends TestCase {

	public void testHtmlParser() {
		StringBuilder sb = new StringBuilder();
		sb.append( "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">" );
		sb.append( "<html lang=\"en\">" );
		sb.append( "<@master file=\"master.html\">" );
		sb.append( "<!-- #include virtual=\"inc_menu.html\" -->" );
		sb.append( "Hello world" );
		sb.append( "</html>" );
		sb.append( "<?Monkeys?>" );

		byte[] bytes;
		ByteArrayInputStream in;
		HtmlParser parser = new HtmlParser();
		List result;
		try {
			bytes = sb.toString().getBytes( "UTF-8" );
			in = new ByteArrayInputStream( bytes );

			result = parser.parse( in );

			Assert.assertNotNull( result );

			validateResult( result );

			Reader reader = new StringReader( sb.toString() );

			result = parser.parse( reader );

			Assert.assertNotNull( result );

			validateResult( result );
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

	Object[][] expected = new Object[][] {
			{
				HtmlItem.T_EXCLAMATION
			},
			{
				HtmlItem.T_TAG
			},
			{
				HtmlItem.T_DIRECTIVE
			},
			{
				HtmlItem.T_COMMENT
			},
			{
				HtmlItem.T_TEXT
			},
			{
				HtmlItem.T_ENDTAG
			},
			{
				HtmlItem.T_PROCESSING
			},
	};

	public void validateResult(List result) {
		HtmlItem htmlItem;
		int type;

		Assert.assertEquals( expected.length, result.size() );

		for ( int i=0; i<result.size(); ++i ) {
			htmlItem = (HtmlItem)result.get( i );
			type = htmlItem.getType();

			// debug
			//System.out.println( htmlItem.getType() );

			Assert.assertEquals( expected[ i ][ 0 ], htmlItem.getType() );

			switch ( type ) {
			case HtmlItem.T_EXCLAMATION:
				Assert.assertEquals( "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">", htmlItem.getText() );
				break;
			case HtmlItem.T_TAG:
				Assert.assertEquals( "<html lang=\"en\">", htmlItem.getText() );
				Assert.assertEquals( "html", htmlItem.getTagname() );
				Assert.assertEquals( 1, htmlItem.getAttributes().size() );
				Assert.assertEquals( "en", htmlItem.getAttribute( "lang" ) );
				Assert.assertEquals( false, htmlItem.getClosed() );
				break;
			case HtmlItem.T_DIRECTIVE:
				//Assert.assertEquals( "<@master file=\"master.html\">", htmlItem.getText() );
				Assert.assertEquals( "master", htmlItem.getTagname() );
				Assert.assertEquals( 1, htmlItem.getAttributes().size() );
				Assert.assertEquals( "master.html", htmlItem.getAttribute( "file" ) );
				Assert.assertEquals( true, htmlItem.getClosed() );
				break;
			case HtmlItem.T_COMMENT:
				Assert.assertEquals( "<!-- #include virtual=\"inc_menu.html\" -->", htmlItem.getText() );
				break;
			case HtmlItem.T_TEXT:
				Assert.assertEquals( "Hello world", htmlItem.getText() );
				break;
			case HtmlItem.T_ENDTAG:
				Assert.assertEquals( "</html>", htmlItem.getText() );
				Assert.assertEquals( "html", htmlItem.getTagname() );
				break;
			case HtmlItem.T_PROCESSING:
				Assert.assertEquals( "<?Monkeys?>", htmlItem.getText() );
				break;
			}
		}
	}

}
