<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Meals</title>
    <style>
        .normal {color: green;}
        .excess {color: red;}
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<br/>

<c:if test="${!empty meals}">
    <table border="1" cellpadding="8" cellspacing="0"   >
        <tr>
            <th width="160">Date and Time</th>
            <th width="60">Description</th>
            <th width="80">Calories</th>
        </tr>
        <tbody id="target">
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td><%=TimeUtil.toString(meal.getDateTime())%></td>
                <td align="center">${meal.description}</td>
                <td align="center">${meal.calories}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>