// interaccionLogin.js

document.addEventListener("DOMContentLoaded", function () {
    // Obtener elementos del DOM
    
    const emailInput = document.getElementById("exampleInputEmail");
    const passwordInput = document.getElementById("exampleInputPassword");
    const errorMessage = document.getElementById("error-message");
    const loginForm = document.getElementById("loginForm"); 


    // Función para mostrar el mensaje de error y cambiar estilos
    function showError() {

        errorMessage.classList.remove("d-none");
        errorMessage.classList.add("d-flex");
        errorMessage.innerText = "Correo electronico y/o contraseña incorrectos.";
        errorMessage.style.color = "red";
        errorMessage.style.fontSize = "small";
        emailInput.style.border = "1px solid red";
        passwordInput.style.border = "1px solid red";
    }

    // Función para manejar la respuesta del backend
    function handleBackendResponse(isAuthenticated) {
        if (!isAuthenticated) {
            showError(); // Mostrar mensaje de error si la autenticación es falsa
        } else {
             // Redirigir al usuario a la página deseada después de iniciar sesión
            window.location.href = "indexUsuario.html";
        }
    }

    // Función para manejar el evento click del botón de inicio de sesión
    async function handleLoginClick(event) {
        event.preventDefault(); // Evitar que el formulario se envíe

        // Aquí debes realizar la solicitud al backend para verificar la autenticidad
        const credentials = {
            email: emailInput.value,
            password: passwordInput.value,
        };

        try {
            const response = await fetch('URL_DE_TU_BACKEND', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(credentials),
            });

            const result = await response.json();
            handleBackendResponse(result.isAuthenticated);
        } catch (error) {
            console.error('Error al autenticar', error);
        }
    }

    // Agregar el evento click al botón de inicio de sesión
    const loginButton = document.getElementById("loginButton");
    loginButton.addEventListener("click", handleLoginClick);
});
