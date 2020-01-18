package com.gdw.cms.common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.StringUtils;

/**
 * @author borythewide 각종 String instance의 조작에 관련된 method를 모아놓은 클래스이다. 거의 모든
 *         method가 public static으로 제공된다.
 */
public class StringUtil {

	public static String nvl(String str) {
		if ((str == null) || (str == "") || (str.equals("null")) || (str.length() == 0)) {
			return "";
		}
		else {
			return new String(str);
		}
	}

	
	public static String nvl(String str, String nullvalue) {

		if ((str == null) || (str == "") || (str.equals("null")) || (str.length() == 0)) {
			return nullvalue;
		}
		else {

			str = str.trim(); // 공백제거 한다.
			return new String(str);
		}
	}

	public static int nvl(String str, int nullvalue) {
		if ((str == null) || (str == "") || (str.equals("null")) || (str.length() == 0)) {
			return nullvalue;
		}
		else {
			return Integer.parseInt(str);
		}
	}

	public static long nvl(String str, long nullvalue) {
		if ((str == null) || (str == "") || (str.equals("null")) || (str.length() == 0)) {
			return nullvalue;
		}
		else {
			return Long.parseLong(str);
		}
	}
	
	public static int nvl(Integer val, int nullvalue) {
		if(val == null)
			return nullvalue;
		else
			return val.intValue();
	}

	/**
	 * parameter로 받은 object 의 full Package 명에서 클래스명만 얻어온다.
	 * 
	 * @param object
	 * @return
	 */
	public static String getClassNameWithoutPackage(Object object) {
		String returnValue = null, tmpClassName = null;

		tmpClassName = object.getClass().getName(); // full package name이 얻어진다.
		returnValue = tmpClassName.substring(tmpClassName.lastIndexOf(".") + 1,
				tmpClassName.length());

		return returnValue;
	}

	/**
	 * full path로 입력된 FilePath에서 파일명만 가져온다.
	 * @return
	 */
	public static String getOnlyFileName(String fullPath){
		String returnValue = null;
		int reverseSlashIndex = fullPath.lastIndexOf("\\");
		int slashIndex = fullPath.indexOf("/");
		int index = reverseSlashIndex > slashIndex ? reverseSlashIndex : slashIndex;
		
		returnValue = fullPath.substring(index+1, fullPath.length());
		return returnValue;
	}
	
	/**
	 * 14자리의 String 형식 날짜(20050401142323)을 date형식으로 return한다.
	 * @param str
	 * @return
	 */
	public static Date getDateFrom14LengthString(String str){
		Date aDate = null;
		
		if(str.length() != 14) return aDate;
		try{
			Long.parseLong(str);
		}catch(Exception e){
			return aDate;
		}
		
		String YYYY = str.substring(0, 4);
		String MM = str.substring(4, 6);
		String DD = str.substring(6, 8);
		String HH = str.substring(8, 10);
		String mi = str.substring(10, 12);
		String SS = str.substring(12, 14);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,   Integer.parseInt(YYYY));
		calendar.set(Calendar.MONTH,  Integer.parseInt(MM)-1);
		calendar.set(Calendar.DATE,   Integer.parseInt(DD));
		calendar.set(Calendar.HOUR,   Integer.parseInt(HH));
		calendar.set(Calendar.MINUTE, Integer.parseInt(mi));
		calendar.set(Calendar.SECOND, Integer.parseInt(SS));
		
		aDate = calendar.getTime();
		return aDate;
	}
	
	/**
	 * String(문자열)을 Byte 단위로 잘라서 return한다.
	 * @param str
	 * @param length
	 * @return
	 */
	public static String getSubByteData(String str, int length) {

		// 널(null) or "" 값 체크
		if(str == null || str.trim().equals("")){ 
			return "";
		}

		// 이미 작은 경우 오리널 반환
		if(str.getBytes().length <= length){
			return str;
		}


		byte[] oldByte = str.getBytes();
		byte[] newByte = new byte[length];

		int start = 0;
		for(int j = 0 ; start < length ; j++) {
	        
	        // 아스키 코드 0 ~ 127까지
			if(oldByte[j] >= 0 && oldByte[j] <= 127) {     
				newByte[start] = oldByte[j];
				start++;
			} else {
				if(oldByte[j] < 0 && start+1 < length){       
					newByte[start] = oldByte[j];
					newByte[++start] = oldByte[++j];
					start++;
				} else if(oldByte[j] < 0 && start+1 >= length) { 
					j++;
					start=start+2;
				} else {
	  				newByte[start] = oldByte[j];
	  				start++;
	 			}
			}
		}
		return new String(newByte);
	}
	
	/**
	 * 해당 Url Content를 가져와 return한다.
	 * @param urlString
	 * @param message
	 * @return
	 */
	public static String getHttpUrlConnectionData(String urlString, String message) throws Exception{
	
		URL url = new URL(urlString);
	    HttpURLConnection con = (HttpURLConnection)url.openConnection();
	    con.setRequestMethod("POST");
	    con.setDoOutput(true);
	    con.setDoInput(true);
	
	    DataOutputStream dos = new DataOutputStream(con.getOutputStream());
	    dos.writeBytes(message);
	    dos.flush();
	    dos.close();
	    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    
	    String nextline = "";
	    String content = "";  
	    while((nextline = br.readLine()) != null) {
	    	content = content + nextline;
	    }
	    br.close();
	    return content;
	}
	
	/**
     * 스트링을 주어진 delimiter로 나눈뒤 결과를 벡터에 넣어서 주는메소드.
     * @param s 나누어야 할 스트링.
     * @param delimiter 나누고자하는 delimiter. delimiter는 제외가 됨으로 벡터에 들어가지않음
     * @return 토큰의 배열.
     */
    public static String[] split(String s, String delimiter){
        Vector v = new Vector();
        StringTokenizer st = new StringTokenizer(s, delimiter);
        while(st.hasMoreTokens())
            v.addElement(st.nextToken());

        String array[] = new String[v.size()];
        v.copyInto(array);

        return(array);
    }

    /**
     * 토큰의 배열을 delimiter를 중간에 넣어서 합친뒤 스트링으로 반환하는 메소드.
     * @param array 합쳐야할 토큰들.
     * @param delimiter 토큰이 합쳐질때 사이에 들어갈 delimiter.
     * @return 합쳐진 스트링.
     */
    public static String join(String array[], String delimiter){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < array.length; i++){
            if(i > 0) sb.append(delimiter);
                sb.append(array[i]);
        }

        return(sb.toString());
    }
    
    /**
     * 문자 취환 
     * @param in 작업할 문자를 포함하는 문자열
     * @param from 찾는 문자열
     * @param to 취환할 문자열
     * @return The new String
     */
    public  static  String  replace(String in, String from, String to) {
        StringBuffer sb     =   new StringBuffer(in.length() * 2);
        String  posString   =   in.toLowerCase();
        String  cmpString   =   from.toLowerCase();
        int     i   =   0;
        boolean done    =   false;
        while (i < in.length() && !done) {
            int start   =   posString.indexOf(cmpString, i);
            if  (start == -1) {
                    done = true;
            }
            else {
                sb.append(in.substring(i, start) + to);
                i = start + from.length();
            }
        }
        if (i < in.length()) {
            sb.append(in.substring(i));
        }
        return sb.toString();    
    }
    
    /**
     * 스트링을 주어진 길이만큼 자르고 "..." 넣어서 주는메소드.
     * @param str 초기 문자열
     * @param len 자르고자 하는 길이 
     * @return 변형된 스트링.
     */
    public static String cut(String str, int len) {	
		int iStart, iEnd ;
		String original = str;
		String str1 = "";
		
		if ( str == null ) return str;
		if ( str.length() <= len ) return str;
		
		iStart = str.indexOf("<");
		iEnd = str.indexOf(">");
		
		while (iStart >= 0 && iEnd >= 0) {
			//if (iStart > 0) iStart = iStart;
			if (iEnd < str.length()) iEnd = iEnd +1;
			
			str1 += str.substring(0,iStart);		
			str = str.substring(iEnd);		
			iStart = str.indexOf("<");
			iEnd = str.indexOf(">");
			
		}
		if (original.length() < len ) return original;
		else if (str1.length() == 0) return original.substring(0, len) + "...";
		else if (str1.length() > 0 && str1.length() < len) return original;
		else return replace(original, str1, str1.substring(0, len) + "...");
		
	}
    
    //한글고려한 ..붙이기    ex) cutBytes("우리1234", 3, "...") ==> "우리1..."
    public static String cutBytes(String string, int maxSize, String re) {
    	
    	if(string == null)
    		return "";
    	
        int tLen = string.length();
        int count = 0;
        char c;
        int s=0;
        for(s=0;s<tLen;s++){
            c = string.charAt(s);
            if(count > maxSize) break;
            if(c>127) count +=2;
            else count ++;
        }
        return (tLen >s)? string.substring(0,s)+re : string;
    }  
    
    
    
    
    /**
     * 스트링을 주어진 길이만큼 자르는 메소드.
     * @param str 초기 문자열
     * @param len 자르고자 하는 길이 
     * @return 변형된 스트링.
     */
    public static String cutString(String str, int len) { 
    	if(str == null || str.length() == 0)
    		return str;
    	
    	byte[] by = str.getBytes();
    	int count = 0;
    	try  {
    		if(by.length < len)
    			return str;
    		
    		for(int i = 0; i < len; i++) {
    			if((by[i] & 0x80) == 0x80)
    				count++;
    		}
    		if((by[len - 1] & 0x80) == 0x80 && (count % 2) == 1)
    			len--;
    		return new String(by, 0, len) + "...";   
    	}
    	catch(java.lang.ArrayIndexOutOfBoundsException e) {
    		return "";
    	}
    }
	
	/**
	 * 파일명에서 extension을 제거한 이름만을 가져온다.
	 * @param fileName
	 * @return
	 */
	public static String getFileNameWithoutExtension(String fileName){
		int index = fileName.lastIndexOf(".");
		return fileName.substring(0, index);
	}
	
	/**
	 * 파일명에서 확장자만 return 한다.
	 * @param fileName
	 * @return
	 */
	public static String getFileExtension(String fileName){
		int index = fileName.lastIndexOf(".");
		return fileName.substring(index + 1);
	}
	
	/**
     * String 중 특정한 문자가 몇번 들어가 있는지 return
     * @param str 대상 문자열
     * @param find 찾고자 하는 문자열
     */
    public static int cntInStr(String str, String find){
        int i   =   0;
        int pos =   0;
        while ( true ) {
            pos =   str.indexOf(find, pos );
            if ( pos == -1 )
                break;
            i++;
            pos++;
        }
        return  i;
    }
    
    /**
     * String에 외따움표를 넣어서 return
     * @param str 대상 문자열
     */
    public static String makeSQL(String str){
		String result = null;
		if(str != null){
			result = "'"+str+"'";
		}
		return result;
	}
    
    
    /**
     * Sky life Url 인증
     * @return
     */
    public static long getTotalDays(){

		Calendar cal = Calendar.getInstance();

		int iYear = cal.get(Calendar.YEAR);
		int iMonth = cal.get(Calendar.MONTH) + 1;
		int iDate = cal.get(Calendar.DAY_OF_MONTH);
		int iHour = cal.get(Calendar.HOUR_OF_DAY);
		int iMinute = cal.get(Calendar.MINUTE);
		int iSecond = cal.get(Calendar.SECOND);

		long n = 0;

		int[] gMonthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		n = 365 * (iYear - 1);
		n = n + (int)((iYear - 1) / 4);
		n = n - (int)((iYear - 1) / 100);
		n = n + (int)((iYear - 1) / 400);

		if (iMonth > 2){
			if ((iYear % 4) == 0){
				n = n + 1;
			}
			if ((iYear % 100) == 0){
				n = n - 1;
			}
			if ((iYear % 400) == 0){
				n = n + 1;
			}
		}

		for(int cx=0; cx<=iMonth-2; cx++ ){
			n = n + gMonthDays[cx];
		}

		n = n + iDate;
		n = n * 24 + iHour;
		n = n * 60 + iMinute;
		n = n * 60 + iSecond;

		return n;
	}
	public static String encodeV200(String uid){
		long lms = 0;
		long lidx = 0;

		lms = (long)getTotalDays();
		lidx = (long)(Math.random() * 99 + 1);
		
		return "?uid="+uid+"&key="+((lms * lidx * 100) + lidx);
	}
	
	/**
	* 현재일자를 구함    TYPE = yyyymmdd
	* <p>
	* @return     현재일자 문자열
	*/
	public static String getSysDate()   {
	   return getSysYear() + getSysMonth() + getSysDay();
	}
	
	/**
	* 현재일자를 구함    TYPE = yyyy[/|:|-]mm[/|:|-]dd
	* <p>
	* @param    cTocken  구분자 Char [/|:|-]
	* @return   	현재일자 문자열
	*/
	public static String getSysDate(char cTocken)   {
	   return getSysYear() + cTocken + getSysMonth() + cTocken + getSysDay();
	}

	/**
	* 현재일자를 구함    TYPE = yyyy[/|:|-]mm[/|:|-]dd
	* <p>
	* @param   	cTocken  구분자 Char [/|:|-]
	* @return    	현재일자 문자열
	*/
	public static String getSysDate(String cTocken)   {
	   return getSysYear() + cTocken + getSysMonth() + cTocken + getSysDay();
	}     

	/**
	* 현재시간을 구함    TYPE = ttmmss
	* <p>
	* @return     현재시간 문자열
	*/
	public static String getSysTime()   {
	   return getSysHour() + getSysMinute() + getSysSecond();
	} 

	/**
	* 현재시간을 구함    TYPE = tt[/|:|-]mm[/|:|-]ss
	* <p>
	* @param   	cTocken  구분자 Char [/|:|-]
	* @return    	현재시간 문자열
	*/
	public static String getSysTime(char cTocken)   {
	   return getSysHour() + cTocken + getSysMinute() + cTocken + getSysSecond();
	} 

	/**
	* 현재날짜와 시간을 구함 TYPE = yyyy-mm-dd tt:m:ss
	* <p>
	* @return     현재날짜와 시간 문자열
	*/
	public static String getSysDateTime()  {
	   return getSysDate('-') + " " + getSysTime(':');
	} 

	public static String getSysDateTime(String format)  {
	    String fmt;
	    
	    if (format == null) {
	        fmt = "yyyy/MM/dd HH:mm:ss";
	    } else {
	        fmt = format;
	    }

	    SimpleDateFormat formatter = new SimpleDateFormat(fmt);

	    return formatter.format(new Date());
	} 
	   
	/**
	* 현재년도를 구함 TYPE = yyyy
	* <p>
	* @return     현재년도 문자열
	*/
	public static String getSysYear()   {
	   GregorianCalendar todaysDate = new GregorianCalendar();

	   return Integer.toString(todaysDate.get(Calendar.YEAR));
	} 

	/**
	* 현재달을 구함 TYPE = mm
	* <p>
	* @return     현재달 문자열
	*/
	public static String getSysMonth()  {
	   int nMonth;
	   DecimalFormat formatter = new DecimalFormat("00");
	   GregorianCalendar todaysDate = new GregorianCalendar();

	   nMonth = todaysDate.get(Calendar.MONTH)+1;
	   return formatter.format(nMonth);
	} 

	/**
	* 현재일을 구함 TYPE = dd
	* <p>
	* @return     현재일 문자열
	*/
	public static String getSysDay() {
	   int nDay;
	   DecimalFormat formatter = new DecimalFormat("00");
	   GregorianCalendar todaysDate = new GregorianCalendar();

	   nDay = todaysDate.get(Calendar.DATE);
	   return formatter.format(nDay);
	} 

	/**
	* 현재시를 구함 TYPE = tt
	* <p>
	* @return     현재시 문자열
	*/
	public static String getSysHour()   {
	   int nHour, nAmpm;
	  
	   DecimalFormat formatter = new DecimalFormat("00");
	   GregorianCalendar todaysDate = new GregorianCalendar();

	   nAmpm = todaysDate.get(Calendar.AM_PM);
	   nHour = todaysDate.get(Calendar.HOUR);
	   if(nAmpm == 1) {
	      nHour = nHour+12;
	      return Integer.toString(nHour);
	   } 
	   return formatter.format(nHour);
	} 

	/**
	* 현재분을 구함 TYPE = mm
	* <p>
	* @return     현재분 문자열
	*/
	public static String getSysMinute() {
	   int nMinute;
	   DecimalFormat formatter = new DecimalFormat("00");
	   GregorianCalendar todaysDate = new GregorianCalendar();

	   nMinute = todaysDate.get(Calendar.MINUTE);
	   return formatter.format(nMinute);
	} 

	/**
	* 현재초를 구함 TYPE = ss
	* <p>
	* @return     현재초 문자열
	*/
	public static String getSysSecond() {
	   int nSecond;
	   DecimalFormat formatter = new DecimalFormat("00");
	   GregorianCalendar todaysDate = new GregorianCalendar();

	   nSecond = todaysDate.get(Calendar.SECOND);
	   return formatter.format(nSecond);
	} 

	/**
	* 날짜에서 년도를 더하고 뺌
	* <p>
	* @param    String   yyyymmdd    	날짜(TYPE = yyyymmdd)
	*           		String   strY        		증감값
	* @return   	String   변환날짜문자열
	*/
	public static String getAddYear(String yyyymmdd, String strY) {
	   return getAddYear(yyyymmdd, Integer.parseInt(strY));
	}

	/**
	* 날짜에서 년도를 더하고 뺌
	* <p>
	* @param    String   	yyyymmdd    날짜(TYPE = yyyymmdd)
	*           		int      	nY          		증감값
	* @return   	String   	변환날짜문자열
	*/
	public static String getAddYear(String yyyymmdd, int distYear) {
	    if (yyyymmdd.length () != 8) {
	        return "";
	    }
	    
	    String year = yyyymmdd.substring(0, 4);
	    String month = yyyymmdd.substring(4, 6);
	    String day = yyyymmdd.substring(6, 8);
	    int sumYear = Integer.parseInt(year) + distYear;

	    if(month.equals("02") && day.equals ("29"))  {
	        // 2월29일이고 윤년이 아니면 28일을 되돌린다.
	        if(!(sumYear % 4 == 0 && (sumYear % 100 != 0 || sumYear % 400 == 0))) {
	            day = "28";
	        }
	    }
	    
	    return Integer.toString(sumYear) + month + day;
	}

	/**
	* 날짜에서 월을 더하고 뺌
	* <p>
	* @param    String   s           			날짜(TYPE = yyyymmdd)
	*           		String   addMonth    	addMonth 증감값
	* @return   	String   변환날짜문자열
	*/
	public static String getAddMonth(String date, String addMonth) {
	    int distMonth = Integer.parseInt(addMonth);

	    return getAddMonth(date, distMonth);
	}

	/**
	* 날짜에서 월을 더하고 뺌
	* <p>
	* @param    String   	s           		날짜(TYPE = yyyymmdd)
	*           		int      	addMonth    	addMonth 증감값
	* @return   	String   	변환날짜문자열
	*/
	public static String getAddMonth(String sDate, int addMonth) {
	    if (sDate.length () != 8) {
	        return "";
	    }

	    int year = Integer.parseInt (sDate.substring (0, 4));
	    int month = Integer.parseInt (sDate.substring (4, 6));
	    int day = Integer.parseInt (sDate.substring (6, 8));

	    Calendar calendar = new GregorianCalendar(year, month - 1, day);
	    calendar.add (Calendar.MONTH, addMonth);

	    SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMdd");

	    return formatter.format (calendar.getTime ());
	}
	
	/**
	* 숫자형을 '###,###,###,###,###' 형식으로 바꿈
	* <p>
	* @param
	* @return   	String   	형식에 맞게 변환된결과, 결과가 "0"일경우 "" 반환
	*/
	public static String strToDate(String sDate, String sep) {
		if(sDate == null || sep == null || sDate.length() != 8)
			return "";
		
		return sDate.substring (0, 4) + sep + sDate.substring (4, 6) + sep + sDate.substring (6, 8);
	}
	
	public static String strToYMDate(String sDate, String sep) {
		if(sDate == null || sep == null || sDate.length() != 6)
			return "";
		
		return sDate.substring (0, 4) + sep + sDate.substring (4, 6);
	}
	
	public static String setComma(String val) {
		if(val == null || val.length() == 0)
			return val;
		
		NumberFormat nf = NumberFormat.getInstance();
		double d = Double.parseDouble(val);
		return nf.format(d);
	}
	
	public static String setComma(int val) {
		return setComma(String.valueOf(val));
	}
	
	public static String setComma(long val) {
		return setComma(String.valueOf(val));
	}
	
	public static String setComma(float val) {
		return setComma(String.valueOf(val));
	}
	
	public static String setComma(double val) {
		return setComma(String.valueOf(val));
	}
	
	/**
	* 해당 Object를 String으로 형변형하여 리턴한다.
	* <p>
	* @param    obj  변환할 객체
	* @return   	변환된 문자열 
	*/
	public static String nvl(Object obj){
		String str="";
		
		if (obj == null)
			str = "";
		else{
			try{				
				str = (String)obj;	
			}catch(Exception e){
				try{
					str = obj.toString();				
				}catch(Exception ex){
					str = "";
				}
			}
		}
		
		if(str.equals("null") || (str.length() == 0))
			str = "";
		
		return str;
	}
	
	public static String nvl(Object obj, String nullvalue) {
		String str="";
		
		if (obj == null)
			str = nullvalue;
		
		else{
			try{				
				str = (String)obj;	
			}catch(Exception e){
				try{
					str = obj.toString();				
				}catch(Exception ex){
					str = nullvalue;
				}
			}
		}
		
		return str;
	}
	
	public static Long nvlL(Object obj, Long nullvalue) {
		Long str=0L;
		
		if (obj == null)
			str = nullvalue;
		
		else{
			try{				
				str = (Long)obj;	
			}catch(Exception e){
				try{
					str = (Long)obj;				
				}catch(Exception ex){
					str = nullvalue;
				}
			}
		}
		
		return str;
	}	
	
	public static String namoReplace(String original, String oldstr, String newstr) {
		String convert = new String();
		int pos = 0;
		int begin = 0;
		pos = original.indexOf(oldstr);

		if(pos == -1)
			return original;

		while(pos != -1)
		{
			convert = convert + original.substring(begin, pos) + newstr;
			begin = pos + oldstr.length();
			pos = original.indexOf(oldstr, begin);
		}
		convert = convert + original.substring(begin);

		return convert;
	}
	
	public static String namoConvertHtmlchars(String htmlstr) {
		String convert = new String();
		convert = namoReplace(htmlstr, "<", "&lt;");
		convert = namoReplace(convert, ">", "&gt;");
		convert = namoReplace(convert, "\"", "&quot;");
		convert = namoReplace(convert, "&nbsp;", "&amp;nbsp;");
		return convert;
	}
	
	/** 
	* getTelNumberFormat
	* 문자열로 입력된 숫자10 or 11자리를 받아 전화번호 format으로 return한다. 
	* ex) "0535427845"  ----> "053-542-7845" 
	* @param    numberValue   String 입력받은 문자열 
	* @return   Str           String 전화번호 출력 
	*/ 
	 public static String getTelNumberFormat(String numberValue) { 
		if(numberValue.equals("") || numberValue == null || numberValue.length() < 10) 
			return numberValue; 
			
		String newFormat = ""; 
		String FNumber = ""; 
		String MNumber = ""; 
		String LNumber = ""; 
		
		if(numberValue.length()==10) { 
			FNumber = numberValue.substring(0,3); 
			MNumber = numberValue.substring(3,6); 
			LNumber = numberValue.substring(6,numberValue.length()); 
		} else if (numberValue.length()==11) { 
			FNumber = numberValue.substring(0,3); 
			MNumber = numberValue.substring(3,7); 
			LNumber = numberValue.substring(7,numberValue.length()); 
		} 
		
		newFormat = FNumber+"-"+MNumber+"-"+LNumber; 
		
		return newFormat; 
	}
	 
	 /** 
	 * CLOB Data Read 
	 *  
	 * @param str 
	 * @return 
	 * @throws java.io.IOException 
	 */ 
	 public static String readClobData(Reader reader) throws IOException {
		 StringBuilder data = new StringBuilder();
		 char[] buf = new char[1024];
		 int cnt = 0;
		 if (null != reader) {
		     while ( (cnt = reader.read(buf)) != -1) {
		    	 data.append(buf, 0, cnt);
		     }
		 }
		 return data.toString();
	 }
    /**
     * TextArea에서 입력받은 캐리지 리턴값을 <BR>태그로 변환
     * @param comment
     * @return
     */
    public static String nl2br(String comment){
        int length = comment.length();
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < length; ++i)
        {
            String comp = comment.substring(i, i+1);
            if ("\r".compareTo(comp) == 0)
            {
                comp = comment.substring(++i, i+1);
                if ("\n".compareTo(comp) == 0)
                    buffer.append("<BR>\r");
                else
                    buffer.append("\r");
            }

            buffer.append(comp);
        }
        return buffer.toString();
    }
    
    public static String htmlTagDelete(String memo){
    	String textWithoutTag = "";
    	textWithoutTag = memo.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
//    	textWithoutTag = textWithoutTag.replaceAll(" ", "").replaceAll("&nbsp;", "  ");
    	textWithoutTag = textWithoutTag.replaceAll("&nbsp;", "  ");
    	textWithoutTag = textWithoutTag.replaceAll("<!--", "");
    	Pattern p = Pattern.compile("");
    	
    	return textWithoutTag;
    	
    }
    
    /**
     * 특수 문제 제거
     * @param str
     * @return
     */
    public static String StringReplace(String str){
        String str_imsi   = ""; 
        String[] filter_word = {"","\\.","\\?","\\/","\\~","\\!","\\@","\\#","\\$","\\%","\\^","\\&","\\*","\\(","\\)","\\_","\\+","\\=","\\|","\\\\","\\}","\\]","\\{","\\[","\\\"","\\'","\\:","\\;","\\<","\\,","\\>","\\.","\\?","\\/"};

        for(int i=0;i<filter_word.length;i++){
            str_imsi = str.replaceAll(filter_word[i],"");
            str = str_imsi;
        }

        return str;

    }


	public static final String fillString(String str, int len, char fillCh, boolean bJustifyLeft) {
		if (null == str) {
			StringBuffer strbuf = new StringBuffer(len);
			for (int i=0; i < len; i++) strbuf.append(fillCh);
			return strbuf.toString();
		}
		int oriLen = str.length();
		if (oriLen > len) return str.substring(0, len);
		if (oriLen == len) return str;
		StringBuffer strbuf = new StringBuffer(len);
		if (!bJustifyLeft) for (int i=0; i < (len - oriLen); i++) strbuf.append(fillCh);
		strbuf.append(str);
		if (bJustifyLeft) for (int i=0; i < (len - oriLen); i++) strbuf.append(fillCh);
		return strbuf.toString();
	}


	public static byte[] toByteArray(String str) {
		return str.getBytes();
	}
	public static String fromByteArray(byte[] byteArr) {
		return new String(byteArr);
	}
	/*
	 * 문자열 치환
	 */
	public static String getSpecialCharacters(String str) {
		  str = getReplace(str, "&", "&amp;");
		  str = getReplace(str, "<", "&lt;");
		  str = getReplace(str, ">", "&gt;");
		  str = getReplace(str, "'", "&acute;");
		  str = getReplace(str, "\"", "&quot;");
		  str = getReplace(str, "|", "&brvbar;");
		  str = getReplace(str, "\n", "<BR>");
		  str = getReplace(str, "\r", "");

		  return str;
	}
	public static String getSpecialCharactersForAsk(String str) {
		  str = getReplace(str, "&", "&amp;");
		  str = getReplace(str, "<", "&lt;");
		  str = getReplace(str, ">", "&gt;");
		  str = getReplace(str, "'", "&acute;");
		  str = getReplace(str, "\"", "&quot;");
		  str = getReplace(str, "|", "&brvbar;");
//		  str = getReplace(str, "\n", "<BR>");
		  str = getReplace(str, "\r", "");

		  return str;
	}
	
	/*
	 * 문자열 치환
	 */
	public static String getReplace(String str, String rep, String tok) {
		String retStr = "";
		if (str==null) return "";
		if (str.equals("")) return "";
		for (int i = 0, j = 0; (j = str.indexOf(rep,i)) > -1 ; i = j+rep.length()) {
			retStr += (str.substring(i,j) + tok);
		}
		
		return (str.indexOf(rep) == -1) ? str : retStr + str.substring(str.lastIndexOf(rep)+rep.length(),str.length());
	 }

	public static boolean isNumber(String str) {  
        boolean check = true;
        if (str == null || str == "") { 
            return false; 
      } 

        for(int i = 0; i < str.length(); i++) {
            if(!Character.isDigit(str.charAt(i))) 
            {
                check = false;
                break; 
            }// end if
        } //end for
        return check;  
	} //isNumber 
	
	
	/**
	 * 입력값이 null이거나 ""이면 true를 리턴한다.
	 * @param p
	 * @return
	 */
	public static boolean isEmptyStr(String p) {
		return (p == null || p.trim().equals(""));
	}
	
	/**
	 * 입력값이 빈값이 아니면 true를 리턴한다.
	 * @param p
	 * @return
	 */
	public static boolean isNotEmptyStr(String p) {
		return (p != null || !p.trim().equals(""));
	}
	
	/**
	 * 
	 */
	public static String getCsfFormat(String csf){
		if(csf.length()==11){
			return csf;
		}else if(csf.length()==10){
			if("02".equals(csf.substring(0, 2))){
				csf = "0" + csf;
				return csf;
			}else{
				String ctn1 = csf.toString().substring(0,3);
				String ctn2 = "0" + csf.toString().substring(3, csf.toString().length());
				csf = ctn1 +  ctn2;
				return csf;
			}
		}else if(csf.length()==9){
			String ctn1 = "0" + csf.toString().substring(0,2); 
			String ctn2 = "0" + csf.toString().substring(2, csf.toString().length());
			csf = ctn1 + ctn2;
			return csf;
		}
		return csf;
		
	}
	
	/**
	 * 전화번호 형식 작성자 ID 마스킹(010****1234) 처리 
	 * @param str
	 * @return
	 */
	public static String maskingId(String str){
		
		if(str.length() >= 10){
			//아이디 전체가 전화번호 형식이면 바로 마스킹 처리 (010형태의 11자리와 019형태의 10자리)
			if(StringUtil.isNumber(str)){
				switch (str.length()){
					case 10 :
						str = str.substring(0,3)+"***"+str.substring(6, 10);
						break;
					case 11 :
						str = str.substring(0,3)+"****"+str.substring(7, 11);
						break;
				}
			}else{
			//아이디 앞뒤로 알파벳이 들어갈경우 아이디앞뒤로 전화번호 형태 체크하여 마스킹처리 
				String firstCut = str.substring(0,1);
				String lastCut = str.substring(str.length()-1, str.length()); 
				String strFirstCut = str.substring(1,str.length());
				String strLastCut = str.substring(0, str.length()-1);
				
				if(StringUtil.isNumber(strFirstCut)){
					switch(strFirstCut.length()){
					case 10 :
						str = firstCut+strFirstCut.substring(0,3)+"***"+strFirstCut.substring(6, 10);
						break;
					case 11 :
						str = firstCut+strFirstCut.substring(0,3)+"****"+strFirstCut.substring(7, 11);
						break;
					}
				}
				if(StringUtil.isNumber(strLastCut)){
					switch(strLastCut.length()){
					case 10 :
						str = strLastCut.substring(0,3)+"***"+strLastCut.substring(6, 10)+lastCut;
						break;
					case 11 :
						str = strLastCut.substring(0,3)+"****"+strLastCut.substring(7, 11)+lastCut;
						break;
					}
				}
			}				
		}
		return str;		
	}
	/**
	 * 리턴 되는 view 경로를 리턴
	 * @param req
	 * @return
	 */
	public static String getViewName(HttpServletRequest req) {
		String viewName = null;
		if(req != null) {
			if(isNotEmptyStr(req.getRequestURI())){
				viewName = req.getRequestURI().substring(0, req.getRequestURI().length()-3);
			}
		}
		return viewName;
	}
}
