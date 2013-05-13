<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Tambah Page</title>
        <style type="text/css">
            h1{
               font-family: sans-serif;
               border-bottom: 3px solid #abcae8;
               color:#4d7ba7;
            }
            
            table{
                border:2px solid #4d7ba7;
                color: #4d7ba7;
                font-family: arial;
                padding: 5px;
            }
            
            table td{
                padding: 5px;
                padding-bottom: 10px;
            }
            
            table input{
                border:1px solid #4d7ba7;
                padding: 3px;
            }
        </style>
    </head>
    <body>
        <h1>Tambah Playlist</h1>
        <form:form modelAttribute="lagu" action="${pageContext.request.contextPath}/tambah" method="post">
            <table>
                <tr>
                    <td>Judul</td>
                    <td>:</td>
                    <td><form:input path="judul" /></td>
                </tr>
                <tr>
                    <td>Penyanyi</td>
                    <td>:</td>
                    <td><form:input path="penyanyi" /></td>
                </tr>
                <tr>
                    <td>Pencipta</td>
                    <td>:</td>
                    <td><form:input path="pencipta" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Simpan" /></td>
                </tr>
            </table>
    </form:form>
    </body>
</html>
