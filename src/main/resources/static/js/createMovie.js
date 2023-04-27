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

function bold() {
    var textarea = document.getElementById('synopsis-textarea');
    var ini = textarea.selectionStart;
    var fin = textarea.selectionEnd;
    var newText = "<strong>" + textarea.value.substring(ini, fin) + "</strong>";
    textarea.value = textarea.value.substring(0, ini) +newText+ textarea.value.substring(fin);
}

function italic() {
    var textarea = document.getElementById('synopsis-textarea');
    var ini = textarea.selectionStart;
    var fin = textarea.selectionEnd;
    var newText = "<i>" + textarea.value.substring(ini, fin) + "</i>";
    textarea.value = textarea.value.substring(0, ini) +newText+ textarea.value.substring(fin);
}

function underline() {
    var textarea = document.getElementById('synopsis-textarea');
    var ini = textarea.selectionStart;
    var fin = textarea.selectionEnd;
    var newText = "<u>" + textarea.value.substring(ini, fin) + "</u>";
    textarea.value = textarea.value.substring(0, ini) +newText+ textarea.value.substring(fin);
}
