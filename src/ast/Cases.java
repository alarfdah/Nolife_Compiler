package ast;

import java.util.List;

import visitor.Visitor;

public class Cases extends ASTNode {

	public List<ASTNode> getClauseList() {
		return getChildren();
	}
	
	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
