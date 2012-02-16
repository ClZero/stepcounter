package jp.sf.amateras.stepcounter;

import java.util.List;

/**
 * �X�e�b�v�J�E���g���̃J�e�S���ʂ̏W�v�pDTO�ł��B
 *
 *  * @author takanori
 *
 */
public class CategoryStepDto extends CategoryDto {

	/** ���s�s�� */
	private long	step;

	/** �R�����g�s�� */
	private long	comment;

	/** ��s�� */
	private long	none;

	/**
	 * �f�t�H���g�R���X�g���N�^�B
	 */
	public CategoryStepDto() {}

	public long getStep() {
		return step;
	}

	public void setStep(long step) {
		this.step = step;
	}

	public long getComment() {
		return comment;
	}

	public void setComment(long comment) {
		this.comment = comment;
	}

	public long getNone() {
		return none;
	}

	public void setNone(long none) {
		this.none = none;
	}

	/**
	 * �J�e�S��DTO�̃��X�g����A�w�肳�ꂽ�J�e�S����DTO���擾���܂��B
	 *
	 * @param categoryList �J�e�S��DTO�̃��X�g
	 * @param category �J�e�S��
	 * @return �J�e�S��DTO
	 */
	public static CategoryStepDto getDto(List<CategoryStepDto> categoryList,
			String category) {
		for (CategoryStepDto dto : categoryList) {
			if (dto.getCategory().equals(category)) {
				return dto;
			}
		}

		CategoryStepDto dto = new CategoryStepDto();
		dto.setCategory(category);
		categoryList.add(dto);

		return dto;
	}
}
