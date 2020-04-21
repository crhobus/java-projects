SELECT	f.cpf,
		f.nome,
		u.perfil,
		((f.qt_horas_trabalho_dia * f.valor_hora) *  (220 / f.qt_horas_trabalho_dia)) + 600 vl_salario
FROM	funcionario f
	INNER JOIN usuario u ON f.usuario = u.id
WHERE	u.perfil = 0
UNION
SELECT	f.cpf,
		f.nome,
		u.perfil,
		((f.qt_horas_trabalho_dia * f.valor_hora) *  (180 / f.qt_horas_trabalho_dia)) vl_salario
FROM	funcionario f
	INNER JOIN usuario u ON f.usuario = u.id
WHERE	u.perfil = 1
ORDER BY perfil ASC, vl_salario DESC;