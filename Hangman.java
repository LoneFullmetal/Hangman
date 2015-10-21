import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Hangman extends GraphicsProgram {
	public void init() {
		loading = true;
		/*frame = new JFrame();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		letters.add('A');
		letters.add('B');
		letters.add('C');
		letters.add('D');
		letters.add('E');
		letters.add('F');
		letters.add('G');
		letters.add('H');
		letters.add('I');
		letters.add('J');
		letters.add('K');
		letters.add('L');
		letters.add('M');
		letters.add('N');
		letters.add('O');
		letters.add('P');
		letters.add('Q');
		letters.add('R');
		letters.add('S');
		letters.add('T');
		letters.add('U');
		letters.add('V');
		letters.add('W');
		letters.add('X');
		letters.add('Y');
		letters.add('Z');
		for(int i = 0; i < letters.size(); ++i) {
			letterCheck.add(false);
		}
		addKeyListeners();
		loading = false;
		this.requestFocus();
	}
	public void run() {
		this.resize(800,600);
		reset();
		
		while(opportunities > 0) {
			drawBody();
			if (checkforWinner == word.length()) {
				GLabel YouWin = new GLabel("You Win", 400,300);
				YouWin.setFont(new Font("Serif", Font.BOLD, 70));
				YouWin.setColor(Color.blue);
				add(YouWin);
				wins++;
				remove(w);
				w = new GLabel("Wins: " + wins, 100, 600);
				w.setFont(new Font("Courier", Font.BOLD, 20));
				add(w);
				for(int i = 0; i < 100000; i++) {
					for(int j = 0; j < 100000; j++) {
						
					}
					YouWin.setColor(rgen.nextColor());
					add(YouWin);
				}
				run();
			}
		}
	}
	public void reset() {
		removeAll();
		loading = true;
		w = new GLabel("Wins: " + wins, 100, 600);
		w.setFont(new Font("Courier", Font.BOLD, 20));
		add(w);
		l = new GLabel("Loses: " + loses, 600, 600);
		l.setFont(new Font("Courier", Font.BOLD, 20));
		add(l);
		GLabel loadin = new GLabel("Loading...", 400,300);
		loadin.setFont(new Font("Serif", Font.BOLD, 70));
		loadin.setColor(Color.green);
		add(loadin);
		for(int i = 0; i < letterCheck.size(); ++i) {
			letterCheck.set(i, false);
		}
		checkforWinner = 0;
		guessX = 450;
		BPCheck = 0;
		opportunities = 6;
		answerCheck = false;
		word = getWord();
		elucidate();
		GRect post = new GRect(100,100,10,SCAFFOLD_HEIGHT);
		post.setFilled(true);
		add(post);
		
		GRect post2 = new GRect(100,100,BEAM_LENGTH + 13,10);
		post2.setFilled(true);
		add(post2);
		
		GRect post3 = new GRect(100 + BEAM_LENGTH + 13,100,10,ROPE_LENGTH);
		post3.setFilled(true);
		add(post3);
		remove(loadin);
		loading = false;
		
	}
	
	public void elucidate() {
		int wordx = 100;
		for(int i =0; i < word.length(); i++) {
			GLine hyphen = new GLine(wordx,500,wordx + 40,500);
			wordx += 50;
			add(hyphen);
		}
	}
	public void drawBody() {
		loading = true;
		if (BPCheck == 0 && answerCheck) {
			GOval head = new GOval(100 + BEAM_LENGTH,100 + ROPE_LENGTH, HEAD_RADIUS,HEAD_RADIUS);
			add(head);
			BPCheck++;
			answerCheck = false;
		}
		else if (BPCheck == 1 && answerCheck) {
			GLine body = new GLine(100 + BEAM_LENGTH + HEAD_RADIUS/2,100   + ROPE_LENGTH + HEAD_RADIUS, 100 + BEAM_LENGTH + HEAD_RADIUS/2 , 100 + ROPE_LENGTH + BODY_LENGTH);
			add(body);
			BPCheck++;
			answerCheck = false;
		}
		else if (BPCheck == 2 && answerCheck) {
			GLine arm1 = new GLine(100 + BEAM_LENGTH + HEAD_RADIUS/2, 100   + ROPE_LENGTH + 50, 100 + BEAM_LENGTH + HEAD_RADIUS/2 - 30,  100   + ROPE_LENGTH + 50 + 50);
			add(arm1);
			BPCheck++;
			answerCheck = false;
		}
		else if (BPCheck == 3 && answerCheck) {
			GLine arm2 = new GLine(100 + BEAM_LENGTH + HEAD_RADIUS/2, 100   + ROPE_LENGTH + 50, 100 + BEAM_LENGTH + HEAD_RADIUS/2 + 30,  100   + ROPE_LENGTH + 50 + 50);
			add(arm2);
			BPCheck++;
			answerCheck = false;
		}
		else if (BPCheck == 4 && answerCheck) {
			GLine leg1 = new GLine(100 + BEAM_LENGTH + HEAD_RADIUS/2, 100   + ROPE_LENGTH + 177 - HEAD_RADIUS, 100 + BEAM_LENGTH + HEAD_RADIUS/2 - 30,  100   + ROPE_LENGTH + 200);
			add(leg1);
			BPCheck++;
			answerCheck = false;
		}
		else if (BPCheck == 5 && answerCheck) {
			GLine leg2 = new GLine(100 + BEAM_LENGTH + HEAD_RADIUS/2, 100   + ROPE_LENGTH + 177 - HEAD_RADIUS, 100 + BEAM_LENGTH + HEAD_RADIUS/2 + 30,  100   + ROPE_LENGTH + 200);
			add(leg2);
			answerCheck = false;
			GLabel YouLose = new GLabel("You Lose", 400,300);
			YouLose.setFont(new Font("Serif", Font.BOLD, 70));
			YouLose.setColor(Color.red);
			add(YouLose);
			GLabel wordL = new GLabel(word, 100,550);
			wordL.setFont(new Font("Courier", Font.BOLD, 69));
			wordL.setColor(Color.red);
			add(wordL);
			loses++;
			remove(l);
			l = new GLabel("Loses: " + loses, 500, 600);
			l.setFont(new Font("Courier", Font.BOLD, 20));
			add(l);
			for(int i = 0; i < 100000; i++) {
				for(int j = 0; j < 100000; j++) {
					
				}
			}
			run();
		}
		loading = false;
	}
    
    public void keyPressed(KeyEvent e) {
    	if(!loading) {
    		loading = true;
    		switch (e.getKeyCode()) {
	  		  case KeyEvent.VK_A: key = 'A'; break;
	  		  case KeyEvent.VK_B: key = 'B'; break;
	  		  case KeyEvent.VK_C: key = 'C'; break;
	  		  case KeyEvent.VK_D: key = 'D'; break;
	  		  case KeyEvent.VK_E: key = 'E'; break;
	  		  case KeyEvent.VK_F: key = 'F'; break;
	  		  case KeyEvent.VK_G: key = 'G'; break;
	  		  case KeyEvent.VK_H: key = 'H'; break;
	  		  case KeyEvent.VK_I: key = 'I'; break;
	  		  case KeyEvent.VK_J: key = 'J'; break;
	  		  case KeyEvent.VK_K: key = 'K'; break;
	  		  case KeyEvent.VK_L: key = 'L'; break;
	  		  case KeyEvent.VK_M: key = 'M'; break;
	  		  case KeyEvent.VK_N: key = 'N'; break;
	  		  case KeyEvent.VK_O: key = 'O'; break;
	  		  case KeyEvent.VK_P: key = 'P'; break;
	  		  case KeyEvent.VK_Q: key = 'Q'; break;
	  		  case KeyEvent.VK_R: key = 'R'; break;
	  		  case KeyEvent.VK_S: key = 'S'; break;
	  		  case KeyEvent.VK_T: key = 'T'; break;
	  		  case KeyEvent.VK_U: key = 'U'; break;
	  		  case KeyEvent.VK_V: key = 'V'; break;
	  		  case KeyEvent.VK_W: key = 'W'; break;
	  		  case KeyEvent.VK_X: key = 'X'; break;
	  		  case KeyEvent.VK_Y: key = 'Y'; break;
	  		  case KeyEvent.VK_Z: key = 'Z'; break;
	  		}
	      	noteIncorrectGuess();
	      	loading = false;
    	}
	}
	
   	public String getWord() {
		try {
			rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
	        int count = 0;
	        String readChars = "";
			while ((readChars = rd.readLine()) != null) {
				    count++;
				}
	        int lineNum = count;
	        int randWord = rgen.nextInt(0, lineNum);
	        rd.close();
	        BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
	        for(int i = 0; i <= randWord; ++i) {
				String w = rd.readLine();
				if(i == randWord) {
					rd.close();
					return w;
				}
				
	        }
			rd.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		return "FAIL";
	};
   	
	
	public void noteIncorrectGuess() {
		g = false;
		boolean check = false;
		int place = 0;
		c = Character.toString(key);
		for(int i = 0; i < letters.size(); ++i) {
			if(key == letters.get(i)) {
				check = letterCheck.get(i);
				place = i;
			}
		}
		for(int i = 0; i < word.length(); ++i) {
			if(word.charAt(i) == key && !check) {
				g = true;
				GLabel Input = new GLabel(c,110 + (i * 50),498);
				Input.setFont(new Font("Courier", Font.BOLD, 30));
				add(Input);
				checkforWinner++;
			} 
		}
    	if(!g && !check) {
			GLabel input = new GLabel(c,guessX,100);
			input.setFont(new Font("Courier", Font.BOLD, 30));
			add(input);
			guessX += 50;
			answerCheck = true;
			opportunities--;
		}
    	letterCheck.set(place, true);
		

		/* You fill this in */
	}

/* Constants for the simple version of the picture (in pixels) */
	//private JFrame frame;
	private JApplet t;
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 75;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private int BPCheck = 0;
	private boolean answerCheck = false;
    private char key;
    private int opportunities = 6;
    private String word;
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private boolean g = false;
    private int guessX;
    private String c;
    private int checkforWinner;
    private List<Boolean> letterCheck = new ArrayList<Boolean>();
    private List<Character> letters = new ArrayList<Character>();
    private BufferedReader rd;
    private boolean loading = true;
    private int wins = 0;
    private int loses = 0;
    private GLabel w;
    private GLabel l;
}