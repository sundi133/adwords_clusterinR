import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class CreateFile {

	public  int aline=0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 ReadWithScanner parser = new ReadWithScanner("C:\\Users\\sundi133\\data\\keywords.txt");
		  try {
			parser.processLineByLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class ReadWithScanner{
	

	public ReadWithScanner(String aFileName){
    	fFile = new File(aFileName);  
  	}	
  
  /** Template method that calls {@link #processLine(String)}.  */
  public final void processLineByLine() throws FileNotFoundException {
    //Note that FileReader is used, not File, since File is not Closeable
	  int aline=0;
    Scanner scanner = new Scanner(new FileReader(fFile));
    try {
      //first use a Scanner to get each line
      while ( scanner.hasNextLine() ){
        processLine( scanner.nextLine() , aline);
        aline++;
      }
    }
    finally {
      //ensure the underlying stream is always closed
      //this only has any effect if the item passed to the Scanner
      //constructor implements Closeable (which it does in this case).
      scanner.close();
    }
  }
  
  /** 
   Overridable method for processing lines in different ways.
    
   <P>This simple default implementation expects simple name-value pairs, separated by an 
   '=' sign. Examples of valid input : 
   <tt>height = 167cm</tt>
   <tt>mass =  65kg</tt>
   <tt>disposition =  "grumpy"</tt>
   <tt>this is the name = this is the value</tt>
 * @param aline 
  */
  protected void processLine(String aLine, int aline){
    //use a second Scanner to parse the content of each line 
    log(aline + aLine);
    createFile(aline,aLine);
    //no need to call scanner.close(), since the source is a String
  }
  
  private void createFile(int aline, String aLine2) {
	// TODO Auto-generated method stub

		FileOutputStream fop = null;
		File file;
		String content = aLine2;

		try {

			file = new File("C:\\Users\\sundi133\\data\\keywords1\\" + aline +".txt");
			fop = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}

// PRIVATE 
  private final File fFile;
  
  private static void log(Object aObject){
    System.out.println(String.valueOf(aObject));
  }
  
  private String quote(String aText){
    String QUOTE = "'";
    return QUOTE + aText + QUOTE;
  }
}

