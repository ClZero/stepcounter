package jp.sf.amateras.stepcounter.ant;

import java.io.File;
import java.io.FileOutputStream;

import jp.sf.amateras.stepcounter.diffcount.Main;
import jp.sf.amateras.stepcounter.diffcount.renderer.RendererFactory;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;


/**
 * �����J�E���g���s�����߂�Ant�^�X�N�ł��B
 *
 * @author Naoki Takezoe
 */
public class DiffCounterTask extends Task {

	private String srcdir = null;
	private String olddir = null;
	private String format = null;
	private String output = null;
	private String encoding = null;

	/**
	 * ������������s���܂��B
	 * @see org.apache.tools.ant.Task#execute()
	 */
	public void execute() throws BuildException {
		// �K�{�p�����[�^�̃`�F�b�N
		if(RendererFactory.getRenderer(format) == null){
			throw new BuildException("format " + format + " is invalid!");
		}
		if(srcdir == null || srcdir.length() == 0){
			throw new BuildException("srcdir is required!");
		}
		if(!new File(srcdir).isDirectory()){
			throw new BuildException("srcdir '" + srcdir + "' is not directory!");
		}
		if(olddir == null || olddir.length() == 0){
			throw new BuildException("olddir is required!");
		}
		if(!new File(olddir).isDirectory()){
			throw new BuildException("olddir '" + olddir + "' is not directory!");
		}

		try {
			File basedir = getProject().getBaseDir();

			Main main = new Main();
			main.setFormat(format);
			if(output != null && output.length() != 0){
				main.setOutput(new FileOutputStream(output));
			}
			main.setEncoding(encoding);
			main.setSrcdir(new File(basedir, srcdir));
			main.setOlddir(new File(basedir, olddir));

			main.executeCount();

			if(output!=null && !output.equals("")){
				System.out.println(new File(output).getAbsolutePath() + "�ɃJ�E���g���ʂ��o�͂��܂����B");
			}
		} catch(Throwable t){
			t.printStackTrace();
		}
	}

	/**
	 * ���݂̃\�[�X�f�B���N�g�����w�肵�܂��B
	 * @param srcdir ���݂̃\�[�X�f�B���N�g��
	 */
	public void setSrcdir(String srcdir) {
		this.srcdir = srcdir;
	}

	/**
	 * �ߋ��̃\�[�X�f�B���N�g�����w�肵�܂��B
	 * @param olddir �ߋ��̃\�[�X�f�B���N�g��
	 */
	public void setOlddir(String olddir) {
		this.olddir = olddir;
	}

	/**
	 * �t�H�[�}�b�g���w�肵�܂��B
	 * @param format �t�H�[�}�b�g
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * �o�͂���t�@�C�����w�肵�܂��B
	 * @param file �o�͂���t�@�C��
	 */
	public void setOutput(String output) {
		this.output = output;
	}

	/**
	 * �\�[�X�t�@�C���̕����R�[�h���w�肵�܂��B
	 * @param encoding �����R�[�h
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
