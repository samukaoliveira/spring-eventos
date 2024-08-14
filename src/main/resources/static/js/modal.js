document.querySelector("#formEditaConvidado").addEventListener("submit", function(event) {
    event.preventDefault();

    var formData = new FormData(this);

    fetch("/editaConvidado", {
        method: "POST",
        body: formData,
    })
    .then(response => response.json()) // Ajustado para tratar a resposta como JSON
    .then(data => {
        if (data.redirectUrl) {
            // Fechar o modal
            var myModal = bootstrap.Modal.getInstance(document.getElementById(form.closest('.modal').id));
            myModal.hide();

            // Redirecionar após o modal ser fechado
            window.location.href = data.redirectUrl;
        } else {
            console.error('Erro: Redirecionamento não fornecido');
        }
    })
    .catch(error => console.error('Erro:', error));
});

    document.querySelector(".btn-close").addEventListener("click", function() {
        var myModal = bootstrap.Modal.getInstance(document.getElementById('modalEditaConvidado'));
        myModal.hide();
    });

    document.addEventListener('hidden.bs.modal', function (event) {
        document.querySelectorAll('.modal-backdrop').forEach(el => el.remove());
    });
