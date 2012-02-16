package tk.stepcounter.diffcount.renderer.gui;

import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

import tk.stepcounter.diffcount.object.AbstractDiffResult;
import tk.stepcounter.diffcount.object.DiffFileResult;

public class DiffCountTreeTableModel extends DefaultTreeTableModel {

	private static final int	INDEX_NAME		= 0;

	private static final int	INDEX_STATUS	= 1;

	private static final int	INDEX_ADD		= 2;

	private static final int	INDEX_DEL		= 3;

	@Override
	public boolean isLeaf(Object node) {
		AbstractDiffResult obj = (AbstractDiffResult)((DefaultMutableTreeTableNode)node).getUserObject();
		return (obj instanceof DiffFileResult);
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case INDEX_NAME:
			return "���O";
		case INDEX_STATUS:
			return "�X�e�[�^�X";
		case INDEX_ADD:
			return "�ǉ��s��";
		case INDEX_DEL:
			return "�폜�s��";
		default:
			return null;
		}
	}

	@Override
	public Object getValueAt(Object node, int column) {
		AbstractDiffResult obj = (AbstractDiffResult)((DefaultMutableTreeTableNode)node).getUserObject();

		switch (column) {
		case INDEX_NAME:
			return obj.getName();
		case INDEX_STATUS:
			return obj.getStatus();
		case INDEX_ADD:
			return obj.getAddCount();
		case INDEX_DEL:
			return obj.getDelCount();
		default:
			return null;
		}
	}

}
