import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
public class WordPuzzle {
	private static MyHashTable<String> dictionaryHashTable;
	private static char[][] wordGrid;
	private static int maxWordLength = 0;
	private static int wordCount = 0;
	  
	/**
     * Method to set the dictionaryHashTable.
     * @param dictHashTable the table to be set.
     */
	public static void setDictionaryHashTable(MyHashTable<String> dictHashTable) {
		dictionaryHashTable = dictHashTable;
	}
	
	  /**
     * Method to set the word grid.
     * @param wordGrid the word grid to be set.
     */
	public static void setWordGrid(char[][] grid) {
		wordGrid = grid;
	}
	
	/**
     * Method to set the maxWordLength.
     * @param maxLength the max length of a word in dictionary.
     */
	public static void setMaxWordLength(int maxLength) {
		maxWordLength = maxLength;
	}

	/**
     * External Method to set the check whether the word in grid is present or not.
     */
	public static void checkWordsInDictionaryWithoutPrefix() {
		for(int row=0; row<wordGrid.length; row++)
			for(int column=0; column<wordGrid[row].length; column++)
				for(int orientation=0; orientation<8; orientation++)
					checkWordsInDictionaryWithoutPrefix(row, column, orientation);
	}
	
	 /**
     * External Method to set the check whether the word in grid is present in dictionary or not.
     * @param x the x axis.
     * @param y the y axis.
     * @param orientation the orientation for the word to be checked.
     */
	private static void checkWordsInDictionaryWithoutPrefix(int x, int y, int orientation) {
		String word = "";
		switch(orientation) {
			case 0:		{											//right
				int i=y;	
				while(i < wordGrid[x].length) {
					word = word + wordGrid[x][i];
					if(dictionaryHashTable.containsCompleteWord(word.toLowerCase())) {
						System.out.println(word + " --- Orientation: right --- Position: (" + x + "," + y + ")");
						wordCount++;
					}
					i++;
				}
			}
			break;
			case 1:		{											//left
				int i=y;
				while(i >= 0) {
					word = word + wordGrid[x][i];
					if(word.length() !=1 && dictionaryHashTable.containsCompleteWord(word.toLowerCase())) {
						System.out.println(word + " --- Orientation: left --- Position: (" + x + "," + y + ")");
						wordCount++;
					}
					i--;
				}
			}
			break;
			case 2:		{											//up
				int i=x;	
				while(i >= 0) {
					word = word + wordGrid[i][y];
					if(word.length() !=1 && dictionaryHashTable.containsCompleteWord(word.toLowerCase())) {
						System.out.println(word + " --- Orientation: up --- Position: (" + x + "," + y + ")");
						wordCount++;
					}
					i--;
				}
			}
			break;
			case 3:		{											//down
				int i=x;	
				while(i < wordGrid.length) {
					word = word + wordGrid[i][y];
					if(word.length() !=1 && dictionaryHashTable.containsCompleteWord(word.toLowerCase())) {
						System.out.println(word + " --- Orientation: down --- Position: (" + x + "," + y + ")");
						wordCount++;
					}
					i++;
				}
			}
			break;
			case 4:		{											//up-right
				int i=x;
				int j=y;
				while(i >= 0 && j < wordGrid[x].length) {
					word = word + wordGrid[i][j];
					if(word.length() !=1 && dictionaryHashTable.containsCompleteWord(word.toLowerCase())) {
						System.out.println(word + " --- Orientation: up-right --- Position: (" + x + "," + y + ")");
						wordCount++;
					}
					i--;
					j++;
				}
			}
			break;
			case 5:		{											//up-left
				int i=x;
				int j=y;
				while(i >= 0 && j >= 0) {
					word = word + wordGrid[i][j];
					if(word.length() !=1 && dictionaryHashTable.containsCompleteWord(word.toLowerCase())) {
						System.out.println(word + " --- Orientation: up-left --- Position: (" + x + "," + y + ")");
						wordCount++;
					}
					i--;
					j--;
				}
			}
			break;
			case 6:		{											//down-right
				int i=x;
				int j=y;
				while(i < wordGrid.length && j < wordGrid[x].length) {
					word = word + wordGrid[i][j];
					if(word.length() !=1 && dictionaryHashTable.containsCompleteWord(word.toLowerCase())) {
						System.out.println(word + " --- Orientation: down-right --- Position: (" + x + "," + y + ")");
						wordCount++;
					}
					i++;
					j++;
				}
			}
			break;
			case 7:		{											//down-left
				int i=x;
				int j=y;
				while(i < wordGrid.length && j >= 0) {
					word = word + wordGrid[i][j];
					if(word.length() !=1 && dictionaryHashTable.containsCompleteWord(word.toLowerCase())) {
						System.out.println(word + " --- Orientation: down-right --- Position: (" + x + "," + y + ")");
						wordCount++;
					}
					i++;
					j--;
				}
			}
			break;
		}
	}
	
	/**
     * External Method to set the check whether the word in grid is present or not.
     */
	public static void checkWordsInDictionaryWithPrefix() {
		for(int row=0; row<wordGrid.length; row++)
			for(int column=0; column<wordGrid[row].length; column++)
				for(int orientation=0; orientation<8; orientation++)
					checkWordsInDictionaryWithPrefix(row, column, orientation);
	}
	
	 /**
     * External Method to set the check whether the word in grid is present in dictionary or not.
     * @param x the x axis.
     * @param y the y axis.
     * @param orientation the orientation for the word to be checked.
     */
	private static void checkWordsInDictionaryWithPrefix(int x, int y, int orientation) {
		String word = "";
		switch(orientation) {
			case 0:		{											//right
				int i=y;	
				while(i < wordGrid[x].length && word.length()< maxWordLength) {
					word = word + wordGrid[x][i];
					if(!dictionaryHashTable.contains(word.toLowerCase()))
						break;
					if(dictionaryHashTable.containsCompleteWord(word.toLowerCase())) {
						System.out.println(word + " --- Orientation: right --- Position: (" + x + "," + y + ")");
						wordCount++;
					}
					i++;
				}
			}
			break;
			case 1:		{											//left
				int i=y;
				while(i >= 0 && word.length()<= maxWordLength) {
					word = word + wordGrid[x][i];
					if(!dictionaryHashTable.contains(word.toLowerCase()))
						break;
					if(word.length() !=1 && dictionaryHashTable.containsCompleteWord(word.toLowerCase())) {
						System.out.println(word + " --- Orientation: left --- Position: (" + x + "," + y + ")");
						wordCount++;
					}
					i--;
				}
			}
			break;
			case 2:		{											//up
				int i=x;	
				while(i >= 0 && word.length()<= maxWordLength) {
					word = word + wordGrid[i][y];
					if(!dictionaryHashTable.contains(word.toLowerCase()))
						break;
					if(word.length() !=1 && dictionaryHashTable.containsCompleteWord(word.toLowerCase())) {
						System.out.println(word + " --- Orientation: up --- Position: (" + x + "," + y + ")");
						wordCount++;
					}
					i--;
				}
			}
			break;
			case 3:		{											//down
				int i=x;	
				while(i < wordGrid.length && word.length()<= maxWordLength) {
					word = word + wordGrid[i][y];
					if(!dictionaryHashTable.contains(word.toLowerCase()))
						break;
					if(word.length() !=1 && dictionaryHashTable.containsCompleteWord(word.toLowerCase())) {
						System.out.println(word + " --- Orientation: down --- Position: (" + x + "," + y + ")");
						wordCount++;
					}
					i++;
				}
			}
			break;
			case 4:		{											//up-right
				int i=x;
				int j=y;
				while(i >= 0 && j < wordGrid[x].length && word.length()<= maxWordLength) {
					word = word + wordGrid[i][j];
					if(!dictionaryHashTable.contains(word.toLowerCase()))
						break;
					if(word.length() !=1 && dictionaryHashTable.containsCompleteWord(word.toLowerCase())) {
						System.out.println(word + " --- Orientation: up-right --- Position: (" + x + "," + y + ")");
						wordCount++;
					}
					i--;
					j++;
				}
			}
			break;
			case 5:		{											//up-left
				int i=x;
				int j=y;
				while(i >= 0 && j >= 0 && word.length()<= maxWordLength) {
					word = word + wordGrid[i][j];
					if(!dictionaryHashTable.contains(word.toLowerCase()))
						break;
					if(word.length() !=1 && dictionaryHashTable.containsCompleteWord(word.toLowerCase())) {
						System.out.println(word + " --- Orientation: up-left --- Position: (" + x + "," + y + ")");
						wordCount++;
					}
					i--;
					j--;
				}
			}
			break;
			case 6:		{											//down-right
				int i=x;
				int j=y;
				while(i < wordGrid.length && j < wordGrid[x].length && word.length()<= maxWordLength) {
					word = word + wordGrid[i][j];
					if(!dictionaryHashTable.contains(word.toLowerCase()))
						break;
					if(word.length() !=1 && dictionaryHashTable.containsCompleteWord(word.toLowerCase())) {
						System.out.println(word + " --- Orientation: down-right --- Position: (" + x + "," + y + ")");
						wordCount++;
					}
					i++;
					j++;
				}
			}
			break;
			case 7:		{											//down-left
				int i=x;
				int j=y;
				while(i < wordGrid.length && j >= 0 && word.length()<= maxWordLength) {
					word = word + wordGrid[i][j];
					if(!dictionaryHashTable.contains(word.toLowerCase()))
						break;
					if(word.length() !=1 && dictionaryHashTable.containsCompleteWord(word.toLowerCase())) {
						System.out.println(word + " --- Orientation: down-right --- Position: (" + x + "," + y + ")");
						wordCount++;
					}
					i++;
					j--;
				}
			}
			break;
		}
	}
	
    @SuppressWarnings("resource")
	public static void main( String [ ] args ) throws IOException
    {
      System.out.println("Enter the number of rows: ");	
   	  Scanner rowsIn = new Scanner(System.in);
      int rows = rowsIn.nextInt();
      System.out.println("Enter the number of columns: ");
      Scanner columnsIn = new Scanner(System.in);
      int columns = columnsIn.nextInt();
      if(rows < 0 || columns < 0)
    	  System.err.println("These values cannot be number of rows (or) columns");
      else {
   	  char[][] wordGrid = new char[rows][columns];
   	  System.out.println("The word grid is: ");
   	  for(int i = 0;i<rows; i++) {
   		  for(int j=0;j<columns;j++) {
   			 Random rnd = new Random();
   			wordGrid[i][j] = (char) (rnd.nextInt(26) + 'a');
   			System.out.print(wordGrid[i][j] + " ");
   		  }
   		  System.out.println();
   	  }
   	  WordPuzzle.setWordGrid(wordGrid);
/////////////////////////////////////////////////////////////////////////////////WORD PUZZLE WITHOUT PREFIX CHECK
   	  System.out.println("------------------WITHOUT PREFIX--------------------");
   	  MyHashTable<String> dictionaryHashTable = new MyHashTable<>( );
   	  File file = new File("E:\\5343.001 - Data Structures\\Assignments\\dictionary.txt"); 
   	  BufferedReader br = new BufferedReader(new FileReader(file)); 
   	  String word;
   	  int maxWordLength = 0;
   	  while ((word = br.readLine()) != null) {
   	    		dictionaryHashTable.insertValue(word, true);
   		if(word.length() > maxWordLength)
   			maxWordLength = word.length();
   	  }
//    dictionaryHashTable.printHashTable();
   	  long startTime = System.currentTimeMillis( );
   	  WordPuzzle.setDictionaryHashTable(dictionaryHashTable);
   	  WordPuzzle.checkWordsInDictionaryWithoutPrefix();
   	  long endTime = System.currentTimeMillis( );
   	  int wordCountWithoutPrefix =  wordCount;	
   	  wordCount = 0;
////////////////////////////////////////////////////////////////////////////////WORD PUZZLE WITH PREFIX(ENHANCED ALGORITHM) CHECK
   	  System.out.println("------------------WITH PREFIX(ENHANCED ALGORITHM)--------------------");
 	  MyHashTable<String> dictionaryHashTableWithPrefix = new MyHashTable<>( );
 	  File fileWithPrefix = new File("E:\\5343.001 - Data Structures\\Assignments\\dictionary.txt"); 
 	  BufferedReader brWithPrefix = new BufferedReader(new FileReader(fileWithPrefix)); 
 	  String wordWithPrefix;
 	  int maxWordLengthWithPrefix = 0;
 	  while ((wordWithPrefix = brWithPrefix.readLine()) != null) {
 		String hashTableValueWithPrefix = "";
 	    for(int i = 0; i < wordWithPrefix.length(); i++) {
 	    	hashTableValueWithPrefix = hashTableValueWithPrefix + wordWithPrefix.charAt(i);
 	    	if(hashTableValueWithPrefix.length() == wordWithPrefix.length())
 	    		dictionaryHashTableWithPrefix.insertValue(hashTableValueWithPrefix, true);
 	    	else
 	    		dictionaryHashTableWithPrefix.insertValue(hashTableValueWithPrefix, false);
 	    }
 		if(wordWithPrefix.length() > maxWordLengthWithPrefix)
 			maxWordLengthWithPrefix = wordWithPrefix.length();
 	  }
 	  WordPuzzle.setMaxWordLength(maxWordLengthWithPrefix);
// 	  dictionaryHashTableWithPrefix.printHashTable();
 	  long startTimeWithPrefix = System.currentTimeMillis( );
 	  WordPuzzle.setDictionaryHashTable(dictionaryHashTableWithPrefix);
 	  WordPuzzle.checkWordsInDictionaryWithPrefix();
 	  long endTimeWithPrefix = System.currentTimeMillis( );
 	  System.out.println();
 	  System.out.println( "---------NO. OF WORDS & TIME ELAPSED--------" );
   	  System.out.println( "---------------WITHOUT PREFIX---------------" );
 	  System.out.println( "Total number of words: " + wordCountWithoutPrefix );
 	  System.out.println( "Elapsed time: " + (endTime - startTime) );   
   	  System.out.println( "------------------WITH PREFIX(ENHANCED ALGORITHM)--------------------");
      System.out.println( "Total number of words: " + wordCount );
 	  System.out.println( "Elapsed time: " + (endTimeWithPrefix - startTimeWithPrefix) );
      }
    }
}
