'usestrict';
window.onload = () => {
    const loginForm = document.getElementById("login--form");
    const loginError = document.querySelector("#login__error");
    loginForm.addEventListener("submit", event => {
        event.preventDefault();
        const response = grecaptcha.getResponse();
        if (response) {
            loginForm.submit();
        } else {
            loginError.innerHTML = "Please check confirm box before submiting";
        }
    });
};
const loginViaGoogle = () => {
    window.location.href = "https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:8080/SE171089_Assignment/googleLogin&response_type=code&client_id=91448810599-3lirhtkmf0gnaeraf636nsctpmhac6pg.apps.googleusercontent.com&approval_prompt=force";
};