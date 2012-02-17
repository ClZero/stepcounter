package jp.sf.amateras.stepcounter.diffcount.renderer;

import java.util.Date;

import jp.sf.amateras.stepcounter.diffcount.DiffCounterUtil;
import jp.sf.amateras.stepcounter.diffcount.object.DiffFolderResult;


/**
 * �����J�E���g�̌��ʂ��e�L�X�g�`���Ń����_�����O���܂��B
 *
 * @author Naoki Takezoe
 */
public class SimpleRenderer implements Renderer {

	public byte[] render(DiffFolderResult root) {
		StringBuilder sb = new StringBuilder();
		sb.append("���s�����F").append(DiffCounterUtil.formatDate(new Date())).append("\n");
		sb.append("--\n");
		sb.append(root.toString());

		return sb.toString().getBytes();
	}

}
