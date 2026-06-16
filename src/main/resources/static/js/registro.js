var selectedType = "usuario";

function validarStep(stepId) {
  var form = document.getElementById(
    "form" + stepId.charAt(0).toUpperCase() + stepId.slice(1),
  );
  if (!form) return false;

  var confirmId = stepId === "step2prov" ? "passConfirmProv" : "passConfirm";
  var passId = stepId === "step2prov" ? "passInputProv" : "passInput";
  var confirmInput = document.getElementById(confirmId);
  var passInput = document.getElementById(passId);

  if (passInput && confirmInput) {
    if (passInput.value !== confirmInput.value) {
      confirmInput.setCustomValidity("Las contraseñas no coinciden");
    } else {
      confirmInput.setCustomValidity("");
    }
  }

  form.classList.add("was-validated");
  if (!form.checkValidity()) {
    return false;
  }

  var stepNum = stepId === "step2prov" || stepId === "step2" ? 3 : 2;
  goStep(stepNum);
  return false;
}

function goStep(n) {
  // Hide all steps
  var allSteps = ["step1", "step2", "step3", "step2prov", "step3prov"];
  allSteps.forEach(function (id) {
    document.getElementById(id).style.display = "none";
  });

  // For proveedor, steps 2 and 3 use proveedor panels
  var stepId;
  if (selectedType === "proveedor" && n === 2) stepId = "step2prov";
  else if (selectedType === "proveedor" && n === 3) stepId = "step3prov";
  else if (n === 1) stepId = "step1";
  else if (n === 2) stepId = "step2";
  else if (n === 3) stepId = "step3";

  document.getElementById(stepId).style.display = "block";

  var pcts = { 1: "33%", 2: "67%", 3: "100%" };
  document.getElementById("progressBar").style.width = pcts[n];
  document.getElementById("stepLabel").textContent = "Paso " + n + " de 3";
  document.getElementById("stepPercent").textContent = pcts[n];
}

function selectType(type) {
  selectedType = type;
  var uCard = document.getElementById("typeUsuario");
  var pCard = document.getElementById("typeProveedor");
  var uCheck = document.getElementById("checkUsuario");
  var pCheck = document.getElementById("checkProveedor");

  if (type === "usuario") {
    uCard.style.border = "2px solid #10b981";
    uCard.style.background = "#f0fdf4";
    pCard.style.border = "2px solid #e5e7eb";
    pCard.style.background = "#fff";
    uCheck.style.display = "block";
    pCheck.style.display = "none";
  } else {
    pCard.style.border = "2px solid #10b981";
    pCard.style.background = "#f0fdf4";
    uCard.style.border = "2px solid #e5e7eb";
    uCard.style.background = "#fff";
    pCheck.style.display = "block";
    uCheck.style.display = "none";
  }
}

function togglePref(el) {
  var active = el.dataset.active === "true";
  el.dataset.active = active ? "false" : "true";
  el.style.border = active ? "1.5px solid #e5e7eb" : "1.5px solid #10b981";
  el.style.background = active ? "#fff" : "#f0fdf4";
}

function togglePrefProv(el) {
  var active = el.dataset.active === "true";
  el.dataset.active = active ? "false" : "true";
  el.style.border = active ? "1.5px solid #e5e7eb" : "1.5px solid #10b981";
  el.style.background = active ? "#fafafa" : "#f0fdf4";
}

function togglePass(inputId) {
  var input = document.getElementById(inputId);
  input.type = input.type === "password" ? "text" : "password";
}

function handleFotoPerfilChange(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
      document.getElementById("fotoPerfilImg").src = e.target.result;
      document.getElementById("fotoPerfilPreview").style.display = "block";
      document.getElementById("fotoPerfilIcon").style.display = "none";
      document.getElementById("fotoPerfilText").textContent =
        input.files[0].name;
      document.getElementById("fotoPerfilBox").style.borderColor = "#10b981";
      document.getElementById("fotoPerfilBox").style.background = "#f0fdf4";
    };
    reader.readAsDataURL(input.files[0]);
  }
}

function handleDocumentosChange(input) {
  if (input.files && input.files.length > 0) {
    var names = Array.from(input.files)
      .map(function (f) {
        return f.name;
      })
      .join(", ");
    document.getElementById("docIcon").innerHTML =
      '<path d="M22 11.08V12a10 10 0 1 1-5.93-9.14" stroke="#10b981"/><polyline points="22 4 12 14.01 9 11.01" stroke="#10b981"/>';
    document.getElementById("documentosText").textContent =
      input.files.length + " archivo(s) seleccionado(s)";
    document.getElementById("documentosText").style.color = "#10b981";
    document.getElementById("documentosSubtext").textContent =
      names.length > 50 ? names.substring(0, 50) + "..." : names;
    document.getElementById("documentosBox").style.borderColor = "#10b981";
    document.getElementById("documentosBox").style.background = "#f0fdf4";
  }
}


