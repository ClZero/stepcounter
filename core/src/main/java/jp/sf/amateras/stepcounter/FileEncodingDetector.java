package jp.sf.amateras.stepcounter;

import java.io.File;

/**
 * �t�@�C���̕����R�[�h���o���g�����邽�߂̃C���^�[�t�F�[�X�ł��B
 *
 * @see Util#setFileEncodingDetector(FileEncodingDetector)
 * @see Util#getFileEncoding(File)
 *
 * @author Naoki Takezoe
 */
public interface FileEncodingDetector {

	/**
	 * �t�@�C���̕����R�[�h��Ԃ��܂��B���o�ł��Ȃ��ꍇ��null��Ԃ��܂��B
	 *
	 * @param file �t�@�C��
	 * @return �����R�[�h
	 */
	public String getEncoding(File file);

}
