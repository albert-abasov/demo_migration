<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="/WEB-INF/tags" %>
<t:baseLayout>
    <div class="col-md-12" role="main">
        <h1 class="alert alert-success" aria-live="polite">Thank you, ${account.username != null ? account.username : 'User'}!</h1>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <a href="/user/dashboard" class="btn btn-primary btn-block" role="button" aria-label="Continue to your dashboard">Continue »</a>
        </div>
    </div>
</t:baseLayout>