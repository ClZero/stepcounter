package jp.sf.amateras.stepcounter.diffcount.renderer;

import java.util.Date;

import jp.sf.amateras.stepcounter.diffcount.DiffCounterUtil;
import jp.sf.amateras.stepcounter.diffcount.object.AbstractDiffResult;
import jp.sf.amateras.stepcounter.diffcount.object.DiffFolderResult;


/**
 * �����J�E���g�̌��ʂ�HTML�`���Ń����_�����O���܂��B
 *
 * @author Naoki Takezoe
 */
public class HTMLRenderer implements Renderer {

	public byte[] render(DiffFolderResult root) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>\n");
		sb.append("<head>\n");
		sb.append("<title>�ύX�X�e�b�v��</title>\n");
		sb.append("<script type=\"text/javascript\">\n");
		sb.append("function switchDir(dirId){\n");
		sb.append("  var trList = document.getElementsByTagName('tr');\n");
		sb.append("  for(var i=0;i<trList.length;i++){\n");
		sb.append("    var className = trList[i].className;\n");
		sb.append("    if(className != null && className.indexOf(dirId) == 0){\n");
		sb.append("      if(trList[i].style.display == 'none'){\n");
		sb.append("        if(className == dirId){\n");
		sb.append("          trList[i].style.display = '';\n");
		sb.append("        }\n");
		sb.append("      } else {\n");
		sb.append("        trList[i].style.display = 'none';\n");
		sb.append("      }\n");
		sb.append("    }\n");
		sb.append("  }\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("function showAll(){\n");
		sb.append("  var trList = document.getElementsByTagName('tr');\n");
		sb.append("  for(var i=0;i<trList.length;i++){\n");
		sb.append("    if(trList[i].className != ''){\n");
		sb.append("      trList[i].style.display = '';\n");
		sb.append("    }\n");
		sb.append("  }\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("function hideAll(){\n");
		sb.append("  var trList = document.getElementsByTagName('tr');\n");
		sb.append("  for(var i=0;i<trList.length;i++){\n");
		sb.append("    if(trList[i].className != ''){\n");
		sb.append("      trList[i].style.display = 'none';\n");
		sb.append("    }\n");
		sb.append("  }\n");
		sb.append("}\n");
		sb.append("</script>\n");
		sb.append("</head>\n");
		sb.append("<body>\n");

		sb.append("���s�����F").append(DiffCounterUtil.formatDate(new Date())).append(
				"\n");
		sb.append("<input type=\"button\" onclick=\"showAll();\" value=\"���ׂēW�J\">");
		sb.append("<input type=\"button\" onclick=\"hideAll();\" value=\"���ׂĎ��[\">");

		sb.append("<table border=\"1\" width=\"100%\">\n");

		sb.append("<tr>");
		sb.append("<th width=\"80%\">���O</th><th width=\"10%\">���</th><th width=\"10%\">�ǉ��E�ύX�s��</th><th>�폜�s��</th>");
		sb.append("</tr>\n");

		for (AbstractDiffResult obj : root.getSortedChildren()) {
			renderLine(null, obj, sb, 0);
		}

		sb.append("</table>\n");

		sb.append("</body>\n");
		sb.append("</html>\n");

		return sb.toString().getBytes();
	}

	private void renderLine(DiffFolderResult parent, AbstractDiffResult obj,
			StringBuilder sb, int nest) {
		if (obj instanceof DiffFolderResult && nest == 0) {
			sb.append("<tr>");
		} else {
			sb.append("<tr class=\"").append(parent.getClassName()).append("\" style=\"display: none;\">");
		}

		sb.append("<td>");
		for (int i = 0; i < nest; i++) {
			sb.append("&nbsp;");
		}
		if (obj instanceof DiffFolderResult) {
			sb.append("<a href=\"javascript:switchDir('").append(obj.getClassName()).append("')\">");
			sb.append(obj.getName() + "/");
			sb.append("</a>");
		} else {
			sb.append(obj.getName());
		}
		sb.append("</td>");

		sb.append("<td>");
		sb.append(obj.getStatus());
		sb.append("</td>");

		sb.append("<td style=\"text-align: right;\">");
		sb.append(obj.getAddCount());
		sb.append("</td>");

		sb.append("<td style=\"text-align: right;\">");
		sb.append(obj.getDelCount());
		sb.append("</td>");

		sb.append("</tr>\n");

		if (obj instanceof DiffFolderResult) {
			DiffFolderResult folder = (DiffFolderResult)obj;
			for (AbstractDiffResult child : folder.getSortedChildren()) {
				renderLine(folder, child, sb, nest + 1);
			}
		}
	}

}
