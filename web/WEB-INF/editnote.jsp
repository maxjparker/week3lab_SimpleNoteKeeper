<%-- 
    Document   : editnote
    Created on : Sep 20, 2019, 3:39:11 PM
    Author     : 657306
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Note</title>
    </head>
    <body>
        <h1>Simple Note Keeper</h1>
        <h2>Edit Note</h2>
        <form>
            <b>Title: </b>
            <input type="text" value="" name="titleInput"><br><br>
            <b>Contents:</b><br>
            <textarea rows="7" cols="40" value="Content goes here" name="contentInput"></textarea><br><br>
            <input type="submit" value="Submit">
        </form><br>
        <a href="/Week3Lab_SimpleNoteKeeper/note">Edit</a>
    </body>
</html>
