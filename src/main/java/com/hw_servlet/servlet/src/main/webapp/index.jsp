
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Request" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>IP list</title>
</head>
<body>
<%ArrayList<Request> repository = (ArrayList<Request>) request.getAttribute("repository");%>
<%Request currentRequest = repository.get(repository.size()-1);%>
<h1>CURRENT REQUEST</h1>
<div class="1" style ="font-weight: bold">
    <p>IP ADRESS - <%out.println(currentRequest.getIp());%></p>
    <p> DATE - <%out.println(currentRequest.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss")));%></p>
    <p> USER_AGENT - <%out.println(currentRequest.getUserAgent());%></p>
</div>

<h2>REQUEST LIST</h2>
<ul>
    <%for (Request request1:repository){%>
    <li>
        <%out.print(request1.getIp());%>
        <%out.print(request1.getUserAgent());%>
        <%out.print(request1.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss")));}%>
    </li>
</ul>
</body>
</html>