<%@page import="com.ozonetel.kookoo.CollectDtmf"%>
<%@page import="com.ozonetel.kookoo.Response,java.util.*,com.ozonetel.kookoo.Record"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String pin = "1234";%>
<c:choose>
    <c:when test='${param.event == "NewCall" || sessionScope.state == "start"}'> 
        <%
            Response kookooResponse = new Response();
            CollectDtmf cd = new CollectDtmf();
            cd.setMaxDigits(4);
            cd.addPlayText("Please enter the four digit pin terminated by hash");
            kookooResponse.addCollectDtmf(cd);
            session.setAttribute("state", "pinentered");
            out.print(kookooResponse.getXML());
        %>
    </c:when>
    <c:when test='${param.event == "GotDTMF" || requestScope.state == "pinentered"}'>
        <%
            String number = request.getParameter("data");

            Response kResponse1 = new Response();

            if (number == null || number.equals("")) {
                kResponse1.addPlayText("you have not entered any input");
                session.setAttribute("state", "start");
            } else if (number.equals(pin)) {
                kResponse1.addPlayText("You have entered the correct pin, Thanks for calling");
            } else {
                kResponse1.addPlayText("You have entered invalid pin, Thanks for calling");
            }

            out.print(kResponse1.getXML());
        %>
    </c:when>
</c:choose>
