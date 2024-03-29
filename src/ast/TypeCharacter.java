package ast;

import visitor.Visitor;

public class TypeCharacter extends Type {

	@Override
	public String getTypeKeyword() {
		return "CHARACTER";
	}

	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
