-- 1 
SELECT ID_MaterialConstruccion ID, Nombre_Material NOMBRE, Precio_Unidad PRECIO 
FROM MaterialConstruccion 
WHERE Importado = 'Si'
ORDER BY Nombre_Material

-- 2 
SELECT p.ID_Proyecto ID, p.Constructora, p.Ciudad, p.Clasificacion, t.Estrato, l.Nombre || ' ' || l.Primer_Apellido || ' ' || Segundo_Apellido LIDER
FROM Proyecto p 
JOIN Tipo t ON p.ID_Tipo = t.ID_Tipo 
JOIN Lider l ON p.ID_Lider = l.ID_Lider 
WHERE Banco_Vinculado = 'Conavi' 
ORDER BY  Fecha_Inicio DESC, Ciudad, Constructora;

-- 3
SELECT Ciudad, Clasificacion, count(Clasificacion) TOTAL, min(Fecha_Inicio) VIEJO, max(Fecha_Inicio) RECIENTE 
FROM Proyecto 
WHERE Clasificacion NOT IN ('Casa Campestre', 'Condominio')
GROUP BY Ciudad, Clasificacion 
ORDER BY Ciudad;

-- 4 Las dos estan bien
SELECT p.ID_Proyecto, sum(c.Cantidad * m.Precio_Unidad) VALOR
FROM Proyecto p 
JOIN Compra c ON p.ID_Proyecto = c.ID_Proyecto 
JOIN MaterialConstruccion m ON c.ID_MaterialConstruccion = m.ID_MaterialConstruccion
WHERE c.Pagado = 'No'
GROUP BY p.ID_Proyecto HAVING VALOR > 50000
ORDER BY VALOR DESC;

SELECT p.ID_Proyecto, sum(c.Cantidad * m.Precio_Unidad) VALOR
FROM Proyecto p 
JOIN Compra c USING (ID_Proyecto) 
JOIN MaterialConstruccion m USING (ID_MaterialConstruccion)
WHERE c.Pagado = 'No'
GROUP BY p.ID_Proyecto HAVING VALOR > 50000
ORDER BY VALOR DESC;

-- 5 
SELECT (l.Nombre || ' ' || l.Primer_Apellido || ' ' || l.Segundo_Apellido) LIDER, sum(c.Cantidad * m.Precio_Unidad) VALOR 
FROM Lider l
JOIN Proyecto p ON l.ID_Lider = p.ID_Lider
JOIN Compra c ON p.ID_Proyecto = c.ID_Proyecto
JOIN MaterialConstruccion m ON c.ID_MaterialConstruccion = m.ID_MaterialConstruccion 
GROUP BY LIDER
ORDER BY VALOR DESC
LIMIT 10;
