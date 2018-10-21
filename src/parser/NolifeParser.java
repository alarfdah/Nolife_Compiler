/* Generated By:JavaCC: Do not edit this line. NolifeParser.java */
package parser;

import ast.*;
import visitor.*;
import java.io.*;

public class NolifeParser implements NolifeParserConstants {

        private static ASTNodeFactory factory;

        public static void main(String args[]) {
                NolifeParser parser;
                java.io.InputStream input;

                if (args.length == 1) {
                        try {
                                input = new java.io.FileInputStream(args[args.length - 1]);
                        } catch (java.io.FileNotFoundException e) {
                                System.out.println("Filen not found.");
                                return;
                        }
                } else {
                        System.out.println("Usage: nsc <inputfile>");
                        return;
                }

                try {
                        factory = new ASTNodeFactory();
                        parser = new NolifeParser(input);
                        ASTNode node = parser.program();
                        SourceVisitor sv = new SourceVisitor();
                        node.accept(sv);
                        System.out.println(sv.getSource());
                } catch (ParseException e) {
                        System.err.println("Syntax Error: " + e.getMessage());
                }
        }

  static final public ASTNode program() throws ParseException {
        ASTNode programNode = (Program)factory.makeASTNode("Program");
        ASTNode compoundStatement = null;
        ASTNode varDeclsNode = null;
        Token progName = null;
    jj_consume_token(O_PROGRAM);
    progName = jj_consume_token(O_IDENTIFIER);
    jj_consume_token(O_SEMICOLON);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_VAR:
      varDeclsNode = decls();
                        programNode.addChild(varDeclsNode);
      break;
    default:
      jj_la1[0] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_FUNCTION:
    case O_PROCEDURE:
      subprogram_decls();
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    compoundStatement = compound_stmt();
        programNode.setLabel(progName.image);
        programNode.addChild(compoundStatement);

        {if (true) return programNode;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode decls() throws ParseException {
        ASTNode varDeclsNode = null;
    jj_consume_token(O_VAR);
    varDeclsNode = decl_list();
                {if (true) return varDeclsNode;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode decl_list() throws ParseException {
        ASTNode typeNode = null;
        ASTNode declareNode = null;
        VariableDeclarations varDeclsNode = null;
                varDeclsNode = (VariableDeclarations)factory.makeASTNode("VariableDeclarations");
    label_1:
    while (true) {
      declareNode = identifier_list();
      jj_consume_token(O_COLON);
      typeNode = type();
      jj_consume_token(O_SEMICOLON);
                        declareNode.addChild(typeNode);
                        varDeclsNode.addChild(declareNode);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case O_IDENTIFIER:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_1;
      }
    }
                {if (true) return varDeclsNode;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode identifier_list() throws ParseException {
        Declare declareNode = null;
        IdDecl idDeclNode = null;
        Token id = null;
    id = jj_consume_token(O_IDENTIFIER);
                declareNode = (Declare)factory.makeASTNode("Declare");
                idDeclNode = (IdDecl)factory.makeASTNode("IdDecl");
                idDeclNode.setLabel(id.image);
                declareNode.addChild(idDeclNode);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case O_COMMA:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_2;
      }
      jj_consume_token(O_COMMA);
      id = jj_consume_token(O_IDENTIFIER);
                idDeclNode = (IdDecl)factory.makeASTNode("IdDecl");
                idDeclNode.setLabel(id.image);
                declareNode.addChild(idDeclNode);
    }
                {if (true) return declareNode;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode type() throws ParseException {
        ASTNode typeNode = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_CHARACTER:
    case O_FLOAT:
    case O_INTEGER:
      typeNode = standard_type();
      break;
    case O_ARRAY:
      typeNode = array_type();
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                {if (true) return typeNode;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode standard_type() throws ParseException {
        Type typeNode = null;
        Token type = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_INTEGER:
      type = jj_consume_token(O_INTEGER);
                typeNode = (TypeInteger)factory.makeASTNode("TypeInteger");
      break;
    case O_FLOAT:
      type = jj_consume_token(O_FLOAT);
                typeNode = (TypeFloat)factory.makeASTNode("TypeFloat");
      break;
    case O_CHARACTER:
      type = jj_consume_token(O_CHARACTER);
                typeNode = (TypeCharacter)factory.makeASTNode("TypeCharacter");
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                {if (true) return typeNode;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode array_type() throws ParseException {
    jj_consume_token(O_ARRAY);
    jj_consume_token(O_LBRACKET);
    dim();
    jj_consume_token(O_RBRACKET);
    jj_consume_token(O_OF);
    standard_type();
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode dim() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_INT:
      jj_consume_token(O_INT);
      jj_consume_token(O_DOT);
      jj_consume_token(O_DOT);
      jj_consume_token(O_INT);
      break;
    case O_CHAR:
      jj_consume_token(O_CHAR);
      jj_consume_token(O_DOT);
      jj_consume_token(O_DOT);
      jj_consume_token(O_CHAR);
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode subprogram_decls() throws ParseException {
    label_3:
    while (true) {
      subprogram_decl();
      jj_consume_token(O_SEMICOLON);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case O_FUNCTION:
      case O_PROCEDURE:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_3;
      }
    }
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode subprogram_decl() throws ParseException {
    subprogram_head();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_VAR:
      decls();
      break;
    default:
      jj_la1[8] = jj_gen;
      ;
    }
    compound_stmt();
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode subprogram_head() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_FUNCTION:
      jj_consume_token(O_FUNCTION);
      jj_consume_token(O_IDENTIFIER);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case O_LPAREN:
        arguments();
        break;
      default:
        jj_la1[9] = jj_gen;
        ;
      }
      jj_consume_token(O_COLON);
      standard_type();
      jj_consume_token(O_SEMICOLON);
      break;
    case O_PROCEDURE:
      jj_consume_token(O_PROCEDURE);
      jj_consume_token(O_IDENTIFIER);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case O_LPAREN:
        arguments();
        break;
      default:
        jj_la1[10] = jj_gen;
        ;
      }
      jj_consume_token(O_SEMICOLON);
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode arguments() throws ParseException {
    jj_consume_token(O_LPAREN);
    parameter_list();
    jj_consume_token(O_RPAREN);
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode parameter_list() throws ParseException {
    identifier_list();
    jj_consume_token(O_COLON);
    type();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case O_SEMICOLON:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_4;
      }
      jj_consume_token(O_SEMICOLON);
      identifier_list();
      jj_consume_token(O_COLON);
      type();
    }
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode compound_stmt() throws ParseException {
        ASTNode statementList = null;
    jj_consume_token(O_BEGIN);
    statementList = stmt_list();
    jj_consume_token(O_END);
                {if (true) return statementList;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode stmt_list() throws ParseException {
        ASTNode statement = null;
        ASTNode compoundStatement = null;
    statement = stmt();
                compoundStatement = (CompoundStatement)factory.makeASTNode("CompoundStatement");
                compoundStatement.addChild(statement);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case O_SEMICOLON:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_5;
      }
      jj_consume_token(O_SEMICOLON);
      statement = stmt();
                compoundStatement.addChild(statement);
    }
                {if (true) return compoundStatement;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode stmt() throws ParseException {
        ASTNode statement = null;
    if (jj_2_1(2)) {
      statement = assignment();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case O_IF:
        statement = if_stmt();
        break;
      case O_WHILE:
        statement = while_stmt();
        break;
      case O_IDENTIFIER:
        statement = procedure_invocation();
        break;
      case O_READ:
      case O_WRITE:
        statement = i_o_stmt();
        break;
      case O_BEGIN:
        statement = compound_stmt();
        break;
      case O_RETURN:
        statement = return_stmt();
        break;
      case O_CASE:
        statement = case_stmt();
        break;
      default:
        jj_la1[14] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
                {if (true) return statement;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode assignment() throws ParseException {
        ASTNode varNode = null;
        ASTNode exprNode = null;
        Assignment assignNode = null;
    varNode = variable();
    jj_consume_token(O_ASSIGN);
    exprNode = expr();
                assignNode = (Assignment)factory.makeASTNode("Assignment");
                assignNode.addChild(varNode);
                assignNode.addChild(exprNode);
                {if (true) return assignNode;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode if_stmt() throws ParseException {
    jj_consume_token(O_IF);
    expr();
    jj_consume_token(O_THEN);
    stmt();
    if (jj_2_2(2147483647)) {
      jj_consume_token(O_ELSE);
      stmt();
    } else {
      ;
    }
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode while_stmt() throws ParseException {
    jj_consume_token(O_WHILE);
    expr();
    jj_consume_token(O_DO);
    stmt();
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode procedure_invocation() throws ParseException {
    jj_consume_token(O_IDENTIFIER);
    jj_consume_token(O_LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_NOT:
    case O_LPAREN:
    case O_CHAR:
    case O_IDENTIFIER:
    case O_FLOATCON:
    case O_INT:
      expr_list();
      break;
    default:
      jj_la1[15] = jj_gen;
      ;
    }
    jj_consume_token(O_RPAREN);
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode i_o_stmt() throws ParseException {
        Expression exprNode = null;
        IdDef idDefNode = null;
        Read readNode = null;
        Write writeNode = null;
        ASTNode varNode = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_READ:
      jj_consume_token(O_READ);
      jj_consume_token(O_LPAREN);
                                 idDefNode = (IdDef)variable();
      jj_consume_token(O_RPAREN);
                exprNode = (IdRef)factory.makeASTNode("IdRef");
                exprNode.setLabel(idDefNode.getId());
                readNode = (Read)factory.makeASTNode("Read");
                readNode.addChild(exprNode);
                {if (true) return readNode;}
      break;
    case O_WRITE:
      jj_consume_token(O_WRITE);
      jj_consume_token(O_LPAREN);
                    exprNode = (Expression)expr();
                        writeNode = (Write)factory.makeASTNode("Write");
                        writeNode.addChild(exprNode);
      jj_consume_token(O_RPAREN);
                        {if (true) return writeNode;}
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode return_stmt() throws ParseException {
        Expression exprNode = null;
        Return returnNode = null;
    jj_consume_token(O_RETURN);
                     exprNode = (Expression)expr();
                returnNode = (Return)factory.makeASTNode("Return");
                returnNode.addChild(exprNode);
                {if (true) return returnNode;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode case_stmt() throws ParseException {
    jj_consume_token(O_CASE);
    expr();
    jj_consume_token(O_OF);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_FLOATCON:
    case O_INT:
      cases();
      break;
    default:
      jj_la1[17] = jj_gen;
      ;
    }
    jj_consume_token(O_END);
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode cases() throws ParseException {
    case_element();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case O_SEMICOLON:
        ;
        break;
      default:
        jj_la1[18] = jj_gen;
        break label_6;
      }
      jj_consume_token(O_SEMICOLON);
      case_element();
    }
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode case_element() throws ParseException {
    case_labels();
    jj_consume_token(O_COLON);
    stmt();
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode case_labels() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_INT:
      jj_consume_token(O_INT);
      break;
    case O_FLOATCON:
      jj_consume_token(O_FLOATCON);
      break;
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case O_COMMA:
        ;
        break;
      default:
        jj_la1[20] = jj_gen;
        break label_7;
      }
      jj_consume_token(O_COMMA);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case O_INT:
        jj_consume_token(O_INT);
        break;
      case O_FLOATCON:
        jj_consume_token(O_FLOATCON);
        break;
      default:
        jj_la1[21] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode expr_list() throws ParseException {
    expr();
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case O_COMMA:
        ;
        break;
      default:
        jj_la1[22] = jj_gen;
        break label_8;
      }
      jj_consume_token(O_COMMA);
      expr();
    }
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode expr() throws ParseException {
        ASTNode term1 = null;
    term1 = term1();
    exprPrime();
                {if (true) return term1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode exprPrime() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_OR:
      jj_consume_token(O_OR);
      term1();
      exprPrime();
      break;
    case O_AND:
      jj_consume_token(O_AND);
      term1();
      exprPrime();
      break;
    default:
      jj_la1[23] = jj_gen;

    }
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode term1() throws ParseException {
        ASTNode term2 = null;
    term2 = term2();
    term1Prime();
                {if (true) return term2;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode term1Prime() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_LT:
      jj_consume_token(O_LT);
      term2();
      term1Prime();
      break;
    case O_LE:
      jj_consume_token(O_LE);
      term2();
      term1Prime();
      break;
    case O_GT:
      jj_consume_token(O_GT);
      term2();
      term1Prime();
      break;
    case O_GE:
      jj_consume_token(O_GE);
      term2();
      term1Prime();
      break;
    case O_NE:
      jj_consume_token(O_NE);
      term2();
      term1Prime();
      break;
    case O_EQ:
      jj_consume_token(O_EQ);
      term2();
      term1Prime();
      break;
    default:
      jj_la1[24] = jj_gen;

    }
                {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode term2() throws ParseException {
        ASTNode term3 = null;
        ASTNode term2Prime = null;
        Add addNode = null;
    term3 = term3();
    term2Prime = term2Prime(term3);
            {if (true) return term2Prime;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode term2Prime(ASTNode exprNode) throws ParseException {
        Add addNode = null;
        Subtract subtractNode = null;
        ASTNode term3Node = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_PLUS:
      jj_consume_token(O_PLUS);
      term3Node = term3();
                        addNode = (Add)factory.makeASTNode("Add");
                        addNode.addChild(exprNode);
                        addNode.addChild(term2Prime(term3Node));
                        {if (true) return addNode;}
      break;
    case O_MINUS:
      jj_consume_token(O_MINUS);
      term3Node = term3();
                        subtractNode = (Subtract)factory.makeASTNode("Subtract");
                        subtractNode.addChild(exprNode);
                        subtractNode.addChild(term2Prime(term3Node));
                        {if (true) return subtractNode;}
      break;
    default:
      jj_la1[25] = jj_gen;
          {if (true) return exprNode;}
    }
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode term3() throws ParseException {
        ASTNode factor = null;
        ASTNode term3Prime = null;
    factor = factor();
    term3Prime = term3Prime(factor);
                {if (true) return term3Prime;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode term3Prime(ASTNode exprNode) throws ParseException {
        Multiply multiplyNode = null;
        Modulo moduloNode = null;
        ASTNode factorNode = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_TIMES:
      jj_consume_token(O_TIMES);
      factorNode = factor();
                        multiplyNode = (Multiply)factory.makeASTNode("Multiply");
                        multiplyNode.addChild(exprNode);
                        multiplyNode.addChild(term3Prime(factorNode));
                        {if (true) return multiplyNode;}
      break;
    case O_MOD:
      jj_consume_token(O_MOD);
      factorNode = factor();
                        moduloNode = (Modulo)factory.makeASTNode("Modulo");
                        moduloNode.addChild(exprNode);
                        moduloNode.addChild(term3Prime(factorNode));
                        {if (true) return moduloNode;}
      break;
    default:
      jj_la1[26] = jj_gen;
          {if (true) return exprNode;}
    }
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode factor() throws ParseException {
        Expression exprNode = null;
        Token var = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_IDENTIFIER:
      var = jj_consume_token(O_IDENTIFIER);
                        exprNode = (IdRef)factory.makeASTNode("IdRef");
                        exprNode.setLabel(var.image);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case O_LBRACKET:
      case O_LPAREN:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case O_LBRACKET:
          jj_consume_token(O_LBRACKET);
          expr();
          jj_consume_token(O_RBRACKET);
          break;
        case O_LPAREN:
          jj_consume_token(O_LPAREN);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case O_NOT:
          case O_LPAREN:
          case O_CHAR:
          case O_IDENTIFIER:
          case O_FLOATCON:
          case O_INT:
            expr_list();
            break;
          default:
            jj_la1[27] = jj_gen;
            ;
          }
          jj_consume_token(O_RPAREN);
          break;
        default:
          jj_la1[28] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[29] = jj_gen;
        ;
      }
      break;
    case O_INT:
      jj_consume_token(O_INT);
                exprNode = (ConstantInteger)factory.makeASTNode("ConstantInteger");
      break;
    case O_FLOATCON:
      jj_consume_token(O_FLOATCON);
                exprNode = (ConstantInteger)factory.makeASTNode("ConstantFloat");
      break;
    case O_CHAR:
      jj_consume_token(O_CHAR);
                exprNode = (ConstantInteger)factory.makeASTNode("ConstantCharacter");
      break;
    case O_LPAREN:
      jj_consume_token(O_LPAREN);
      expr();
      jj_consume_token(O_RPAREN);
      break;
    case O_NOT:
      jj_consume_token(O_NOT);
      factor();
      break;
    default:
      jj_la1[30] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                {if (true) return exprNode;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode variable() throws ParseException {
        IdDef idDefNode = null;
        Token id = null;
    id = jj_consume_token(O_IDENTIFIER);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case O_LBRACKET:
      jj_consume_token(O_LBRACKET);
      expr();
      jj_consume_token(O_RBRACKET);
      break;
    default:
      jj_la1[31] = jj_gen;
      ;
    }
                idDefNode = (IdDef) factory.makeASTNode("IdDef");
                idDefNode.setLabel(id.image);

                {if (true) return idDefNode;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode string() throws ParseException {
        ConstantString constantString = null;
        Token string = null;
    string = jj_consume_token(O_STRING);
                constantString = (ConstantString)factory.makeASTNode("ConstantString");
                constantString.setLabel(string.image);
                {if (true) return constantString;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_3_2() {
    if (jj_scan_token(O_ELSE)) return true;
    return false;
  }

  static private boolean jj_3_1() {
    if (jj_3R_9()) return true;
    return false;
  }

  static private boolean jj_3R_11() {
    if (jj_scan_token(O_LBRACKET)) return true;
    return false;
  }

  static private boolean jj_3R_10() {
    if (jj_scan_token(O_IDENTIFIER)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_11()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_9() {
    if (jj_3R_10()) return true;
    if (jj_scan_token(O_ASSIGN)) return true;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public NolifeParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[32];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x4000000,0x208000,0x0,0x0,0x24500,0x24400,0x0,0x208000,0x4000000,0x0,0x0,0x208000,0x0,0x0,0x39810200,0x40000,0x10800000,0x0,0x0,0x0,0x0,0x0,0x0,0x100080,0xc0000000,0x0,0x40,0x40000,0x0,0x0,0x40000,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x100000,0x80,0x0,0x0,0x810000,0x0,0x0,0x400,0x400,0x0,0x40,0x40,0x100000,0xd10400,0x0,0xc00000,0x40,0xc00000,0x80,0xc00000,0x80,0x0,0xf,0x6000,0x8000,0xd10400,0x500,0x500,0xd10400,0x100,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[2];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public NolifeParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public NolifeParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new NolifeParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public NolifeParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new NolifeParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public NolifeParser(NolifeParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(NolifeParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[56];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 32; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 56; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 2; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
