<%@page import="com.ozonetel.kookoo.Dial"%>
<%@page import="com.ozonetel.kookoo.CollectDtmf"%>
<%@page import="com.ozonetel.kookoo.Response,java.util.*,com.ozonetel.kookoo.Record"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
    <c:when test='${param.event == "NewCall" || sessionScope.state == "start" }'> 
        <%            
            Response kResponse = new Response();
            CollectDtmf cd = new CollectDtmf();
            cd.setMaxDigits(11);
            cd.setTermChar("#");
            cd.addPlayText("please enter the number that you want to dial followed by hash, if it is s t d number, enter 0 as pre fix");
            kResponse.addCollectDtmf(cd);
            session.setAttribute("state", "collectNumber");
            out.print(kResponse.getXML());
        %>
    </c:when>
    <c:when test='${param.event == "GotDTMF" || requestScope.state == "collectNumber"}' >
        <%            
            String number = request.getParameter("data");
            
            Response kResponse1 = new Response();
            
            if (number == null || number.equals("")) {
                kResponse1.addPlayText("you have not entered any input");
                session.setAttribute("state", "start");
            } else {
                session.setAttribute("number", number);
                kResponse1.addPlayText("please wait while we transfer your call to out customer care");
                Dial dial = new Dial(true);
                dial.setNumber(number);
                kResponse1.addDial(dial);
                session.setAttribute("state", "dialed");
            }
            
            out.print(kResponse1.getXML());
        %>
    </c:when>
    <c:when test='${param.event == "Dial" || requestScope.state == "dialed"}' >
        <%            
            
            Response dialStResponse = new Response();
            if (request.getParameter("status").equalsIgnoreCase("answered")) {
                dialStResponse.addPlayText("Thank you for calling");
            } else {
                dialStResponse.addPlayText("The call is not  answered");
            }
            dialStResponse.addHangup();
            out.print(dialStResponse.getXML());
        %>
    </c:when>
</c:choose>
