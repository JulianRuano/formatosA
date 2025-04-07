INSERT INTO Rol (id, rol_asignado) VALUES (1, 'Coordinador');
INSERT INTO Rol (id, rol_asignado) VALUES (2, 'Docente de planta');
INSERT INTO Rol (id, rol_asignado) VALUES (3, 'Docente de cátedra');

INSERT INTO Docente (id, apellido, correo, nombre, nombreGrupo)
VALUES (1, 'apellido 1', 'mail@mail.com', 'nombre', 'grupo');

INSERT INTO Historico (id, estado, fechaInicio, idDocente, idRol)
VALUES (1, 1, '2025-04-09 19:08:05', 1, 1);
