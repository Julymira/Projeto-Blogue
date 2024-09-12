function logout() {
    // Remove o token ou qualquer informação de autenticação
    document.cookie = "authToken=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    window.location.href = "login.html"; // Redirecionar para a página de login após o logout
}

document.addEventListener('DOMContentLoaded', function() {
    const logoutLink = document.getElementById('logout-link');

    logoutLink.addEventListener('click', function(event) {
        event.preventDefault();
        logout();
    });
});