let gen = document.getElementById("general");
var form = document.getElementById("searchAction");
form.addEventListener("submit", function(event) {
    event.preventDefault(); // Prevenir el comportamiento por defecto del formulario
    // Tu código de JavaScript aquí
    gen.display.style="none";
    console.log("holaquetal");
    // Acción del formulario
    form.action = "search";
    form.submit();
});
