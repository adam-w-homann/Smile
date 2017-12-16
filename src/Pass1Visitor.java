import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import wci.intermediate.*;
import wci.intermediate.symtabimpl.*;
import wci.util.*;

import static wci.intermediate.symtabimpl.SymTabKeyImpl.*;
import static wci.intermediate.symtabimpl.DefinitionImpl.*;

public class Pass1Visitor extends SmileBaseVisitor<Integer> 
{
    private SymTabStack symTabStack;
    private SymTabEntry programId;
    private ArrayList<SymTabEntry> variableIdList;
    private PrintWriter jFile;
   
    
    public Pass1Visitor()
    {
        // Create and initialize the symbol table stack.
        symTabStack = SymTabFactory.createSymTabStack();
        Predefined.initialize(symTabStack);
      
    }
    
    public PrintWriter getAssemblyFile() { return jFile; }
    
    @Override 
    public Integer visitProgram(SmileParser.ProgramContext ctx) 
    { 
        Integer value = visitChildren(ctx); 
        
        // Print the cross-reference table.
        CrossReferencer crossReferencer = new CrossReferencer();
        crossReferencer.print(symTabStack);
        
        return value;
    }
    
    @Override 
    public Integer visitHeader(SmileParser.HeaderContext ctx) 
    { 
        String programName = ctx.IDENTIFIER().toString();
        
        programId = symTabStack.enterLocal(programName);
        programId.setDefinition(DefinitionImpl.PROGRAM);
        programId.setAttribute(ROUTINE_SYMTAB, symTabStack.push());
        symTabStack.setProgramId(programId);
        
        // Create the assembly output file.
        try {
            jFile = new PrintWriter(new FileWriter(programName + ".j"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
        
        // Emit the program header.
        jFile.println(".class public " + programName);
        jFile.println(".super java/lang/Object");
        
        // Emit the RunTimer and PascalTextIn fields.
        jFile.println();
        jFile.println(".field private static _runTimer LRunTimer;");
        jFile.println(".field private static _standardIn LPascalTextIn;");

        return visitChildren(ctx);
    }

    @Override 
    public Integer visitDeclarations(SmileParser.DeclarationsContext ctx) 
    { 
        Integer value = visitChildren(ctx); 
        
        // Emit the class constructor.
        jFile.println();
        jFile.println(".method public <init>()V");
        jFile.println();
        jFile.println("\taload_0");
        jFile.println("\tinvokenonvirtual    java/lang/Object/<init>()V");
        jFile.println("\treturn");
        jFile.println();
        jFile.println(".limit locals 1");
        jFile.println(".limit stack 1");
        jFile.println(".end method");
        
        return value;
    }

    @Override 
    public Integer visitDecl(SmileParser.DeclContext ctx) 
    { 
        jFile.println("\n; " + ctx.getText() + "\n");
        return visitChildren(ctx); 
    }

    @Override 
    public Integer visitVarList(SmileParser.VarListContext ctx) 
    { 
        variableIdList = new ArrayList<SymTabEntry>();
        return visitChildren(ctx);         
    }
    
    @Override 
    public Integer visitVarId(SmileParser.VarIdContext ctx) 
    {
        String variableName = ctx.IDENTIFIER().toString();
        
        SymTabEntry variableId = symTabStack.enterLocal(variableName);
        variableId.setDefinition(VARIABLE);
        variableIdList.add(variableId);
        
        return visitChildren(ctx); 
    }
    
    @Override 
    public Integer visitTypeId(SmileParser.TypeIdContext ctx) 
    { 
        String typeName = ctx.IDENTIFIER().toString();
        
        TypeSpec type;
        String   typeIndicator;
        
        if (typeName.equalsIgnoreCase("integer")) {
            type = Predefined.integerType;
            typeIndicator = "I";
        }
        else if (typeName.equalsIgnoreCase("real")) {
            type = Predefined.realType;
            typeIndicator = "F";
        }
        else if(typeName.equalsIgnoreCase("boolean")) {
        	type = Predefined.booleanType;
            typeIndicator = "B";
        }
        else if(typeName.equalsIgnoreCase("char")) {
        	type = Predefined.charType;
            typeIndicator = "C";
        }
        else if(typeName.equalsIgnoreCase("undefined")) {
        	type = Predefined.undefinedType;
            typeIndicator = "U";
        }
        else {
            type = null;
            typeIndicator = "?";
        }
                    
        for (SymTabEntry id : variableIdList) {
            id.setTypeSpec(type);
            
            // Emit a field declaration.
            jFile.println(".field private static " +
                               id.getName() + " " + typeIndicator);
        }
        
        return visitChildren(ctx); 
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
        
        TypeSpec type = integerMode ? Predefined.integerType
                      : realMode    ? Predefined.realType
                      :               null;
        ctx.type = type;
        
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
        
        TypeSpec type = integerMode ? Predefined.integerType
                      : realMode    ? Predefined.realType
                      :               null;
        ctx.type = type;
        
        return value; 
    }
    
    @Override 
    public Integer visitVariableExpr(SmileParser.VariableExprContext ctx)
    {
        String variableName = ctx.variable().IDENTIFIER().toString();
        SymTabEntry variableId = symTabStack.lookup(variableName);
        
        ctx.type = variableId.getTypeSpec();
        return visitChildren(ctx); 
    }

    @Override 
    public Integer visitSignedNumberExpr(SmileParser.SignedNumberExprContext ctx)
    {
        Integer value = visitChildren(ctx);
        ctx.type = ctx.signedNumber().type;
        return value;
    }

    @Override 
    public Integer visitSignedNumber(SmileParser.SignedNumberContext ctx)
    {
        Integer value = visit(ctx.number());
        ctx.type = ctx.number().type;
        return value;
    }
    
    public Integer visitStringLiteral(SmileParser.StringLiteralContext ctx)
    {
    		Integer value = visit(ctx.STRING());
//        ctx.type = ctx.STRING().type;
        return value;
    }

    @Override 
    public Integer visitUnsignedNumberExpr(SmileParser.UnsignedNumberExprContext ctx)
    {
        Integer value = visit(ctx.number());
        ctx.type = ctx.number().type;
        return value;
    }

    @Override 
    public Integer visitIntegerConst(SmileParser.IntegerConstContext ctx)
    {
        ctx.type = Predefined.integerType;
        return visitChildren(ctx); 
    }

    @Override 
    public Integer visitFloatConst(SmileParser.FloatConstContext ctx)
    {
        ctx.type = Predefined.realType;
        return visitChildren(ctx); 
    }
    
    @Override 
    public Integer visitParenExpr(SmileParser.ParenExprContext ctx)
    {
        Integer value = visitChildren(ctx); 
        ctx.type = ctx.expr().type;
        return value;
    }
}