
function borrar(){
    let modify = document.getElementById("modify");
    let deleteMovie = document.getElementById("delete")
    modify.style.display = "none";
    deleteMovie.style.display = "block";
}

function modify2(){
    let modify = document.getElementById("modify");
    let deleteMovie = document.getElementById("delete")
    modify.style.display = "block";
    deleteMovie.style.display = "none";
}

function confirmDelete() {
    let confirmed = document.getElementById("confirmed");
    if(confirm('Eliminar una pel'+String.fromCharCode(237)+'cula tambi'+String.fromCharCode(233)+'n elimina sus rese'+String.fromCharCode(241)+'as'))
        confirmed.value = true;
    else
        confirmed.value = false;
}

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