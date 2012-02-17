package jp.sf.amateras.stepcounter;

import java.io.Closeable;
import java.io.File;
import java.util.ArrayList;

/**
 * �e�탆�[�e�B���e�B���\�b�h��񋟂���N���X
 */
public class Util {

	private static String fileEncoding = null;
	private static FileEncodingDetector fileEncodingDetector = null;

	/**
	 * ��������w�蕶����ŕ������A�z��ŕԋp���܂��B
	 *
	 * @param str ������
	 * @param del ��؂蕶����
	 * @return �������ꂽ��������i�[�����z��
	 */
	public static String[] split(String str,String del){
		ArrayList<String> list = new ArrayList<String>();
		int pos   = 0;
		int index = 0;
		while((index=str.indexOf(del,pos))!=-1){
			list.add(str.substring(pos,index));
			pos = index + del.length();
		}
		list.add(str.substring(pos,str.length()));
		return (String[])list.toArray(new String[list.size()]);
	}

	/**
	 * �n���ꂽ��������w��G���R�[�f�B���O�̎w��o�C�g���Ő擪����؂�o���B
	 * �J�^�J�i�̔���͐������s�����Ƃ��ł��Ȃ��B
	 *
	 * @param   s    �؂�o���Ώە�����
	 * @param   cnt  �؂�o���o�C�g��
	 * @return  ���ʕ�����
	 */
	public static String substring(String str,int length){
		String resultStr = null;
		int zenCnt      = 0;
//		int kisuuFlg    = 0;
		int loopCnt     = length;
		byte[] resBytes = new byte[length];
		byte[] bytes    = str.getBytes();
		// �w��o�C�g���ȉ��̏ꍇ�͂��̂܂ܕԋp
		if(bytes.length <= length) {
			return str;
		}
		for (int i=0; i < length; i++) {
			if (bytes[i] < 0) {
				// bytes[i]��8�r�b�g�ڂ������Ă���(�S�p)
				zenCnt ++;
			}
		}
		// �S�p�o�C�g�̐�����̏ꍇ
		if(zenCnt % 2 == 1) {
			loopCnt--;
		}
		for(int i=0; i < loopCnt ; i++) {
			resBytes[i] = bytes[i];
		}
		resultStr = new String(resBytes);
		return resultStr;
	}

	/**
	 * �����œn����������̃o�C�g����Ԃ��܂��B
	 *
	 * @param str ������
	 * @return �o�C�g��
	 */
	public static int getByteLength(String str){
		try {
			byte[] bytes = str.getBytes();
			return bytes.length;
		} catch(Exception ex){
			return str.getBytes().length;
		}
	}

	/**
	 * HTML/XML�̓��ꕶ�������ԎQ�Ƃɕϊ����܂��B
	 *
	 * @param str ������
	 * @return �ϊ���̕�����
	 */
	public static String escapeXML(String str){
		str.replaceAll("&" ,"&amp;");
		str.replaceAll("<" ,"&gt;");
		str.replaceAll(">" ,"&lt;");
		str.replaceAll("\"","&quot;");
		return str;
	}

	/**
	 * �X�g���[���������I�ɃN���[�Y���܂��B
	 *
	 * @param closeable �X�g���[��
	 */
	public static void close(Closeable closeable){
		if(closeable != null){
			try {
				closeable.close();
			} catch(Exception ex){
				;
			}
		}
	}

	public static void setFileEncodingDetector(FileEncodingDetector detector){
		fileEncodingDetector = detector;
	}

	public static void setFileEncoding(String encoding){
		fileEncoding = encoding;
	}

	public static String getFileEncoding(File file){
		if(fileEncoding != null){
			return fileEncoding;
		}

		if(fileEncodingDetector != null){
			String encoding = fileEncodingDetector.getEncoding(file);
			if(encoding != null){
				return encoding;
			}
		}

		return System.getProperty("file.encoding");
	}
}
