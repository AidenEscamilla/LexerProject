import java.util.*;
import java.io.*;

public class Lexer {
	
	public static void main(String[] args) {
		String fileName = args[0];
		
		try {
			File inFile = new File(fileName);
			Scanner input = new Scanner(inFile);
			while(input.hasNextLine()) {
				String line = input.nextLine();
				for(int i = 0; i < line.length(); i++) {
					//skip over whitespace characters
					if(Character.isWhitespace(line.charAt(i)))
						continue;
					//if it's the start of a string
					if(line.charAt(i) == '"'){
						i++;//move past initial "
						String sToken = "";
						
						if(line.charAt(i) == '"') {
							System.out.println("STRING_tok(emptySet)");
							i++;//skip "
							break;
						}
						
						while(line.charAt(i) != '"') {//while next char not quotations
							if(line.charAt(i) == '\\')//if it's an escape
								if(line.charAt(i + 1) == '"') {
									i++;//skip escape
									sToken += line.charAt(i);//add "
									i++;
									continue;//skip bottom add
								}
								else if(line.charAt(i + 1) == '\\') {
									i++;//skip escape
									sToken += line.charAt(i);//add \
									i++;
									continue;//skip bottom add
								}
								else if(line.charAt(i + 1) == 'n') {
									i++;//skip escape
									sToken += "\n";//add NEWLINE
									i++;
									continue;//skip bottom add
								}
								else if(line.charAt(i + 1) == 't') {
									i++;;//skip escape
									sToken += "\t";//add TAB
									i++;
									continue;//skip bottom add
								}
								else {
									while(line.charAt(i) != '"')
										i++;
									System.out.println("STRING_tok(" + sToken + ")");
									break;//break the while
								}
							
							
							sToken += line.charAt(i);
							i++;
						}
						System.out.println("STRING_tok(" + sToken + ")");
					}
					
					
					if(Character.isDigit(line.charAt(i))) {
						String integer = "";
						integer+= line.charAt(i);//add first number
						
						if(i + 1 < line.length())
							i++;//move past
						else {
							line = input.nextLine();
							i=0;
						}
						
						while(Character.isDigit(line.charAt(i))) {
							integer += line.charAt(i);
							i++;
								
						}
						System.out.println("INT_tok(" + Integer.parseInt(integer) +")");
					}
					
					if(Character.isLetterOrDigit(line.charAt(i)) || line.charAt(i) == '_') {
						String idToken = "";
						idToken += line.charAt(i);
						
						
						if(i + 1 < line.length())
							i++;//move past
						else {
							line = input.nextLine();
							i=0;
						}
						
						while(Character.isLetterOrDigit(line.charAt(i)) || line.charAt(i) == '_') {
							idToken += line.charAt(i);
							i++;	
						}
						
						if(idToken.equals("and")) {
							System.out.println("AND_tok");
							continue;
						}
						if(idToken.equals("or")) {
							System.out.println("OR_tok");
							continue;
						}
						if(idToken.equals("not")) {
							System.out.println("NOT_tok");
							continue;
						}
						if(idToken.equals("get")) {
							System.out.println("GET_tok");
							continue;
						}
						if(idToken.equals("print")) {
							System.out.println("PRINT_tok");
							continue;
						}
						if(idToken.equals("do")) {
							System.out.println("DO_tok");
							continue;
						}
						if(idToken.equals("end")) {
							System.out.println("END_tok");
							continue;
						}
						if(idToken.equals("then")) {
							System.out.println("THEN_tok");
							continue;
						}
						if(idToken.equals("while")) {
							System.out.println("WHILE_tok");
							continue;
						}
						if(idToken.equals("for")) {
							System.out.println("FOR_tok");
							continue;
						}
						if(idToken.equals("if")) {
							System.out.println("IF_tok");
							continue;
						}
						System.out.println("ID_tok(" + idToken +")");
					}
					
					switch(line.charAt(i)) {
					case '-':
						System.out.println("MINUS_lit");
						break;
					case '*':
						System.out.println("MULT_lit");
						break;
					case '/':
						System.out.println("DIV_lit");
						break;
					case '%':
						System.out.println("MOD_lit");
						break;
					case '+':
						System.out.println("PLUS_lit");
						break;
					case ';':
						System.out.println("SEMI_lit");
						break;
					case '(':
						System.out.println("OPENP_lit");
						break;
					case ')':
						System.out.println("CLOSEP_lit");
						break;
					case '!':
						i++;
						if(line.charAt(i) == '=')
							System.out.println("NE_tok");
						else {
							System.out.println("ERROR after: !");
							System.exit(0);
						}
						i--;
						break;
					case '=':
						i++;
						if(line.charAt(i) == '=')
							System.out.println("EQEQ_tok");
						else 
							System.out.println("EQ_lit");
						i--;
						break;
					case '>':
						i++;
						if(line.charAt(i) == '=')
							System.out.println("GREATE_tok");
						else 
							System.out.println("GREAT_lit");
						i--;
						break;
					case '<':
						i++;
						if(line.charAt(i) == '=')
							System.out.println("LESSE_tok");
						else 
							System.out.println("LESS_lit");
						i--;
						break;
//					default:
//						System.out.println("undefined character");
//						break;
					}
				}
			}
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}