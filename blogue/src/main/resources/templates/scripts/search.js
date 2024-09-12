document.getElementById('searchForm').addEventListener('submit', function(event) {
              event.preventDefault(); // Evita o comportamento padrão do formulário

              const searchQuery = document.getElementById('searchQuery').value; // Pega o valor da busca
              const url = `http://localhost:8080/users/filter?search=${encodeURIComponent(searchQuery)}`; // Cria a URL da requisição

              fetch(url)
                .then(response => response.json())
                .then(posts => {
                  const container = document.getElementById('postsContainer');
                  container.innerHTML = ''; // Limpa os posts antigos

                  posts.forEach(post => {
                    const postElement = `
                      <div class="col-12 col-md-6 col-xl-4 pb-4">
                        <div class="card">
                          <img src="${post.imageUrl}" class="card-img-top" alt="${post.title}">
                          <div class="card-body">
                            <h5 class="card-title">${post.title}</h5>
                            <p class="card-text">${post.text}</p>
                            <p>${new Date(post.dateTime).toLocaleString()}</p>
                            <button class="btn btn-primary" onclick="verMais(${post.id})">Ver Mais</button>
                          </div>
                        </div>
                      </div>
                    `;

                    container.innerHTML += postElement;
                  });
                })
                .catch(error => console.error('Erro ao buscar posts:', error));
            });

            function verMais(postId) {
                window.location.href = `exibePost.html?postId=${postId}`;
            }