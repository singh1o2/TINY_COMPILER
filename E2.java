import java.io.*;
import java.util.ArrayList;

public class E2 {
static int pointer = -1;	
static ArrayList tokens = new ArrayList();
static boolean program() throws Exception
{
	int savePointer = pointer;
    if (MethodDecl() && program_opt()) 
    {	
			return true;
	}
	pointer = savePointer;
	return true;
}

static boolean program_opt() throws Exception
{
	int savePointer = pointer;
    if (program()) 
    {
		    
			return true;
	}
	pointer = savePointer;
	return true;
}

static boolean MethodDecl() throws Exception
{
	int savePointer = pointer;
	if (Type() && MAIN_opt() && nextToken().sym == A5Sym.IDENTIFIER && nextToken().sym == A5Sym.LPAREN && FormalParams() && nextToken().sym == A5Sym.RPAREN && Block()) 
    
	{
			return true;
	}
	pointer = savePointer;
	return false;

}

static boolean MAIN_opt() throws Exception
{
	int savePointer = pointer;
	if (nextToken().sym == A5Sym.MAIN) 
    {
			
			return true;
	}
	pointer = savePointer;
	return true;

}

static boolean FormalParams() throws Exception
{
	int savePointer = pointer;
	if (FormalParam() && FP_opt())
    {
		return true;
	}
    
	pointer = savePointer;
	return true;
}

static boolean FP_opt() throws Exception
{
	int savePointer = pointer;
	if (nextToken().sym == A5Sym.COMMA && FormalParams())
    {
		
		return true;
	}
	pointer = savePointer;
	return true;
}

static boolean FormalParam() throws Exception
{
	int savePointer = pointer;
	if (Type() && nextToken().sym == A5Sym.IDENTIFIER) 
    {
		
		return true;
	}
	pointer = savePointer;
    return false;
}

static boolean Type() throws Exception
{
	int savePointer = pointer;
    int sym = nextToken().sym;
	if (sym == A5Sym.INT|| sym == A5Sym.REAL || sym == A5Sym.STRING) 
    {
		
		return true;
    }
	pointer = savePointer;
	return false;
}   

static boolean Block() throws Exception
{
	int savePointer = pointer;
	if (nextToken().sym == A5Sym.BEGIN && Statements() && nextToken().sym == A5Sym.END) 
        {
		return true;
	}
	pointer = savePointer;
    return false;
}   

static boolean Statements() throws Exception
{
	int savePointer = pointer;
    if (Statement() && S_option()) 
    {
		
		return true;
	}
	pointer = savePointer;
	return false;
}

static boolean S_option() throws Exception
{
    int savePointer = pointer;
    if (Statements()) 
    {
	
		return true;
	}
	pointer = savePointer;
	return true;

}  
static boolean Statement() throws Exception
{
	int savePointer = pointer;
	if(Block() || LocalVarDecl() || AssignStmt() || WriteStmt()||ReadStmt()  || IfStmt() ||ReturnStmt() ) 
    {
			
			return true;
    }
	pointer = savePointer;

	pointer = savePointer;
	return false;

}    

static boolean LocalVarDecl() throws Exception
{
	int savePointer = pointer;
	if (Type() && type_option()) 
    {

		return true;
	}
	pointer = savePointer;
	return false;

} 

static boolean type_option() throws Exception
{
	int savePointer = pointer;
	if (nextToken().sym == A5Sym.IDENTIFIER && nextToken().sym == A5Sym.SEMICOLON) 
    {
		return true;
	}
    if(AssignStmt())
    {
		return true;
    }
	pointer = savePointer;
	return false;

}   

static boolean AssignStmt() throws Exception
{
	int savePointer = pointer;
	if (nextToken().sym == A5Sym.IDENTIFIER && nextToken().sym == A5Sym.ASSIGNEQU && (Expression()||nextToken().sym == A5Sym.QUOTE)&& nextToken().sym == A5Sym.SEMICOLON) 
    {
		return true;
	}
	pointer = savePointer;
	return false;
}
 
static boolean ReturnStmt() throws Exception
{
	int savePointer = pointer;
	if (nextToken().sym == A5Sym.RETURN && Expression() && nextToken().sym == A5Sym.SEMICOLON) 
    {
		return true;
	}
	pointer = savePointer;
	return false;
} 
static boolean IfStmt() throws Exception
{
	int savePointer = pointer;
     if (nextToken().sym == A5Sym.IF && nextToken().sym == A5Sym.LPAREN && BoolExpression() && nextToken().sym == A5Sym.RPAREN && Statement() &&  If_opt()) 
    {
		return true;
	}
    pointer = savePointer;
	return false;
}        
static boolean If_opt() throws Exception
{
	int savePointer = pointer;
     if (nextToken().sym == A5Sym.ELSE && Statement()) 
    {
		return true;
	}
    pointer = savePointer;
	return true;
}        
static boolean WriteStmt() throws Exception
{
	int savePointer = pointer;
	if (nextToken().sym == A5Sym.WRITE && nextToken().sym == A5Sym.LPAREN && Expression() && nextToken().sym == A5Sym.COMMA && nextToken().sym == A5Sym.QUOTE && nextToken().sym == A5Sym.RPAREN && nextToken().sym == A5Sym.SEMICOLON) 
    {
		return true;
	}                 
    pointer = savePointer;
	return false;
}
static boolean ReadStmt() throws Exception
{
	int savePointer = pointer;
	if (nextToken().sym == A5Sym.READ && nextToken().sym == A5Sym.LPAREN && nextToken().sym == A5Sym.IDENTIFIER && nextToken().sym == A5Sym.COMMA && nextToken().sym == A5Sym.QUOTE && nextToken().sym == A5Sym.RPAREN && nextToken().sym == A5Sym.SEMICOLON) 
    {
		return true;
	}                 
    pointer = savePointer;
	return false;
}  
static boolean Expression() throws Exception
{
	int savePointer = pointer;
    if (MultExpr() && expr_option()) 
    {
		return true;
	}
	pointer = savePointer;
	return false;

} 
static boolean expr_option() throws Exception
{
	int savePointer = pointer;
    int sym = nextToken().sym;
    if ((sym == A5Sym.PLUS ||sym==A5Sym.MINUS) && Expression()) 
    {
		return true;
	}
	pointer = savePointer;
	return true;

}             

 
static boolean MultExpr() throws Exception
{
	int savePointer = pointer;
    if (PrimaryExpr() && ME_optional()) 
    {
		return true;
	}
    pointer = savePointer; 
	return false;
}   
static boolean ME_optional() throws Exception
{
	int savePointer = pointer;
	int sym = nextToken().sym;
        if ((sym == A5Sym.DIVIDE||sym == A5Sym.TIMES) && MultExpr()) 
        {
		return true;
	}
	pointer = savePointer;
	return true;
}                

static boolean PrimaryExpr() throws Exception
{
     int savePointer = pointer;
     if (nextToken().sym == A5Sym.IDENTIFIER && nextToken().sym == A5Sym.LPAREN && ActualParams() && nextToken().sym == A5Sym.RPAREN) 
    {
		return true;
	}
    pointer = savePointer;
    if (nextToken().sym == A5Sym.LPAREN && Expression() && nextToken().sym == A5Sym.RPAREN) 
    {
		return true;
	}
	pointer = savePointer;
    int symb = nextToken().sym;
	if (symb == A5Sym.NUMBER || symb == A5Sym.IDENTIFIER) 
    {
		return true;
	}
	pointer = savePointer;
	
   
	return false;
} 
                 
static boolean BoolExpression() throws Exception
{
	int savePointer = pointer;
    int x;
	if (Expression() && ((x=nextToken().sym) == A5Sym.EEQUAL||x==A5Sym.NEQUAL) && Expression()) 
    {
		return true;
	}
	pointer = savePointer;
	return false;
}  
static boolean ActualParams() throws Exception
{
	int savePointer = pointer;
     if (Expression() && AP_option()) 
    {
		return true;
	}
	pointer = savePointer;
	return true;
}  
static boolean AP_option() throws Exception
{
	int savePointer = pointer;
     if (nextToken().sym == A5Sym.COMMA && ActualParams()) 
    {
		return true;
	}
	pointer = savePointer;
	return true;
}  
static Symbol nextToken() {
	if (pointer < tokens.size()-1) 
        {
		pointer++;
		return (Symbol) tokens.get(pointer);
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
	tokens.add(token); 
	boolean legal= program() && nextToken().sym==A5Sym.EOF;
	bw.write((legal)?"legal":"illegal");
	bw.close();
}
}
