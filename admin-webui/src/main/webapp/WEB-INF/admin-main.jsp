<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@include file="/WEB-INF/includex/include-head.jsp" %>
<body>
<%@include file="/WEB-INF/includex/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/includex/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">控制面板</h1>
            <div class="row placeholders">
                <security:authorize access="hasRole('DBA')">
	                <div class="col-xs-6 col-sm-3 placeholder">
	                    <img data-src="holder.js/200x200/auto/sky" class="img-responsive"
	                         alt="Generic placeholder thumbnail">
	                    <h4>Label</h4>
	                    <span class="text-muted">Something else</span>
	                </div>
                </security:authorize>
                 <security:authorize access="hasRole('管理员') or hasRole('DBA') or hasRole('SVIP') or hasAuthority('role:get')">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img data-src="holder.js/200x200/auto/vine" class="img-responsive"
                         alt="Generic placeholder thumbnail">
                    <h4>Label</h4>
                    <span class="text-muted">Something else</span>
                </div>
                </security:authorize>
                <security:authorize access="hasRole('DBA') or hasRole('管理员') or hasRole('SVIP') or hasRole('VIP')">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img data-src="holder.js/200x200/auto/sky" class="img-responsive"
                         alt="Generic placeholder thumbnail">
                    <h4>Label</h4>
                    <span class="text-muted">Something else</span>
                </div>
                </security:authorize>
                <security:authorize access="hasRole('DBA') or hasRole('管理员') or hasRole('SVIP') or hasRole('VIP') or hasRole('普通用户')">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img data-src="holder.js/200x200/auto/vine" class="img-responsive"
                         alt="Generic placeholder thumbnail">
                    <h4>Label</h4>
                    <span class="text-muted">Something else</span>
                </div>
                </security:authorize>
            </div>
        </div>
    </div>
</div>
</body>
</html>