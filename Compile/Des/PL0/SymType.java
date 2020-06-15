/**
 * token类型
 *
 * @author Jason
 */
public enum SymType {

	/**
	 * BEG 		begin
	 * END 		end
	 * IF 		if
	 * THEN 	then
	 * ELS 		else
	 * CON 		const
	 * PROC 	procedure
	 * VAR 		var
	 * DO 		do
	 * WHI 		while
	 * CAL 		call
	 * REA 		read
	 * WRI 		write
	 * ODD 		odd
	 * REP 		repeat
	 * UNT 		until
	 * IDN		identifier
	 * CONST	CONST
	 * EOF		end of file
	 * <p>
	 * EQU 		=
	 * LES 		<
	 * LESE 	<=
	 * LAR 		>
	 * LARE 	>=
	 * NEQE 	<>
	 * ADD 		+
	 * SUB 		-
	 * MUL 		*
	 * DIV 		/
	 * CEQU 	:=
	 * COMMA 	,
	 * SEMI 	;
	 * POI 		.
	 * LBR 		(
	 * EBR 		)
	 * COL		:
	 */
	BEG, END, IF, THEN, ELS, CON, PROC, VAR, DO, WHI, CAL, REA, WRI, ODD, REP, UNT, IDN, CONST, EOF,

	EQU, LES, LESE, LARE, LAR, NEQE, ADD, SUB, MUL, DIV, CEQU, COMMA, SEMI, POI, LBR, RBR, COL,

}
