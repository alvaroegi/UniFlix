document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('formMovie');
    form.addEventListener('submit', function(event) {
        const selectedCategorys = document.querySelectorAll('input[name="selectedCategorys"]:checked');
        if (selectedCategorys.length === 0) {
            event.preventDefault();
            alert('Debe seleccionar al menos una categor'+String.fromCharCode(237)+'a');
        }
    });
});
