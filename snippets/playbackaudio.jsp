
<%@page import="com.ozonetel.kookoo.Response,java.util.Date,com.ozonetel.kookoo.Record"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose> 
    <c:when test='${param.event == "NewCall"}'> 
        <%--When we get a new call,we have to prompt for voice--%>
        <%
            Record record = new Record();
            ////give unique file name for each recording
            record.setFileName("kookoo_audio" + new Date().getTime());
            record.setFormat("wav");
            record.setMaxDuration(60L);
            request.getSession().setAttribute("state", "recorded");
            Response kookooResponse = new Response();
            kookooResponse.addPlayText("Please record your message after beep");
            kookooResponse.addRecord(record);
            out.print(kookooResponse.getXML());
        %>
    </c:when>
    <c:when test='${param.event == "Record" && sessionScope.state == "recorded"}' >
        <%
            Response audioResponse = new Response();
            audioResponse.addPlayText("Your recorded audio is ");
            //recorded file will be come as  url in request parameter called data
            audioResponse.addPlayAudio(request.getParameter("data"));
            audioResponse.addPlayText("Thank you for calling, have a nice day");
            audioResponse.addHangup();
            out.println(audioResponse.getXML());
        %>
    </c:when>
</c:choose>

