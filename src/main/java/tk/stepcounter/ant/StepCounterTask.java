/*
 * Created on 2003/06/19
 *
 * Ant�p�J�X�^���^�X�N
 * �R���p�C������Ƃ��̓N���X�p�X��ant.jar���w�肵�Ă��������B
 * �ł�Ant�ŃR���p�C������΁A���Ɏw�肵�Ȃ��Ă�OK
 */

package tk.stepcounter.ant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;

import tk.stepcounter.Main;
import tk.stepcounter.format.FormatterFactory;

/**
 * �X�e�b�v�J�E���^�i{@link tk.stepcounter.Main}�j�����s����Ant�^�X�N�ł��B
 * ����q��fileset�^�O�Ńt�@�C�����w�肵�܂��B
 *
 * @author sawat
 */
public class StepCounterTask extends Task {

	private List<FileSet> filesets = new ArrayList<FileSet>();
	private String format = null;
	private String output = null;
	private String encoding = null;
	private List<Path> showDirectoryList = new LinkedList<Path>();

	/**
	 * �X�e�b�v����������s���܂��B
	 * @see org.apache.tools.ant.Task#execute()
	 */
	public void execute() throws BuildException {

		List<File> files = new ArrayList<File>();

		// �t�@�C���Z�b�g����Y������t�@�C�������o��
		for (int i = 0; i < filesets.size(); i++) {
			FileSet fs = (FileSet) filesets.get(i);
			DirectoryScanner scanner = fs.getDirectoryScanner(getProject());
			scanner.scan();
			File baseDir = scanner.getBasedir();

			String[] includeFile = scanner.getIncludedFiles();
			for (int j = 0; j < includeFile.length; j++) {
				files.add(new File(baseDir, includeFile[j]));
			}
		}

		// ���s
		try {
			Main main = new Main();
			main.setFormatter(FormatterFactory.getFormatter(format));
			if (showDirectoryList.size() > 0) {
				List<File> dirs = new LinkedList<File>();

				for (Path s : showDirectoryList) {
					String[] path = s.list();
					for (int i = 0; i < path.length; i++) {
						dirs.add(new File(path[i]));
					}
				}
				main.setFiles((File[]) dirs.toArray(new File[dirs.size()]));
				main.setShowDirectory(true);
			} else {
				main.setFiles((File[]) files.toArray(new File[files.size()]));
			}

			if(output != null && !output.equals("")){
				main.setOutput(new FileOutputStream(new File(output)));
			}
			System.out.println(files.size() + "�t�@�C��");

			if(encoding == null || encoding.length() == 0){
				encoding = System.getProperty("file.encoding");
			}
			main.executeCount(encoding);
			if(output!=null && !output.equals("")){
				System.out.println(new File(output).getAbsolutePath() + "�ɃJ�E���g���ʂ��o�͂��܂����B");
			}
		} catch (IOException e) {
			throw new BuildException(e);
		}

	}
	/**
	 * �t�@�C���Z�b�g��ǉ����܂��B
	 * @param fileset �t�@�C���Z�b�g
	 */
	public void addFileSet(FileSet fileset) {
		this.filesets.add(fileset);
	}

	/**
	 * �t�H�[�}�b�g���w�肵�܂��B
	 * @param format �t�H�[�}�b�g
	 */
	public void setFormat(String format){
		this.format = format;
	}

	/**
	 * �o�͂���t�@�C�����w�肵�܂��B
	 * @param file �o�͂���t�@�C��
	 */
	public void setOutput(String output){
		this.output = output;
	}

	/**
	 * �\�[�X�t�@�C���̕����R�[�h���w�肵�܂��B
	 * @param encoding �����R�[�h
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * �f�B���N�g���܂��̓t�@�C���̏o�͌`����ǉ����܂��B
	 *
	 * @param showDirectory �o�͌`��
	 */
	public Path createShowDirectory() {
		Path showDirectory = new Path(getProject());
		this.showDirectoryList.add(showDirectory);
		return showDirectory;
	}
}
