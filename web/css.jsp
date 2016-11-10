<%-- 
    Document   : css
    Created on : 09/10/2016, 14:59:56
    Author     : Regis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    table {
        border-collapse: collapse;
        width: 100%;
    }

    tr, td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #33d6ff;
    }
    tr:nth-child(even){
        background-color: #e6faff
    }
   
    tr:first-child {
    background-color: #666;
    color: white;
    }
    
    tr:hover {
        background-color: #33d6ff
    }
    
    .ul.pagination {
        display: inline-block;
        padding: 0;
        margin: 0;
    }

    ul.pagination li {display: inline;}

    ul.pagination li a {
        color: black;
        float: left;
        padding: 8px 16px;
        text-decoration: none;
        transition: background-color .3s;
    }

    ul.pagination li a.active {
        background-color: #4CAF50;
        color: white;
    }

    ul.pagination li a:hover:not(.active) {background-color: #ddd;}
    
    div.center {text-align: center;}
</style>