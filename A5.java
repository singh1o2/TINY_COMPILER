import java.io.*;
import java.util.ArrayList;

public class E2 {

static int pointer = -1;	
static ArrayList tokens = new ArrayList();

public E2() 
{
	super();

}
/**  program-->MethodDecl program_opt
*/
static boolean program() throws Exception
{
	int savePointer = pointer;
    if (MethodDecl() && program_opt()) 
    {
			System.out.println("program-->MethodDecl program_opt");
			return true;
	}
	pointer = savePointer;
	return true;
}
/** program_opt-->program | empty
*/
static boolean program_opt() throws Exception
{
	int savePointer = pointer;
    if (program()) 
    {
		    System.out.println("program_opt-->program | empty");
			return true;
	}
	pointer = savePointer;
	return true;
}
/**
        MethodDecl-->Type MAIN_opt IDENTIFIER LPAREN FormalParams RPAREN Block
*/
static boolean MethodDecl() throws Exception
{
	int savePointer = pointer;
	if (Type() && MAIN_opt() && nextToken().sym == A5Sym.IDENTIFIER && nextToken().sym == A5Sym.LPAREN && FormalParams() && nextToken().sym == A5Sym.RPAREN && Block()) 
    {
			System.out.println("MethodDecl--> Type MAIN_opt IDENTIFIER LPAREN FormalParams RPAREN Block");
			return true;
	}
	pointer = savePointer;
	return false;

}
/**
    MAIN_opt --> MAIN | empty
*/
static boolean MAIN_opt() throws Exception
{
	int savePointer = pointer;
	if (nextToken().sym == A5Sym.MAIN) 
    {
			System.out.println("MAIN_opt--> MAIN | empty");
			return true;
	}
	pointer = savePointer;
	return true;

}
/**FormalParams-->FormalParam FP_opt | empty
*/
static boolean FormalParams() throws Exception
{
	int savePointer = pointer;
	if (FormalParam() && FP_opt())
    {
		System.out.println("FormalParams --> FormalParam FP_opt");
		return true;
	}
    System.out.println("FormalParams --> Empty");
	pointer = savePointer;
	return true;
}
//FP_opt--> COMMA FormalParams|empty
static boolean FP_opt() throws Exception
{
	int savePointer = pointer;
	if (nextToken().sym == A5Sym.COMMA && FormalParams())
    {
		System.out.println("FP_opt --> COMMA FormalParams");
		return true;
	}
	pointer = savePointer;
	return true;
}
//FormalParam-->Type IDENTIFIER
static boolean FormalParam() throws Exception
{
	int savePointer = pointer;
	if (Type() && nextToken().sym == A5Sym.IDENTIFIER) 
    {
		System.out.println("FormalParam-->Type IDENTIFIER");
		return true;
	}
	pointer = savePointer;
    return false;
}
//Type-->INT | REAL | STRING
static boolean Type() throws Exception
{
	int savePointer = pointer;
    int sym = nextToken().sym;
	if (sym == A5Sym.INT|| sym == A5Sym.REAL || sym == A5Sym.STRING) 
    {
		System.out.println("Type-->INT|REAL|STRING");
		return true;
    }
	pointer = savePointer;
	return false;
}   
//Block--> BEGIN Statements END
static boolean Block() throws Exception
{
	int savePointer = pointer;
	if (nextToken().sym == A5Sym.BEGIN && Statements() && nextToken().sym == A5Sym.END) 
    {
		System.out.println("Block--> BEGIN Statements END");
		return true;
	}
	pointer = savePointer;
    return false;
}   
/**Statements --> Statement S_option
  */
static boolean Statements() throws Exception
{
	int savePointer = pointer;
    if (Statement() && S_option()) 
    {
		System.out.println("Statements --> Statement S_option");
		return true;
	}
	pointer = savePointer;
	return false;
}
/**
    S_option -->Statements |empty
*/
static boolean S_option() throws Exception
{
	int savePointer = pointer;
    if (Statements()) 
    {
		System.out.println("S_option-->Statements |empty");
		return true;
	}
	pointer = savePointer;
	return true;

}
//Statement-->Block | LocalVarDecl | AssignStmt | ReturnStmt | IfStmt | WriteStmt | ReadStmt  
static boolean Statement() throws Exception
{
	int savePointer = pointer;
	if(Block() || LocalVarDecl() || AssignStmt() || ReturnStmt() || IfStmt() || WriteStmt() || ReadStmt() ) 
    {
			System.out.println("Statement-->Block | LocalVarDecl | AssignStmt | ReturnStmt | IfStmt | WriteStmt | ReadStmt");
			return true;
    }
	pointer = savePointer;

	pointer = savePointer;
	return false;

}    
//LocalVarDecl-->Type type_option
static boolean LocalVarDecl() throws Exception
{
	int savePointer = pointer;
	if (Type() && type_option()) 
    {
		System.out.println("LocalVarDecl-->Type IDENTIFIER SEMICOLON");
		return true;
	}
	pointer = savePointer;
	return false;

} 
// type_option-->IDENTIFIER SEMICOLON |  AssignStmt  
static boolean type_option() throws Exception
{
	int savePointer = pointer;
	if (nextToken().sym == A5Sym.IDENTIFIER && nextToken().sym == A5Sym.SEMICOLON) 
    {
		System.out.println("type_option--> IDENTIFIER SEMICOLON");
		return true;
	}
    if(AssignStmt())
    {
        System.out.println("type_option--> AsssignStmt");
		return true;
    }
	pointer = savePointer;
	return false;

}   
//AssignStmt-->IDENTIFIER ASSIGNEQU Expression SEMICOLON | IDENTIFIER ASSIGNEQU QUOTE SEMICOLON
static boolean AssignStmt() throws Exception
{
	int savePointer = pointer;
	if (nextToken().sym == A5Sym.IDENTIFIER && nextToken().sym == A5Sym.ASSIGNEQU && Expression() && nextToken().sym == A5Sym.SEMICOLON) 
    {
		System.out.println("AssignStmt-->IDENTIFIER ASSIGNEQU Expression SEMICOLON");
		return true;
	}
	pointer = savePointer;
	if (nextToken().sym == A5Sym.IDENTIFIER && nextToken().sym == A5Sym.ASSIGNEQU && nextToken().sym == A5Sym.QUOTE && nextToken().sym == A5Sym.SEMICOLON) 
    {
		System.out.println("AssignStmt-->IDENTIFIER ASSIGNEQU QUOTE SEMICOLON");
		return true;
	}
	pointer = savePointer;
	return false;
}
 
//ReturnStmt-->RETURN Expression SEMICOLON  
static boolean ReturnStmt() throws Exception
{
	int savePointer = pointer;
	if (nextToken().sym == A5Sym.RETURN && Expression() && nextToken().sym == A5Sym.SEMICOLON) 
    {
		System.out.println("ReturnStmt-->RETURN Expression SEMICOLON ");
		return true;
	}
	pointer = savePointer;
	return false;
} 
/**IfStmt-->IF LPAREN BoolExpression RPAREN Statement
        | IF LPAREN BoolExpression RPAREN Statement ELSE Statement
*/
static boolean IfStmt() throws Exception
{
	int savePointer = pointer;
     if (nextToken().sym == A5Sym.IF && nextToken().sym == A5Sym.LPAREN && BoolExpression() && nextToken().sym ==            A5Sym.RPAREN && Statement() && nextToken().sym == A5Sym.ELSE && Statement() ) 
    {
		System.out.println("IfStmt-->IF LPAREN BoolExpression RPAREN Statement ELSE Statement");
		return true;
	}
	pointer = savePointer;
	if (nextToken().sym == A5Sym.IF && nextToken().sym == A5Sym.LPAREN && BoolExpression() && nextToken().sym == A5Sym.RPAREN && Statement()) 
    {
		System.out.println("IfStmt-->IF LPAREN BoolExpression RPAREN Statement");
		return true;
	}
    pointer = savePointer;
	return false;
}              
//WriteStmt-->WRITE LPAREN Expression COMMA QUOTE RPAREN SEMICOLON
static boolean WriteStmt() throws Exception
{
	int savePointer = pointer;
	if (nextToken().sym == A5Sym.WRITE && nextToken().sym == A5Sym.LPAREN && Expression() && nextToken().sym == A5Sym.COMMA && nextToken().sym == A5Sym.QUOTE && nextToken().sym == A5Sym.RPAREN && nextToken().sym == A5Sym.SEMICOLON) 
    {
		System.out.println("WriteStmt-->WRITE LPAREN Expression COMMA QUOTE RPAREN SEMICOLON");
		return true;
	}                 
    pointer = savePointer;
	return false;
}
//ReadStmt --> READ LPAREN IDENTIFIER COMMA QUOTE RPAREN SEMICOLON  
static boolean ReadStmt() throws Exception
{
	int savePointer = pointer;
	if (nextToken().sym == A5Sym.READ && nextToken().sym == A5Sym.LPAREN && nextToken().sym == A5Sym.IDENTIFIER && nextToken().sym == A5Sym.COMMA && nextToken().sym == A5Sym.QUOTE && nextToken().sym == A5Sym.RPAREN && nextToken().sym == A5Sym.SEMICOLON) 
    {
		System.out.println("ReadStmt --> READ LPAREN IDENTIFIER COMMA QUOTE RPAREN SEMICOLON ");
		return true;
	}                 
    pointer = savePointer;
	return false;
}  
//Expression --> MultExpr expr_option
static boolean Expression() throws Exception
{
	int savePointer = pointer;
    if (MultExpr() && expr_option()) 
    {
		System.out.println("Expression --> MultExpr expr_option");
		return true;
	}
	pointer = savePointer;
	return false;

} 
//expr_option-->  PLUS  Expression| MINUS Expression |empty
static boolean expr_option() throws Exception
{
	int savePointer = pointer;
    int sym = nextToken().sym;
    if ((sym == A5Sym.PLUS ||sym==A5Sym.MINUS) && Expression()) 
    {
		System.out.println("Expression --> MultExpr MINUS Expression");
		return true;
	}
	pointer = savePointer;
	return true;

}             

/**MultExpr-->PrimaryExpr  ME_optional
 */
static boolean MultExpr() throws Exception
{
	int savePointer = pointer;
    if (PrimaryExpr() && ME_optional()) 
    {
		System.out.println("MultExpr-->PrimaryExpr ME");
		return true;
	}
    pointer = savePointer; 
	return false;
}   
//ME_optional ---> TIMES  MultExpr|DIVIDE MultExpr|empty
static boolean ME_optional() throws Exception
{
	int savePointer = pointer;
	int sym = nextToken().sym;
    if ((sym == A5Sym.DIVIDE||sym == A5Sym.TIMES) && MultExpr()) 
    {
		System.out.println("MultExpr-->PrimaryExpr  DIVIDE MultExpr");
		return true;
	}
	pointer = savePointer;
	return true;
}                
/**
PrimaryExpr      ::= NUMBER
                  | IDENTIFIER
                  | LPAREN Expression RPAREN
                  | IDENTIFIER LPAREN ActualParams RPAREN
                  | IDENTIFIER LPAREN RPAREN*/                
static boolean PrimaryExpr() throws Exception
{
	int savePointer = pointer;
     if (nextToken().sym == A5Sym.IDENTIFIER && nextToken().sym == A5Sym.LPAREN && ActualParams() && nextToken().sym == A5Sym.RPAREN) 
    {
		System.out.println("MultExpr-->IDENTIFIER LPAREN ActualParams RPAREN");
		return true;
	}
    pointer = savePointer;
     if (nextToken().sym == A5Sym.IDENTIFIER && nextToken().sym == A5Sym.LPAREN && nextToken().sym == A5Sym.RPAREN) 
    {
		System.out.println("MultExpr-->IDENTIFIER LPAREN ActualParams RPAREN");
		return true;
	}
	pointer = savePointer;
    if (nextToken().sym == A5Sym.LPAREN && Expression() && nextToken().sym == A5Sym.RPAREN) 
    {
		System.out.println("MultExpr--> LPAREN Expression RPAREN");
		return true;
	}
	pointer = savePointer;
    int symb = nextToken().sym;
	if (symb == A5Sym.NUMBER || symb == A5Sym.IDENTIFIER) 
    {
		System.out.println("PrimaryExpr-->NUMBER| IDENTIFIER");
		return true;
	}
	pointer = savePointer;
	
   
	return false;
} 
/**BoolExpression-->Expression EEQUAL Expression
 */                 
static boolean BoolExpression() throws Exception
{
	int savePointer = pointer;
	if (Expression() && nextToken().sym == A5Sym.EEQUAL && Expression()) 
    {
		System.out.println("BoolExpression-->Expression EEQUAL Expression");
		return true;
	}
	pointer = savePointer;
    if (Expression() && nextToken().sym == A5Sym.NEQUAL && Expression()) 
    {
		System.out.println("BoolExpression-->Expression NEQUAL Expression");
		return true;
	}
	pointer = savePointer;
	return false;
}  
//ActualParams    --> Expression AP_option               
static boolean ActualParams() throws Exception
{
	int savePointer = pointer;
     if (Expression() && AP_option()) 
    {
		System.out.println("ActualParams-->Expression COMMA ActualParamas");
		return true;
	}
	pointer = savePointer;
	return false;
}  
//AP_option --> COMMA ActualParams
static boolean AP_option() throws Exception
{
	int savePointer = pointer;
     if (nextToken().sym == A5Sym.COMMA && ActualParams()) 
    {
		System.out.println("ActualParams-->Expression COMMA ActualParamas");
		return true;
	}
	pointer = savePointer;
	return true;
}  
static Symbol nextToken() {
	if (pointer < tokens.size()-1) 
    {
		pointer++;
		Symbol token = (Symbol) tokens.get(pointer);
		System.out.println ("next Token: " + token.toString());
		return token;
	} else
		return null;
}
public static void main(String[] args) throws Exception 
{
	BufferedWriter bw=new BufferedWriter(new FileWriter("a5.output"));
	A5Scanner scanner = new A5Scanner(new FileInputStream(new File("A5.tiny")));
	Symbol token; 
	while ((token=scanner.yylex()).sym!= A5Sym.EOF) 
    {
		tokens.add(token);
	}
	tokens.add(token);   // add EOF as the last token in the array
	boolean legal= program() && nextToken().sym==A5Sym.EOF;
	bw.write((legal)?"legal":"illegal");
	bw.close();
}
}
