<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/static_content/Header.jsp"/>

<div class="container">
    <a href="/playlists">back to playlists</a>
    <h1>tracks</h1>
    <form method="post" action="#">
        <input type="hidden" name="searchtrack" value="search">
        <input type="text" name="trackname">
        <input type="submit" value="search">
    </form>
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
    <c:if test="${not empty requestScope['TRACK_LIST']}">
        <c:forEach items='${requestScope["TRACK_LIST"]}' var="track" >
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
                    <form method="post" action="#">
                        <input type="hidden" name="addtrack" value="add">
                        <input type="hidden" name="title" value="<c:out value="${track.title}"/>">
                        <input type="hidden" name="preformer" value="<c:out value="${track.preformer}"/>">
                        <input type="submit" value="Add track">
                    </form>
                </div>
            </div>
        </c:forEach>
    </c:if>

    <c:set var="track" value='${requestScope["TRACK"]}' />
    <c:if test="${not empty track}">
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
            <form method="post" action="#">
                <input type="hidden" name="addtrack" value="add">
                <input type="hidden" name="title" value="<c:out value="${track.title}"/>">
                <input type="hidden" name="preformer" value="<c:out value="${track.preformer}"/>">
                <input type="submit" value="Add track">
            </form>
        </div>
        </c:if>
    </div>
</div>

<jsp:include page="/static_content/Footer.jsp"/>

