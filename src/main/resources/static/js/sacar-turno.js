//sacar-turno.js

function agendarCita(nombre, descripcion, imagen) {
    document.getElementById('nombreProfesional').innerText = nombre;
    document.getElementById('descripcionProfesional').innerText = descripcion;
    document.getElementById('imagenProfesional').src = imagen;
}
