<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/static_content/Header.jsp"/>

<div class="container">
    <h1>Add Playlist</h1>
    <div id="container" class="cols">
        <form method="post" action="addplaylist">
            <input type="hidden" name="addplaylist" value="add">
            <div class="form-group">
                <label for="playlistname">playlist name:</label>
                <input type="playlistname" class="form-control" name="playlistname" id="playlistname">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </div>
</div>

<jsp:include page="/static_content/Footer.jsp"/>
