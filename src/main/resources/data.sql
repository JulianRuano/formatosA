INSERT INTO roles (id, rol_asignado) VALUES (1, 'Coordinador');
INSERT INTO roles (id, rol_asignado) VALUES (2, 'Docente de planta');
INSERT INTO roles (id, rol_asignado) VALUES (3, 'Docente de cátedra');

INSERT INTO docentes (id, apellido, correo, nombre, nombreGrupo)
VALUES (1, "Ortiz", "mail@mail", "Juan", "Grupo 1");

INSERT INTO docentes (id, apellido, correo, nombre, nombreGrupo)
VALUES (2, "Gonzalez", "mail2@mail", "Pedro", "Grupo 2");

INSERT INTO docentes (id, apellido, correo, nombre, nombreGrupo)
VALUES (3, "Lopez", "mail3@mail", "Maria", "Grupo 3");

INSERT INTO historicos (id, estado, fechaInicio, idDocente, idRol)
VALUES (1, 1, '2025-04-09 19:08:05', 1, 1);

INSERT INTO historicos (id, estado, fechaInicio, idDocente, idRol)
VALUES (2, 1, '2025-04-09 19:08:05', 2, 2);

INSERT INTO historicos (id, estado, fechaInicio, idDocente, idRol)
VALUES (3, 1, '2025-04-09 19:08:05', 3, 3);
