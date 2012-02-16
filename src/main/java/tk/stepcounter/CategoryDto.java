package tk.stepcounter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * �J�e�S���ʏW�v�p��DTO�ł��B
 *
 * @author takanori
 *
 */
public abstract class CategoryDto {

	/** �J�e�S�� */
	private String	category;

	/**
	 * �f�t�H���g�R���X�g���N�^�B
	 */
	public CategoryDto() {}

	/**
	 * �J�e�S�����擾���܂��B
	 *
	 * @return �J�e�S��
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * �J�e�S����ݒ肵�܂��B
	 *
	 * @param category �J�e�S��
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * �\�[�g���܂��B
	 *
	 * @param categoryList �J�e�S��DTO�̃��X�g
	 */
	public static void sort(List<? extends CategoryDto> categoryList) {
		Collections.sort(categoryList, new Comparator<CategoryDto>() {
			public int compare(CategoryDto o1, CategoryDto o2) {
				if (o1.getCategory().length() == 0
						&& o2.getCategory().length() == 0) {
					return 0;
				}
				if (o1.getCategory().length() == 0) {
					return 1;
				}
				if (o2.getCategory().length() == 0) {
					return -1;
				}
				return o1.getCategory().compareTo(o2.getCategory());
			}
		});
	}
}
