<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%--Шаблон чата взят отсюда: https://bootsnipp.com/snippets/vrzGb--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>History Message page</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>
<body>

<%--НАВИГАЦИОННЫЙ БАР, ШТОРКА--%>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">MERA school 2K17</a>
        </div>

        <div class="collapse navbar-collapse" id="navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Log out</a></li>
            </ul>
            <form class="navbar-form navbar-right" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search message..">
                </div>
                <button type="submit" class="btn btn-default">Search</button>
            </form>
        </div>
    </div>
</nav>
<%--НАВИГАЦИОННЫЙ БАР, ШТОРКА - КОНЕЦ--%>
<br>
<br>
<br>

<h2 class="form-heading" align="center">History message</h2>

<br>
<br>


<%--Список активных пользователей--%>
<div class="main_section">
    <div class="container">
        <div class="chat_container">
            <div class="col-sm-3 chat_sidebar">
                <div class="row">
                    <div class="dropdown all_conversation">
                        <button class="dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                            All users
                        </button>
                    </div>
                    <div class="member_list">
                        <ul class="list-unstyled">
                            <li class="left clearfix">
                     <span class="chat-img pull-left">
                     </span>
                                <div class="chat-body clearfix">
                                    <div class="header_sec">
                                        <strong class="primary-font">One people</strong>
                                    </div>
                                </div>
                            </li>
                            <li class="left clearfix">
                     <span class="chat-img pull-left">
                     </span>
                                <div class="chat-body clearfix">
                                    <div class="header_sec">
                                        <strong class="primary-font">Two people </strong>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <!--СПИСОК ПОЛЬЗОВАТЕЛЕЙ КОНЕЦ-->


            <%--Форма для отображения сообщений --%>
            <div class="col-sm-9 message_section">
                <div class="row">
                    <div class="chat_area">
                        <ul class="list-unstyled">
                            <li class="left clearfix">
                     <span class="chat-img1 pull-left">

                     </span>
                                <div class="chat-body1 clearfix">
                                    <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots
                                        in a piece of classical Latin literature from 45 BC, making it over 2000 years
                                        old. Richard McClintock, a Latin professor at Hampden-Sydney College in
                                        Virginia.</p>
                                    <div class="chat_time pull-right">09:40PM</div>
                                </div>
                            </li>
                            <li class="left clearfix">
                     <span class="chat-img1 pull-left">
                     </span>
                                <div class="chat-body1 clearfix">
                                    <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots
                                        in a piece of classical Latin literature from 45 BC, making it over 2000 years
                                        old. Richard McClintock, a Latin professor at Hampden-Sydney College in
                                        Virginia.</p>
                                    <div class="chat_time pull-right">09:40PM</div>
                                </div>
                            </li>
                        </ul>
                    </div><!--chat_area-->
                    <%--Форма для отображения сообщений КОНЕЦ--%>
                    <form:form method="POST" modelAttribute="messageForm">
                        <div class="message_write">
                            <textarea path="message" class="form-control"
                                           placeholder="Your message.."></textarea>
                            <div class="clearfix"></div>
                            <div class="chat_bottom">
                                <button class="pull-right btn btn-primary" type="submit">Send</button>
                                <a href="/chat" class="pull-left btn btn-primary">Refresh</a>
                            </div>
                        </div>
                    </form:form>


                </div>
            </div>
        </div>
    </div>
</div>

<br>
<br>
<br>

<footer>
    <span style='padding-left:10px;'> &copy; Aymaletdinov R.</span>
</footer>

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>