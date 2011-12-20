package net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.willpower.WillpowerContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class SimpleWillpowerBoxContentEncoder implements IBoxContentEncoder {

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) {
    PdfTraitEncoder traitEncoder   = PdfTraitEncoder.createMediumTraitEncoder();
    WillpowerContent content = createContent(reportContent);
    float padding = PADDING / 2f;
    float width = bounds.width - 2 * padding;
    float leftX = bounds.x + padding;
    float entryHeight = Math.max((bounds.height - padding) / 2, traitEncoder.getTraitHeight());
    float yPosition = bounds.getMaxY() - entryHeight;
    traitEncoder.encodeDotsCenteredAndUngrouped(graphics, new Position(leftX, yPosition), width, content.getWillpowerValue(), 10);
    yPosition -= entryHeight;
    traitEncoder.encodeSquaresCenteredAndUngrouped(graphics, new Position(leftX, yPosition), width, 0, 10);
  }

  public boolean hasContent(ReportContent content) {
    return createContent(content).hasContent();
  }

  public String getHeaderKey(ReportContent content) {
    return createContent(content).getHeader();
  }

  private WillpowerContent createContent(ReportContent reportContent){
    return reportContent.createSubContent(WillpowerContent.class);
  }
}
