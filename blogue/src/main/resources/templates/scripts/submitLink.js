document.getElementById("submitLink").addEventListener("click", async function(event) {
        event.preventDefault();  // Impede o comportamento padrão do link

        // Pega os dados do formulário
        const form = document.getElementById("registerForm");
        const formData = new FormData(form);

        try {
            const response = await fetch("http://localhost:8080/users/register", {
                method: "POST",
                body: new URLSearchParams(formData) // Transforma o FormData em URL-encoded
            });

            if (response.ok) {
                // Redireciona para a página de login após sucesso
                window.location.href = "login.html";
            } else {
                alert("Erro no cadastro. Tente novamente.");
            }
        } catch (error) {
            console.error("Erro:", error);
            alert("Erro ao processar o cadastro.");
        }
    });