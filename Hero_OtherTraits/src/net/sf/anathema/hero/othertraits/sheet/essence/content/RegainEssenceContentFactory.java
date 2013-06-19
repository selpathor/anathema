package net.sf.anathema.hero.othertraits.sheet.essence.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = RegainEssenceContent.class)
public class RegainEssenceContentFactory implements ReportContentFactory<RegainEssenceContent> {

  private Resources resources;

  public RegainEssenceContentFactory(Resources resources)  {
    this.resources = resources;
  }

  @Override
  public RegainEssenceContent create(ReportSession session, IGenericCharacter character, Hero hero) {
    return new RegainEssenceContent(resources, character);
  }
}
