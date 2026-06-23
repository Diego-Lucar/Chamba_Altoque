// Alterna entre el formulario de Usuario y el de Freelancer
function mostrarTipoLogin(tipo) {
  var esUsuario = tipo === "usuario";

  document.getElementById("formLoginUsuario").style.display = esUsuario ? "block" : "none";
  document.getElementById("formLoginFreelancer").style.display = esUsuario ? "none" : "block";

  document.getElementById("btnTipoUsuario").classList.toggle("active", esUsuario);
  document.getElementById("btnTipoFreelancer").classList.toggle("active", !esUsuario);
}

function togglePassword(icon) {
  var input = icon.previousElementSibling;
  input.type = input.type === "password" ? "text" : "password";
}

document.getElementById("btnTipoUsuario").addEventListener("click", function () {
  mostrarTipoLogin("usuario");
});

document.getElementById("btnTipoFreelancer").addEventListener("click", function () {
  mostrarTipoLogin("freelancer");
});

// Si el servidor devolvió un error, mantiene visible el formulario que falló
mostrarTipoLogin(document.body.dataset.tipoActivo || "usuario");

// Botón global que envía el formulario visible
var btnIniciar = document.getElementById('btnIniciar');
if (btnIniciar) {
  btnIniciar.addEventListener('click', function() {
    var formUsuario = document.getElementById('formLoginUsuario');
    var formFreelancer = document.getElementById('formLoginFreelancer');
    if (formUsuario.style.display !== 'none') {
      formUsuario.submit();
      return;
    }
    if (formFreelancer.style.display !== 'none') {
      formFreelancer.submit();
      return;
    }
  });
}
