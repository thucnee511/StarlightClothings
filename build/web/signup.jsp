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
            <div id="signup__block" class="form--block">
                <p class="form--block__title">Signup new account</p>
                <form id="signup--form" action="main" method="POST">
                    <div class="form--input__block">
                        <label class="form--label" for="signup__email">Email</label>
                        <input class="form--input__text" id="signup__email" name="email" type="text" required=""/>
                    </div>
                    <div class="form--input__block">
                        <label class="form--label" for="signup__username">Username</label>
                        <input class="form--input__text" id="signup__username" name="username" type="text" required=""/>
                    </div>
                    <div class="form--input__block">
                        <label class="form--label" for="signup__password">Password</label>
                        <input class="form--input__text" id="signup__password" name="password" type="password" required=""/>
                    </div>
                    <div class="form--input__block">
                        <label class="form--label" for="signup__confirmpassword">Confirm password</label>
                        <input class="form--input__text" id="signup__confirmpassword" name="confirmPassword" type="password" required=""/>
                    </div>
                    <div class="form--input__block">
                        <label class="form--label" for="signup__phone">Phone number</label>
                        <input class="form--input__text" id="signup__phone" name="phone" type="text" required=""/>
                    </div>
                    <div class="form--input__block">
                        <label class="form--label" for="signup__address">Address</label>
                        <input class="form--input__text" id="signup__address" name="address" type="text" required=""/>
                    </div>
                    <div class="g-recaptcha" data-sitekey="6LcyhbEoAAAAAKHCdCPIHmtnsb02xGf8ssIQBa9y"></div>
                    <div id="signup__error">${SIGNUP_ERROR}</div>
                    <input type="text" name="action" value="Signup" hidden="" style="display:none"/>
                    <input class="form--input__button" type="submit" value="Signup"/>
                </form>
                <span class="form--line">Or sign up with</span>
                <div class="form--input__block">
                    <div class="form--input__button" onclick="loginViaGoogle()">Login via Google mail</div>
                </div>
                <div class="changestate">
                    <p class="changestate__message">Already had an account?</p>
                    <a href="login.html" class="changestate__button">Login existing account</a>
                </div>
            </div>
        </div>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <script src="js/signup.js" type="text/javascript"></script>
    </body>
</html>
