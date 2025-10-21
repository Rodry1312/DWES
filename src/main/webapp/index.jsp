<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- Entrada única: redirige al controlador principal --%>
<%
  response.sendRedirect(request.getContextPath() + "/huella");
%>
