<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/static_content/Header.jsp"/>

<c:set var="track" value='${requestScope["TRACK"]}' />


    <c:if test="${not empty requestScope['SONG']}">
        <div class="container">
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
        <div class="col-md-2">
            <h2>Album</h2>
        </div>
    </div>
        <div class="row">
                <c:set var="song" value='${requestScope["SONG"]}' />
            <div class="col-md-3">
                <c:out value="${song.title}"/>
            </div>
            <div class="col-md-3">
                <c:out value="${song.preformer}"/>
            </div>
            <div class="col-md-2">
                <c:out value="${song.duration}"/>
            </div>
                <div class="col-md-2">
                    <c:out value="${song.album}"/>
                </div>
        </div>
        </div>
            </c:if>
            <c:if test="${not empty requestScope['VIDEO']}">
                <div class="container">
                    <div class="row">
                        <div class="col-md-1">
                            <h2>Title</h2>
                        </div>
                        <div class="col-md-2">
                            <h2>Preformer</h2>
                        </div>
                        <div class="col-md-2">
                            <h2>Duration</h2>
                        </div>
                        <div class="col-md-2">
                            <h2>playcount</h2>
                        </div>
                        <div class="col-md-2">
                            <h2>publicationdate</h2>
                        </div>
                        <div class="col-md-3">
                            <h2>description</h2>
                        </div>
                        <div class="row">
                            <c:set var="video" value='${requestScope["VIDEO"]}' />
                            <div class="col-md-1">
                                <c:out value="${video.title}"/>
                            </div>
                            <div class="col-md-2">
                                <c:out value="${video.preformer}"/>
                            </div>
                            <div class="col-md-2">
                                <c:out value="${video.duration}"/>
                            </div>
                            <div class="col-md-2">
                                <c:out value="${video.playCount}"/>
                            </div>
                            <div class="col-md-2">
                                <c:out value="${video.publicationDate}"/>
                            </div>
                            <div class="col-md-3">
                                <c:out value="${video.description}"/>
                            </div>
                        </div>
                    </div>
            </c:if>


<jsp:include page="/static_content/Footer.jsp"/>
