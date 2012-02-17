package jp.sf.amateras.stepcounter;

import java.util.Comparator;

/**
 * �t�@�C���ʃe�[�u���̊e�s���\�[�g����Comparator
 */
public class TableComparator implements Comparator<String[]> {

	public static final int	ASC = 1;
	private static String TOTAL = StepCounterPlugin.getResourceString("DiffCountView.total"); //$NON-NLS-1$

	private int index					= 0;
	private int firstNumberColumnIndex	= 0;
	private int order					= ASC;

	/**
	 * �R���X�g���N�^
	 *
	 * @param index �\�[�g����J�����̃C���f�b�N�X
	 * @param firstNumberColumnIndex �擪�̐����J�����̃C���f�b�N�X
	 */
	public TableComparator(int index, int firstNumberColumnIndex, int order) {
		this.index = index;
		this.firstNumberColumnIndex = firstNumberColumnIndex;
		this.order = order;
	}

	public int compare(String[] obj1, String[] obj2) {
		String[] data1 = obj1;
		String[] data2 = obj2;

		// ���v�s�͏�Ɉ�ԉ�
		if(data1[0].equals(TOTAL)){
			return 1;
		} else if(data2[0].equals(TOTAL)){
			return -1;
		}

		if (this.index >= this.firstNumberColumnIndex) {
			// ���l�\�[�g
			long value1 = 0;
			long value2 = 0;
			if (data1[this.index] != null && !data1[this.index].equals("")) { //$NON-NLS-1$
				value1 = Long.parseLong(data1[this.index]);
			}
			if (data2[this.index] != null && !data2[this.index].equals("")) { //$NON-NLS-1$
				value2 = Long.parseLong(data2[this.index]);
			}

			int result;
			if (value1 == value2) {
				result = 0;
			} else if (value1 > value2) {
				result = -1;
			} else {
				result = 1;
			}
			return result * this.order;
		} else {
			// ������\�[�g
			String value1 = data1[this.index];
			String value2 = data2[this.index];

			int result;
			if (value1.length() == 0 && value2.length() == 0) {
				result = 0;
			} else if (value1.length() == 0) {
				result = 1;
			} else if (value2.length() == 0) {
				result = -1;
			} else {
				result = value1.compareTo(value2);
			}
			return result * this.order;
		}
	}
}
