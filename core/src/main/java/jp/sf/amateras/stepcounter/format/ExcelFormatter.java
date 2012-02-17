package jp.sf.amateras.stepcounter.format;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.sf.amateras.stepcounter.CategoryStepDto;
import jp.sf.amateras.stepcounter.CountResult;
import jp.sf.amateras.stepcounter.Util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.seasar.fisshplate.template.FPTemplate;


/**
 * �J�E���g���ʂ�Excel�ŏo�͂��܂��B
 *
 * @author Naoki Takezoe
 */
public class ExcelFormatter implements ResultFormatter {

	public byte[] format(CountResult[] result) {
		try {
			InputStream in = ExcelFormatter.class.getResourceAsStream("ExcelFormatter.xls");

			long totalStep = 0;
			long totalNone = 0;
			long totalComment = 0;

			List<CategoryStepDto> categories = new ArrayList<CategoryStepDto>();
			CategoryStepDto nonCategory = new CategoryStepDto();
			nonCategory.setCategory("");
			boolean useNonCategory = false;
			for (CountResult resultDto : result) {
				CategoryStepDto categoryDto = null;
				if (resultDto.getCategory() == null || "".equals(resultDto.getCategory())) {
					categoryDto = nonCategory;
					useNonCategory = true;
				} else {
					categoryDto = getCategoryDto(categories, resultDto.getCategory());
				}
				categoryDto.setStep(categoryDto.getStep() + resultDto.getStep());
				categoryDto.setNone(categoryDto.getNone() + resultDto.getNon());
				categoryDto.setComment(categoryDto.getComment()
						+ resultDto.getComment());

				totalStep += resultDto.getStep();
				totalNone += resultDto.getNon();
				totalComment += resultDto.getComment();
			}
			if (useNonCategory) {
				categories.add(nonCategory);
			}

			Collections.sort(categories, new Comparator<CategoryStepDto>() {
				public int compare(CategoryStepDto o1, CategoryStepDto o2) {
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

			// �J�e�S���E�t�@�C���^�C�v�����w��̏ꍇ��null����󕶎��ɏC������B(fishplate�Ή�)
			for (CountResult r : result) {
				if (r.getCategory() == null) {
					r.setCategory("");
				}
				if (r.getFileType() == null) {
					r.setFileType("���Ή�");
				}
			}

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("results", result);
			data.put("categories", categories);
			data.put("totalStep", totalStep);
			data.put("totalNone", totalNone);
			data.put("totalComment", totalComment);

			return merge(in, data);

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Fisshplate���g�p����Excel�t�@�C���𐶐����܂��B
	 * �����ŗ^�����e���v���[�g�̓��̓X�g���[���͂��̃��\�b�h���ŃN���[�Y����܂��B
	 */
	private static byte[] merge(InputStream in, Map<String, Object> data)
			throws Exception {
		FPTemplate template = new FPTemplate();
		HSSFWorkbook wb;

		try {
			wb = template.process(in, data);
		} catch (Exception ex) {
			throw ex;
		} finally {
			Util.close(in);
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		wb.write(out);

		return out.toByteArray();
	}

	private static CategoryStepDto getCategoryDto(
			List<CategoryStepDto> categoryResult, String category) {
		for (CategoryStepDto categoryDto : categoryResult) {
			if (categoryDto.getCategory().equals(category)) {
				return categoryDto;
			}
		}

		CategoryStepDto categoryDto = new CategoryStepDto();
		categoryDto.setCategory(category);
		categoryResult.add(categoryDto);

		return categoryDto;
	}

}
