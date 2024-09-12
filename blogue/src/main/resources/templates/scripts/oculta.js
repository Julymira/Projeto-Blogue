document.addEventListener('DOMContentLoaded', function() {
    const loginLink = document.getElementById('login-link');
    const registerLink = document.getElementById('register-link');
    const logoutLink = document.getElementById('logout-link');

    if (isLoggedIn()) {
        // Usuário logado: oculta links de login e registro, mostra o logout
        loginLink.style.display = 'none';
        registerLink.style.display = 'none';
        logoutLink.style.display = 'block';
    } else {
        // Usuário não logado: mostra links de login e registro, oculta o logout
        loginLink.style.display = 'block';
        registerLink.style.display = 'block';
        logoutLink.style.display = 'none';
    }

    // Adiciona evento de clique no link de logout
    logoutLink.addEventListener('click', function(event) {
        event.preventDefault();
        logout();
    });
});