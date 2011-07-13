<%@page import="com.ozonetel.kookoo.Response,java.util.*,com.ozonetel.kookoo.Record"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String[] playTexts = {"Press 1 for English,", "Press 2 for Hindi,", "Press 3 for Kannada"};%>
<c:if test='${param.event == "NewCall"}'> 
    <%
        Response kookooResponse = new Response();
        for (String text : playTexts) {
            kookooResponse.addPlayText(text);
        }
        out.print(kookooResponse.getXML());
    %>
</c:if>
