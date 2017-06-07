<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../content/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="../content/css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <div class="row">
    <div class="col-md-4 col-md-offset-7">
        <div class="panel panel-default">
            <div class="panel-heading">
                <span class="glyphicon glyphicon-lock"></span> Login
            </div>
            <div class="panel-body">
                <form class="form-horizontal" action="index" method="post" >
                    <div class="form-group">
                        <label for="username" class="col-sm-3 control-label">
                            username</label>
                        <div class="col-sm-9">
                            <input type="username" name="username" class="form-control" id="username" placeholder="username" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-3 control-label">
                            password</label>
                        <div class="col-sm-9">
                            <input type="password" name="password" class="form-control" id="password" placeholder="password" required>
                        </div>
                    </div>
                    <div class="form-group last">
                        <div class="col-sm-offset-3 col-sm-9">
                            <button type="submit" class="btn btn-success btn-sm">
                                Sign in</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>

</body>
</html>





