function getCookie(name) {
    let cookieArr = document.cookie.split(";");

    for (let i = 0; i < cookieArr.length; i++) {
        let cookiePair = cookieArr[i].split("=");

        if (name === cookiePair[0].trim()) {
            return decodeURIComponent(cookiePair[1]);
        }
    }
    return null;
}

function isLoggedIn() {
    return getCookie('authToken') !== null;
}

document.addEventListener('DOMContentLoaded', function() {
    const logoutLink = document.getElementById('logout-link');

    if (!isLoggedIn()) {
        logoutLink.style.display = 'none';
    } else {
        logoutLink.style.display = 'block';
    }
});