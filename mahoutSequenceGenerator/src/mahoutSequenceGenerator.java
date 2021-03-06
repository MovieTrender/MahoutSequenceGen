import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class mahoutSequenceGenerator{

    public static void main(String args[]) throws IOException{
    	    	
		//Check that the use of the process is the correct
		if (args.length!=3){
			System.out.println("Usage of process: mahoutSequenceGenerator.jar <<Class>> <<InputFolder>> <<FileToGenerate>>");
		}
		else
		{
			//Extract the arguments of the process
			String classToAssign = args[0];
			String inputFolder = args[1];
			String fileToGenerate =args[2];
		
			//Prepare the variables needed for the read of all the files in the folder
			String currentFile="";
			FileReader fileReader;
			BufferedReader  bufferedReader;
			String content="";
			
			//Show args read
			System.out.println("Class to assign: "+classToAssign);
			System.out.println("Input folder: "+ inputFolder);
			System.out.println("File to generate: "+ fileToGenerate);
			
			//Open the folder and take all the files inside
			File folder = new File(inputFolder);
			File [] listofFiles= folder.listFiles();
			
			//Open configuration from cluster
			Configuration conf = new Configuration();
			FileSystem fs = FileSystem.get(conf);
		
			//Key and value to be written in the file
			Text key = new Text();
			Text value = new Text();
			
			//Open the sequence file
			Path outputPath = new Path(fileToGenerate);
			@SuppressWarnings("deprecation")
			SequenceFile.Writer fileOutput = new SequenceFile.Writer(fs, conf, outputPath, org.apache.hadoop.io.Text.class, org.apache.hadoop.io.Text.class);
			
		
			
			
			//Iterate for all the files of the input folder\
			for (File file: listofFiles){
				if (file.isFile()){
					
					//Read the file 
					currentFile=file.getName();
					fileReader =new FileReader(folder.getAbsolutePath()+"/"+currentFile);
					bufferedReader = new BufferedReader(fileReader);
					
					content="";
					
					while (bufferedReader.ready()){
						content+=bufferedReader.readLine();
					}
					
					
					//Write the content of the file to the sequence file
					key =new Text("/"+classToAssign+"/"+currentFile);
					value=new Text(content);
					//Append the key value to the file
					fileOutput.append(key,value);
				}
			}
		
		
			//Close the sequence file
			fileOutput.close();		


		}
    }

}
