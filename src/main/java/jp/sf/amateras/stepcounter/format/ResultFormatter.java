package jp.sf.amateras.stepcounter.format;

import jp.sf.amateras.stepcounter.CountResult;

/**
 * �J�E���g���ʂ̃t�H�[�}�b�^
 */
public interface ResultFormatter {

	public byte[] format(CountResult[] result);

}
