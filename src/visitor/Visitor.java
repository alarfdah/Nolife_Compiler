package visitor;

import ast.*;

public interface Visitor {
	public Object visit(Add n);
	public Object visit(AND n);
	public Object visit(ArrayDecl n);
	public Object visit(ArrayDef n);
	public Object visit(ArrayRef n);
	public Object visit(Assignment n);
	public Object visit(Call n);
	public Object visit(Cases n);
	public Object visit(CaseStatement n);
	public Object visit(Clause n);
	public Object visit(CompoundStatement n);
	public Object visit(ConstantCharacter n);
	public Object visit(ConstantFloat n);
	public Object visit(ConstantInteger n);
	public Object visit(ConstantString n);
	public Object visit(Declare n);
	public Object visit(Equal n);
	public Object visit(GreaterThan n);
	public Object visit(GreaterThanEqual n);
	public Object visit(IdDecl n);
	public Object visit(IdDef n);
	public Object visit(IdRef n);
	public Object visit(IfStatement n);
	public Object visit(LessThan n);
	public Object visit(LessThanEqual n);
	public Object visit(Modulo n);
	public Object visit(Multiply n);
	public Object visit(NOT n);
	public Object visit(NotEqual n);
	public Object visit(OR n);
	public Object visit(Parameters n);
	public Object visit(Program n);
	public Object visit(Read n);
	public Object visit(Return n);
	public Object visit(Subscript n);
	public Object visit(Subtract n);
	public Object visit(TypeCharacter n);
	public Object visit(TypeFloat n);
	public Object visit(TypeInteger n);
	public Object visit(VariableDeclarations n);
	public Object visit(WhileStatement n);
	public Object visit(Write n);
}
