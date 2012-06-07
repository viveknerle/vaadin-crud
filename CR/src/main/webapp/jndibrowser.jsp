<%@page import="javax.naming.Binding"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.NameClassPair"%>
<%@page import="javax.naming.NamingEnumeration"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>

<%!
	
	private String printTabs(StringBuilder sb, int i) {
		return StringUtils.leftPad("", 6*i, "&nbsp;");	
	}

	public void tryLookup(Context ctx, String lookup,StringBuilder sb, int i) throws NamingException {
		// sb.append(printTabs(sb,i)).append("--lookup:").append(ctx.getNameInNamespace());
		try {
			Object o = ctx.lookup(lookup);
			sb.append(printTabs(sb,i)).append("tryLookup Class:").append(o.getClass()).append("<br/>");	
			if (Context.class.isAssignableFrom(o.getClass())) {
				sb.append(printTabs(sb,i)).append("isAssignableFrom");	
				listNamingEnumeration((Context)o,"",sb,i+1);				
			} else {
				sb.append(o.toString()).append("<br/>");	
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	

	public void listBindings(Context ctx, String path,StringBuilder sb, int i) throws NamingException {
		NamingEnumeration<Binding> ne = ctx.listBindings(path);
		while (ne.hasMoreElements()) {
			Binding elem = ne.nextElement();
			sb.append(printTabs(sb,i)).append("listBinding:").append(elem.getName()).append("<br/>");	
			
		}
		
	}

	
	
	public void listNamingEnumeration(Context ctx, String path,StringBuilder sb, int i) throws NamingException {
	if (path==null || "".equals(path)) {	
		try {
			String nns=ctx.getNameInNamespace();
			sb.append(printTabs(sb,i)).append("listNamingEnumeration Context:").append(nns).append("<br/>");
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
	} else {
		sb.append(printTabs(sb,i)).append("listNamingEnumeration Path:").append(path).append("<br/>");	
	}
	try {	
		listBindings(ctx,path,sb,i+1);
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	
	
	NamingEnumeration ne = ctx.list(path);
	if (!ne.hasMoreElements() && i<10) {
		if (i<10) tryLookup(ctx,path,sb,i);
		return;
	}	
	while (ne.hasMoreElements()) {
		Object elem = (Object) ne.nextElement();
		if (elem instanceof NameClassPair) {
			NameClassPair e = (NameClassPair)elem;
			/*
			try {
				sb.append(printTabs(sb,i)).append("Name:").append(e.getName());
			} catch (Exception ex) {ex.printStackTrace();}
			try {
				sb.append(" ClassName:").append(e.getClassName());
			} catch (Exception ex) {ex.printStackTrace();}
			sb.append("<br/>");
			*/
			try{
				Class cl = Class.forName(e.getClassName());
				if (Context.class.isAssignableFrom(cl)) {
					String lookup = path + "/" + e.getName();
					// sb.append(printTabs(sb,i)).append("Lookup:").append(lookup).append("<br/>");	
					// Context context = (Context)ctx.lookup(lookup);
					if (i<10) {
						try {
							// tryLookup(ctx,lookup,sb,i+1);
							listNamingEnumeration(ctx,lookup,sb, i+1);	
						} catch (Exception ex) {ex.printStackTrace();sb.append("Errore").append(ex.getMessage());}
					}
					
				}
			} catch (Exception ex) {ex.printStackTrace();}

		}
	}
	
}%>

<%
	String list = null;
	list = request.getParameter("list");
	if (list == null) {
		list = "java:";
	}
	String lookup = null;
	lookup = request.getParameter("lookup");

	Context ctx = new InitialContext();
	StringBuilder sb = new StringBuilder();
	listNamingEnumeration(ctx,list,sb, 0);
	out.print(sb.toString());
	if (lookup!=null) {
		tryLookup(ctx,lookup,sb,0);
	}
	
	
%>

<form method="post"> 
<input type="text" value="<%=list%>" name="list" size="50" />
<input type="submit" value="Esegui" />

</form>

