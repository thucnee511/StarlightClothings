<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/reset.css" rel="stylesheet" type="text/css"/>
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <div id="login__block" class="form--block">
                <p class="form--block__title">Login to continue</p>
                <form id="login--form" action="main" method="POST">
                    <div class="form--input__block">
                        <label class="form--label" for="login__username">Username or Email</label>
                        <input class="form--input__text" id="login__username" name="username" type="text" required=""/>
                    </div>
                    <div class="form--input__block">
                        <label class="form--label" for="login__password">Password</label>
                        <input class="form--input__text" id="login__password" name="password" type="password" required=""/>
                    </div>
                    <div class="g-recaptcha" data-sitekey="6LcyhbEoAAAAAKHCdCPIHmtnsb02xGf8ssIQBa9y"></div>
                    <div id="login__error">${LOGIN_ERROR}</div>
                    <input type="text" name="action" value="Login" hidden="" style="display:none"/>
                    <input class="form--input__button" type="submit" value="Login"/>
                </form>
                <span class="form--line">Or login with</span>
                <div class="form--input__block">
                    <div class="form--input__button" onclick="loginViaGoogle()">Login via Google mail</div>
                </div>
                <div class="changestate">
                    <p class="changestate__message">Don't have an account?</p>
                    <a href="signup.html" class="changestate__button">Sign up new account</a>
                </div>
            </div>
        </div>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <script src="js/login.js" type="text/javascript"></script>
    </body>
</html>
