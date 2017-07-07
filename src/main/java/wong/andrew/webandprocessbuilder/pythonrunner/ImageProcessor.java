package wong.andrew.webandprocessbuilder.pythonrunner;

import java.io.*;

/**
 * Created by andrewwong on 6/29/17.
 */
public class ImageProcessor {

    public static void processMp4(File mp4File){
        try{
            String mp4FileName;
            mp4FileName = mp4File.getName();

            ProcessBuilder pb = new ProcessBuilder("python3","object_movement.py", "-v","" + mp4FileName);
            Process p = pb.start();

        }catch(Exception e){System.out.println(e);}
    }
}
