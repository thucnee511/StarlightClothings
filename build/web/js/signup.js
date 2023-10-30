'usestrict';
window.onload = () => {
    const signupForm = document.getElementById("signup--form");
    const signupError = document.querySelector("#signup__error");
    signupForm.addEventListener("submit", event => {
        event.preventDefault();
        const response = grecaptcha.getResponse();
        if (response) {
            signupForm.submit();
        } else {
            signupError.innerHTML = "Please check confirm box before submiting";
        }
    });
};
const loginViaGoogle = () => {
    window.location.href = "https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:8080/SE171089_Assignment/googleLogin&response_type=code&client_id=91448810599-3lirhtkmf0gnaeraf636nsctpmhac6pg.apps.googleusercontent.com&approval_prompt=force";
};