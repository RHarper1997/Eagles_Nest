<%-- 
    Document   : header
    Created on : Feb 9, 2018, 10:45:58 AM
    Author     : regin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>The Eagle's Nest</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/WebStyle.css"/>
    </head>
    <body>
        <header>
            <p> Hello, ${theUser.firstName} </p>
            <h1><img id="eagles" src="images/EaglesLogo.png" alt="Philadelphia Eagles Logo">The Eagle's Nest</h1>
        </header>
