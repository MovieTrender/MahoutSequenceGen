import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

public class mahoutSequenceGenerator{

    public static void main(String args[]) throws IOException{

		//Check that the use of the process is the correct
		if (args.length!=2){
			System.out.println("Usage of process: mahoutSequenceGenerator.jar <<Class>> <<InputFolder>> <<FileToGenerate>>");
		}
		else
		{
			//Extract the args of the process
			String classToAssing = args[0];
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
			
			Path outputPath = new Path(args[2]);
			SequenceFile.Writer fileOutput = new SequenceFile.Writer(fs, conf, outputPath, key.getClass(), value.getClass());
			
			//Key and value to be written in the file
			Text key;
			Text value;
			
			
			//Iterate for all the files of the input folder\
			for (File file: listofFiles){
				if (file.isFile()){
					
					//Read the file 
					currentFile=file.getName();
					fileReader =new FileReader(folder.getAbsolutePath()+"\\"+currentFile);
					bufferedReader = new BufferedReader(fileReader);
					
					content="";
					
					while (bufferedReader.ready()){
						content+=bufferedReader.readline();
					}
					
					
					//Write the content of the file to the sequence file
					key =new Text(classAssign);
					value=new Text(content);
					
					fileOutput.append(key,value);
				}
			}
		
		
			//Close the sequence file
			fileOutput.close();		


		}
    }

}
