/*
 * Created on 16/06/2013
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.antiaction.common.html;

import junit.framework.TestCase;

import org.junit.Assert;

public class TestHtmlDirective extends TestCase {

	public void testHtmlDirective() {
		HtmlDirective htmlItem;
		HtmlDirective htmlItem2;

		/*
		 * Null parameters.
		 */

		htmlItem = new HtmlDirective( null );
		Assert.assertNotNull( htmlItem );
		Assert.assertEquals( HtmlItem.T_DIRECTIVE, htmlItem.getType() );
		Assert.assertEquals( null, htmlItem.getText() );
		Assert.assertEquals( null, htmlItem.getTagname() );
		Assert.assertEquals( true, htmlItem.getClosed() );
		Assert.assertEquals( 0, htmlItem.getAttributes().size() );

		htmlItem2 = (HtmlDirective)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_DIRECTIVE, htmlItem2.getType() );
		Assert.assertEquals( null, htmlItem2.getText() );
		Assert.assertEquals( null, htmlItem2.getTagname() );
		Assert.assertEquals( true, htmlItem2.getClosed() );
		Assert.assertEquals( 0, htmlItem2.getAttributes().size() );

		/*
		 * Values.
		 */

		htmlItem = new HtmlDirective( "<@master file=\"master.html\">" );
		Assert.assertNotNull( htmlItem );
		Assert.assertEquals( HtmlItem.T_DIRECTIVE, htmlItem.getType() );
		Assert.assertEquals( null, htmlItem.getText() );
		Assert.assertEquals( "<@master file=\"master.html\">", htmlItem.getTagname() );
		Assert.assertEquals( true, htmlItem.getClosed() );
		Assert.assertEquals( 0, htmlItem.getAttributes().size() );

		htmlItem.attribute( "file", "slave.html");

		Assert.assertEquals( 1, htmlItem.getAttributes().size() );
		Assert.assertEquals( "slave.html", htmlItem.getAttribute( "file" ) );

		Assert.assertEquals( "slave.html", htmlItem.setAttribute( "file", "master.html") );

		Assert.assertEquals( 1, htmlItem.getAttributes().size() );
		Assert.assertEquals( "master.html", htmlItem.getAttribute( "file" ) );

		htmlItem2 = (HtmlDirective)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_DIRECTIVE, htmlItem2.getType() );
		Assert.assertEquals( null, htmlItem2.getText() );
		Assert.assertEquals( "<@master file=\"master.html\">", htmlItem2.getTagname() );
		Assert.assertEquals( true, htmlItem2.getClosed() );
		Assert.assertEquals( 1, htmlItem2.getAttributes().size() );
		Assert.assertEquals( "master.html", htmlItem2.getAttribute( "file" ) );

		htmlItem2.setClosed( false );

		Assert.assertEquals( true, htmlItem2.getClosed() );

		Assert.assertEquals( "master.html", htmlItem.removeAttribute( "file" ) );
		Assert.assertEquals( 0, htmlItem.getAttributes().size() );
		Assert.assertEquals( null, htmlItem.removeAttribute( "file" ) );
		Assert.assertEquals( 0, htmlItem.getAttributes().size() );
		htmlItem.setClosed( true );

		htmlItem2 = (HtmlDirective)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_DIRECTIVE, htmlItem2.getType() );
		Assert.assertEquals( null, htmlItem2.getText() );
		Assert.assertEquals( "<@master file=\"master.html\">", htmlItem2.getTagname() );
		Assert.assertEquals( true, htmlItem2.getClosed() );
		Assert.assertEquals( 0, htmlItem2.getAttributes().size() );

		htmlItem2.setText( "text" );
		Assert.assertEquals( null, htmlItem2.getText() );
	}

}
