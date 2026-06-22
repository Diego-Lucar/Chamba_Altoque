let tipoSeleccionado = "";

function seleccionarTipo(tipo) {
  tipoSeleccionado = tipo;
  document
    .querySelectorAll(".option-card")
    .forEach((c) => c.classList.remove("selected"));
  document
    .getElementById(tipo === "usuario" ? "cardUsuario" : "cardFreelancer")
    .classList.add("selected");
  document.getElementById("btnContinue").disabled = false;
}

function continuar() {
  if (!tipoSeleccionado) return;
  document.getElementById("stepSelector").style.display = "none";
  document.getElementById("stepForm").style.display = "block";
  document.getElementById("formTitle").textContent =
    tipoSeleccionado === "usuario"
      ? "Datos Básicos"
      : "Registro de Freelancer";
  document.getElementById("formUsuario").style.display =
    tipoSeleccionado === "usuario" ? "block" : "none";
  document.getElementById("formFreelancer").style.display =
    tipoSeleccionado === "freelancer" ? "block" : "none";
}

function volver() {
  document.getElementById("stepForm").style.display = "none";
  document.getElementById("stepSelector").style.display = "block";
  document.getElementById("formUsuario").style.display = "none";
  document.getElementById("formFreelancer").style.display = "none";
}

function mostrarNombreArchivos(input) {
  const area = input.closest(".upload-area");
  const text = area.querySelector(".upload-area-text");
  const file = area.querySelector(".upload-area-file");
  if (input.files.length > 0) {
    text.textContent = input.files.length + " documento(s) seleccionado(s)";
    file.textContent = Array.from(input.files).map(f => f.name).join(", ");
    file.classList.remove("d-none");
  }
}

function mostrarNombreArchivo(input) {
  const area = input.closest(".upload-area");
  const text = area.querySelector(".upload-area-text");
  const file = area.querySelector(".upload-area-file");
  if (input.files.length > 0) {
    text.textContent = "Imagen seleccionada";
    file.textContent = input.files[0].name;
    file.classList.remove("d-none");
  }
}

function toggleCategoria(el) {
  const checkbox = el.querySelector(".cat-checkbox");
  checkbox.checked = !checkbox.checked;
  el.classList.toggle("selected", checkbox.checked);
}

if (tipoRegistro) {
  document.getElementById("stepSelector").style.display = "none";
  document.getElementById("stepForm").style.display = "block";
  document.getElementById("formTitle").textContent =
    tipoRegistro === "usuario" ? "Registro de Usuario" : "Registro de Freelancer";
  document.getElementById("formUsuario").style.display =
    tipoRegistro === "usuario" ? "block" : "none";
  document.getElementById("formFreelancer").style.display =
    tipoRegistro === "freelancer" ? "block" : "none";
}
