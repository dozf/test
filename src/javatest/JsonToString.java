package javatest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class JsonToString {

	/**
	 * @param args
	 * @throws URISyntaxException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, URISyntaxException {


		//取回显
		String theEcho = FileUtils.readFileToString(new File(JsonToString.class.getResource("/javatest/content.txt").toURI()));
		//转成字符串
		//jsonToString(theEcho);
		
		//转义字符串
		toMyString(theEcho);
	}
	
	public static void jsonToString(String theJson){
		System.out.println("原json："+theJson);
		
		String theString = theJson.replaceAll("\"", "\\\\\"");
		//String ip = theJson.replaceAll("\\n", "\',\'");
		System.out.println("转义后的json：\n"+"var baseValue = \""+theString+"\"");
		//System.out.println(ip);
		//System.out.println("\\");
	}
	
	//linux命令转义
	public static void toMyString(String theStr){
		
		try {
			//System.out.println("原字符串："+theStr+"\n----------------");
			
			Pattern pattern = Pattern.compile("^\\s*$");//去空行
			ByteArrayInputStream bis = new ByteArrayInputStream(theStr.getBytes());
			BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(bis));
			StringBuffer sb = new StringBuffer();
			String sTempOneLine = new String("");
			while ((sTempOneLine = tBufferedReader.readLine()) != null){
				Matcher matcher = pattern.matcher(sTempOneLine);
				if(matcher.find()){
					
				}else{
					//System.out.println("一行数据："+sTempOneLine.trim());
					//自定义转义：\转义成\\,"转义成\"。
					String tempString = sTempOneLine.trim().replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\"");
					
					//每行加上" 和 \n"
					tempString = "\""+tempString+"\\n\"+";
					//System.out.println("一行数据转义后："+tempString);
					sb.append(tempString+'\n');
				}
				
			}
			String theString = sb.toString();
			theString = theString+"\"getEcho;\\n\";";
			//theString = theString.substring(0, theString.length()-2);
			System.out.println("最终转义后的字符串：\n"+theString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
