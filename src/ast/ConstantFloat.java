package ast;

import visitor.Visitor;

public class ConstantFloat extends Expression {

	public String getFloat() {
		return getLabel();
	}
	
	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
