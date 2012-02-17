package jp.sf.amateras.stepcounter.format;

import jp.sf.amateras.stepcounter.CountResult;

/**
 * �J�E���g���ʂ�CSV�`���Ńt�H�[�}�b�g���܂��B
 *
 * TODO ���ۉ�
 */
public class CSVFormatter implements ResultFormatter {

//	private String fileHeader    = "�t�@�C��";
//	private String stepHeader    = "���s";
//	private String nonHeader     = "��s";
//	private String commentHeader = "����";
//	private String typeHeader    = "���";
//	private String sumHeader     = "���v";

	public byte[] format(CountResult[] results){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<results.length;i++){
			CountResult result = results[i];
			// ���Ή��̌`�����t�H�[�}�b�g
			if(result.getFileType()==null){
				sb.append(result.getFileName());
				sb.append(",");
				sb.append("���Ή�");
				sb.append(",");
				sb.append(",");
				sb.append(",");
				sb.append(",");
				sb.append(",");
				sb.append("\n");
			// ����ɃJ�E���g���ꂽ���̂��t�H�[�}�b�g
			} else {
				sb.append(result.getFileName());
				sb.append(",");
				sb.append(result.getFileType());
				sb.append(",");
				sb.append(result.getCategory());
				sb.append(",");
				sb.append(result.getStep());
				sb.append(",");
				sb.append(result.getNon());
				sb.append(",");
				sb.append(result.getComment());
				sb.append(",");
				sb.append(result.getStep()+result.getNon()+result.getComment());
				sb.append("\n");
			}
		}
		return sb.toString().getBytes();
	}
}
