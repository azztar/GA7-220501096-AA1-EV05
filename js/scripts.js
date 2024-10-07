// Manejo del Login
function login(event) {
  event.preventDefault();

  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;
  const errorMessage = document.getElementById("error-message");

  errorMessage.textContent = "";

  // Validar campos vacíos
  if (username === "" || password === "") {
    errorMessage.textContent = "Both fields are required.";
    return;
  }

  // Validación de credenciales
  if (username === "admin" && password === "12345") {
    alert("Login successful!");
    localStorage.setItem("loggedIn", "true");
    window.location.href = "index.html";
  } else {
    errorMessage.textContent = "Incorrect username or password.";
  }
}

// Asignar evento al botón de Login
document.getElementById("loginForm").addEventListener("submit", login);

// Manejo del Formulario de Contacto
document
  .getElementById("contactForm")
  .addEventListener("submit", function (event) {
    event.preventDefault();

    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const message = document.getElementById("message").value;
    const errorMessage = document.getElementById("contact-error-message");

    // Limpiar mensajes anteriores
    errorMessage.textContent = "";

    // Validar campos vacíos
    if (name === "" || email === "" || message === "") {
      errorMessage.textContent = "All fields are required.";
      return;
    }

    // Enviar formulario exitosamente
    alert("Form submitted successfully!");
  });

// Manejo del Menú de Navegación (Hamburguesa)
const hamburger = document.querySelector(".hamburger");
const navMenu = document.querySelector(".nav-menu");

hamburger.addEventListener("click", function () {
  navMenu.classList.toggle("open");
});
