
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
