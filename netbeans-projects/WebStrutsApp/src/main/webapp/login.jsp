<%-- 
    Document   : login
    Created on : Jun 4, 2015, 11:51:55 AM
    Author     : Caio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <body>
        <h1>Login</h1>
        <html:form action="/login">

            <table border="0">
                <tbody>
                    <tr>
                        <td colspan="2">
                            <bean:write name="LoginForm" property="error" filter="false"/>
                            &nbsp;</td>
                    </tr>
                    <tr>
                        <td>Enter your name:</td>
                        <td><html:text property="name"/></td>
                        <th><html:errors property="name"/></th>
                    </tr>
                    <tr>
                        <td>Enter your email:</td>
                        <td><html:text property="email"/></td>
                        <th><html:errors property="email"/></th>
                    </tr>
                    <tr>
                        <td></td>
                        <td><html:submit value="Login"/></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>

        </html:form>
    </body>
</html>
