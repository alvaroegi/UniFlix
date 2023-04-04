function showAddForm() {
    let addButton = document.getElementById("addReviewButton");
    let addForm = document.getElementById("addReviewForm")
    addButton.style.display = "none";
    addForm.style.display = "block";
}
function hideAddForm() {
    let addButton = document.getElementById("addReviewButton");
    let addForm = document.getElementById("addReviewForm")
    addButton.style.display = "block";
    addForm.style.display = "none";
}

function showModifyForm(id) {
    let modifyButton = document.getElementById("modifyReviewButton" + id);
    let modifyForm = document.getElementById("modifyReviewForm" + id)
    modifyButton.style.display = "none";
    modifyForm.style.display = "block";
}
function hideModifyForm(id) {
    let modifyButton = document.getElementById("modifyReviewButton" + id);
    let modifyForm = document.getElementById("modifyReviewForm" + id)
    modifyButton.style.display = "block";
    modifyForm.style.display = "none";
}
function confirmDelete(id) {
    let confirmed = document.getElementById("confirmed" + id);
    if(confirm(String.fromCharCode(191)+'Eliminar rese'+String.fromCharCode(241)+'a?'))
        confirmed.value = true;
    else
        confirmed.value = false;
}