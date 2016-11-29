import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Disassembler {

	public static int breakFlag = 0;
	public static int memoryAddress = 596;
	BufferedWriter writer;
	
	// Main Function
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		Disassembler input = new Disassembler();
		input.fileInput(args[0],args[1]);	
	}
	
	// Reading the input file
	public void fileInput(String file, String fileOutput) throws FileNotFoundException, IOException{
		
		String opCode = null;
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOutput),"UTF-8"));
		
		// To fetch binary text data from binary (.bin) file -- START
		Path p = FileSystems.getDefault().getPath("", file);
        byte [] fileData = Files.readAllBytes(p);
        String data = toBinary(fileData);
        List<String> dataStrings = new ArrayList<String>();
        int index = 0;
        while (index < data.length()) {
        	dataStrings.add(data.substring(index, Math.min(index + 32,data.length())));
            index += 32;
        }
        
        // To fetch binary text data from binary (.bin) file -- END
        
		for(String dataInput : dataStrings)
		{
			opCode = dataInput.substring(0, 6);
			instruction(opCode,dataInput);
		}

		writer.close();
	}
	
	// Returns Binary from ByteArray
	public static String toBinary( byte[] bytes )
	{
	    StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
	    for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
	        sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
	    return sb.toString();
	}
	
	// Returns the instruction based on opcode
	public String instruction(String opcode, String line) throws IOException{
		String instruction= null;
		String offset;
		int offset1;
		int rs;
		int rt;
		int rd;
		int sa;
		int immediate_value;
		 
		if(breakFlag==1){
			memoryAddress+=4;
			String temp= ""+signedBinary(line.toString());
			writer.write(line+" "+memoryAddress+" "+temp);
			writer.newLine();
		}
		else
		{
			if(opcode.equalsIgnoreCase("000000"))
			{
				opcode = line.substring(26,32);
				switch(opcode)
				{
				case "100000": 
					instruction = "ADD";
					memoryAddress+=4;
					rs=Integer.parseInt(line.substring(6,11),2);
					rt= Integer.parseInt(line.substring(11,16),2);
					rd= Integer.parseInt(line.substring(16,21),2);
					for(int i = 0; i<line.length(); i++)
					{
						if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
							writer.write(" " + line.charAt(i));
						else
							writer.write(line.charAt(i));
					}
					writer.write(" "+memoryAddress+" "+instruction+" R"+rd+", R"+rs+", R"+rt);
					writer.newLine();
					break;
				case "100001": 
					instruction = "ADDU";
					memoryAddress+=4;
					rs=Integer.parseInt(line.substring(6,11),2);
					rt= Integer.parseInt(line.substring(11,16),2);
					rd= Integer.parseInt(line.substring(16,21),2);
					for(int i = 0; i<line.length(); i++)
					{
						if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
							writer.write(" " + line.charAt(i));
						else
							writer.write(line.charAt(i));
					}
					writer.write(" "+memoryAddress+" "+instruction+" R"+rd+", R"+rs+", R"+rt);
					writer.newLine();
					break;
				case "100010": 
					instruction = "SUB";
					memoryAddress+=4;
					rs=Integer.parseInt(line.substring(6,11),2);
					rt= Integer.parseInt(line.substring(11,16),2);
					rd= Integer.parseInt(line.substring(16,21),2);
					for(int i = 0; i<line.length(); i++)
					{
						if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
							writer.write(" " + line.charAt(i));
						else
							writer.write(line.charAt(i));
					}
					writer.write(" "+memoryAddress+" "+instruction+" R"+rd+", R"+rs+", R"+rt);
					writer.newLine();
					break;
				case "100011": 
					instruction = "SUBU";
					memoryAddress+=4;
					rs=Integer.parseInt(line.substring(6,11),2);
					rt= Integer.parseInt(line.substring(11,16),2);
					rd= Integer.parseInt(line.substring(16,21),2);
					for(int i = 0; i<line.length(); i++)
					{
						if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
							writer.write(" " + line.charAt(i));
						else
							writer.write(line.charAt(i));
					}
					writer.write(" "+memoryAddress+" "+instruction+" R"+rd+", R"+rs+", R"+rt);
					writer.newLine();
					break;
				case "101010": 
					instruction = "SLT";
					memoryAddress+=4;
					rs=Integer.parseInt(line.substring(6,11),2);
					rt= Integer.parseInt(line.substring(11,16),2);
					rd= Integer.parseInt(line.substring(16,21),2);
					for(int i = 0; i<line.length(); i++)
					{
						if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
							writer.write(" " + line.charAt(i));
						else
							writer.write(line.charAt(i));
					}
					writer.write(" "+memoryAddress+" "+instruction+" R"+rd+", R"+rs+", R"+rt);
					writer.newLine();
					break;
				case "101011": 
					instruction = "SLTU";
					memoryAddress+=4;
					rs=Integer.parseInt(line.substring(6,11),2);
					rt= Integer.parseInt(line.substring(11,16),2);
					rd= Integer.parseInt(line.substring(16,21),2);
					for(int i = 0; i<line.length(); i++)
					{
						if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
							writer.write(" " + line.charAt(i));
						else
							writer.write(line.charAt(i));
					}
					writer.write(" "+memoryAddress+" "+instruction+" R"+rd+", R"+rs+", R"+rt);
					writer.newLine();
					break;
				case "000010": 
					instruction = "SRL";
					memoryAddress+=4;
					rt= Integer.parseInt(line.substring(11,16),2);
					rd= Integer.parseInt(line.substring(16,21),2);
					sa=Integer.parseInt(line.substring(21,26),2);
					for(int i = 0; i<line.length(); i++)
					{
						if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
							writer.write(" " + line.charAt(i));
						else
							writer.write(line.charAt(i));
					}
					writer.write(" "+memoryAddress+" "+instruction+" R"+rd+", R"+rt+", "+sa);
					writer.newLine();
					break;
				case "000011": 
					instruction = "SRA";
					memoryAddress+=4;
					rt= Integer.parseInt(line.substring(11,16),2);
					rd= Integer.parseInt(line.substring(16,21),2);
					sa=Integer.parseInt(line.substring(21,26),2);
					for(int i = 0; i<line.length(); i++)
					{
						if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
							writer.write(" " + line.charAt(i));
						else
							writer.write(line.charAt(i));
					}
					writer.write(" "+memoryAddress+" "+instruction+" R"+rd+", R"+rt+", "+sa);
					writer.newLine();
					break;
				case "000000": 
					
					memoryAddress+=4;
					rs=Integer.parseInt(line.substring(6,11),2);
					rt= Integer.parseInt(line.substring(11,16),2);
					rd= Integer.parseInt(line.substring(16,21),2);
					
					if(rs == 0 && rt == 0 && rd == 0)
					{
						if(breakFlag == 0)
						{
							instruction = "NOP";
							for(int i = 0; i<line.length(); i++)
							{
								if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
									writer.write(" " + line.charAt(i));
								else
									writer.write(line.charAt(i));
							}
							writer.write(" "+memoryAddress+" "+instruction);
						}
						else
							writer.write(line+ " "+memoryAddress+" 0");
					}
					else
					{
						instruction = "SLL";
						for(int i = 0; i<line.length(); i++)
						{
							if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
								writer.write(" " + line.charAt(i));
							else
								writer.write(line.charAt(i));
						}
						writer.write(" "+memoryAddress+" "+instruction+" R"+rd+", R"+rs+", R"+rt);
					}
					writer.newLine();
					break;
				case "100100": 
					instruction = "AND";
					memoryAddress+=4;
					rs=Integer.parseInt(line.substring(6,11),2);
					rt= Integer.parseInt(line.substring(11,16),2);
					rd= Integer.parseInt(line.substring(16,21),2);
					for(int i = 0; i<line.length(); i++)
					{
						if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
							writer.write(" " + line.charAt(i));
						else
							writer.write(line.charAt(i));
					}
					writer.write(" "+memoryAddress+" "+instruction+" R"+rd+", R"+rs+", R"+rt);
					writer.newLine();
					break;
				case "100101": 
					instruction = "OR";
					memoryAddress+=4;
					rs=Integer.parseInt(line.substring(6,11),2);
					rt= Integer.parseInt(line.substring(11,16),2);
					rd= Integer.parseInt(line.substring(16,21),2);
					for(int i = 0; i<line.length(); i++)
					{
						if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
							writer.write(" " + line.charAt(i));
						else
							writer.write(line.charAt(i));
					}
					writer.write(" "+memoryAddress+" "+instruction+" R"+rd+", R"+rs+", R"+rt);
					writer.newLine();
					break;
				case "100110": 
					instruction = "XOR";
					memoryAddress+=4;
					rs=Integer.parseInt(line.substring(6,11),2);
					rt= Integer.parseInt(line.substring(11,16),2);
					rd= Integer.parseInt(line.substring(16,21),2);
					for(int i = 0; i<line.length(); i++)
					{
						if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
							writer.write(" " + line.charAt(i));
						else
							writer.write(line.charAt(i));
					}
					writer.write(" "+memoryAddress+" "+instruction+" R"+rd+", R"+rs+", R"+rt);
					writer.newLine();
					break;
				case "100111": 
					instruction = "NOR";
					memoryAddress+=4;
					rs=Integer.parseInt(line.substring(6,11),2);
					rt= Integer.parseInt(line.substring(11,16),2);
					rd= Integer.parseInt(line.substring(16,21),2);
					for(int i = 0; i<line.length(); i++)
					{
						if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
							writer.write(" " + line.charAt(i));
						else
							writer.write(line.charAt(i));
					}
					writer.write(" "+memoryAddress+" "+instruction+" R"+rd+", R"+rs+", R"+rt);
					writer.newLine();
					break;
				case "001101": 
					instruction = "BREAK";
					breakFlag=1;
					memoryAddress+=4;
					for(int i = 0; i<line.length(); i++)
					{
						if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
							writer.write(" " + line.charAt(i));
						else
							writer.write(line.charAt(i));
					}
					writer.write(" "+memoryAddress+" "+instruction);
					writer.newLine();
					break;
				}
			}
			else
			{
				if(opcode.equalsIgnoreCase("000010"))
				{
					instruction = "J";
					memoryAddress+=4;
					offset = line.substring(6);
					for(int i = 0; i<line.length(); i++)
					{
						if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
							writer.write(" " + line.charAt(i));
						else
							writer.write(line.charAt(i));
					}
					writer.write(" "+memoryAddress+" "+instruction+" "+"#"+leftShiftBy2(offset));
					writer.newLine();
				}
				else
				{
					switch(opcode)
					{
					case "000100": 
						instruction = "BEQ";
						memoryAddress+=4;
						rs=Integer.parseInt(line.substring(6,11),2);
						rt= Integer.parseInt(line.substring(11,16),2);
						offset = line.substring(16);
						for(int i = 0; i<line.length(); i++)
						{
							if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
								writer.write(" " + line.charAt(i));
							else
								writer.write(line.charAt(i));
						}
						writer.write(" "+memoryAddress+" "+instruction+" R"+rs+", R"+rt+", #"+signedBinary(leftShiftBy2ReturnBin(offset)));
						writer.newLine();
						break;
					case "000101": 
						instruction = "BNE";
						memoryAddress+=4;
						rs=Integer.parseInt(line.substring(6,11),2);
						rt= Integer.parseInt(line.substring(11,16),2);
						offset = line.substring(16);
						for(int i = 0; i<line.length(); i++)
						{
							if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
								writer.write(" " + line.charAt(i));
							else
								writer.write(line.charAt(i));
						}
						writer.write(" "+memoryAddress+" "+instruction+" R"+rs+", R"+rt+", #"+signedBinary(leftShiftBy2ReturnBin(offset)));
						writer.newLine();
						break;
					case "000111": 
						instruction = "BGTZ";
						memoryAddress+=4;
						rs=Integer.parseInt(line.substring(6,11),2);
						offset = line.substring(16);
						for(int i = 0; i<line.length(); i++)
						{
							if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
								writer.write(" " + line.charAt(i));
							else
								writer.write(line.charAt(i));
						}
						writer.write(" "+memoryAddress+" "+instruction+" R"+rs+", #"+signedBinary(leftShiftBy2ReturnBin(offset)));
						writer.newLine();
						break;
					case "000110": 
						instruction = "BLEZ";
						memoryAddress+=4;
						rs=Integer.parseInt(line.substring(6,11),2);
						offset = line.substring(16);
						for(int i = 0; i<line.length(); i++)
						{
							if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
								writer.write(" " + line.charAt(i));
							else
								writer.write(line.charAt(i));
						}
						writer.write(" "+memoryAddress+" "+instruction+" R"+rs+", #"+signedBinary(leftShiftBy2ReturnBin(offset)));
						writer.newLine();
						break;
					case "101011": 
						instruction = "SW";
						memoryAddress+=4;
						rs=Integer.parseInt(line.substring(6,11),2);
						rt= Integer.parseInt(line.substring(11, 16),2);offset1 = signedBinary(line.substring(16));	
						for(int i = 0; i<line.length(); i++)
						{
							if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
								writer.write(" " + line.charAt(i));
							else
								writer.write(line.charAt(i));
						}
						writer.write(" "+memoryAddress+" "+instruction+" R"+rt+", "+offset1+"(R"+rs+")");
						writer.newLine();
						break;
					case "100011": 
						instruction = "LW";
						memoryAddress+=4;
						rs=Integer.parseInt(line.substring(6,11),2);
						rt= Integer.parseInt(line.substring(11,16),2);
						offset1 = signedBinary(line.substring(16));
						for(int i = 0; i<line.length(); i++)
						{
							if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
								writer.write(" " + line.charAt(i));
							else
								writer.write(line.charAt(i));
						}
						writer.write(" "+memoryAddress+" "+instruction+" R"+rt+", "+offset1+"(R"+rs+")");
						writer.newLine();
						break;
					case "001000": 
						instruction = "ADDI";
						memoryAddress+=4;
						rs=Integer.parseInt(line.substring(6,11),2);
						rt= Integer.parseInt(line.substring(11,16),2);
						immediate_value = signedBinary(line.substring(16));
						for(int i = 0; i<line.length(); i++)
						{
							if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
								writer.write(" " + line.charAt(i));
							else
								writer.write(line.charAt(i));
						}
						writer.write(" "+memoryAddress+" "+instruction+" R"+rt+", R"+rs+", #"+immediate_value);
						writer.newLine();
						break;
					case "001001": 
						instruction = "ADDIU";
						memoryAddress+=4;
						rs=Integer.parseInt(line.substring(6,11),2);
						rt= Integer.parseInt(line.substring(11,16),2);
						immediate_value = signedBinary(line.substring(16));
						for(int i = 0; i<line.length(); i++)
						{
							if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
								writer.write(" " + line.charAt(i));
							else
								writer.write(line.charAt(i));
						}
						writer.write(" "+memoryAddress+" "+instruction+" R"+rt+", R"+rs+", #"+immediate_value);
						writer.newLine();
						break;
					case "001010": 
						instruction = "SLTI";
						memoryAddress+=4;
						rs=Integer.parseInt(line.substring(6,11),2);
						rt= Integer.parseInt(line.substring(11,16),2);
						immediate_value = signedBinary(line.substring(16));
						for(int i = 0; i<line.length(); i++)
						{
							if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
								writer.write(" " + line.charAt(i));
							else
								writer.write(line.charAt(i));
						}
						writer.write(" "+memoryAddress+" "+instruction+" R"+rt+", R"+rs+", #"+immediate_value);
						writer.newLine();
						break;
					case "000001": 
						if(line.substring(11,16).equalsIgnoreCase("00000"))
							instruction = "BLTZ";
						else
							instruction = "BGEZ";
						
						memoryAddress+=4;
						rs=Integer.parseInt(line.substring(6,11),2);
						offset = line.substring(16);
						for(int i = 0; i<line.length(); i++)
						{
							if(i == 6 || i == 11 || i == 16 || i == 21 || i == 26)
								writer.write(" " + line.charAt(i));
							else
								writer.write(line.charAt(i));
						}
						writer.write(" "+memoryAddress+" "+instruction+" R"+rs+", #"+signedBinary(leftShiftBy2ReturnBin(offset)));
						writer.newLine();
						break;
					}
				}
			}
		}		
		return instruction;

	}

	public int leftShiftBy2(String offset){
		int res;
		if(offset==null) return 0;
		StringBuffer s = new StringBuffer(offset);
		s.append("00");
		res = Integer.parseInt(s.toString(),2);
		return res;
	}
	public String leftShiftBy2ReturnBin(String offset){
		String res=null;
		if(offset==null) return null;
		StringBuffer s = new StringBuffer(offset);
		s.append("00");
		res = s.toString();
		return res;
	}
	
	public int signedBinary(String offset){
		int offsetInt;
		if(offset==null) return 0;
		if(offset.startsWith("1")){
			StringBuffer buff = new StringBuffer(offset);
			for(int i=0;i<buff.length();i++)
			{
				switch(buff.charAt(i)) {
				case '0': buff.setCharAt(i, '1');
				; break;
				case '1': buff.setCharAt(i, '0');break;
				}
			}
			offsetInt=Integer.parseInt(buff.toString(), 2);
			offsetInt+=1;
			offsetInt=-offsetInt;
		}
		else 
			offsetInt = Integer.parseInt(offset,2);
		return offsetInt;
	}

}