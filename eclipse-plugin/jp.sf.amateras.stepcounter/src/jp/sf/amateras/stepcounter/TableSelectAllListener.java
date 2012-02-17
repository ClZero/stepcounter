package jp.sf.amateras.stepcounter;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;

/**
 * �e�[�u���̑S�Ă̍s��I�����邽�߂̃��X�i�B
 */
public class TableSelectAllListener extends SelectionAdapter {

	private Table table;

	public TableSelectAllListener(Table table) {
		this.table = table;
	}

	public void widgetSelected(SelectionEvent e) {
		this.table.selectAll();
	}
}
