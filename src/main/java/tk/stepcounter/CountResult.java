package tk.stepcounter;

import java.io.File;

/** �J�E���g���ʁi�P�t�@�C���j */
public class CountResult {

	private File file;
	private String fileName;
	private String fileType;
	private String category;
	private long step;
	private long non;
	private long comment;

	/** �����Ȃ��̃R���X�g���N�^ */
	public CountResult(){ }


	public CountResult(File file, String fileName,String fileType,String category,long step,long non,long comment){
		setFileName(fileName);
		setFileType(fileType);
		setStep(step);
		setNon(non);
		setComment(comment);
		setCategory(category);
		setFile(file);
	}


	/** �t�@�C������ݒ肵�܂� */
	public void setFileName(String fileName){
		this.fileName = fileName;
	}

	/** �t�@�C���̎�ނ�ݒ肵�܂� */
	public void setFileType(String fileType){
		this.fileType = fileType;
	}

	/** ���s�X�e�b�v����ݒ肵�܂� */
	public void setStep(long step){
		this.step = step;
	}

	/** ��s����ݒ肵�܂� */
	public void setNon(long non){
		this.non = non;
	}

	/** �R�����g�s����ݒ肵�܂� */
	public void setComment(long comment){
		this.comment = comment;
	}

	/** �t�@�C�������擾���܂� */
	public String getFileName(){
		return this.fileName;
	}

	/** �t�@�C���̎�ނ��擾���܂� */
	public String getFileType(){
		return this.fileType;
	}

	/** ���s�X�e�b�v�����擾���܂� */
	public long getStep(){
		return this.step;
	}

	/** ��s�����擾���܂� */
	public long getNon(){
		return this.non;
	}

	/** �R�����g�s�����擾���܂� */
	public long getComment(){
		return this.comment;
	}

	/** �J�E���g���ʂ𕶎���Ŏ擾���܂� */
	public String getResultString(){
		return toString();
	}

	/** �t�@�C���̃J�e�S�����擾���܂� */
	public String getCategory() {
		return category;
	}

	/** �t�@�C���̃J�e�S����ݒ肵�܂� */
	public void setCategory(String category) {
		this.category = category;
	}

	/** �t�@�C���I�u�W�F�N�g��ݒ肵�܂� */
	public void setFile(File file) {
		this.file = file;
	}

	/** �t�@�C���I�u�W�F�N�g��ԋp���܂� */
	public File getFile() {
		return this.file;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(getFileName()).append(" ");
		sb.append("���s:").append(Long.toString(getStep())).append(" ");
		sb.append("��s:").append(Long.toString(getNon())).append(" ");
		sb.append("�R�����g:").append(Long.toString(getComment()));
		return sb.toString();
	}
}