import java.io.*;
import java.util.*;

// This program reads a text file, input.txt. 
// For each character, it produces a hexadecimal number which is the hamming 
// code for 8-bit characters using even parity. These hex numbers are written to hamming.txt
// Copyright (c) Paul Koester, Dallas County Community College, 2017


public	class Hamming
	{
	private char letter;      // The Ascii character with no parity bits
	private int[] bits = new int[12];
	private int code;         // The integer value of the character with the parity bits added
	
	public Hamming(char let) { letter = let; encode(); }
	public Hamming(int c) { code = c; decode(); }
	public int getCode() { return code; }
	public char getLetter() { return letter; }

	private void encode() {
		int value = letter;
		
		// Set value bits
		for (int i = 0; i < 12; i++) {
			if (i != 0 && i != 1 && i != 3 && i != 7) {
				bits[i] = value % 2;
				value /= 2;
			}
		}
		
		// Set parity bits
		// also using bitwise
		bits[0] = bits[2] ^ bits[4] ^ bits[6] ^ bits[8] ^ bits[10]; // ^ is XOR in Java
		bits[1] = bits[2] ^ bits[5] ^ bits[6] ^ bits[9] ^ bits[10];
		bits[3] = bits[4] ^ bits[5] ^ bits[6] ^ bits[11];
		bits[7] = bits[8] ^ bits[9] ^ bits[10] ^ bits[11];

		// compute integer code
		code = 0;
		for (int i = 11; i >= 0; i--) {
			code *= 2;
			code += bits[i];
		}
	}

	private void decode() {
		int error = 0;
		int value = code;

		// Set the bit array
		for (int i = 0; i < 12; i++) {
			bits[i] = value % 2;
			value /= 2;
		}

		// Check for transmission errors
		//this is bitwise just like in your CSCE 3600 class we are learning now (XOR)
		if (bits[0] != (bits[2] ^ bits[4] ^ bits[6] ^ bits[8] ^ bits[10])) error += 1; 
		if (bits[1] != (bits[2] ^ bits[5] ^ bits[6] ^ bits[9] ^ bits[10])) error += 2;
		if (bits[3] != (bits[4] ^ bits[5] ^ bits[6] ^ bits[11])) error += 4;
		if (bits[7] != (bits[8] ^ bits[9] ^ bits[10] ^ bits[11])) error += 8;
		
		// Correct Error
		if (error != 0)
			bits[error - 1] ^= 1;

		// Extract character
		letter = 0;
		for (int i = 11; i >= 0; i--) {
			if (i != 0 && i != 1 && i != 3 && i != 7) {
				letter *= 2;
				letter += bits[i];
			}
		}
		// Display where error detected
		if (error != 0)
			System.out.println("Error in bit " + (error - 1) + " corrected in character " + letter);
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner  inFile = new Scanner( new File("hamming.txt"));
		PrintStream outFile = new PrintStream(new File("output.txt"));
		String line;
		int code;
			
		System.out.println("File hamming.txt opened");	
		while (inFile.hasNextInt(16)) {
				code = inFile.nextInt(16);
				// Decode the Hex code to get a character
				
				// Create a Hamming object
				Hamming ham = new Hamming(code);
					
				// Display the decoded character
				outFile.print(ham.getLetter()); 
			
			}
		inFile.close();
		System.out.println("File output.txt closed");
	}
}
