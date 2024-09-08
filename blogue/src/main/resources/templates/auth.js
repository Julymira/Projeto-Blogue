document.getElementById("loginButton").addEventListener("click", login);

async function login(event) {
    event.preventDefault(); // Impede o comportamento padrão do botão

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    try {
        const response = await fetch('http://localhost:8080/users/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });

        if (response.ok) {
            console.log("response", response);
            console.log("response.token", response.token);
            setCookie('authToken', response.token, 1); // Armazena o token por 1 dia
            document.getElementById("responseMessage").innerText = `Login bem-sucedido: ${data.message}`;
        } else {
            document.getElementById("responseMessage").innerText = "Falha no login: Usuário ou senha incorretos.";
        }
    } catch (error) {
        document.getElementById("responseMessage").innerText = "Erro na requisição: " + error.message;
    }
}

function setCookie(name, value, days) {
    const date = new Date();
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000)); // Calcula a data de expiração do cookie
    const expires = "expires=" + date.toUTCString();
    document.cookie = name + "=" + value + ";" + expires + ";path=/"; // Define o cookie
}