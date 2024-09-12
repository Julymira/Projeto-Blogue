
window.onload = function() {
    const token = getCookie('authToken');

    if (!token) {
        alert('É necessário estar logado.');
        window.location.href = 'login.html'; // Redireciona para a página de login
    }
};

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

