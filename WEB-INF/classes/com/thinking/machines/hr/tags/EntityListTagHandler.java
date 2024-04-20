package com.thinking.machines.hr.tags;
import javax.servlet.jsp.tagext.*;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import java.lang.reflect.*;
import java.util.*;
import javax.servlet.jsp.*;
public class EntityListTagHandler extends BodyTagSupport
{
private String populatorClass;
private String populatorMethod;
private String name;
private int index;
private List list;
public EntityListTagHandler()
{
reset();
}
private void reset()
{
this.populatorClass=null;
this.populatorMethod=null;
this.name=null;
this.index=0;
if(this.list!=null)
{
this.list.clear();
this.list=null;
}
}
public void setPopulatorClass(java.lang.String populatorClass)
{
this.populatorClass=populatorClass;
}
public java.lang.String getPopulatorClass()
{
return this.populatorClass;
}
public void setPopulatorMethod(java.lang.String populatorMethod)
{
this.populatorMethod=populatorMethod;
}
public java.lang.String getPopulatorMethod()
{
return this.populatorMethod;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public int doStartTag()
{
try
{
Class c=Class.forName(this.populatorClass);
Object obj=c.newInstance();
Class parameters[]=new Class[0];
Method m=c.getMethod(this.populatorMethod,parameters);

list=(List)m.invoke(obj);
if(list==null) return super.SKIP_BODY;
if(list.size()==0) return super.SKIP_BODY;
index=0;
Object bean=list.get(index);
pageContext.setAttribute("serialNumber",new Integer(index+1),PageContext.REQUEST_SCOPE);
pageContext.setAttribute(name,bean,PageContext.REQUEST_SCOPE);
index++;
return super.EVAL_BODY_INCLUDE;
}catch(Throwable t)
{
// some logging act should be done later on (in next styles)
return super.SKIP_BODY;
}

}
public int doAfterBody()
{
if(this.list.size()==index) return super.SKIP_BODY;
Object bean=list.get(index);
pageContext.setAttribute("serialNumber",new Integer(index+1),PageContext.REQUEST_SCOPE);
pageContext.setAttribute(this.name,bean,PageContext.REQUEST_SCOPE);
index++;
return super.EVAL_BODY_AGAIN;
}
public int doEndTag()
{
reset();
return super.EVAL_PAGE;
}

}