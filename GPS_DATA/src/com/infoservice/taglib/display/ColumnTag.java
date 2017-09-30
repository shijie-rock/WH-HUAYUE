package com.infoservice.taglib.display;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;

import com.infoservice.taglib.TagUtil;

public class ColumnTag extends BaseColumnTag {
	private String format;

	private String symbol;

	private String inparam;

	private String outparam;

	/* £¨·Ç Javadoc£©
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		try
		{
			TableTag tag = (TableTag) findAncestorWithClass(this, Class
					.forName(TagUtil.TABLE_TAG_CLASS));
			Object obj = tag.findFieldValueByName(field);
			if (obj != null)
			{
				//add by ansen 
				if (symbol != null && inparam != null && outparam != null)
				{
					String outPrint = conditionExt(obj,symbol,inparam,outparam);
					pageContext.getOut().print(outPrint);
				}
				else
				{
					if (format != null && format.length() > 0)
						pageContext.getOut().print(MessageFormat.format(format, new Object[] { obj }));
					else
						pageContext.getOut().print(obj.toString());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();

		}
		return super.doEndTag();
	}

	/* £¨·Ç Javadoc£©
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {

		return super.doStartTag();
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	private String conditionExt(Object o, String symbol, String inparam, String outparam) {

		String sout = "";
		if (validitySymbol(symbol) && inparam != null)
		{
			if (symbol.equals("=="))
			{
				if (o.toString().equals(inparam))
				{
					sout=outparam;
				}
			}
			
			if (symbol.equals(">"))
			{
				if (isNumber(o.toString()) && isNumber(inparam))
				{
					if (Double.valueOf(o.toString()) > Double.valueOf(inparam))
					{
						sout=outparam;
					}
				}
			}
			
			if (symbol.equals(">="))
			{
				if (isNumber(o.toString()) && isNumber(inparam))
				{
					if (Double.valueOf(o.toString()) >= Double.valueOf(inparam))
					{
						sout=outparam;
					}
				}
			}
			
			if (symbol.equals("<"))
			{
				if (isNumber(o.toString()) && isNumber(inparam))
				{
					if (Double.valueOf(o.toString()) < Double.valueOf(inparam))
					{
						sout=outparam;
					}
				}
			}
			
			if (symbol.equals("<="))
			{
				if (isNumber(o.toString()) && isNumber(inparam))
				{
					if (Double.valueOf(o.toString()) <= Double.valueOf(inparam))
					{
						sout=outparam;
					}
				}
			}
		}
		return sout;
	}

	private boolean validitySymbol(String symbol) {
		boolean ret = false;
		if (symbol != null && symbol.length() > 0)
		{
			if (symbol.equals("==") || symbol.equals(">") || symbol.equals(">=")
					|| symbol.equals("<") || symbol.equals("<="))
			{
                ret = true;
			}
		}
		return ret;
	}
	
	private boolean isNumber(String s)
	{
		boolean ret =true;
		Pattern pattern = Pattern.compile("[0-9+./]");
	    char[] ss = s.toCharArray();
	    for (int i=0;i<ss.length; i++)
	    {
	    	Matcher isNum = pattern.matcher(String.valueOf(ss[i]));
	    	if (!isNum.find())
	    	{
	    		ret = false;
	    		break;
	    	}
	    }
		return ret;
	}

	public String getInparam() {
		return inparam;
	}

	public void setInparam(String inparam) {
		this.inparam = inparam;
	}

	public String getOutparam() {
		return outparam;
	}

	public void setOutparam(String outparam) {
		this.outparam = outparam;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	

}
