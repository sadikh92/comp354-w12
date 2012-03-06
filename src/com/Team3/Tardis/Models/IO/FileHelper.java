package com.Team3.Tardis.Models.IO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.Team3.Tardis.Util.Logger;

public class FileHelper {

	public static boolean write(String path, String content){

		Logger.log(FileHelper.class.getName(), "write() - START ");
		boolean valid = false;

		try {
			
			FileWriter fileWriter = new FileWriter(path);
			PrintWriter writer = new PrintWriter(fileWriter);
			
			writer.println(content);
			
			writer.close();
			fileWriter.close();
			valid = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Logger.log(FileHelper.class.getName(), "write() - END ");
		return valid;
	}
}
