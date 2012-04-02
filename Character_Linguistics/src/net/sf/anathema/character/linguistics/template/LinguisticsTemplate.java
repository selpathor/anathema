package net.sf.anathema.character.linguistics.template;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;

public class LinguisticsTemplate implements IGlobalAdditionalTemplate {

  public static final String ID = "Linguistics"; //$NON-NLS-1$

  @Override
  public boolean supportsEdition(IExaltedEdition edition) {
    return true;
  }

  @Override
  public String getId() {
    return ID;
  }
}