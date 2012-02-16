package tk.stepcounter;

import java.util.List;

/**
 * �����J�E���g���̃J�e�S���ʂ̏W�v�pDTO�ł��B
 *
 * @author takanori
 *
 */
public class CategoryDiffDto extends CategoryDto {

	/** �ǉ��s�� */
	private long	addCount	= 0;

	/** �폜�s�� */
	private long	delCount	= 0;

	/**
	 * �f�t�H���g�R���X�g���N�^�B
	 */
	public CategoryDiffDto() {}

	public long getAddCount() {
		return addCount;
	}

	public void setAddCount(long addCount) {
		this.addCount = addCount;
	}

	public long getDelCount() {
		return delCount;
	}

	public void setDelCount(long delCount) {
		this.delCount = delCount;
	}

	/**
	 * �J�e�S��DTO�̃��X�g����A�w�肳�ꂽ�J�e�S����DTO���擾���܂��B
	 *
	 * @param categoryList �J�e�S��DTO�̃��X�g
	 * @param category �J�e�S��
	 * @return �J�e�S��DTO
	 */
	public static CategoryDiffDto getDto(List<CategoryDiffDto> categoryList,
			String category) {
		for (CategoryDiffDto dto : categoryList) {
			if (dto.getCategory().equals(category)) {
				return dto;
			}
		}

		CategoryDiffDto dto = new CategoryDiffDto();
		dto.setCategory(category);
		categoryList.add(dto);

		return dto;
	}
}
