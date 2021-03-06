package net.sf.anathema.hero.charms.advance.creation;

import net.sf.anathema.hero.template.creation.ICreationPoints;
import net.sf.anathema.hero.advance.overview.model.AbstractSpendingModel;

public class DefaultMagicModel extends AbstractSpendingModel {

  private final MagicCreationCostCalculator magicCalculator;
  private final ICreationPoints creationPoints;

  public DefaultMagicModel(MagicCreationCostCalculator magicCalculator, ICreationPoints creationPoints) {
    super("Charms", "General");
    this.magicCalculator = magicCalculator;
    this.creationPoints = creationPoints;
  }

  @Override
  public int getSpentBonusPoints() {
    if (magicCalculator == null) {
      return 0;
    }
    return magicCalculator.getBonusPointCost();
  }

  @Override
  public Integer getValue() {
    return magicCalculator.getGeneralCharmPicksSpent();
  }

  @Override
  public int getAllotment() {
    return creationPoints.getDefaultCreationMagicCount();
  }
}