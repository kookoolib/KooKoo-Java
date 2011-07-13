<%@page import="com.ozonetel.kookoo.Dial"%>
<%@page import="com.ozonetel.kookoo.CollectDtmf"%>
<%@page import="com.ozonetel.kookoo.Response,java.util.*,com.ozonetel.kookoo.Record"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String[] numbers = {"1234","4321"}; %>
<c:choose>
    <c:when test='${param.event == "NewCall" || sessionScope.state == "start" }'> 
        <%            
            Response kResponse = new Response();
            CollectDtmf cd = new CollectDtmf();
            cd.setMaxDigits(4);
            cd.setTermChar("#");
            cd.addPlayText("please enter the conference number followed by hash");
            kResponse.addCollectDtmf(cd);
            session.setAttribute("state", "collectdNumber");
            out.print(kResponse.getXML());
        %>
    </c:when>
    <c:when test='${param.event == "GotDTMF" || requestScope.state == "collectdNumber"}' >
        <%            
            String number = request.getParameter("data");
            
            Response kResponse1 = new Response();
            
            if (number == null || !number.equals(numbers[0])) {
                kResponse1.addPlayText("you have not entered any input");
                session.setAttribute("state", "start");
            } else {
                kResponse1.addConference(number);
            }
            
            out.print(kResponse1.getXML());
        %>
    </c:when>
    
</c:choose>
