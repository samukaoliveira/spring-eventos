document.addEventListener('DOMContentLoaded', function() {
    var alertElement = document.getElementById('message');
    if (alertElement) {
        setTimeout(function() {
            alertElement.style.display = 'none';
        }, 3000); // Tempo em milissegundos para exibir o alerta
    }
});
