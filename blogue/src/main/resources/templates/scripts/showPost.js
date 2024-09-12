document.addEventListener("DOMContentLoaded", function() {
        const urlParams = new URLSearchParams(window.location.search);
        const postId = urlParams.get('postId');

        if (postId) {
            fetchPostDetails(postId);
            fetchComments(postId);
        }
    });

    function fetchPostDetails(postId) {
        fetch(`http://localhost:8080/users/posts/${postId}`)
            .then(response => response.json())
            .then(post => {
                // Atualiza o conteúdo da página com as informações do post
                document.getElementById('post-title').innerText = post.title;
                document.getElementById('post-text').innerText = post.text;
                document.getElementById('post-image').src = post.imageUrl;
            })
            .catch(error => console.error('Erro ao buscar o post:', error));
    }

    function fetchComments(postId) {
    fetch(`http://localhost:8080/Comment/${postId}/comments`)
        .then(response => response.json())
        .then(comments => {
            const commentsList = document.getElementById('comments-list');
            commentsList.innerHTML = ''; // Limpa a lista antes de adicionar novos itens

            comments.forEach(comment => {
                const li = document.createElement('li');
                li.classList.add('comment-box');  // Adiciona classe para estilização
                li.innerHTML = `<strong>${comment.userName}</strong><br>${comment.comment_text}`;
                commentsList.appendChild(li);
            });
        })
        .catch(error => console.error('Erro ao buscar comentários:', error));
}