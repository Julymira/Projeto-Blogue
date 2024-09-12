document.addEventListener("DOMContentLoaded", function() {
              fetch('http://localhost:8080/users/AllPosts') // URL do endpoint para listar os posts
                .then(response => response.json())
                .then(posts => {
                  const container = document.getElementById('postsContainer'); // Container onde os posts serão exibidos

                  posts.forEach(post => {
                    const postElement = `
                      <div class="col-12 col-md-6 col-xl-4 pb-4">
                        <div class="card">
                          <img src="${post.imageUrl}" class="card-img-top" alt="${post.title}">
                          <div class="card-body">
                            <h5 class="card-title">${post.title}</h5>
                            <p>${new Date(post.dateTime).toLocaleString()}</p>
                            <button class="btn btn-primary" onclick="verMais(${post.id})">Ver Mais</button>
                          </div>
                        </div>
                      </div>
                    `;
                    container.innerHTML += postElement; // Adiciona o post no container
                  });
                })
                .catch(error => console.error('Erro ao buscar posts:', error));
            });

            function verMais(postId) {
                window.location.href = `exibePost.html?postId=${postId}`;
            }