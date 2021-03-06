import java.util.*;
import java.io.*;

public class TinyGeneration{    
    private static LinkedList<String> registerPool = new LinkedList<String>();
    private static int registercount = 1;
    
    //    public static IRNode getIRNode(int i)
    //    {
    //        return IRList.getNode(i);
    //    }
    
    public static Boolean isRegister(String op) {
	if (op.startsWith("$T"))
	    return true;
	else
	    return false;
    }
    public static Boolean isIdentifier(String id) {
	if (id.matches("[0-9]+")) {
	    return false;
	}
	else if (id.startsWith("$T")) {
	    return false;
	}
	else if (id.matches("([a-zA-Z])+")) {
	    return true;
	}
	else {
	    return false;
	}
    }

    public static void buildTiny() {
	initializeIdentifiers();
        String reg = "r0";
        int i;
	String opcode = "";
	String op1 = "";
	String op2 = "";
	String optype = "";

        for(i = 0; i < IRList.getSize(); i++) {
	    IRNode currentIRNode = IRList.getNode(i);
	    opcode = "";
	    op1 = "";
	    op2 = "";
	    optype = "";

	    if(currentIRNode.opcode.startsWith("STORE")){   
		opcode = "move";	      
		if (isRegister(currentIRNode.op1)){
		    op1 = getRegister(currentIRNode.op1); 
		    op2 = currentIRNode.res;	
		}
		else if (isIdentifier(currentIRNode.op1) &&
			 isIdentifier(currentIRNode.res)){
		    //create 2 tiny instructions
		    op1 = currentIRNode.op1;
		    op2 = newRegister();
		    TinyInstr instr = new TinyInstr(opcode, op1, op2);
		    System.out.println(instr.printInstr());
		    op1 = registerPool.peek();
		    op2 = currentIRNode.res;
		}
		else {
		    op1 = currentIRNode.op1;		    
		    op2 = newRegister();
		}
	    }
	    else if(currentIRNode.opcode.equals("ADDI")){
		//Gets translated as 2 Tiny instructions
		opcode = "move";
		if (isRegister(currentIRNode.op1)){
		    op1 = getRegister(currentIRNode.op1); }
		else {
		    op1 = currentIRNode.op1;}
		op2 = newRegister();

		TinyInstr instr = new TinyInstr(opcode, op1, op2);
		System.out.println(instr.printInstr());
	       
		opcode = "addi";
		if (isRegister(currentIRNode.op2)){
		    op1 = getRegister(currentIRNode.op2); }
		else {
		    op1 = currentIRNode.op2;}
		op2 = op2;
	    }	   
	    else if(currentIRNode.opcode.equals("ADDF")){
		opcode = "move";
		if (isRegister(currentIRNode.op1)){
		    op1 = getRegister(currentIRNode.op1); }
		else {
		    op1 = currentIRNode.op1;}
		op2 = newRegister();

		TinyInstr instr = new TinyInstr(opcode, op1, op2);
		System.out.println(instr.printInstr());
	       
		opcode = "addr";
		if (isRegister(currentIRNode.op2)){
		    op1 = getRegister(currentIRNode.op2); }
		else {
		    op1 = currentIRNode.op2;}
		op2 = op2;
	    }
	    else if(currentIRNode.opcode.equals("SUBI")){
		opcode = "move";
		if (isRegister(currentIRNode.op1)){
		    op1 = getRegister(currentIRNode.op1); }
		else {
		    op1 = currentIRNode.op1;}
		op2 = newRegister();

		TinyInstr instr = new TinyInstr(opcode, op1, op2);
		System.out.println(instr.printInstr());
	       
		opcode = "subi";
		if (isRegister(currentIRNode.op2)){
		    op1 = getRegister(currentIRNode.op2); }
		else {
		    op1 = currentIRNode.op2;}
		op2 = op2;
	    }
	    else if(currentIRNode.opcode.equals("SUBF")){
		opcode = "move";
		if (isRegister(currentIRNode.op1)){
		    op1 = getRegister(currentIRNode.op1); }
		else {
		    op1 = currentIRNode.op1;}
		op2 = newRegister();

		TinyInstr instr = new TinyInstr(opcode, op1, op2);
		System.out.println(instr.printInstr());
	       
		opcode = "subr";
		if (isRegister(currentIRNode.op2)){
		    op1 = getRegister(currentIRNode.op2); }
		else {
		    op1 = currentIRNode.op2;}
		op2 = op2;
	    }
	    else if(currentIRNode.opcode.equals("MULTI")){
		opcode = "move";
		if (isRegister(currentIRNode.op1)){
		    op1 = getRegister(currentIRNode.op1); }
		else {
		    op1 = currentIRNode.op1;}
		op2 = newRegister();

		TinyInstr instr = new TinyInstr(opcode, op1, op2);
		System.out.println(instr.printInstr());
	       
		opcode = "muli";
		if (isRegister(currentIRNode.op2)){
		    op1 = getRegister(currentIRNode.op2); }
		else {
		    op1 = currentIRNode.op2;}
		op2 = op2;
	    }
	    else if(currentIRNode.opcode.equals("MULTF")){
		opcode = "move";
		if (isRegister(currentIRNode.op1)){
		    op1 = getRegister(currentIRNode.op1); }
		else {
		    op1 = currentIRNode.op1;}
		op2 = newRegister();

		TinyInstr instr = new TinyInstr(opcode, op1, op2);
		System.out.println(instr.printInstr());
	       
		opcode = "mulr";
		if (isRegister(currentIRNode.op2)){
		    op1 = getRegister(currentIRNode.op2); }
		else {
		    op1 = currentIRNode.op2;}
		op2 = op2;
	    }
	    else if(currentIRNode.opcode.equals("DIVI")){
		opcode = "move";
		if (isRegister(currentIRNode.op1)){
		    op1 = getRegister(currentIRNode.op1); }
		else {
		    op1 = currentIRNode.op1;}
		op2 = newRegister();

		TinyInstr instr = new TinyInstr(opcode, op1, op2);
		System.out.println(instr.printInstr());
	       
		opcode = "divi";
		if (isRegister(currentIRNode.op2)){
		    op1 = getRegister(currentIRNode.op2); }
		else {
		    op1 = currentIRNode.op2;}
		op2 = op2;
	    }
	    else if(currentIRNode.opcode.equals("DIVF")){
		opcode = "move";
		if (isRegister(currentIRNode.op1)){
		    op1 = getRegister(currentIRNode.op1); }
		else {
		    op1 = currentIRNode.op1;}
		op2 = newRegister();

		TinyInstr instr = new TinyInstr(opcode, op1, op2);
		System.out.println(instr.printInstr());
	       
		opcode = "divr";
		if (isRegister(currentIRNode.op2)){
		    op1 = getRegister(currentIRNode.op2); }
		else {
		    op1 = currentIRNode.op2;}
		op2 = op2;
	    }
	    else if(currentIRNode.opcode.equals("WRITEI")){	      
		opcode = "sys writei";
		if (isRegister(currentIRNode.op1)){
		    op1 = getRegister(currentIRNode.op1); }
		else {
		    op1 = currentIRNode.op1;}      
	    }
	    else if(currentIRNode.opcode.equals("WRITEF")){
		opcode = "sys writer";
		if (isRegister(currentIRNode.op1)){
		    op1 = getRegister(currentIRNode.op1); }
		else {
		    op1 = currentIRNode.op1;}
	    }
	    else if(currentIRNode.opcode.equals("WRITES")){
		opcode = "sys writes";
		if (isRegister(currentIRNode.op1)){
		    op1 = getRegister(currentIRNode.op1); }
		else {
		    op1 = currentIRNode.op1;}
	    }
	    else if(currentIRNode.opcode.equals("READI")){
		opcode = "sys readi";
		if (isRegister(currentIRNode.op1)){
		    op1 = getRegister(currentIRNode.op1); }
		else {
		    op1 = currentIRNode.op1;}
	    }
	    else if(currentIRNode.opcode.equals("READF")){
		opcode = "sys readr";
		if (isRegister(currentIRNode.op1)){
		    op1 = getRegister(currentIRNode.op1); }
		else {
		    op1 = currentIRNode.op1;}
	    }
	    else if(currentIRNode.opcode.equals("READS")){
		opcode = "sys reads";
		if (isRegister(currentIRNode.op1)){
		    op1 = getRegister(currentIRNode.op1); }
		else {
		    op1 = currentIRNode.op1;}
	    }
	    else if(currentIRNode.opcode.equals("GE") ||
		    currentIRNode.opcode.equals("LE") ||
		    currentIRNode.opcode.equals("GT") ||
		    currentIRNode.opcode.equals("LT") ||
		    currentIRNode.opcode.equals("EQ") ||
		    currentIRNode.opcode.equals("NE")
		    ){
		//  ;;GE i $T4 label1
		//becomes
		//  cmpi i r3
		//  jge label1			

		//just need to check type of one operand to find type
		//because LITTLE and Tiny do not work with operations involving
		//different types
		optype = getOpType(currentIRNode.op1);
		opcode = "cmp"+optype;

		if (isRegister(currentIRNode.op1)){
		    op1 = getRegister(currentIRNode.op1); }
		else {
		    op1 = currentIRNode.op1;}
		if (isRegister(currentIRNode.op2)){
			op2 = getRegister(currentIRNode.op2);
		}
		else { //it's a literal or a variablename
			op2 = currentIRNode.op2;
		}
		//op2 = newRegister();

		TinyInstr instr = new TinyInstr(opcode, op1, op2);
		System.out.println(instr.printInstr());
		opcode = "";
		op1 = "";
		op2 = "";

		opcode = "j"+currentIRNode.opcode.toLowerCase(); //converts GE to jge, LE to jle, etc
		//		op1 = op2;
		op1 = currentIRNode.res.toLowerCase(); //res stores the label for IRNodes
	    }
	    else if(currentIRNode.opcode.equals("JUMP")){
		opcode = "jmp";
		op1 = currentIRNode.op1.toLowerCase(); //not gonna happen but just to ensure it
	    }
	    else if(currentIRNode.opcode.equals("LABEL")){
		opcode = "label";
		op1 = currentIRNode.op1.toLowerCase();
	    }
	    TinyInstr instr = new TinyInstr(opcode, op1, op2);
	    System.out.print(instr.printInstr());
	    System.out.println(" ;"+currentIRNode.printNode());
	}
        
        TinyInstr instr = new TinyInstr("sys halt", "", "");
        System.out.println(instr.printInstr());
    }

    public static void initializeIdentifiers() {
	SymbolTable.createTinyInstr();
    }

    public static String getRegister(String temp) {
        return "r"+temp.substring(2);
        //return registerPool.peek();
    }

    public static String newRegister() {
	registerPool.addFirst("r"+Integer.toString(registercount++));
        return registerPool.peek();
    }
    public static String getOpType(String op) {
	if (op.startsWith("$T")) { //if it is a register,
	    //so find register type
	    //	    System.out.print("Register "+op1);
	    return ValueRegister.getType(op).toLowerCase();
	}
	else if (op.matches("[0-9]+")) { //if it's an int
	    return "i";
	}
	else if (op.contains(".")) { //if it's a float
	    return "r";
	}
	else { //it is a symbol/literal
	    //so find symbol type	    
	    String retval = SymbolTable.getType(op).toLowerCase();
	    if (retval.startsWith("f")) { return "r"; } //it's a float type
	    else { return retval; }
	}
    }
}