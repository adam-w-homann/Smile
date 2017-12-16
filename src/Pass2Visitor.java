import java.io.PrintWriter;


import wci.intermediate.*;
import wci.intermediate.symtabimpl.*;

public class Pass2Visitor extends SmileBaseVisitor<Integer> 
{
	private int labelCount;
	String programName;
    private PrintWriter jFile;
    private final String halfIndent = "  ";
    
    public Pass2Visitor(PrintWriter jFile)
    {
        this.jFile = jFile;
        this.labelCount = 1;
    }
    
    @Override 
    public Integer visitProgram(SmileParser.ProgramContext ctx) 
    { 
        Integer value = visitChildren(ctx); 
        jFile.close();
        return value;
    }
    
    @Override 
    public Integer visitHeader(SmileParser.HeaderContext ctx) 
    { 
        programName = ctx.IDENTIFIER().toString();       
        return visitChildren(ctx); 
    }
    
    @Override 
    public Integer visitMainBlock(SmileParser.MainBlockContext ctx) 
    { 
        // Emit the main program header.
        jFile.println();
        jFile.println(".method public static main([Ljava/lang/String;)V");
        jFile.println();
        jFile.println("\tnew RunTimer");
        jFile.println("\tdup");
        jFile.println("\tinvokenonvirtual RunTimer/<init>()V");
        jFile.println("\tputstatic        " + programName + "/_runTimer LRunTimer;");
        jFile.println("\tnew PascalTextIn");
        jFile.println("\tdup");
        jFile.println("\tinvokenonvirtual PascalTextIn/<init>()V");
        jFile.println("\tputstatic        " + programName + "/_standardIn LPascalTextIn;");
        
        Integer value = visitChildren(ctx);
        
        // Emit the main program epilogue.
        jFile.println();
        jFile.println("\tgetstatic     " + programName + "/_runTimer LRunTimer;");
        jFile.println("\tinvokevirtual RunTimer.printElapsedTime()V");
        jFile.println();
        jFile.println("\treturn");
        jFile.println();
        jFile.println(".limit locals 16");
        jFile.println(".limit stack 16");
        jFile.println(".end method");
        
        return value;
    }
    
//    @Override
//    public Integer visitFuncDeclarations(SmileParser.FuncDeclarationsContext ctx)
//    {
////    		for(int i=0; i<ctx.function().size(); i++) {
////    			
////    		}
//    		Integer value = visitChildren(ctx);
//		return value;
//    }
//    
//    @Override
//    public Integer visitFunction(SmileParser.FunctionContext ctx)
//    {
//    		jFile.println(".method public " + ctx.IDENTIFIER().getText() + "(F)F");
//    		jFile.println("    freturn");
//    		jFile.println(".end method");
//    		Integer value = visitChildren(ctx);
//    		return value;
//    }
    
    @Override 
    public Integer visitStmt(SmileParser.StmtContext ctx) 
    { 
        jFile.println("\n; " + ctx.getText() + "\n");
        
        return visitChildren(ctx); 
    }
    @Override 
    public Integer visitAssignmentStmt(SmileParser.AssignmentStmtContext ctx)
    {
        Integer value = visit(ctx.expr());
        
        String typeIndicator = (ctx.expr().type == Predefined.integerType) ? "I"
                             : (ctx.expr().type == Predefined.realType)    ? "F"
                             :                                    "?";
        
        // Emit a field put instruction.
//        jFile.println(";starwhale");
        jFile.println("\tputstatic\t" + programName
                           +  "/" + ctx.variable().IDENTIFIER().toString() 
                           + " " + typeIndicator);

        return value; 
    }

    @Override
    public Integer visitIfStatement(SmileParser.IfStatementContext ctx){
  	
  	  Integer value;
  		int currentLabelCount = this.labelCount++;

  		if(ctx.stmt().size() == 1)  //only if
  		{
  			value = visit(ctx.expr());

  			jFile.println("ifeq	L" + currentLabelCount);

  			value = visit(ctx.stmt(0));

  			jFile.println("L" + currentLabelCount + ":");
  		}
  		else //with else statement
  		{
  			value = visit(ctx.expr());

  			int elseLevel = this.labelCount++;

  			jFile.println("ifeq	L" + elseLevel);

  			value = visit(ctx.stmt(0));

  			jFile.println("goto L" + currentLabelCount);

  			jFile.println("L" + elseLevel + ":");

  			value = visit(ctx.stmt(1));

  			jFile.println("L" + currentLabelCount + ":");

  		}

  		return value;
  	  
  	 
    }
    
    @Override 
	public Integer visitWhileStatement(SmileParser.WhileStatementContext ctx)
	{
		Integer value;
		int loopLabel = this.labelCount++;
		int nextLabel = this.labelCount++;

		//loop label
		jFile.println("L" + loopLabel + ":");

		//compare statement

		value = visit(ctx.expr());

		//statement
		jFile.println("ifeq	L" + nextLabel);        
		value = visit(ctx.stmtList());        
		jFile.println("goto L" + loopLabel);

		//next label
		jFile.println("L" + nextLabel + ":");

		return value;
	}   
    
    @Override
    public Integer visitRelOpExpr(SmileParser.RelOpExprContext ctx)
    {
    	Integer value = visitChildren(ctx);
    	
    	TypeSpec type1 = ctx.expr(0).type;
    	TypeSpec type2 = ctx.expr(1).type;
    	
    	boolean integerMode =	(type1 == Predefined.integerType)
    						 && (type2 == Predefined.integerType);
    	boolean realMode	=	(type1 == Predefined.realType)
    					     && (type2 == Predefined.realType);
    	
    	String op = ctx.relOp().getText();
    	String opcode;
    	
    	if (realMode) {
    		jFile.println("fcmpg");
    	}
    	
    	if (op.equals("==")) {
            opcode = integerMode ? "if_icmpeq"
                    : realMode    ? "ifeq"
                    :               "????";
    	}
    	
    	else if (op.equals("!=")) {
            opcode = integerMode ? "if_icmpne"
                    : realMode    ? "ifne"
                    :               "????";
    	}
    	
    	else if (op.equals("<")) {
            opcode = integerMode ? "if_icmplt"
                    : realMode    ? "iflt"
                    :               "????";
    	}
    	
    	else {
            opcode = integerMode ? "if_icmpgt"
                    : realMode    ? "ifgt"
                    :               "????";
    	}
    	
    	Label trueLabel = Label.newLabel();
    	Label nextLabel = Label.newLabel();
    	
    	//emit the appropriate opcode for relational expression
    	jFile.println("\t" + opcode + "\t" + trueLabel.toString());
    	
    	//emit remaining code
    	
    	jFile.println("\ticonst_0");
    	jFile.println("\tgoto" + "\t" + nextLabel.toString());
    	//true-label
    	jFile.println(trueLabel.toString() + ":");
    	jFile.println("\ticonst_1");
    	//next-label
    	jFile.println(nextLabel.toString() + ":");
    	
    	
    	return value;
    }

    
    
 
    
    @Override
    public Integer visitPrintStmt(SmileParser.PrintStmtContext ctx)
    {
    		for(int i = 0; i < ctx.parenthesis().literal().size(); i++)
	    {
	    		visit(ctx.parenthesis().literal(i));
	    }
    		if(ctx.getChild(2) == null) 
    		{
        		jFile.println("\tgetstatic     java/lang/System/out Ljava/io/PrintStream;");
        		jFile.println("\tldc           \"\"");
        		jFile.println("\tinvokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
    		}
    		
	    	return 0;
    }
    
    @Override
    public Integer visitLiteral(SmileParser.LiteralContext ctx)
    {
//	    	for(int i = 0, i < ctx.literal())
    		return visitChildren(ctx);
    }

    @Override
    public Integer visitStringLiteral(SmileParser.StringLiteralContext ctx)
    {
    		jFile.println("\tgetstatic     java/lang/System/out Ljava/io/PrintStream;");
    		String sub = ctx.STRING().getText().substring(1,  ctx.STRING().getText().length() - 1); // take out single or double quotes
    		jFile.println("\tldc           \"" + sub + "\"");
    		jFile.println("\tinvokevirtual java/io/PrintStream/print(Ljava/lang/String;)V");
    		return visitChildren(ctx);
    }
    
    public Integer visitExprLiteral(SmileParser.ExprLiteralContext ctx)
    {
    		
    		TypeSpec type = ctx.expr().type;
            
        String typeIndicator = (type == Predefined.integerType) ? "I"
                             : (type == Predefined.realType)    ? "F"
                             :                                    "?";
//        typeIndicator = "I";
        jFile.println("; starwhale 2");
    		jFile.println("\tgetstatic     java/lang/System/out Ljava/io/PrintStream;");
//    		jFile.println("\tdup");
    		Integer value = visitChildren(ctx);
    		jFile.println("\tinvokevirtual java/io/PrintStream/print(" + typeIndicator + ")V");
    		
    		jFile.println("\tgetstatic     java/lang/System/out Ljava/io/PrintStream;");
    		jFile.println("\tldc           \" \"");
    		jFile.println("\tinvokevirtual java/io/PrintStream/print(Ljava/lang/String;)V");
    		
    		return value;
    }
    
    
    @Override 
    public Integer visitAddSubExpr(SmileParser.AddSubExprContext ctx)
    {
        Integer value = visitChildren(ctx);
                        
        TypeSpec type1 = ctx.expr(0).type;
        TypeSpec type2 = ctx.expr(1).type;
        
        boolean integerMode =    (type1 == Predefined.integerType)
                              && (type2 == Predefined.integerType);
        boolean realMode    =    (type1 == Predefined.realType)
                              && (type2 == Predefined.realType);
        
        String op = ctx.addSubOp().getText();
        String opcode;

        if (op.equals("+")) {
            opcode = integerMode ? "iadd"
                   : realMode    ? "fadd"
                   :               "????";
        }
        else {
            opcode = integerMode ? "isub"
                   : realMode    ? "fsub"
                   :               "????";
        }
        
        // Emit an add or subtract instruction.
        jFile.println("\t" + opcode);
        
        return value; 
    }

    @Override 
    public Integer visitMulDivExpr(SmileParser.MulDivExprContext ctx)
    {
        Integer value = visitChildren(ctx);
                
        TypeSpec type1 = ctx.expr(0).type;
        TypeSpec type2 = ctx.expr(1).type;
        
        boolean integerMode =    (type1 == Predefined.integerType)
                              && (type2 == Predefined.integerType);
        boolean realMode    =    (type1 == Predefined.realType)
                              && (type2 == Predefined.realType);
        
        String op = ctx.mulDivOp().getText();
        String opcode;

        if (op.equals("*")) {
            opcode = integerMode ? "imul"
                   : realMode    ? "fmul"
                   :               "f???";
        }
        else {
            opcode = integerMode ? "idiv"
                   : realMode    ? "fdiv"
                   :               "????";
        }
        
        // Emit a multiply or divide instruction.
        jFile.println("\t" + opcode);
        
        return value; 
    }

    
    
    
    @Override 
    public Integer visitVariableExpr(SmileParser.VariableExprContext ctx)
    {
        String variableName = ctx.variable().IDENTIFIER().toString();
        TypeSpec type = ctx.type;
        
        String typeIndicator = (type == Predefined.integerType) ? "I"
                             : (type == Predefined.realType)    ? "F"
                             :                                    "?";
        
        // Emit a field get instruction.
        jFile.println("\tgetstatic\t" + programName +
                      "/" + variableName + " " + typeIndicator);
        
        return visitChildren(ctx); 
    }
    
    @Override 
    public Integer visitSignedNumber(SmileParser.SignedNumberContext ctx)
    {
        Integer value = visitChildren(ctx);         
        TypeSpec type = ctx.number().type;
        
        if (ctx.sign().getChild(0) == ctx.sign().SUB_OP()) {
            String opcode = (type == Predefined.integerType) ? "ineg"
                          : (type == Predefined.realType)    ? "fneg"
                          :                                    "?neg";
            
            // Emit a negate instruction.
            jFile.println("\t" + opcode);
        }
        
        return value;
    }

    @Override 
    public Integer visitIntegerConst(SmileParser.IntegerConstContext ctx)
    {
        // Emit a load constant instruction.
        jFile.println("\tldc\t" + ctx.getText());
        
        return visitChildren(ctx); 
    }

    @Override 
    public Integer visitFloatConst(SmileParser.FloatConstContext ctx)
    {
        // Emit a load constant instruction.
        jFile.println("\tldc\t" + ctx.getText());
        
        return visitChildren(ctx); 
    }
}