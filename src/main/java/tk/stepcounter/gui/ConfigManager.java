package tk.stepcounter.gui;

import java.io.*;
import java.util.*;
import java.text.*;

/**
 * �ݒ�t�@�C����ǂݍ��݁A�X�V���邽�߂̔ėp�N���X�B
 *
 * @author  Naoki Takezoe
 * @version 1.0
 */
public class ConfigManager {

	private File file;
	private HashMap<String, Vector<String>> map;

	/**
	 * �R���X�g���N�^
	 *
	 * @param fileName �v���p�e�B�t�@�C���̖��O
	 */
	public ConfigManager(String fileName) {
		this(new File(fileName));
	}

	/**
	 * �R���X�g���N�^
	 *
	 * @param file �v���p�e�B�t�@�C��
	 */
	public ConfigManager(File file){
		this.file = file;
		try {
			this.read();
		} catch(Exception e){
			this.map = new HashMap<String, Vector<String>>();
		}
	}


	/**
	 * �v���p�e�B��ݒ肵�܂��B
	 * �����̃p�����[�^�����݂���ꍇ�A�㏑������܂��B
	 *
	 * @param key   �v���p�e�B�L�[
	 * @param value �v���p�e�B�̒l
	 */
	public void setProperty(String key,String value){
		String[] values = {value};
		setProperty(key,values);
	}

	/**
	 * �C���f�b�N�X�v���p�e�B��ݒ肵�܂��B
	 *
	 * @param key    �v���p�e�B�L�[
	 * @param values �v���p�e�B�̒l
	 */
	public void setProperty(String key,String[] values){
		Vector<String> vec = new Vector<String>();
		for(int i=0;i<values.length;i++){
			vec.add(values[i]);
		}
		this.map.put(key,vec);
	}


	/**
	 * �v���p�e�B���擾���܂��B
	 *
	 * @param key �v���p�e�B�̃L�[
	 * @return    �v���p�e�B�̒l
	 */
	public String getProperty(String key){
		String[] values = getPropertyValues(key);
		if(values!=null && values.length==0){ return null; }
		return values[0];
	}

	/**
	 * �C���f�b�N�X�v���p�e�B���擾���܂��B
	 *
	 * @param key �v���p�e�B�̃L�[
	 * @return    �v���p�e�B�̒l
	 */
	public String[] getPropertyValues(String key){
		if(this.map.get(key)==null){ return null; }
		Vector<String> vec = this.map.get(key);
		String[] dim = new String[vec.size()];
		for(int i=0;i<vec.size();i++){
			dim[i] = (String)vec.get(i);
		}
		return dim;
	}

	/**
	 * �v���p�e�B�t�@�C����ǂݍ��݂܂��B
	 * �C���X�^���X�̐������ɓ����I�ɌĂяo����܂��B
	 */
	private synchronized void read() throws IOException {
		this.map = new HashMap<String, Vector<String>>();
		String line;
		BufferedReader reader = new BufferedReader(new FileReader(this.file));
		while((line=reader.readLine())!=null){
			// �g����
			line.trim();
			// #�Ŏn�܂�s�̓R�����g
			if(!line.startsWith("#")){
				String[] dim = StringUtility.split(line,"=");
				if(dim.length>=2){
					String key   = dim[0].trim();
					String value = dim[1].trim();

					Vector<String> values;
					if(this.map.get(key) == null){
						values = new Vector<String>();
					} else {
						values = this.map.get(key);
					}

					values.add(value);
					map.put(key,values);
				}
			}
		}
		reader.close();
	}

	/**
	 * �v���p�e�B�t�@�C�����X�V�i���݂��Ȃ���΍쐬�j���܂��B
	 */
	public synchronized void save() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
		// �w�b�_���o��
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		writer.write("# Date: " + formatter.format(new Date()));
		writer.newLine();

		// �L�[�Z�b�g���擾
		Set<String> set = this.map.keySet();
		Iterator<String> iterator = set.iterator();
		// �����o��
		while(iterator.hasNext()){
			String key    = (String)iterator.next();
			Vector<String> values = this.map.get(key);
			for(int i=0;i<values.size();i++){
				writer.write(key + "=" + (String)values.get(i));
				writer.newLine();
			}
		}
		// �t���b�V��
		writer.flush();
		writer.close();
	}
}