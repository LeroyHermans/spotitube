<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <jsp:include page="/static_content/Header.jsp"/>

    <div class="container">
        <h1>Playlists</h1>
        <div class="button">
            <a href="/addplaylist">add playlist</a>
        </div>
        <div id="container" class="cols">
            <c:forEach items="${PLAYLISTS}" var="playlist">
                <div class="box">
                    <div class="title">
                       Title: <c:out value="${playlist.name}" />
                    </div>
                    <div class="duration">
                       Duration: <c:out value="${playlist.calculateDuration()}" />
                    </div>
                    <div class="button">
                        <form method="get" action="playlistdetails">
                            <input type="hidden" name="playlistname" value="<c:out value="${playlist.name}" />">
                            <input type="submit" value="Details">
                        </form>
                    </div>
                    <div class="button">
                        <form method="post" action="playlists">
                            <input type="hidden" name="delete" value="delete">
                            <input type="hidden" name="playlistname" value="<c:out value="${playlist.name}"/>">
                            <input type="submit" value="verwijder">
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <jsp:include page="/static_content/Footer.jsp"/>
