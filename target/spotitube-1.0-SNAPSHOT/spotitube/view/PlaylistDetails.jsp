<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/static_content/Header.jsp"/>

<c:set var="playlist" value='${requestScope["PLAYLIST"]}' />

<div class="container">
    <div class="title">
        <h1><c:out value="${playlist.name}" /></h1>
    </div>
    <div class="button">
        <a href="/addtracktoplaylist?playlistname=<c:out value="${playlist.name}" />">add track</a>
    </div>

    <div class="row">
        <div class="col-md-3">
            <h2>Title</h2>
        </div>
        <div class="col-md-3">
            <h2>Preformer</h2>
        </div>
        <div class="col-md-2">
            <h2>Duration</h2>
        </div>
    </div>
    <c:forEach items="${playlist.tracks}" var="track" >
        <div class="row">
            <div class="col-md-3">
                <c:out value="${track.title}"/>
            </div>
            <div class="col-md-3">
                <c:out value="${track.preformer}"/>
            </div>
            <div class="col-md-2">
                <c:out value="${track.duration}"/>
            </div>
            <div class="col-md-2">
                <form method="get" action="/trackdetails">
                    <input type="hidden" name="title" value="<c:out value="${track.title}"/>">
                    <input type="hidden" name="preformer" value="<c:out value="${track.preformer}"/>">
                    <input type="submit" value="details">
                </form>
            </div>
            <div class="col-md-2">
                <form method="post" action="#">
                    <input type="hidden" name="title" value="<c:out value="${track.title}"/>">
                    <input type="hidden" name="preformer" value="<c:out value="${track.preformer}"/>">
                    <input type="submit" name="action" value="delete">
                </form>
            </div>
        </div>

    </c:forEach>


</div>

<jsp:include page="/static_content/Footer.jsp"/>
