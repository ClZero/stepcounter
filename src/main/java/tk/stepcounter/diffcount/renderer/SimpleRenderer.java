package tk.stepcounter.diffcount.renderer;

import java.util.Date;

import tk.stepcounter.diffcount.DiffCounterUtil;
import tk.stepcounter.diffcount.object.DiffFolderResult;

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
