/*
 * Html parser, abstraction above tokenizer.
 * Copyright (C) 2005  Nicholas Clarke
 *
 */

/*
 * History:
 *
 * 12-Jan-2005 : First implementation.
 * 31-Jan-2005 : Usable main method for tokenizer debugging.
 *
 */

package com.antiaction.common.html;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Html parser, abstraction above tokenizer.
 *
 * @version 1.00
 * @author Nicholas Clarke <mayhem[at]antiaction[dot]com>
 */
public class HtmlParser {

	private HtmlTokenizer tokenizer;

	public HtmlParser() {
		tokenizer = new HtmlTokenizer();
	}

	/*
	public void parse(String html) {
		StringReader reader = new StringReader( html );
	}
	*/

	public List parse(InputStream in) throws IOException {
		List result = parse( HtmlStreamInput.getInstance( in ) );
		in.close();
		return result;
	}

	public List parse(Reader in) throws IOException {
		List result = parse( HtmlReaderInput.getInstance( in ) );
		in.close();
		return result;
	}

	public List parse(HtmlInput in) throws IOException {
		tokenizer.init( in );
		int state = 0;
		List result = new ArrayList();

		HtmlTagBase htmlTag = null;
		String attribName = null;
		boolean eq = false;
		String attribValue = null;

		boolean b = true;
		int type;
		while ( b ) {
			type = tokenizer.next();
			switch ( state ) {
			case 0:
				switch ( type ) {
				case HtmlConst.T_TEXT:
					result.add( new HtmlText( tokenizer.textBuf.toString() ) );
					break;
				case HtmlConst.T_PROCESSING:
					result.add( new HtmlProcessing( tokenizer.tagBuf.toString(), tokenizer.procBuf.toString() ) );
					break;
				case HtmlConst.T_EXCLTYPE:
					result.add( new HtmlExclamation( tokenizer.tagBuf.toString(), tokenizer.excltypeBuf.toString() ) );
					break;
				case HtmlConst.T_COMMENT:
					result.add( new HtmlComment( tokenizer.tagBuf.toString(), tokenizer.commentBuf.toString() ) );
					break;
				case HtmlConst.T_ENDTAG:
					result.add( new HtmlEndTag( tokenizer.tagBuf.toString(), tokenizer.endtagBuf.toString() ) );
					break;
				case HtmlConst.T_TAG_START:
					htmlTag = new HtmlTag( tokenizer.tagnameBuf.toString() );
					result.add( htmlTag );
					state = 1;
					attribName = null;
					eq = false;
					break;
				case HtmlConst.T_EOS:
					b = false;
					break;
				case HtmlConst.T_DIRECTIVE_START:
					htmlTag = new HtmlDirective( tokenizer.tagnameBuf.toString() );
					result.add( htmlTag );
					state = 1;
					attribName = null;
					eq = false;
					break;
				default:
					break;
				}
				break;
			case 1:
				switch ( type ) {
				case HtmlConst.T_TAG_TEXT:
					if ( !eq ) {
						if ( attribName != null ) {
							htmlTag.attribute( attribName, "" );
						}
						attribName = tokenizer.tagtextBuf.toString().toLowerCase();
						//eq = false;
					}
					else {
						attribValue = tokenizer.tagtextBuf.toString();
						htmlTag.attribute( attribName, attribValue );
						attribName = null;
						eq = false;
					}
					break;
				case HtmlConst.T_TAG_EQ:
					eq = true;
					break;
				case HtmlConst.T_TAG_TEXT_QUOTED:
					if ( !eq ) {
						if ( attribName != null ) {
							attribValue = tokenizer.tagtextBuf.toString();
							htmlTag.attribute( attribName, attribValue );
							attribName = null;
							//eq = false;
						}
					}
					else {
						attribValue = tokenizer.tagtextBuf.toString();
						if ( attribName != null ) {
							htmlTag.attribute( attribName, attribValue );
							attribName = null;
						}
						eq = false;
					}
					break;
				case HtmlConst.T_TAG_CLOSED:
					htmlTag.setClosed( true );
					eq = false;
					break;
				case HtmlConst.T_TAG_END:
					htmlTag.setText( tokenizer.tagBuf.toString() );
					state = 0;
					break;
				case HtmlConst.T_EOS:
					if ( attribName != null ) {
						htmlTag.attribute( attribName, "" );
					}
					b = false;
					break;
				default:
					break;
				}
				break;
			}
		}

		return result;
	}

}
