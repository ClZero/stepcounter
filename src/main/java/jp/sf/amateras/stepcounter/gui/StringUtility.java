package jp.sf.amateras.stepcounter.gui;

import java.util.*;

/**
 * ������̑�����s���e��static���\�b�h��񋟂���N���X�ł��B
 *
 * @author  Naoki Takezoe
 * @version 2.0
 */
public class StringUtility {

	/*
	// ����m�F�p�N�����\�b�h
	public static void main(String[] args){


		String i="00";


		System.out.println("["+StringUtility.cut(i,3)+"]");
		System.out.println("[100]");
	}
	*/

	/**
	 * HTML�̓���L�������ԎQ�Ƃɕϊ����܂��B
	 * ���s�R�[�h��&lt;BR&gt;�^�O�ɕϊ����܂��B
	 *
	 * @param str �ϊ��Ώۂ̕�����
	 * @return �ϊ���̕�����
	 */
	public static String tagFilter(String str){

		str = StringUtility.replace(str,"<","&lt;");
		str = StringUtility.replace(str,">","&gt;");
		str = StringUtility.replace(str,"\"","&quot;");
		str = StringUtility.replace(str,"\r\n","<BR>"); // Win
		str = StringUtility.replace(str,"\r","<BR>");   // Mac
		str = StringUtility.replace(str,"\n","<BR>");   // Unix

		return str;
	}

	/**
	 * HTML�̓���L�������ԎQ�Ƃɕϊ����܂��B
	 * ���������s�R�[�h�͕ϊ����܂���B
	 * �t�H�[���ɑ����l���i�[����ۂȂǂɎg�p���Ă��������B
	 *
	 * @param str �ϊ��Ώۂ̕�����
	 * @return �ϊ���̕�����
	 */
	public static String formFilter(String str){

		str = StringUtility.replace(str,"<","&lt;");
		str = StringUtility.replace(str,">","&gt;");
		str = StringUtility.replace(str,"\"","&quot;");

		return str;
	}

	/**
	 * �����񒆂̔C�ӂ̕�������w�肵��������ɒu�����܂��B
	 *
	 * @param s �ϊ��Ώۂ̕�����B
	 * @param s1 s2�ɒu������镶����B
	 * @param s2 s1�ɒu�������镶����B
	 * @return �ϊ���̕�����Bs��null�̏ꍇ�͋󕶎����Ԃ��܂��B
	 */
	public static String replace(String s,String s1,String s2){

		// s ��NULL�������ꍇ�A�󕶎����Ԃ�
		if(s==null){ return ""; }

		StringBuffer sb = new StringBuffer();
		for(int i=0;i<s.length();i++){
			if(s.indexOf(s1,i)==i){
				sb.append(s2);
				i = i + s1.length() - 1;
			} else {
				sb.append(s.charAt(i));
			}
		}
		return sb.toString();
	}

	/**
	 * �����񂪎w��̃o�C�g�������Z�������ꍇ�ɍ������X�y�[�X�Ŗ��߂܂��B
	 * �w��̃o�C�g���������������ꍇ�͂Ȃɂ����܂���B
	 *
	 * @param str �ϊ��Ώۂ̕�����
	 * @param length �o�C�g��
	 * @return �ϊ���̕�����
	 */
	public static String fillLeftSpace(String str,int length){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<length-StringUtility.getBytes(str);i++){
			sb.append(" ");
		}
		sb.append(str);
		return sb.toString();
	}

	/**
	 * �����񂪎w��̃o�C�g�������Z�������ꍇ�ɉE�����X�y�[�X�Ŗ��߂܂��B
	 * �w��̃o�C�g���������������ꍇ�͂Ȃɂ����܂���B
	 *
	 * @param str �ϊ��Ώۂ̕�����
	 * @param length �o�C�g��
	 * @return �ϊ���̕�����
	 */
	public static String fillRightSpace(String str,int length){

		int strLength = StringUtility.getBytes(str);
		if(strLength>=length){ return str; }

		String space = StringUtility.createSpace(length-strLength);

		return str + space;
	}

	/**
	 * �����񂪎w��̕������i�o�C�g���ł͂Ȃ��j�𒴂���ꍇ�ɁA
	 * �����������J�b�g�����������Ԃ��܂��B
	 * �w��̕�������菭�Ȃ��ꍇ�͉��������ɕԂ��܂��B
	 *
	 * @param str �ϊ��Ώۂ̕�����
	 * @param length ������
	 * @return �ϊ���̕�����
	 */
	public static String cut(String str,int length){
		if(str.length() <= length){ return str; }
		return str.substring(0,length);
	}

	/**
	 * ������NULL�������ꍇ�ɋ󕶎���ɕϊ����܂��B
	 *
	 * @param s �ϊ��Ώۂ̕�����B
	 * @return s��null�̏ꍇ�͋󕶎�����A����ȊO�̏ꍇ�͌��̕������Ԃ��܂��B
	 */
	public static String nullConvert(String s){
		if(s==null){ return ""; } else { return s; }
	}

	/**
	 * ������NULL�������ꍇ�Ɏw�肵��������ɕϊ����܂��B
	 *
	 * @param s �ϊ��Ώۂ̕�����B
	 * @return s��null�̏ꍇ�͎w�肵����������A����ȊO�̏ꍇ�͌��̕������Ԃ��܂��B
	 */
	public static String nullConvert(String s1,String s2){
		if(s1==null){ return s1; } else { return s2; }
	}


	/**
	 * ��������w�肵��������ŕ������A�z��ŕԂ��܂��B
	 *
	 * @param s1 �����Ώۂ̕�����B
	 * @param s2 �������̋�؂�Ɏg�p���镶����B
	 * @return �������ʂ�z��ŕԂ��܂��Bs1��null�̏ꍇ��null��Ԃ��܂��B
	 */
	public static String[] split(String s1,String s2){
		if(s1 == null){ return null; }
		Vector<String> v  = new Vector<String>();
		String sb = "";
		for(int i=0;i<s1.length();i++){
			if(s1.indexOf(s2,i)==i){
				v.add(sb.toString());
				i = i + s2.length() - 1;
				sb = "";
			} else {
				sb = sb + s1.charAt(i);
			}
		}
		if(sb.length()!=0){ v.add(sb); }

		String[] ret = new String[v.size()];
		for(int i=0;i<v.size();i++){
			ret[i] = (String)v.get(i);
		}
		return ret;
	}

	/**
	 * ������̃o�C�g����Ԃ����\�b�h�ł��B
	 *
	 * @param str �����Ώۂ̕�����B
	 * @return �����̕�����s�̃o�C�g���B
	 */
	public static int getBytes(String str){ return str.getBytes().length; }


	/**
	 * ������̐擪�Ɩ����̃^�u�A�X�y�[�X��r�����܂��B������NULL�̏ꍇ�͋󕶎����Ԃ��܂��B
	 *
	 * @param str ������B
	 * @return �����̕�����str����^�u�A�X�y�[�X����菜����������B
	 */
	public static String trim(String str){
		// NULL�̏ꍇ�A�󕶎����Ԃ�
		if(str==null){ return ""; }

		StringBuffer sb = new StringBuffer();
		for(int i=0;i<str.length();i++){
			char c = str.charAt(i);
			if(c!=' ' && c!='\t' && c!='�@'){ sb.append(c); }
		}
		return sb.toString();
	}

	/**
	 * ������̖����̃^�u�A�X�y�[�X��r�����܂��B������NULL�̏ꍇ�͋󕶎����Ԃ��܂��B
	 *
	 * @param str ������B
	 * @return �����̕�����str�̖�������^�u�A�X�y�[�X����菜����������B
	 */
	public static String rTrim(String str){
		// NULL�̏ꍇ�A�󕶎����Ԃ�
		if(str==null){ return ""; }
		char c = ' ';
		int index = str.length();
		while((c==' ' || c=='�@' || c=='\t') && index > 0){
			index = index - 1;
			c = str.charAt(index);
		}
		return str.substring(0,index+1);
	}

	/**
	 * ������̐擪�̃^�u�A�X�y�[�X��r�����܂��B������NULL�̏ꍇ�͋󕶎����Ԃ��܂��B
	 *
	 * @param str ������B
	 * @return �����̕�����str�̐擪����^�u�A�X�y�[�X����菜����������B
	 */
	public static String lTrim(String str){
		// NULL�̏ꍇ�A�󕶎����Ԃ�
		if(str==null){ return ""; }
		char c = ' ';
		int index = -1;
		while((c==' ' || c=='�@' || c=='\t') && index < str.length()){
			index = index + 1;
			c = str.charAt(index);
		}
		return str.substring(index,str.length());
	}

	/**
	 * �X�y�[�X���琬��w��̒����̕�������쐬���܂��B
	 *
	 * @param num �쐬����X�y�[�X������̒����B
	 * @return �X�y�[�X���琬�镶����B
	 */
	public static String createSpace(int num){
		if(num <= 0){ return ""; }
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<num;i++){ sb.append(" "); }
		return sb.toString();
	}

	/**
	 * ������^�z��̊e�v�f���w�肵�����E��������g���ĘA�����܂��B
	 *
	 * @param str �A�����镶����^�̂P�����z��B
	 * @param del �A�����̋��E������B
	 * @return �A������������B
	 */
	public static String join(String[] str,String del){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<str.length;i++){
			sb.append(str[i]);
			if(i<str.length-1){ sb.append(del); }
		}
		return sb.toString();
	}

	/**
	 * �����̉��s�R�[�h��؂藎�Ƃ����������Ԃ��܂��B
	 * @param str ������B
	 * @return �����̕����񂩂疖���̉��s�R�[�h��؂藎�Ƃ���������B
	 */
	public static String chomp(String str){
		if(str.endsWith("\r\n")){ return str.substring(0,str.length()-2); }
		if(str.endsWith("\r") || str.endsWith("\n")){ return str.substring(0,str.length()-1); }
		return str;
	}
}